package fr.istic.cartaylor.implementation;

import fr.istic.cartaylor.api.Category;
import fr.istic.cartaylor.api.Configuration;
import fr.istic.cartaylor.api.PartType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Arnaud Akoto <yao-arnaud.akoto@etudiant.univ-rennes1.fr>
 * @author Anthony Amiard <anthony.amiard@etudiant.univ-rennes1.fr>
 *        Classe Implementant l'interface Configuration.
 */
public class ConfigurationImpl implements Configuration {
    private Set<Category> categories = new HashSet<Category>(){{}};
    private HashMap<Category, PartType > selections ;

    public ConfigurationImpl(Initiations initiations1){
        categories.addAll(initiations1.getCategories());
        selections = new HashMap< Category , PartType>(){{}};
    }
    /**
     * Tests if the configuration is complete and valid.
     *
     * @return <code>true</code> if the configuration is valid, <code>false</code> otherwise.
     */
    @Override
    public boolean isValid() {
        if (!this.isComplete()) return  false;
        //TODO à finir
        return false;
    }

    /**
     * Tests if the configuration is complete, i.e. all categories have been configurated.
     *
     * @return <code>true</code> if the configuration is complete, <code>false</code> otherwise.
     */
    @Override
    public boolean isComplete() {
        for (Category category : categories){
            if (!selections.containsKey(category)) return false ;
        }
        return true;
    }

    /**
     * Returns selected parts.
     *
     * @return Set of selected parts (immutable)
     */
    @Override
    public Set<PartType> getSelectedParts() {
        Set<PartType> selectParts = new HashSet<PartType>(){{}};
        for (Category category : categories){
            if (selections.containsKey(category))
                selectParts.add(selections.get(category));
        }
        return selectParts;
    }

    /**
     * Select a part.
     *
     * @param chosenPart Part to select
     */
    @Override
    public void selectPart(PartType chosenPart) {
        Category category = chosenPart.getCategory();
        if (category != null && categories.contains(category)){
            selections.put(category,chosenPart);
        }

    }

    /**
     * Returns selected part of given category.
     *
     * @param category Category
     * @return Selected part for given category, or <code>null</code> if no part was selected
     */
    @Override
    public PartType getSelectionForCategory(Category category) {
        if (category != null && categories.contains(category)){
            if (selections.containsKey(category))
                return selections.get(category);
        }
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
        if (categoryToClear != null && categories.contains(categoryToClear)){
            if (selections.containsKey(categoryToClear))
                selections.remove(categoryToClear);
        }
    }

    /**
     * Clear all the configuration, i.e. remove all selected parts.
     */
    @Override
    public void clear() {
        selections.clear();
    }
}
