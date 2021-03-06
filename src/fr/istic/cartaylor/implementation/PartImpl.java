package fr.istic.cartaylor.implementation;

import fr.istic.cartaylor.api.*;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Implementation for the Part type.
 *
 * This abstract class must be extended for each part type.
 * To set available properties, creates a constructor method with no parameters
 * and calls the PartImpl#addProperty method for each property.
 * If extensions are declared inside a class, it must be <code>static</code> and
 * <code>public</code>.
 *
 * After a subclass is instanced, the PartImpl#setType must be called with the
 * PartType corresponding.
 *
 * @author Arnaud Akoto yao-arnaud.akoto@etudiant.univ-rennes1.fr
 * @author Anthony Amiard anthony.amiard@etudiant.univ-rennes1.fr
 */
public abstract class PartImpl implements Part {

    private PartType type;

    // A property is a set of possible values, and two methods to get and change
    // its value.
    static private class Property {
        public final Supplier<String> getter;
        public final Consumer<String> setter;
        public final Set<String> possibleValues;

        Property(Supplier<String> getter,
                 Consumer<String> setter,
                 Set<String> possibleValues) {
            this.getter = getter;
            this.setter = setter;
            this.possibleValues = possibleValues;

        }
    }

    // Associates a property name to a property
    private Map<String, Property> properties = new HashMap<>();

    /**
     * Set the type of the part.
     * Must be done just after creation.
     * @param type Type of the part
     */
    public void setType(PartType type) {
        if(this.type == null) this.type = type;
    }

    /**
     * Add a property to the part.
     *
     * This method is aimed to be called in subclasses' constructors to define
     * available properties.
     *
     * @param name Name of the property
     * @param getter Method called by PartImpl#getProperty to get the current
     *               value.
     * @param setter Method called by PartImpl#setProperty with the user's new
     *               value. If this property has a set of possible values, will
     *               be called only if provided value is a possible value.
     *               <code>null</code> for a read-only property.
     * @param possibleValues Set of possible values. Empty set for no value
     *                       checking, or continuous values.
     */
    protected void addProperty(String name,
                               Supplier<String> getter,
                               Consumer<String> setter,
                               Set<String> possibleValues) {
        properties.put(name, new Property(getter, setter, possibleValues));
    }

    /**
     * Returns the name of the part type.
     * @return Name of the part type
     */
    @Override
    public String getName() {
        return type.getName();
    }

    /**
     * Returns the category of the part.
     * @return Category of the part
     */
    @Override
    public Category getCategory() {
        return type.getCategory();
    }

    /**
     * Returns the type of the part.
     * @return Type of the part
     */
    @Override
    public PartType getType() {
        return type;
    }

    /**
     * Returns an immutable set of the property names supported by the property
     * manager.
     *
     * @return Set of property names (immutable)
     */
    @Override
    public Set<String> getPropertyNames() {
        return Collections.unmodifiableSet(properties.keySet());
    }

    /**
     * Returns the optional value of a property.
     * If the object does not support that property then an empty optional is returned.
     * @param propertyName the property to read
     * @return On optional storing the value set for property, or an empty
     *         optional if the property is not supported
     */
    @Override
    public Optional<String> getProperty(String propertyName) {
        Objects.requireNonNull(propertyName);

        if (properties.containsKey(propertyName)) {
            return Optional.of(properties.get(propertyName).getter.get());
        }
        return Optional.empty();
    }

    /**
     * Sets the value of a given property.
     * If there is not such property, or if it not writable, or if the value is invalid
     * then an IllegalArgumentException is thrown.
     * @param propertyName Name of the property
     * @param propertyValue Value of the property
     * @throws IllegalArgumentException  (see above)
     */
    @Override
    public void setProperty(String propertyName, String propertyValue) {
        Objects.requireNonNull(propertyName);
        Objects.requireNonNull(propertyValue);

        if ((properties.containsKey(propertyName))
                && (properties.get(propertyName).setter != null)
                && (properties.get(propertyName).possibleValues.isEmpty()
                   || properties.get(
                          propertyName
                      ).possibleValues.contains(propertyValue))) {
            properties.get(propertyName).setter.accept(propertyValue);
        } else {
            throw new IllegalArgumentException(
                    "bad property name or value: " + propertyName
            );
        }
    }

    /**
     * Returns the immutable set of discrete string values for a given property.
     * For properties that have a non explicit set of possible values (eg double
     * converted to strings), or for a non existing property name, returns an
     * empty set.
     *
     * @param propertyName a non-null string reference
     * @return an immutable set (see above)
     */
    @Override
    public Set<String> getAvailablePropertyValues(String propertyName) {
        if (properties.containsKey(propertyName)) {
            return Collections.unmodifiableSet(
                    properties.get(propertyName).possibleValues
            );
        }
        return Collections.emptySet();
    }
}