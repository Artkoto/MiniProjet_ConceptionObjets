package fr.istic.cartaylor.test;

import fr.istic.cartaylor.api.Configurator;
import fr.istic.cartaylor.api.Category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Set;

public class ConfiguratorTest {
    private Configurator configurator;
    private Set<Category> categories;

    @BeforeEach
    public void setup() {
        // TODO initialisation configurator
    }

    @Test
    @DisplayName("getCategories")
    void testGetCategories() {
        // TODO teste que le configurator retourne bien l'ensemble des catégories et que cet ensemble est non modifiable
    }

    @Test
    @DisplayName("getVariants")
    void testGetVariants() {
        // TODO Teste que le configurator retourne bien les bonnes variantes pour une catégorie
    }

}
