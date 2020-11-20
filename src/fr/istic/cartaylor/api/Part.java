package fr.istic.cartaylor.api;

/**
 * Instance of PartType.
 * @author plouzeau
 */
public interface Part extends PropertyManager {
    /**
     * Returns the name of the part type.
     * @return Name of the part type
     */
    default String getName() {
        return this.getClass().getTypeName();
    }

    /**
     * Returns the category of the part.
     * @return Category of the part
     */
    Category getCategory();

    /**
     * Returns the type of the part.
     * @return Type of the part
     */
    PartType getType();
}
