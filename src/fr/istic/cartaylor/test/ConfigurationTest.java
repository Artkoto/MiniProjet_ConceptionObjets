package fr.istic.cartaylor.test;

import fr.istic.cartaylor.api.*;

import fr.istic.cartaylor.implementation.CarTaylorExceptions;
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
        configurator = new Configurator() {
            private Configuration c = new Configuration() {
                @Override
                public boolean isValid() {
                    return false;
                }

                @Override
                public boolean isComplete() {
                    return false;
                }

                @Override
                public Set<PartType> getSelectedParts() {
                    return Collections.emptySet();
                }

                @Override
                public void selectPart(PartType chosenPart) {

                }

                @Override
                public PartType getSelectionForCategory(Category category) {
                    return null;
                }

                @Override
                public void unselectPartType(Category categoryToClear) {

                }

                @Override
                public void clear() {

                }
            };
            @Override
            public Set<Category> getCategories() {
                return Collections.emptySet();
            }

            @Override
            public Set<PartType> getVariants(Category category) {
                return Collections.emptySet();
            }

            @Override
            public Configuration getConfiguration() {
                return c;
            }

            @Override
            public CompatibilityChecker getCompatibilityChecker() {
                return null;
            }
        };

        engine = new Category() {
            @Override
            public String getName() {
                return "Engine";
            }
        };
        transmission = new Category() {
            @Override
            public String getName() {
                return "Transmission";
            }
        };
        exterior = new Category() {
            @Override
            public String getName() {
                return "Exterior";
            }
        };
        interior = new Category() {
            @Override
            public String getName() {
                return "Interior";
            }
        };

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

        ta5 = new PartType() {
            @Override
            public String getName() {
                return "TA5";
            }

            @Override
            public Category getCategory() {
                return transmission;
            }
        };
        tsf7 = new PartType() {
            @Override
            public String getName() {
                return "TSF7";
            }

            @Override
            public Category getCategory() {
                return transmission;
            }
        };

        xc = new PartType() {
            @Override
            public String getName() {
                return "XC";
            }

            @Override
            public Category getCategory() {
                return exterior;
            }
        };
        xs = new PartType() {
            @Override
            public String getName() {
                return "XS";
            }

            @Override
            public Category getCategory() {
                return exterior;
            }
        };

        is = new PartType() {
            @Override
            public String getName() {
                return "IS";
            }

            @Override
            public Category getCategory() {
                return interior;
            }
        };
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
    void testIncompleteConf() throws CarTaylorExceptions {
        Configuration c = configurator.getConfiguration();
        c.selectPart(eg100);
        Assertions.assertFalse(c.isComplete());
        Assertions.assertFalse(c.isValid());
    }

    @Test
    @DisplayName("Invalid configuration")
    void testInvalidConf() throws CarTaylorExceptions {
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
    void testValidConf() throws CarTaylorExceptions {
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
    void testGetSelectionForCategory() throws CarTaylorExceptions {
        Configuration c = configurator.getConfiguration();
        c.selectPart(ta5);
        Assertions.assertEquals(ta5, c.getSelectionForCategory(transmission));
    }

    @Test
    @DisplayName("selectPart")
    void testSelectPart() throws CarTaylorExceptions {
        Configuration c = configurator.getConfiguration();
        c.selectPart(xs);
        Assertions.assertEquals(xs, c.getSelectionForCategory(exterior));
    }

    @Test
    @DisplayName("Part replacement")
    void testPartReplacement() throws CarTaylorExceptions {
        Configuration c = configurator.getConfiguration();
        c.selectPart(eg100);
        c.selectPart(eg210);
        Assertions.assertEquals(eg210, c.getSelectionForCategory(engine));
    }

    @Test
    @DisplayName("getSelectedParts")
    void testGetSelectedParts() throws CarTaylorExceptions {
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
    void testUnselectPartType() throws CarTaylorExceptions {
        Configuration c = configurator.getConfiguration();

        c.selectPart(xc);
        c.unselectPartType(exterior);
        Assertions.assertNull(c.getSelectionForCategory(exterior));
    }

    @Test
    @DisplayName("clear")
    void testClear() throws CarTaylorExceptions {
        Configuration c = configurator.getConfiguration();

        c.selectPart(eg100);
        c.selectPart(ta5);
        c.selectPart(xs);
        c.selectPart(is);

        c.clear();

        Assertions.assertIterableEquals(Collections.EMPTY_SET, c.getSelectedParts());
    }
}
