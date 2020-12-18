package fr.istic.cartaylor.implementation;

import fr.istic.cartaylor.api.Category;
import fr.istic.cartaylor.api.CompatibilityManager;
import fr.istic.cartaylor.api.PartType;

import java.util.Map;
import java.util.Set;

/**
 * Interface for objects storing data for the configurator.
 *
 * @author Arnaud Akoto yao-arnaud.akoto@etudiant.univ-rennes1.fr
 * @author Anthony Amiard anthony.amiard@etudiant.univ-rennes1.fr
 */
public interface Initializer {
    /**
     * Returns a map associating categories to their available part types.
     * @return Map associating categories to a set of their available part types
     */
    Map<Category, Set<PartType>> getCatalog();

    /**
     * Initialize given compatibility manager with incompatibilities and
     * requirements.
     * @param compatibilityManager compatibility manager to initialize
     */
    void initCompatibilityManager(CompatibilityManager compatibilityManager);
}
