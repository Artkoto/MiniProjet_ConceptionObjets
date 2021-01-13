package fr.istic.cartaylor.implementation;

import fr.istic.cartaylor.api.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Implementation for the Configurator type.
 *
 * @author Arnaud Akoto yao-arnaud.akoto@etudiant.univ-rennes1.fr
 * @author Anthony Amiard anthony.amiard@etudiant.univ-rennes1.fr
 */
public class ConfiguratorImpl  implements Configurator {
    // Map associating a category and its available variants
    private Map<Category, Set<PartType>> catalog;
    private CompatibilityManager compatibilityManager =
            new CompatibilityManagerImpl();
    private Configuration configuration = new ConfigurationImpl(this);

    /**
     * Creates a new configurator with an empty configuration and an initialized
     * CompatibilityChecker through Initializer#initCompatibilityManager.
     * @param initializer Initializer object storing available part types
     */
    public ConfiguratorImpl(Initializer initializer) {
        this.catalog = initializer.getCatalog();
        initializer.initCompatibilityManager(this.compatibilityManager);
    }


    /**
     * Returns all existing categories.
     *
     * @return Set of categories (immutable)
     */
    @Override
    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(catalog.keySet()) ;
    }

    /**
     * Returns all existing part types for a given category.
     *
     * @param category Category
     * @return Set of part types (immutable)
     */
    @Override
    public Set<PartType> getVariants(Category category) {
        return Collections.unmodifiableSet(
                catalog.getOrDefault(category, Collections.EMPTY_SET)
        );
    }

    /**
     * Returns user's configuration.
     *
     * @return User's configuration (or an empty configuration for a new user)
     */
    @Override
    public Configuration getConfiguration() {
        return this.configuration;
    }

    /**
     * Returns the compatibility checker.
     *
     * @return Compatibility checker
     */
    @Override
    public CompatibilityChecker getCompatibilityChecker() {
        return compatibilityManager;
    }
}
