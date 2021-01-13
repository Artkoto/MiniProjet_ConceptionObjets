package fr.istic.cartaylor.api;

import java.util.Optional;
import java.util.Set;

/**
 * A public type containing a user configuration.
 * @author plouzeau
 */
public interface Configuration {

    /**
     * Tests if the configuration is complete and valid.
     *
     * A valid configuration is a complete configuration where all requirements
     * are satisfied and there are no incompatibilities.
     *
     * @return <code>true</code> if the configuration is valid,
     *         <code>false</code> otherwise.
     */
    boolean isValid();

    /**
     * Tests if the configuration is complete, i.e. all categories have been
     * configured.
     * @return  <code>true</code> if the configuration is complete,
     *          <code>false</code> otherwise.
     */
    boolean isComplete();

    /**
     * Returns selected parts.
     * @return  Set of selected parts (immutable)
     */
    Set<Part> getSelectedParts();

    /**
     * Select a part.
     *
     * If a part was already selected for <code>chosenPart</code>'s category, it
     * is replaced by the new part.
     *
     * @param chosenPart Part to select
     */
    void selectPart(Part chosenPart) ;

    /**
     * Returns selected part of given category.
     * @param   category    Category
     * @return  On optional containing the selected part for given category, or
     *          an empty optional if no part was selected.
     */
    Optional<Part> getSelectionForCategory(Category category);

    /**
     * Clear a category, i.e. remove selected part for this category.
     * If no parts are selected, nothing happens.
     * @param   categoryToClear Category to clear
     */
    void unselectPartType(Category categoryToClear);

    /**
     * Clear all the configuration, i.e. remove all selected parts.
     */
    void clear();

}
