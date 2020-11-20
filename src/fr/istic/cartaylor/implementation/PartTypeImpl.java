package fr.istic.cartaylor.implementation;

import fr.istic.cartaylor.api.Category;
import fr.istic.cartaylor.api.PartType;

import java.lang.reflect.Constructor;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Arnaud Akoto <yao-arnaud.akoto@etudiant.univ-rennes1.fr>
 * @author Anthony Amiard <anthony.amiard@etudiant.univ-rennes1.fr>
 *        Classe Implementant l'interface  PartType.
 */
public class PartTypeImpl implements PartType {
    private String name;
    private Class<? extends PartImpl> classRef;
    private Category category;

    public PartTypeImpl(String name, Class<? extends PartImpl> classRef, Category category) {
        this.name = name;
        this.classRef = classRef;
        this.category = category;
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
    public Category getCategory() {
        return this.category;
    }

    public PartImpl newInstance() {
        Constructor<? extends PartImpl> constructor;
        try {
            constructor = classRef.getConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "constructor call failed", e);
            System.exit(-1);
        }
        return null;
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
