package fr.istic.cartaylor.implementation;

import fr.istic.cartaylor.api.Category;

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
}
