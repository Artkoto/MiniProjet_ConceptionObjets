package fr.istic.cartaylor.implementation;

import fr.istic.cartaylor.api.Category;

import java.util.Objects;

/**
 * Implementation for the Category type.
 *
 * @author Arnaud Akoto yao-arnaud.akoto@etudiant.univ-rennes1.fr
 * @author Anthony Amiard anthony.amiard@etudiant.univ-rennes1.fr
 */
public class CategoryImpl implements Category {
    private String name;

    /**
     * Creates a new category.
     * @param name Name of the category
     */
    public CategoryImpl (String name){
        this.name = name ;
    }

    /**
     * Returns category's name.
     *
     * @return Category's name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * A category is equal to another category if they have the same name.
     * A category cannot be equal to another type of object.
     * @param o Object to compare
     * @return <code>true</code> if both objects are categories of the same
     *         name, <code>false</code> otherwise
     */
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Category)) return false;
        Category category = (Category) o;
        return name.equals(category.getName());
    }

    /**
     * Computes category's hash code.
     * @return Hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
