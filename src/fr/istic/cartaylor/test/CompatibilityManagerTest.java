package fr.istic.cartaylor.test;

import fr.istic.cartaylor.api.Category;
import fr.istic.cartaylor.api.CompatibilityManager;
import fr.istic.cartaylor.api.Part;
import fr.istic.cartaylor.api.PartType;
import fr.istic.cartaylor.implementation.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

/**
 * Test class for the CompatibilityManager type.
 *
 * @author Arnaud Akoto yao-arnaud.akoto@etudiant.univ-rennes1.fr
 * @author Anthony Amiard anthony.amiard@etudiant.univ-rennes1.fr
 */
public class CompatibilityManagerTest {
    private CompatibilityManager compatibilityManager;
    private Category categoryA = new CategoryImpl("categoryA");
    private Category categoryB = new CategoryImpl("categoryB");
    private class PartTypeAA extends PartImpl {}
    private class PartTypeAB extends PartImpl {}
    private class PartTypeBA extends PartImpl {}
    private class PartTypeBB extends PartImpl {}
    private PartType partTypeAA = new PartTypeImpl(PartTypeAA.class, categoryA);
    private PartType partTypeAB = new PartTypeImpl(PartTypeAB.class, categoryA);
    private PartType partTypeBA = new PartTypeImpl(PartTypeBA.class, categoryB);
    private PartType partTypeBB = new PartTypeImpl(PartTypeBB.class, categoryB);

    /**
     * Creates an empty compatibility checker to test.
     */
    @BeforeEach
    void setup() {
        compatibilityManager = new CompatibilityManagerImpl();
    }

    /**
     * Tests CompatibilityChecker#addIncompatibilities on two different part
     * types of different categories.
     */
    @Test
    @DisplayName("Normal addIncompatibilities")
    void testAddIncompatibilities() {
        compatibilityManager.addIncompatibilities(partTypeAA, new HashSet<>() {{
            add(partTypeBB);
        }});
        Assertions.assertTrue(
                compatibilityManager.getIncompatibilities(
                        partTypeAA
                ).contains(partTypeBB)
        );
    }

    /**
     * Tests CompatibilityChecker#addIncompatibilities on two different part
     * types of same category.
     */
    @Test
    @DisplayName("addIncompatibilities for same category")
    void testAddIncompatibilitiesSameCat() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    compatibilityManager.addIncompatibilities(
                            partTypeAA,
                            new HashSet<>() {{ add(partTypeAB); }}
                    );
                }
        );
    }

    /**
     * Tests CompatibilityChecker#addIncompatibilities on a part type and
     * itself.
     */
    @Test
    @DisplayName("addIncompatibilities for same object")
    void testAddIncompatibilitiesItself() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    compatibilityManager.addIncompatibilities(
                            partTypeAA,
                            new HashSet<>() {{ add(partTypeAA); }}
                    );
                }
        );
    }

    /**
     * Tests CompatibilityChecker#addIncompatibilities on a part type and a
     * required part type by the first one.
     */
    @Test
    @DisplayName("addIncompatibilities for required part type")
    void testAddIncompatibilitiesRequiredPartType() {
        compatibilityManager.addRequirements(partTypeAA, new HashSet<>() {{
            add(partTypeBA);
        }});
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    compatibilityManager.addIncompatibilities(
                            partTypeAA,
                            new HashSet<>() {{ add(partTypeBA); }}
                    );
                }
        );
    }

    /**
     * Tests CompatibilityManager#addIncompatibilities reflexivity.
     */
    @Test
    @DisplayName("addIncompatibilities reflexivity")
    void testAddIncompatibilitiesReflexivity() {
        compatibilityManager.addIncompatibilities(partTypeAA, new HashSet<>() {{
            add(partTypeBB);
        }});
        Assertions.assertTrue(
                compatibilityManager.getIncompatibilities(
                        partTypeBB
                ).contains(partTypeAA)
        );
    }

    /**
     * Tests CompatibilityManager#removeIncompatibility
     */
    @Test
    @DisplayName("removeIncompatibility")
    void testRemoveIncompatibility() {
        compatibilityManager.addIncompatibilities(partTypeAA, new HashSet<>() {{
            add(partTypeBB);
        }});
        compatibilityManager.removeIncompatibility(partTypeAA, partTypeBB);
        Assertions.assertFalse(
                compatibilityManager.getIncompatibilities(
                        partTypeAA
                ).contains(partTypeBB)
        );
    }

    /**
     * Tests CompatibilityChecker#addRequirements on two different part types of
     * different categories.
     */
    @Test
    @DisplayName("Normal addRequirements")
    void testAddRequirements() {
        compatibilityManager.addRequirements(partTypeAA, new HashSet<>() {{
            add(partTypeBA);
        }});
        Assertions.assertTrue(
                compatibilityManager.getRequirements(
                        partTypeAA
                ).contains(partTypeBA)
        );
    }

    /**
     * Tests CompatibilityChecker#addRequirements on two different part types of
     * same category.
     */
    @Test
    @DisplayName("addRequirements for same category")
    void testAddRequirementsSameCat() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    compatibilityManager.addRequirements(
                            partTypeAA,
                            new HashSet<>() {{ add(partTypeAB); }}
                    );
                }
        );
    }

    /**
     * Tests CompatibilityChecker#addRequirements on a part type and itself.
     */
    @Test
    @DisplayName("addRequirements for same object")
    void testAddRequirementsItself() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    compatibilityManager.addIncompatibilities(
                            partTypeAA,
                            new HashSet<>() {{ add(partTypeAA); }}
                    );
                }
        );
    }

    /**
     * Tests CompatibilityChecker#addRequirements on a part type and an
     * incompatible part type by the first one.
     */
    @Test
    @DisplayName("addRequirements for required part type")
    void testAddRequirementsRequiredPartType() {
        compatibilityManager.addIncompatibilities(partTypeAA, new HashSet<>() {{
            add(partTypeBB);
        }});
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    compatibilityManager.addRequirements(
                            partTypeAA,
                            new HashSet<>() {{ add(partTypeBB); }}
                    );
                }
        );
    }

    /**
     * Tests CompatibilityManager#removeRequirement
     */
    @Test
    @DisplayName("removeRequirement")
    void testRemoveRequirement() {
        compatibilityManager.addRequirements(partTypeAA, new HashSet<>() {{
            add(partTypeBB);
        }});
        compatibilityManager.removeRequirement(partTypeAA, partTypeBB);
        Assertions.assertFalse(
                compatibilityManager.getRequirements(
                        partTypeAA
                ).contains(partTypeBB)
        );
    }

    /**
     * Tests CompatibilityManager#addRequirements non-reflexivity.
     */
    @Test
    @DisplayName("addRequirements non-reflexivity")
    void testAddRequirementsNonReflexivity() {
        compatibilityManager.addRequirements(partTypeAA, new HashSet<>() {{
            add(partTypeBA);
        }});
        Assertions.assertFalse(
                compatibilityManager.getIncompatibilities(
                        partTypeBA
                ).contains(partTypeAA)
        );
    }
}
