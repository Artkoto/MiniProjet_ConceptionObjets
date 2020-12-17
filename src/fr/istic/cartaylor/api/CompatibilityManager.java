package fr.istic.cartaylor.api;

import java.util.Set;

/**
 * A public interface allowing to system administrator to modify compatibilities
 * and requirements between part types.
 * @author  plouzeau
 */
public interface CompatibilityManager extends CompatibilityChecker {

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
     */
    void addIncompatibilities(PartType reference, Set<PartType> target);

    /**
     * Remove an incompatibility between given part types.
     * @param   reference   First part type
     * @param   target      Second part type
     */
    void removeIncompatibility(PartType reference, PartType target);

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
     * @param   reference   Part type to use
     * @param   target      All required part types
     * @throws IllegalArgumentException Preconditions are not respected
     */
    void addRequirements(PartType reference, Set<PartType> target);

    /**
     * Remove a required part type for a given part type.
     * This means that <code>reference</code> does not longer needs
     * <code>target</code> to be used.
     * It does not specify if <code>target</code> needs <code>reference</code>
     * to be used, or not.
     * @param   reference   Part type to use
     * @param   target      No longer required part type
     */
    void removeRequirement(PartType reference, PartType target);

}
