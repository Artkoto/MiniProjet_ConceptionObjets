package fr.istic.cartaylor.implementation;

import fr.istic.cartaylor.api.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Arnaud Akoto <yao-arnaud.akoto@etudiant.univ-rennes1.fr>
 * @author Anthony Amiard <anthony.amiard@etudiant.univ-rennes1.fr>
 *        Classe Implementant l'interface Configurator.
 */
public class ConfiguratorImpl  implements Configurator {
    // Map associating a category and its available variants
    private Map<Category, Set<PartType>> catalog = new HashMap<>();
    private CompatibilityManager compatibilityManager =
            new CompatibilityManagerImpl();
    private Configuration configuration;


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
