package fr.istic.cartaylor.implementation;

import fr.istic.cartaylor.api.*;

import java.util.*;

/**
 * @author Arnaud Akoto <yao-arnaud.akoto@etudiant.univ-rennes1.fr>
 * @author Anthony Amiard <anthony.amiard@etudiant.univ-rennes1.fr>
 *
 * Implementation for the Configuration type.
 */
public class ConfigurationImpl implements Configuration {
    private Map<Category, Part> selections = new HashMap<>();
    private Configurator configurator;

    /**
     * Creates a new empty configuration.
     * @param configurator Configurator object storing categories and
     *                     compatibility checker
     */
    public ConfigurationImpl(Configurator configurator) {
        this.configurator = configurator;
    }

    /**
     * Tests if the configuration is complete and valid.
     *
     * A valid configuration is a complete configuration where all requirements
     * are satisfied and there are no incompatibilities.
     *
     * @return <code>true</code> if the configuration is valid,
     *         <code>false</code> otherwise.
     */
    @Override
    public boolean isValid() {
        if (!this.isComplete()) return  false;
        Set<Part> selection = getSelectedParts() ;
        for (Part part: selection){
            Set<PartType> requirements =
                    configurator.getCompatibilityChecker().getRequirements(
                            part.getType()
                    );
            Set<PartType> incompatibilities =
                    configurator.getCompatibilityChecker().getIncompatibilities(
                            part.getType()
                    );
            for (PartType req: requirements){
                if (!selection.stream().anyMatch(
                        (p) -> p.getType().equals(req)
                ))
                    return false ;
            }
            for (PartType inc: incompatibilities){
                if (selection.stream().anyMatch(
                        (p) -> p.getType().equals(inc)
                ))
                    return false ;
            }
        }
        return true;
    }

    /**
     * Tests if the configuration is complete, i.e. all categories have been
     * configured.
     * @return  <code>true</code> if the configuration is complete,
     *          <code>false</code> otherwise.
     */
    @Override
    public boolean isComplete() {
        for (Category category : configurator.getCategories()){
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
        for (Category category : configurator.getCategories()){
            if (selections.containsKey(category))
                selectParts.add(selections.get(category));
        }
        return selectParts;
    }

    /**
     * Select a part.
     *
     * If a part was already selected for <code>chosenPart</code>'s category, it
     * is replaced by the new part.
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
     * @return On optional containing the selected part for given category, or
     *         an empty optional if no part was selected.
     */
    @Override
    public Optional<Part> getSelectionForCategory(Category category) {
        if (category != null && configurator.getCategories().contains(category))
        {
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
        if (categoryToClear != null
                && configurator.getCategories().contains(categoryToClear)){
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
