package fr.istic.cartaylor.test;

import fr.istic.cartaylor.api.Configurator;
import fr.istic.cartaylor.api.Category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;

import java.util.Set;

/**
 * @author Arnaud akoto et Anthony Amiard
 *        Classe contenant les tests unitaires pour le Configurator.
 */

public class ConfiguratorTest {
    private Configurator configurator;
    private Set<Category> categories;
    private Category engine;
    private Category transmission;
    private Category exterior;
    private Category interior;

    @BeforeEach
    public void setup() {
        // TODO initialisation configurator
        engine = configurator.getCategories().stream().filter((c) -> c.getName().equals("Engine")).findAny().get();
        transmission =configurator.getCategories().stream().filter((c) -> c.getName().equals("Transmission")).findAny().get();
        exterior = configurator.getCategories().stream().filter((c) -> c.getName().equals("Exterior")).findAny().get();
        interior = configurator.getCategories().stream().filter((c) -> c.getName().equals("Interior")).findAny().get();
        categories.add(engine);
        categories.add(transmission);
        categories.add(exterior);
        categories.add(interior);
    }

    @Test
    @DisplayName("getCategories")
    void testGetCategories() {
        Assertions.assertEquals(categories, configurator.getCategories());

        // TODO teste que le configurator retourne bien l'ensemble des catégories et que cet ensemble est non modifiable
    }

    @Test
    @DisplayName("getVariants")
    void testGetVariants() {
        // TODO Teste que le configurator retourne bien les bonnes variantes pour une catégorie
    }

}
