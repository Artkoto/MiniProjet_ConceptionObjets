package fr.istic.cartaylor.implementation;

import fr.istic.cartaylor.api.Category;

import java.util.Objects;

/**
 * @author Arnaud Akoto <yao-arnaud.akoto@etudiant.univ-rennes1.fr>
 * @author Anthony Amiard <anthony.amiard@etudiant.univ-rennes1.fr>
 *        Classe Implementant l'interface Category.
 */
public class CategoryImpl implements Category {
    private String name;

    public CategoryImpl (String name1){
        this.name = name1 ;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryImpl category = (CategoryImpl) o;
        return name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
