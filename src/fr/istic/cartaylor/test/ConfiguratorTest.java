package fr.istic.cartaylor.test;

import fr.istic.cartaylor.api.*;

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
 *        Classe contenant les tests unitaires pour le Configurator.
 */

public class ConfiguratorTest {
    private Configurator configurator;
    private Set<Category> categories ;
    private Category engine;
    private Category transmission;
    private Category exterior;
    private Category interior;

    private PartType eg100;
    private PartType eg133;
    private PartType eg210;
    private PartType ed110;
    private PartType ed180;
    private PartType eh120;
    private Set<PartType> engines;



    @BeforeEach
    public void setup() {
        // TODO initialisation configurator
        engine = () -> "Engenie";
        transmission = () -> "Transmission";
        exterior = () -> "Exterior";
        interior = () -> "Interior";
        categories = new HashSet<>() {{
            add(engine);
            add(transmission);
            add(exterior);
            add(interior);
        }};

        eg100 = new PartType() {
            @Override
            public String getName() {
                return "EG100";
            }

            @Override
            public Category getCategory() {
                return engine;
            }
        };
        eg133 = new PartType() {
            @Override
            public String getName() {
                return "EG133";
            }

            @Override
            public Category getCategory() {
                return engine;
            }
        };
        eg210 = new PartType() {
            @Override
            public String getName() {
                return "EG210";
            }

            @Override
            public Category getCategory() {
                return engine;
            }
        };
        ed110 = new PartType() {
            @Override
            public String getName() {
                return "ED110";
            }

            @Override
            public Category getCategory() {
                return engine;
            }
        };
        ed180 = new PartType() {
            @Override
            public String getName() {
                return "ED180";
            }

            @Override
            public Category getCategory() {
                return engine;
            }
        };
        eh120 = new PartType() {
            @Override
            public String getName() {
                return "EH120";
            }

            @Override
            public Category getCategory() {
                return engine;
            }
        };
        engines = new HashSet<>(){{
            add(eg100);
            add(eg133);
            add(eg210);
            add(ed110);
            add(ed180);
            add(eh120);
        }};

        configurator = new Configurator() {
            @Override
            public Set<Category> getCategories() {
                return categories;
            }

            @Override
            public Set<PartType> getVariants(Category category) {
                return engines;
            }

            @Override
            public Configuration getConfiguration() {
                return null;
            }

            @Override
            public CompatibilityChecker getCompatibilityChecker() {
                return null;
            }
        };


    }

    @Test
    @DisplayName("getCategories")
    void testGetCategories() {
        Assertions.assertEquals(Collections.unmodifiableSet(categories) , configurator.getCategories());
        // TODO teste que le configurator retourne bien l'ensemble des catégories et que cet ensemble est non modifiable
    }

    @Test
    @DisplayName("getVariants")
    void testGetVariants() {
        Assertions.assertEquals(Collections.unmodifiableSet(engines) , configurator.getVariants(engine));
        // TODO Teste que le configurator retourne bien les bonnes variantes pour une catégorie
    }

}
