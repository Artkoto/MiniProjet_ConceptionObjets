package fr.istic.cartaylor.implementation;

import fr.istic.cartaylor.api.*;

import java.util.Set;

public class ConfiguratorImpl  implements Configurator {
    /**
     * Returns all existing categories.
     *
     * @return Set of categories (immutable)
     */
    @Override
    public Set<Category> getCategories() {
        return null;
    }

    /**
     * Returns all existing part types for a given category.
     *
     * @param category Category
     * @return Set of part types (immutable)
     */
    @Override
    public Set<PartType> getVariants(Category category) {
        return null;
    }

    /**
     * Returns user's configuration.
     *
     * @return User's configuration (or an empty configuration for a new user)
     */
    @Override
    public Configuration getConfiguration() {
        return null;
    }

    /**
     * Returns the compatibility checker.
     *
     * @return Compatibility checker
     */
    @Override
    public CompatibilityChecker getCompatibilityChecker() {
        return null;
    }
}
