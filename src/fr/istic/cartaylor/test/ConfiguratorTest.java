package fr.istic.cartaylor.test;

import fr.istic.cartaylor.api.Configurator;
import fr.istic.cartaylor.api.Category;

import fr.istic.cartaylor.api.PartType;
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
        engine = configurator.getCategories().stream().filter((c) -> c.getName().equals("Engine")).findAny().get();
        transmission =configurator.getCategories().stream().filter((c) -> c.getName().equals("Transmission")).findAny().get();
        exterior = configurator.getCategories().stream().filter((c) -> c.getName().equals("Exterior")).findAny().get();
        interior = configurator.getCategories().stream().filter((c) -> c.getName().equals("Interior")).findAny().get();
        categories = new HashSet<>() {{
            add(engine);
            add(transmission);
            add(exterior);
            add(interior);
        }};

        eg100 = configurator.getVariants(engine).stream().filter((c) -> c.getName().equals("EG100")).findAny().get();
        eg133 = configurator.getVariants(engine).stream().filter((c) -> c.getName().equals("EG133")).findAny().get();
        eg210 = configurator.getVariants(engine).stream().filter((c) -> c.getName().equals("EG210")).findAny().get();
        ed110 = configurator.getVariants(engine).stream().filter((c) -> c.getName().equals("ED110")).findAny().get();
        ed180 = configurator.getVariants(engine).stream().filter((c) -> c.getName().equals("ED180")).findAny().get();
        eh120 = configurator.getVariants(engine).stream().filter((c) -> c.getName().equals("EH120")).findAny().get();
        engines = new HashSet<>(){{
            add(eg100);
            add(eg133);
            add(eg210);
            add(ed110);
            add(ed180);
            add(eh120);
        }};


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
