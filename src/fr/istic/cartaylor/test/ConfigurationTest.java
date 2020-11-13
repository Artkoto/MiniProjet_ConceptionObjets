package fr.istic.cartaylor.test;

import fr.istic.cartaylor.api.*;

import fr.istic.cartaylor.implementation.ConfiguratorImpl;
import fr.istic.cartaylor.implementation.Initiations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


/**
 * @author Arnaud Akoto <yao-arnaud.akoto@etudiant.univ-rennes1.fr>
 * @author Anthony Amiard <anthony.amiard@etudiant.univ-rennes1.fr>
 *        Classe contenant les tests unitaires pour la Configuration.
 */

public class ConfigurationTest {
    private Configurator configurator ;

    private Category engine;
    private Category transmission;
    private Category exterior;
    private Category interior;

    private PartType eg100;
    private PartType eg210;

    private PartType ta5;
    private PartType tsf7;

    private PartType xc;
    private PartType xs;

    private PartType is;

    @BeforeEach
    private void setup() {
        configurator = new ConfiguratorImpl();
        Initiations init = new Initiations();

        engine = init.accessToCategory("Engine");
        transmission = init.accessToCategory("Transmission");
        exterior = init.accessToCategory("Exterior");
        interior = init.accessToCategory("Interior");

        eg100 = init.accessToPartType("EG100");
        eg210 = init.accessToPartType("EG210");

        ta5 = init.accessToPartType("TA5");
        tsf7 = init.accessToPartType("TSF7");

        xc = init.accessToPartType("XC");
        xs = init.accessToPartType("XS");

        is = init.accessToPartType("IS");
    }

    @Test
    @DisplayName("Empty configuration")
    void testEmptyConf() {
        Configuration c = configurator.getConfiguration();
        Assertions.assertFalse(c.isComplete());
        Assertions.assertFalse(c.isValid());
    }

    @Test
    @DisplayName("Incomplete configuration")
    void testIncompleteConf() {
        Configuration c = configurator.getConfiguration();
        c.selectPart(eg100);
        Assertions.assertFalse(c.isComplete());
        Assertions.assertFalse(c.isValid());
    }

    @Test
    @DisplayName("Invalid configuration")
    void testInvalidConf() {
        Configuration c = configurator.getConfiguration();
        c.selectPart(eg100);
        c.selectPart(ta5);
        c.selectPart(xc);
        c.selectPart(is);
        Assertions.assertTrue(c.isComplete());
        Assertions.assertFalse(c.isValid());
    }

    @Test
    @DisplayName("Valid configuration")
    void testValidConf() {
        Configuration c = configurator.getConfiguration();
        c.selectPart(eg210);
        c.selectPart(tsf7);
        c.selectPart(xs);
        c.selectPart(is);
        Assertions.assertTrue(c.isComplete());
        Assertions.assertTrue(c.isValid());
    }

    @Test
    @DisplayName("Empty getSelectionForCategory")
    void testEmptyGetSelectionForCategory() {
        Configuration c = configurator.getConfiguration();
        Assertions.assertNull(c.getSelectionForCategory(transmission));
    }

    @Test
    @DisplayName("Non-empty getSelectionForCategory")
    void testGetSelectionForCategory() {
        Configuration c = configurator.getConfiguration();
        c.selectPart(ta5);
        Assertions.assertEquals(ta5, c.getSelectionForCategory(transmission));
    }

    @Test
    @DisplayName("selectPart")
    void testSelectPart() {
        Configuration c = configurator.getConfiguration();
        c.selectPart(xs);
        Assertions.assertEquals(xs, c.getSelectionForCategory(exterior));
    }

    @Test
    @DisplayName("Part replacement")
    void testPartReplacement() {
        Configuration c = configurator.getConfiguration();
        c.selectPart(eg100);
        c.selectPart(eg210);
        Assertions.assertEquals(eg210, c.getSelectionForCategory(engine));
    }

    @Test
    @DisplayName("getSelectedParts")
    void testGetSelectedParts() {
        Set<PartType> s = new HashSet<PartType>() {{
            add(eg100);
            add(tsf7);
            add(xc);
            add(is);
        }};
        Configuration c = configurator.getConfiguration();

        for(PartType p: s) {
            c.selectPart(p);
        }

        Assertions.assertIterableEquals(s, c.getSelectedParts());
    }

    @Test
    @DisplayName("unselectPartType")
    void testUnselectPartType() {
        Configuration c = configurator.getConfiguration();

        c.selectPart(xc);
        c.unselectPartType(exterior);
        Assertions.assertNull(c.getSelectionForCategory(exterior));
    }

    @Test
    @DisplayName("clear")
    void testClear() {
        Configuration c = configurator.getConfiguration();

        c.selectPart(eg100);
        c.selectPart(ta5);
        c.selectPart(xs);
        c.selectPart(is);

        c.clear();

        Assertions.assertIterableEquals(Collections.EMPTY_SET, c.getSelectedParts());
    }
}
