package fr.istic.cartaylor.test;

import fr.istic.cartaylor.api.Configuration;
import fr.istic.cartaylor.api.Configurator;
import fr.istic.cartaylor.api.Category;
import fr.istic.cartaylor.api.PartType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;

import java.util.Optional;

public class ConfigurationTest {
    private Configurator configurator;
    private Category engine;
    private PartType eg100;

    @BeforeEach
    private void setup() {
        // TODO initialisation du configurator
        engine = configurator.getCategories().stream().filter((c) -> c.getName().equals("Engine")).findAny().get();
        eg100 = configurator.getVariants(engine).stream().filter((c) -> c.getName().equals("EG100")).findAny().get();
    }

    @Test
    @DisplayName("isComplete and isValid")
    void testCompleteValid() {
        Configuration c = configurator.getConfiguration();
        Assertions.assertFalse(c.isComplete());
        Assertions.assertFalse(c.isValid());

        c.selectPart(eg100);
        Assertions.assertFalse(c.isComplete());
        Assertions.assertFalse(c.isValid());
        // TODO teste si une configuration complète mais invalide est complète -> true et valide -> false
        // TODO teste si une configuration complète et valide est complète et valide -> true
    }

    @Test
    @DisplayName("getSelectionForCategory")
    void testGetSelectionForCategory() {
        // TODO teste si une configuration vide renvoie null
        // TODO sélectionner une variante pour une catégorie et tester si la méthode renvoie la variante
    }

    @Test
    @DisplayName("selectPart")
    void testSelectPart() {
        // TODO séléctionner une variante et tester si getSelectionForCategory retourne la bonne variante
        // TODO sélectionner une autre variante de la même catégorie et tester si getSelection for category la renvoie
    }

    @Test
    @DisplayName("getSelectedParts")
    void testGetSelectedParts() {
        // TODO sélectionner un ensemble de parts et vérifier que la méthode retourne le même ensemble
    }

    @Test
    @DisplayName("unselectPartType")
    void testUnselectPartType() {
        // TODO sélectionner une variante, tester si elle a été sélectionnée, désélectionner la variante et tester si elle a été désélectionnée
    }

    @Test
    @DisplayName("clear")
    void testClear() {
        // TODO sélectionner plusieurs variantes, utiliser clear et tester si getSelectedParts retourne l'ensemble vide
    }
}
