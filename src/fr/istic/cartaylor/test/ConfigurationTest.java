package fr.istic.cartaylor.test;

import fr.istic.cartaylor.api.Configuration;
import fr.istic.cartaylor.api.Configurator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class ConfigurationTest {
    private Configurator configurator;

    @BeforeEach
    private void setup() {
        // TODO initialisation du configurator
    }

    @Test
    @DisplayName("isComplete and isValid")
    void testCompleteValid() {
        // TODO teste si la configuration vide est valide et complète -> false
        // TODO teste si une configuration incomplète est valide et complète -> false
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
