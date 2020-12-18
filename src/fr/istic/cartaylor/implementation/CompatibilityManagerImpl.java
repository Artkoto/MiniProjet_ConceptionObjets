package fr.istic.cartaylor.implementation;

import fr.istic.cartaylor.api.CompatibilityManager;
import fr.istic.cartaylor.api.PartType;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Arnaud Akoto <yao-arnaud.akoto@etudiant.univ-rennes1.fr>
 * @author Anthony Amiard <anthony.amiard@etudiant.univ-rennes1.fr>
 *
 * Implementation for the CompatibilityManager type.
 */
public class CompatibilityManagerImpl implements CompatibilityManager {

    /*
     * Pair to store incompatibilities between two part types.
     */
    private static class Incompatibility {
        public final PartType part1;
        public final PartType part2;

        public Incompatibility(PartType part1, PartType part2) {
            this.part1 = part1;
            this.part2 = part2;
        }

        // The order between part1 and part2 is not indicative
        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Incompatibility)) return false;
            Incompatibility inc2 = (Incompatibility) obj;

            return this.part1 == inc2.part1 && this.part2 == inc2.part2
                    || this.part1 == inc2.part2 && this.part2 == inc2.part1;
        }

        @Override
        public int hashCode() {
            return part1.hashCode() + part2.hashCode();
        }
    }

    /*
     * Object to store a requirement between a reference and a required part
     */
    private static class Requirement {
        public final PartType reference;
        public final PartType required;

        // reference needs requirement
        private Requirement(PartType reference, PartType required) {
            this.reference = reference;
            this.required = required;
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Requirement)) return false;
            Requirement req2 = (Requirement) obj;
            return this.reference == req2.reference
                    && this.required == req2.required;
        }

        @Override
        public int hashCode() {
            int f = 45;
            int h = 12;
            h += f * reference.hashCode();
            h += f * required.hashCode();
            return h;
        }
    }

    private Set<Incompatibility> incompatibilities = new HashSet<>();
    private Set<Requirement> requirements = new HashSet<>();

    /**
     * Add a set of incompatible part types to given part type.
     * The incompatibility is set in both direction for each reference-target
     * association.
     *
     * If compatibilities already exist for given PartType, new compatibilities
     * are added to existing ones.
     *
     * An incompatibility cannot be a requirement.
     * A incompatible part type cannot be from the same category as reference.
     *
     * @param   reference   A part type
     * @param   target      All incompatible part types with
     *                      <code>reference</code>
     * @throws IllegalArgumentException Preconditions are not respected
     */
    @Override
    public void addIncompatibilities(PartType reference, Set<PartType> target) {
        if(reference == null || target == null) return;
        Set<PartType> reqs = this.getRequirements(reference);
        for(PartType p: target) {
            if (p.getCategory().equals(reference.getCategory()))
                throw new IllegalArgumentException(
                        "attempted to add part type " + p.getName() +
                                " of same category as " + reference.getName() +
                                " (" + reference.getCategory().getName() + ")"
                );
            if (reqs.contains(p))
                throw new IllegalArgumentException(
                        "attempted to add required part " + p.getName() +
                                " for part " + reference.getName() +
                                " to incompatibilities"
                );
        }
        for(PartType p: target)
            incompatibilities.add(new Incompatibility(reference, p));
    }

    /**
     * Remove an incompatibility between given part types.
     *
     * @param reference First part type
     * @param target    Second part type
     */
    @Override
    public void removeIncompatibility(PartType reference, PartType target) {
        if(reference == null || target == null) return;
        incompatibilities.remove(new Incompatibility(reference, target));
    }

    /**
     * Add a set of required part types to use with given part type.
     * This means that <code>reference</code> needs all part types into
     * <code>target</code> to be used.
     * It does not specify if each part type into <code>target</code> needs
     * <code>reference</code> to be used, or not.
     *
     * A requirement cannot be an incompatibility.
     * A required part type cannot be from the same category as reference.
     *
     * @param reference Part type to use
     * @param target    All required part types
     * @throws IllegalArgumentException Preconditions are not respected
     */
    @Override
    public void addRequirements(PartType reference, Set<PartType> target) {
        if(reference == null || target == null) return;
        Set<PartType> incs = this.getIncompatibilities(reference);
        for(PartType p: target) {
            if(p.getCategory().equals(reference.getCategory()))
                throw new IllegalArgumentException(
                        "attempted to add part type " + p.getName() +
                                " of same category as " + reference.getName() +
                                " (" + reference.getCategory().getName() + ")"
                );
            if(incs.contains(p))
                throw new IllegalArgumentException(
                        "attempted to add incompatible part type " + p.getName()
                                + " with " + reference.getName() +
                                " to its requirements"
                );
        }
        for(PartType p: target)
            requirements.add(new Requirement(reference, p));
    }

    /**
     * Remove a required part type for a given part type.
     * This means that <code>reference</code> does not longer needs
     * <code>target</code> to be used.
     * It does not specify if <code>target</code> needs <code>reference</code>
     * to be used, or not.
     *
     * @param reference Part type to use
     * @param target    No longer required part type
     */
    @Override
    public void removeRequirement(PartType reference, PartType target) {
        if(reference == null || target == null) return;
        requirements.remove(new Requirement(reference, target));
    }

    /**
     * Returns a set of part types that are incompatible with given part type.
     *
     * @param reference Part type to check
     * @return Set of incompatible part types (immutable)
     */
    @Override
    public Set<PartType> getIncompatibilities(PartType reference) {
        PartType[] array = incompatibilities.stream().filter(
            (pair) -> pair.part1.equals(reference)
                        || pair.part2.equals((reference))
        ).map(
            (pair) -> pair.part1.equals(reference) ? pair.part2 : pair.part1
        ).toArray(PartType[]::new);
        Set<PartType> set = new HashSet<>();
        Collections.addAll(set, array);
        return Collections.unmodifiableSet(set);
    }

    /**
     * Returns a set of part type required to use given part type.
     *
     * @param reference Par type to check
     * @return Set of required part types (immutable)
     */
    @Override
    public Set<PartType> getRequirements(PartType reference) {
        PartType[] array = requirements.stream().filter(
                (req) -> req.reference.equals(reference)
        ).map((req) -> req.required).toArray(PartType[]::new);
        Set<PartType> set = new HashSet<>();
        Collections.addAll(set, array);
        return Collections.unmodifiableSet(set);
    }
}
