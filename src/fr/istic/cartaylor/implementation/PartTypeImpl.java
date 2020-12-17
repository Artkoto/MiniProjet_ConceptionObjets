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
    private Class<? extends PartImpl> classRef;
    private Category category;

    /**
     * Creates a new Part type for given Part class.
     * @param classRef Class of part objects. Must be <code>public</code> and
     *                 <code>static</code> for inner classes.
     * @param category Category for this part type
     */
    public PartTypeImpl(Class<? extends PartImpl> classRef, Category category) {
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
        return this.classRef.getSimpleName();
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

    /**
     * Returns a new part of this type.
     * @return New instance of this part type
     */
    public PartImpl newInstance() {
        Constructor<? extends PartImpl> constructor;
        try {
            constructor = classRef.getConstructor();
            PartImpl i = constructor.newInstance();
            i.setType(this);
            return i;
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
        return getName().equals(partType.getName()) &&
                category.equals(partType.category);
    }

    @Override
    public int hashCode() {
        return classRef.hashCode();
    }
}
