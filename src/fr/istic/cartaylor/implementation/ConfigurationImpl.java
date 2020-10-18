package fr.istic.cartaylor.implementation;

import fr.istic.cartaylor.api.Category;
import fr.istic.cartaylor.api.Configuration;
import fr.istic.cartaylor.api.PartType;

import java.util.Set;

public class ConfigurationImpl implements Configuration {
    /**
     * Tests if the configuration is complete and valid.
     *
     * @return <code>true</code> if the configuration is valid, <code>false</code> otherwise.
     */
    @Override
    public boolean isValid() {
        return false;
    }

    /**
     * Tests if the configuration is complete, i.e. all categories have been configurated.
     *
     * @return <code>true</code> if the configuration is complete, <code>false</code> otherwise.
     */
    @Override
    public boolean isComplete() {
        return false;
    }

    /**
     * Returns selected parts.
     *
     * @return Set of selected parts (immutable)
     */
    @Override
    public Set<PartType> getSelectedParts() {
        return null;
    }

    /**
     * Select a part.
     *
     * @param chosenPart Part to select
     */
    @Override
    public void selectPart(PartType chosenPart) {

    }

    /**
     * Returns selected part of given category.
     *
     * @param category Category
     * @return Selected part for given category, or <code>null</code> if no part was selected
     */
    @Override
    public PartType getSelectionForCategory(Category category) {
        return null;
    }

    /**
     * Clear a category, i.e. remove selected part for this category.
     * If no parts are selected, nothing happens.
     *
     * @param categoryToClear Category to clear
     */
    @Override
    public void unselectPartType(Category categoryToClear) {

    }

    /**
     * Clear all the configuration, i.e. remove all selected parts.
     */
    @Override
    public void clear() {

    }
}
