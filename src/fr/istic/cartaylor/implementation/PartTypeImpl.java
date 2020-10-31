package fr.istic.cartaylor.implementation;

import fr.istic.cartaylor.api.PartType;

import java.util.Objects;

/**
 * @author Arnaud Akoto <yao-arnaud.akoto@etudiant.univ-rennes1.fr>
 * @author Anthony Amiard <anthony.amiard@etudiant.univ-rennes1.fr>
 *        Classe Implementant l'interface  PartType.
 */
public class PartTypeImpl implements PartType {
    private String name;
    private CategoryImpl category;

    public PartTypeImpl (String name1 , CategoryImpl category1){
        this.name = name1;
        this.category = category1 ;
    }
    /**
     * Returns part type's name.
     *
     * @return Part type's name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns part type's category
     *
     * @return Part type's category
     */
    @Override
    public CategoryImpl getCategory() {
        return this.category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartTypeImpl partType = (PartTypeImpl) o;
        return name.equals(partType.name) &&
                category.equals(partType.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
