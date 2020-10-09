package fr.istic.nplouzeau.cartaylor.api;

import java.util.Set;

/**
 * Public interface of the system, used to access to all sub-interfaces.
 * @author plouzeau
 */
public interface Configurator {

    /**
     * Returns all existing categories.
     * @return  Set of categories (immutable)
     */
    Set<Category> getCategories();

    /**
     * Returns all existing part types for a given category.
     * @param   category    Category
     * @return  Set of part types (immutable)
     */
    Set<PartType> getVariants(Category category);

    /**
     * Returns user's configuration.
     * @return  User's configuration (or an empty configuration for a new user)
     */
    Configuration getConfiguration();

    /**
     * Returns the compatibility checker.
     * @return  Compatibility checker
     */
    CompatibilityChecker getCompatibilityChecker();

}
