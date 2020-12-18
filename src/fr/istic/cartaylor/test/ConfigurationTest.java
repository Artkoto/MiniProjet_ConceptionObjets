package fr.istic.cartaylor.test;

import fr.istic.cartaylor.api.*;
import fr.istic.cartaylor.implementation.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Test class for the Configuration type.
 *
 * @author Arnaud Akoto yao-arnaud.akoto@etudiant.univ-rennes1.fr
 * @author Anthony Amiard anthony.amiard@etudiant.univ-rennes1.fr
 */
public class ConfigurationTest {
    Configuration configuration;
    Configurator configurator;
    Category categoryA = new CategoryImpl("A");
    Category categoryB = new CategoryImpl("B");
    /** Part class for type AA of category A */
    static public class AA extends PartImpl {}
    /** Part class for type AB of category A */
    static public class AB extends PartImpl {}
    /** Part class for type BA of category B */
    static public class BA extends PartImpl {}
    /** Part class for type BB of category B */
    static public class BB extends PartImpl {}
    PartTypeImpl partTypeAA = new PartTypeImpl(AA.class ,categoryA);
    PartTypeImpl partTypeAB = new PartTypeImpl(AB.class, categoryA);
    PartTypeImpl partTypeBA = new PartTypeImpl(BA.class, categoryB);
    PartTypeImpl partTypeBB = new PartTypeImpl(BB.class, categoryB);

    /**
     * Setup configuration.
     * Catalog:
     *   - category A
     *     - AA
     *     - AB
     *   - category B
     *     - BA
     *     - BB
     * Requirements:
     *   - AA needs BA
     * Incompatibilities
     *   - AB and BA
     */
    @BeforeEach
    void setup() {
        configurator = new ConfiguratorImpl(new Initializer() {
            @Override
            public Map<Category, Set<PartType>> getCatalog() {
                return new HashMap<>() {{
                    put(categoryA, new HashSet<>() {{
                        add(partTypeAA);
                        add(partTypeAB);
                    }});
                    put(categoryB, new HashSet<>() {{
                        add(partTypeBA);
                        add(partTypeBB);
                    }});
                }};
            }

            @Override
            public void
            initCompatibilityManager(CompatibilityManager compatibilityManager){
                compatibilityManager.addRequirements(
                        partTypeAA,
                        new HashSet<>() {{
                    add(partTypeBA);
                }});
                compatibilityManager.addIncompatibilities(
                        partTypeAB,
                        new HashSet<>() {{
                    add(partTypeBA);
                }});
            }
        });
        configuration = new ConfigurationImpl(configurator);
    }

    /**
     * Tests Configuration#isComplete on an empty configuration.
     */
    @Test
    @DisplayName("isComplete on an empty configuration")
    void testEmptyIsComplete() {
        Assertions.assertFalse(configuration.isComplete());
    }

    /**
     * Tests Configuration#isValid on an empty configuration.
     */
    @Test
    @DisplayName("isValid on an empty configuration")
    void testEmptyIsValid() {
        Assertions.assertFalse(configuration.isValid());
    }

    /**
     * Tests Configuration#selectPart on empty selection.
     */
    @Test
    @DisplayName("Initial selectPart")
    void testSelectPartInitial() {
        Part aa = partTypeAA.newInstance();
        configuration.selectPart(aa);
        Assertions.assertEquals(
                aa,
                configuration.getSelectionForCategory(categoryA).get()
        );
    }

    /**
     * Tests Configuration#selectPart after changing selection.
     */
    @Test
    @DisplayName("selectPart after change")
    void testSelectPartChange() {
        Part aa = partTypeAA.newInstance();
        Part ab = partTypeAB.newInstance();
        configuration.selectPart(aa);
        configuration.selectPart(ab);
        Assertions.assertEquals(
                ab,
                configuration.getSelectionForCategory(categoryA).get()
        );
    }

    /**
     * Tests Configuration#unselectPartType.
     */
    @Test
    @DisplayName("unselectPartType")
    void testUnselectPartType() {
        Part aa = partTypeAA.newInstance();
        configuration.selectPart(aa);
        configuration.unselectPartType(categoryA);
        Assertions.assertTrue(
                configuration.getSelectionForCategory(categoryA).isEmpty()
        );
    }

    /**
     * Tests Configuration#isComplete on an incomplete configuration.
     */
    @Test
    @DisplayName("isComplete non-complete")
    void testIncompleteIsComplete() {
        configuration.selectPart(partTypeAA.newInstance());
        Assertions.assertFalse(configuration.isComplete());
    }

    /**
     * Tests Configuration#isValid on an incomplete configuration.
     */
    @Test
    @DisplayName("isValid non-complete")
    void testIncompleteIsValid() {
        configuration.selectPart(partTypeAA.newInstance());
        Assertions.assertFalse(configuration.isValid());
    }

    /**
     * Tests Configuration#isComplete on a complete non-valid configuration
     * because of an incompatibility.
     */
    @Test
    @DisplayName("isComplete complete incompatible")
    void testIncompatibleIsComplete() {
        configuration.selectPart(partTypeAB.newInstance());
        configuration.selectPart(partTypeBA.newInstance());
        Assertions.assertTrue(configuration.isComplete());
    }

    /**
     * Tests Configuration#isValid on a complete non-valid configuration because
     * of an incompatibility.
     */
    @Test
    @DisplayName("isValid complete incompatible")
    void testIncompatibleIsValid() {
        configuration.selectPart(partTypeAB.newInstance());
        configuration.selectPart(partTypeBA.newInstance());
        Assertions.assertFalse(configuration.isValid());
    }

    /**
     * Tests Configuration#isComplete on a complete non-valid configuration
     * because of a non-satisfied requirement.
     */
    @Test
    @DisplayName("isComplete complete requirement")
    void testRequirementIsComplete() {
        configuration.selectPart(partTypeAA.newInstance());
        configuration.selectPart(partTypeBB.newInstance());
        Assertions.assertTrue(configuration.isComplete());
    }

    /**
     * Tests Configuration#isValid on a complete non-valid configuration because
     * of a non-satisfied requirement.
     */
    @Test
    @DisplayName("isValid complete requirement")
    void testRequirementIsValid() {
        configuration.selectPart(partTypeAA.newInstance());
        configuration.selectPart(partTypeBB.newInstance());
        Assertions.assertFalse(configuration.isValid());
    }

    /**
     * Tests Configuration#isComplete on a complete and valid configuration.
     */
    @Test
    @DisplayName("isComplete complete valid")
    void testCompleteValidIsComplete() {
        configuration.selectPart(partTypeAA.newInstance());
        configuration.selectPart(partTypeBA.newInstance());
        Assertions.assertTrue(configuration.isComplete());
    }

    /**
     * Tests Configuration#isValid on a complete and valid configuration.
     */
    @Test
    @DisplayName("isValid complete valid")
    void testCompleteValidIsValid() {
        configuration.selectPart(partTypeAA.newInstance());
        configuration.selectPart(partTypeBA.newInstance());
        Assertions.assertTrue(configuration.isValid());
    }

    /**
     * Tests Configuration#clear.
     */
    @Test
    @DisplayName("clear")
    void testClear() {
        configuration.selectPart(partTypeAA.newInstance());
        configuration.selectPart(partTypeBA.newInstance());
        configuration.clear();
        Assertions.assertEquals(0, configuration.getSelectedParts().size());
    }
}
