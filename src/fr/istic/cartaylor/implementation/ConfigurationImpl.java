package fr.istic.cartaylor.implementation;

import fr.istic.cartaylor.api.*;

import java.util.*;

/**
 * @author Arnaud Akoto <yao-arnaud.akoto@etudiant.univ-rennes1.fr>
 * @author Anthony Amiard <anthony.amiard@etudiant.univ-rennes1.fr>
 *        Classe Implementant l'interface Configuration.
 */
public class ConfigurationImpl implements Configuration {
    private Set<Category> categories = new HashSet<Category>(){{}};
    private HashMap<Category, Part> selections ;
    private CompatibilityManagerImpl compatibilityManager;


    public ConfigurationImpl(Initiations initiations, CompatibilityManagerImpl compatibilityManager){
        this.categories.addAll(initiations.getCategories());
        this.selections = new HashMap< Category , Part>(){{}};
        this.compatibilityManager = compatibilityManager;
    }

    /**
     * Tests if the configuration is complete and valid.
     *
     * @return <code>true</code> if the configuration is valid, <code>false</code> otherwise.
     */
    @Override
    public boolean isValid() {
        if (!this.isComplete()) return  false;
        Set<Part> selection = getSelectedParts() ;
        for (Part part: selection){
            Set<PartType> requirements = compatibilityManager.getRequirements(part.getType());
            Set<PartType> incompatibilities  = compatibilityManager.getIncompatibilities(part.getType());
            for (PartType req: requirements){
                if (!selection.contains(req))
                    return false ;
            }
            for (PartType inc: incompatibilities){
                if (selection.contains(inc))
                    return false ;
            }
        }
        return true;
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
    public Set<Part> getSelectedParts() {
        Set<Part> selectParts = new HashSet<Part>(){{}};
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
    public void selectPart(Part chosenPart) {
        Category category = chosenPart.getCategory();
        selections.put(category,chosenPart);
    }

    /**
     * Returns selected part of given category.
     *
     * @param category Category
     * @return Selected part for given category, or <code>null</code> if no part was selected
     */
    @Override
    public Optional<Part> getSelectionForCategory(Category category) {
        if (category != null && categories.contains(category)){
            if (selections.containsKey(category))
                return Optional.of(selections.get(category));
        }
        return Optional.empty();
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
