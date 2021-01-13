package fr.istic.cartaylor.test;

import fr.istic.cartaylor.api.Category;
import fr.istic.cartaylor.implementation.CategoryImpl;
import fr.istic.cartaylor.implementation.PartImpl;
import fr.istic.cartaylor.implementation.PartTypeImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

/**
 * Test class for the Part type.
 *
 * @author Arnaud Akoto yao-arnaud.akoto@etudiant.univ-rennes1.fr
 * @author Anthony Amiard anthony.amiard@etudiant.univ-rennes1.fr
 */
public class PartTest {

    /**
     * Part class with three properties:
     *   - "p1" with possible values "1", "2", "3", "4",
     *   - "p2" with possible values "true", "false",
     *   - "p3" not writable,
     *   - "p4" with continuous float values.
     */
    static public class ConcretePart extends PartImpl {
        private int p1 = 1;
        /** Map associating possible values in String format to integers. */
        public final Map<String, Integer> table1 = Map.of(
                "1", 1,
                "2", 2,
                "3", 3,
                "4", 4
        );
        private boolean p2 = false;
        /** Map associating possible values in String format to booleans. */
        public final Map<String, Boolean> table2 = Map.of(
                "true", true,
                "false", false
        );
        private double p4 = 0.0;

        /**
         * Creates a new concrete part with initialized properties.
         */
        public ConcretePart() {
            super();
            addProperty(
                    "p1",
                    () -> Integer.toString(this.p1),
                    (str) -> this.p1 = this.table1.get(str),
                    table1.keySet()
            );
            addProperty(
                    "p2",
                    () -> Boolean.toString(this.p2),
                    (str) -> this.p2 = this.table2.get(str),
                    table2.keySet()
            );
            addProperty(
                    "p3",
                    () -> "V3",
                    null,
                    Collections.emptySet()
            );
            addProperty(
                    "p4",
                    () -> Double.toString(this.p4),
                    (str) -> this.p4 = Double.valueOf(str),
                    Collections.emptySet()
            );
        }
    }
    Category cat = new CategoryImpl("cat");
    PartTypeImpl concretePartType = new PartTypeImpl(ConcretePart.class, cat);
    ConcretePart part;

    @BeforeEach
    void setup() {
        this.part = (ConcretePart) concretePartType.newInstance();
    }

    /**
     * Tests Part#getName.
     */
    @Test
    @DisplayName("getName")
    void testGetName() {
        Assertions.assertEquals("ConcretePart", part.getName());
    }

    /**
     * Tests Part#getCategory.
     */
    @Test
    @DisplayName("getCategory")
    void testGetCategory() {
        Assertions.assertEquals(cat, part.getCategory());
    }

    /**
     * Tests Part#getType.
     */
    @Test
    @DisplayName("getType")
    void testGetType() {
        Assertions.assertEquals(concretePartType, part.getType());
    }

    /**
     * Tests PropertyManager#getPropertyNames
     */
    @Test
    @DisplayName("getPropertyNames")
    void testGetPropertyNames() {
        Assertions.assertIterableEquals(
                new HashSet<String>() {{
                    add("p1");
                    add("p2");
                    add("p3");
                    add("p4");
                }},
                part.getPropertyNames()
        );
    }

    /**
     * Tests PropertyManager#getProperty for an existing property.
     */
    @Test
    @DisplayName("getProperty existing property")
    void testGetExistingProperty() {
        Assertions.assertEquals("false", part.getProperty("p2").get());
    }

    /**
     * Tests PropertyManager#getProperty for a non-existing property.
     */
    @Test
    @DisplayName("getProperty non-existing property")
    void testGetNonExistingProperty() {
        Assertions.assertTrue(part.getProperty("p5").isEmpty());
    }

    /**
     * Tests PropertyManager#setProperty for an existing property with a
     * possible value.
     */
    @Test
    @DisplayName("setProperty valid")
    void testSetPropertyValid() {
        part.setProperty("p1", "2");
        Assertions.assertEquals("2", part.getProperty("p1").get());
    }

    /**
     * Tests PropertyManager#setProperty for an existing property with
     * continuous value.
     */
    @Test
    @DisplayName("setProperty continuous")
    void testSetPropertyContinuous() {
        part.setProperty("p4", "7.35");
        Assertions.assertEquals(
                7.35,
                Double.valueOf(part.getProperty("p4").get()),
                0.01
        );
    }

    /**
     * Tests PropertyManager#setProperty for an existing property with a illegal
     * value.
     */
    @Test
    @DisplayName("setProperty illegal value")
    void testSetPropertyIllegalValue() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> part.setProperty("p2", "maybe")
        );
    }

    /**
     * Tests PropertyManager#setProperty for an existing non-writable property.
     */
    @Test
    @DisplayName("setProperty non-writable")
    void testSetPropertyNonWritable() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> part.setProperty("p3", "val")
        );
    }

    /**
     * Tests PropertyManager#setProperty for a non-existing property.
     */
    @Test
    @DisplayName("setProperty non-existing")
    void testSetNonExistingProperty() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> part.setProperty("p5", "val")
        );
    }

    /**
     * Tests PropertyManager#getAvailablePropertyValues.
     */
    @Test
    @DisplayName("getAvailablePropertyValues")
    void testGetAvailablePropertyValues() {
        Assertions.assertIterableEquals(
                part.table1.keySet(),
                part.getAvailablePropertyValues("p1")
        );
    }

    /**
     * Tests PropertyManager#getAvailablePropertyValues for a non-existing
     * property.
     */
    @Test
    @DisplayName("getAvailablePropertyValues non-existing")
    void testGetAvailableNonExistingPropertyValues() {
        Assertions.assertIterableEquals(
                Collections.EMPTY_SET,
                part.getAvailablePropertyValues("p5")
        );
    }

    /**
     * Tests PropertyManager#getAvailablePropertyValues for a continuous
     * property.
     */
    @Test
    @DisplayName("getAvailablePropertyValues continuous")
    void testGetAvailableContinuousPropertyValues() {
        Assertions.assertIterableEquals(
                Collections.EMPTY_SET,
                part.getAvailablePropertyValues("p4")
        );
    }
}
