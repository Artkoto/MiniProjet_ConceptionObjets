package fr.istic.cartaylor.api;

/**
 * Variant of an category.
 * A part type is an abstract representation of a part, when the user selects it
 * they obtain a Part, which is an instance of the part type they can configure.
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
