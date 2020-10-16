package fr.istic.cartaylor.api;

import java.util.Set;

/**
 * A public type that checks compatibilities and requirements for part types.
 * @author plouzeau
 */
public interface CompatibilityChecker {

    /**
     * Returns a set of part types that are incompatible with given part type.
     * @param   reference   Part type to check
     * @return  Set of incompatible part types (immutable)
     */
    Set<PartType> getIncompatibilities(PartType reference);

    /**
     * Returns a set of part type required to use given part type.
     * @param   reference   Par type to check
     * @return  Set of required part types (immutable)
     */
    Set<PartType> getRequirements(PartType reference);

}
