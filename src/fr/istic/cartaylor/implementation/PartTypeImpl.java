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
 *
 * Implementation for the PartType type.
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

    /**
     * Tests if both objects are equal part types.
     * Part types are equal when the Part implementation class they reference is
     * the same.
     * A part type cannot be equal to another type of object.
     * @param o Object to compare
     * @return <code>true</code> if both part types are equal,
     *         <code>false</code> otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartTypeImpl partType = (PartTypeImpl) o;
        return classRef.equals(partType.classRef);
    }

    /**
     * Computes part type's hash code.
     * @return Hash code
     */
    @Override
    public int hashCode() {
        return classRef.hashCode();
    }
}
