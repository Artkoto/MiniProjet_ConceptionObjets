package fr.istic.cartaylor.implementation;

import fr.istic.cartaylor.api.CompatibilityChecker;
import fr.istic.cartaylor.api.PartType;

import java.util.Set;

public class CompatibilityCheckerImpl implements CompatibilityChecker {
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
