package fr.istic.nplouzeau.cartaylor.api;


import java.util.Set;

/**
 * A public type containing a user configuration.
 * @author plouzeau
 */
public interface Configuration {

    /**
     * Tests if the configuration is complete and valid.
     * @return  <code>true</code> if the configuration is valid, <code>false</code> otherwise.
     */
    boolean isValid();

    /**
     * Tests if the configuration is complete, i.e. all categories have been configurated.
     * @return  <code>true</code> if the configuration is complete, <code>false</code> otherwise.
     */
    boolean isComplete();

    /**
     * Returns selected parts.
     * @return  Set of selected parts (immutable)
     */
    Set<PartType> getSelectedParts();

    /**
     * Select a part.
     * @param   chosenPart  Part to select
     */
    void selectPart(PartType chosenPart);

    /**
     * Returns selected part of given category.
     * @param   category    Category
     * @return  Selected part for given category
     */
    PartType getSelectionForCategory(Category category);

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
