package fr.istic.cartaylor.api;

import java.util.Optional;
import java.util.Set;

/**
 * A public type to manages properties for a part.
 * @author plouzeau
 */
public interface PropertyManager {
    /**
     * Returns an immutable set of the property names supported by the property
     * manager.
     *
     * @return Immutable set of property names
     */
    public Set<String> getPropertyNames();

    /**
     * Returns an immutable set of discrete string values for a given property.
     * For properties that have a non explicit set of possible values (eg double
     * converted to strings), or for a non existing property name, returns an
     * empty set.
     *
     * @param propertyName a non-null string reference
     * @return an immutable set (see above)
     */
    public Set<String> getAvailablePropertyValues(String propertyName);


    /**
     * Returns the optional value of a property.
     * If the object does not support that property then an empty optional is
     * returned.
     * @param propertyName the property to read
     * @return On optional storing the value set for property, or an empty
     *         optional if the property is not supported
     */
    public Optional<String> getProperty(String propertyName);

    /**
     * Sets the value of a given property.
     * If there is not such property, or if it not writable, or if the value is
     * invalid then an IllegalArgumentException is thrown.
     * @param propertyName Name of the property
     * @param propertyValue Value of the property
     * @throws IllegalArgumentException  (see above)
     */
    void setProperty(String propertyName, String propertyValue);

}

