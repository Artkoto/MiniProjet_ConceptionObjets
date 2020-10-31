package fr.istic.cartaylor.test;

import fr.istic.cartaylor.api.Category;
import fr.istic.cartaylor.api.CompatibilityChecker;
import fr.istic.cartaylor.api.PartType;
import fr.istic.cartaylor.implementation.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Arnaud Akoto <yao-arnaud.akoto@etudiant.univ-rennes1.fr>
 * @author Anthony Amiard <anthony.amiard@etudiant.univ-rennes1.fr>
 *        Classe contenant les tests unitaires pour les implementations.
 */
public class ImplementationsTest {
    private ConfiguratorImpl configurator = new ConfiguratorImpl() ;
    private ConfigurationImpl configuration;
    private CompatibilityChecker compatibilityManager ;
    private Initiations initiations ;

    @BeforeEach
    public void setup(){
        initiations  = new Initiations();
        configuration = configurator.getConfiguration() ;
        compatibilityManager = configurator.getCompatibilityChecker();
    }
    @Test
    @DisplayName("getCategories")
    void testGetCategories() {
        Assertions.assertEquals(Collections.unmodifiableSet(initiations.getCategories()) , configurator.getCategories());
        // TODO teste que le configurator retourne bien l'ensemble des catégories et que cet ensemble est non modifiable
    }

    @Test
    @DisplayName("getVariants")
    void testGetVariants() {
        Assertions.assertEquals(Collections.unmodifiableSet(initiations.getEngPartTypes()) ,
                configurator.getVariants(initiations.accessToCategory("Engine")));

        Assertions.assertEquals(Collections.unmodifiableSet(initiations.getTransPartTypes()) ,
                configurator.getVariants(initiations.accessToCategory("Transmission")));

        Assertions.assertNotEquals(Collections.unmodifiableSet(initiations.getEngPartTypes()) ,
                configurator.getVariants(initiations.accessToCategory("Transmission")));
        // TODO Teste que le configurator retourne bien les bonnes variantes pour une catégorie
    }

    @Test
    @DisplayName("Empty configuration")
    void testEmptyConf() {
        Assertions.assertFalse(configuration.isValid());
        Assertions.assertFalse(configuration.isValid());
    }

    @Test
    @DisplayName("Incomplete configuration")
    void testIncompleteConf() {
        configuration.selectPart(initiations.accessToPartType("EG100"));
        Assertions.assertFalse(configuration.isComplete());
        Assertions.assertFalse(configuration.isValid());
    }

    @Test
    @DisplayName("Invalid configuration")
    void testInvalidConf() {
        configuration.selectPart(initiations.accessToPartType("EG100"));
        configuration.selectPart(initiations.accessToPartType("TA5"));
        configuration.selectPart(initiations.accessToPartType("XC"));
        configuration.selectPart(initiations.accessToPartType("IS"));
        Assertions.assertTrue(configuration.isComplete());
        Assertions.assertFalse(configuration.isValid());
    }

    @Test
    @DisplayName("Valid configuration")
    void testValidConf() {
        configuration.selectPart(initiations.accessToPartType("EG210"));
        configuration.selectPart(initiations.accessToPartType("TSF7"));
        configuration.selectPart(initiations.accessToPartType("XS"));
        configuration.selectPart(initiations.accessToPartType("IS"));
        Assertions.assertTrue(configuration.isComplete());
        Assertions.assertTrue(configuration.isValid());
    }

    @Test
    @DisplayName("Empty getSelectionForCategory")
    void testEmptyGetSelectionForCategory() {
        Assertions.assertNull(configuration.getSelectionForCategory(initiations.accessToCategory("Transmission")));
    }

    @Test
    @DisplayName("Non-empty getSelectionForCategory")
    void testGetSelectionForCategory() {
        configuration.selectPart(initiations.accessToPartType("TA5"));
        Assertions.assertEquals(initiations.accessToPartType("TA5"),
                configuration.getSelectionForCategory(initiations.accessToCategory("Transmission")));
    }

    @Test
    @DisplayName("selectPart")
    void testSelectPart() {
        configuration.selectPart(initiations.accessToPartType("XS"));
        Assertions.assertEquals(initiations.accessToPartType("XS"),
                configuration.getSelectionForCategory(initiations.accessToCategory("Exterior")));
    }

    @Test
    @DisplayName("Part replacement")
    void testPartReplacement() {
        PartType eg100 = initiations.accessToPartType("EG100");
        PartType eg210 = initiations.accessToPartType("EG210");
        Category engine = initiations.accessToCategory("Engine");
        configuration.selectPart(eg100);
        configuration.selectPart(eg210);
        Assertions.assertEquals(eg210, configuration.getSelectionForCategory(engine));
    }

    @Test
    @DisplayName("getSelectedParts")
    void testGetSelectedParts() {
        Set<PartType> s = new HashSet<PartType>() {{
            add(initiations.accessToPartType("EG100"));
            add(initiations.accessToPartType("TSF7"));
            add(initiations.accessToPartType("XC"));
            add(initiations.accessToPartType("IS"));
        }};

        for(PartType p: s) {
            configuration.selectPart(p);
        }

        Assertions.assertIterableEquals(s, configuration.getSelectedParts());
    }

    @Test
    @DisplayName("unselectPartType")
    void testUnselectPartType() throws CarTaylorExceptions {

        configuration.selectPart(initiations.accessToPartType("XC"));
        configuration.unselectPartType(initiations.accessToCategory("Exterior"));
        Assertions.assertNull(configuration.getSelectionForCategory(initiations.accessToCategory("Exterior")));
    }

    @Test
    @DisplayName("clear")
    void testClear() {

        configuration.selectPart(initiations.accessToPartType("EG100"));
        configuration.selectPart(initiations.accessToPartType("TA5"));
        configuration.selectPart(initiations.accessToPartType("XS"));
        configuration.selectPart(initiations.accessToPartType("IS"));

        configuration.clear();

        Assertions.assertIterableEquals(Collections.EMPTY_SET, configuration.getSelectedParts());
    }

}
