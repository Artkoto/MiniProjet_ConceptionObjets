package fr.istic.cartaylor.implementation;

import fr.istic.cartaylor.api.*;

import java.util.Set;

/**
 * @author Arnaud Akoto <yao-arnaud.akoto@etudiant.univ-rennes1.fr>
 * @author Anthony Amiard <anthony.amiard@etudiant.univ-rennes1.fr>
 *        Classe Implementant l'interface Configurator.
 */
public class ConfiguratorImpl  implements Configurator {
    private  ConfigurationImpl configuration;
    private CompatibilityManagerImpl compatibilityManager;
    private  Set<CategoryImpl> categories ;
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
    public ConfigurationImpl getConfiguration() {
        return this.configuration;
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
