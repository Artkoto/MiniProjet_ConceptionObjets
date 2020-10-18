package fr.istic.cartaylor.implementation;

import fr.istic.cartaylor.api.CompatibilityManager;
import fr.istic.cartaylor.api.PartType;

import java.util.Set;

public class CompatibilityManagerImpl implements CompatibilityManager {
    /**
     * Add a set of incompatible part types to given part type.
     * The incompatibility is set in both direction for each reference-target association.
     *
     * @param reference A part type
     * @param target    All incompatible part types with <code>reference</code>
     */
    @Override
    public void addIncompatibilities(PartType reference, Set<PartType> target) {

    }

    /**
     * Remove an incompatibility between given part types.
     *
     * @param reference First part type
     * @param target    Second part type
     */
    @Override
    public void removeIncompatibility(PartType reference, PartType target) {

    }

    /**
     * Add a set of required part types to use with given part type.
     * This means that <code>reference</code> needs all part types into <code>target</code> to be used.
     * It does not specify if each part type into <code>target</code> needs <code>reference</code> to be used, or not.
     *
     * @param reference Part type to use
     * @param target    All required part types
     */
    @Override
    public void addRequirements(PartType reference, Set<PartType> target) {

    }

    /**
     * Remove a required part type for a given part type.
     * This means that <code>reference</code> does not longer needs <code>target</code> to be used.
     * It does not specify if <code>target</code> needs <code>reference</code> to be used, or not.
     *
     * @param reference Part type to use
     * @param target    No longer required part type
     */
    @Override
    public void removeRequirement(PartType reference, PartType target) {

    }

    /**
     * Returns a set of part types that are incompatible with given part type.
     *
     * @param reference Part type to check
     * @return Set of incompatible part types (immutable)
     */
    @Override
    public Set<PartType> getIncompatibilities(PartType reference) {
        return null;
    }

    /**
     * Returns a set of part type required to use given part type.
     *
     * @param reference Par type to check
     * @return Set of required part types (immutable)
     */
    @Override
    public Set<PartType> getRequirements(PartType reference) {
        return null;
    }
}
