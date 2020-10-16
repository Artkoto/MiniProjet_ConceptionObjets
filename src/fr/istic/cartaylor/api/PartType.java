package fr.istic.cartaylor.api;

/**
 * Variant of an category.
 * @author plouzeau
 */
public interface PartType {

    /**
     * Returns part type's name.
     * @return  Part type's name
     */
    String getName();

    /**
     * Returns part type's category
     * @return  Part type's category
     */
    Category getCategory();
}
