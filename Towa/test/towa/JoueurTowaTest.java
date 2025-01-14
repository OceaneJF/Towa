package towa;

import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import static towa.JoueurTowa.ACTIVATION;
import static towa.JoueurTowa.FUSION;
import static towa.JoueurTowa.niveau;

/**
 * Tests sur la classe JoueurTowa.
 */
public class JoueurTowaTest {

    /**
     * Test de la fonction actionsPossibles. Commentez les appels aux tests des
     * niveaux inférieurs, n'activez que le test du niveau à valider.
     */
    @Test
    public void testActionsPossibles() {
        //testActionsPossibles_niveau1();
        //testActionsPossibles_niveau2();
        //testActionsPossibles_niveau3();
        //testActionsPossibles_niveau4();
        //testActionsPossibles_niveau5();
        //testActionsPossibles_niveau6();
        //testActionsPossibles_niveau7();
        //testActionsPossibles_niveau8();
        //testActionsPossibles_niveau9();
        //testActionsPossibles_niveau10();
        //testActionsPossibles_niveau11();
        testActionsPossibles_niveau12();
    }

    /**
     * Test de la fonction actionsPossibles, au niveau 1.
     */
    public void testActionsPossibles_niveau1() {
        JoueurTowa joueur = new JoueurTowa();
        // un plateau sur lequel on veut tester actionsPossibles()
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
        // on choisit la couleur du joueur
        char couleur = Case.CAR_NOIR;        // on choisit le niveau
        int niveau = 1;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // on peut afficher toutes les actions possibles calculées :
        actionsPossibles.afficher();
        // on peut aussi tester si une action est dans les actions possibles :
        assertTrue(actionsPossibles.contient("PaB,1,0"));
        // on peut aussi tester si une action n'est pas dans les actions 
        // possibles :
        assertFalse(actionsPossibles.contient("PaQ,1,0"));
        assertFalse(actionsPossibles.contient("PaA,0,0"));
        // testons les 4 coins :
        assertTrue(actionsPossibles.contient("PaA,1,0"));
        assertTrue(actionsPossibles.contient("PpA,1,0"));
        assertTrue(actionsPossibles.contient("PaP,1,0"));
        assertTrue(actionsPossibles.contient("PpP,1,0"));
        // vérifions s'il y a le bon nombre d'actions possibles :
        assertEquals(Coordonnees.NB_LIGNES * Coordonnees.NB_COLONNES,
                actionsPossibles.nbActions);
    }

    /**
     * Test de la fonction actionsPossibles, au niveau 2.
     */
    public void testActionsPossibles_niveau2() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        // sur le plateau initial : 27 pions noirs et 20 pions blancs
        int niveau = 2;
        // 1 - joueur noir
        char couleur = Case.CAR_NOIR;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        actionsPossibles.afficher();
        // pose sur case vide : possible
        assertTrue(actionsPossibles.contient("PaB,28,20"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PbA,28,20"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PaG,28,20"));
        // pose sur case de même couleur et hauteur > 1 : possible
        assertTrue(actionsPossibles.contient("PcK,28,20"));
        // 2 - joueur blanc
        couleur = Case.CAR_BLANC;
        // on lance actionsPossibles
        actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        actionsPossibles = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // pose sur case vide : possible
        actionsPossibles.afficher();
        assertTrue(actionsPossibles.contient("PaB,27,21"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PaG,27,21"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PbA,27,21"));
        // pose sur case de même couleur et hauteur > 1 : possible
        assertTrue(actionsPossibles.contient("PlF,27,21"));
    }

    public void testActionsPossibles_niveau3() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        // sur le plateau initial : 27 pions noirs et 20 pions blancs
        int niveau = 3;
        // 1 - joueur noir
        char couleur = Case.CAR_NOIR;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        actionsPossibles.afficher();
        // pose sur case vide : possible
        assertTrue(actionsPossibles.contient("PaB,28,20"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PbA,28,20"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PaG,28,20"));
        // pose sur case de même couleur et hauteur > 1 et hauteur < 4 : possible
        assertTrue(actionsPossibles.contient("PlE,28,20"));
        // pose sur une case de meme couleur et de hauteur > 4 : impossible
        assertFalse(actionsPossibles.contient("Pck,28,20"));

        // 2 - joueur blanc
        couleur = Case.CAR_BLANC;
        // on lance actionsPossibles
        actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        actionsPossibles = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // pose sur case vide : possible
        actionsPossibles.afficher();
        assertTrue(actionsPossibles.contient("PaB,27,21"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PaG,27,21"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PbA,27,21"));
        // pose sur case de même couleur et hauteur > 1 et de hauteur < 4 : possible
        assertTrue(actionsPossibles.contient("PmF,27,21"));
        // pose sur une case de meme couleur et de hauteur >= 4 : impossible
        assertFalse(actionsPossibles.contient("PlF,27,21"));

    }

    public void testActionsPossibles_niveau4() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU4);
        // sur le plateau initial : 27 pions noirs et 20 pions blancs
        int niveau = 4;
        // 1 - joueur noir
        char couleur = Case.CAR_NOIR;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        actionsPossibles.afficher();
        //pose sur case vide : possible
        assertTrue(actionsPossibles.contient("PaB,28,20"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PbA,28,20"));
        // pose sur case de couleur opposée : impossible
        assertTrue(actionsPossibles.contient("PaG,28,20"));
        // pose sur case de même couleur et hauteur > 1 et hauteur < 4 : possible
        assertTrue(actionsPossibles.contient("PlE,28,20"));
        // pose sur une case de meme couleur et de hauteur > 4 : impossible
        assertFalse(actionsPossibles.contient("PcK,28,20"));
        assertTrue(actionsPossibles.contient("AlE,27,17"));
        assertTrue(actionsPossibles.contient("AcK,27,18"));
        assertTrue(actionsPossibles.contient("AbA,27,20"));
        assertFalse(actionsPossibles.contient("AbL,27,18"));
        assertFalse(actionsPossibles.contient("AlF,27,19"));
        assertFalse(actionsPossibles.contient("AmE,26,20"));
        // 

        // 2 - joueur blanc
        couleur = Case.CAR_BLANC;
        // on lance actionsPossibles
        actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        actionsPossibles = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // pose sur case vide : possible
        actionsPossibles.afficher();
        assertTrue(actionsPossibles.contient("PaB,27,21"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PaG,27,21"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PbA,27,21"));
        // pose sur case de même couleur et hauteur > 1 et de hauteur < 4 : possible
        assertTrue(actionsPossibles.contient("PmF,27,21"));
        // pose sur une case de meme couleur et de hauteur >= 4 : impossible
        assertFalse(actionsPossibles.contient("PlF,27,21"));
        assertTrue(actionsPossibles.contient("AmF,24,20"));
        assertTrue(actionsPossibles.contient("ApG,24,20"));
        assertTrue(actionsPossibles.contient("AaA,26,20"));
        assertFalse(actionsPossibles.contient("AcL,26,20"));
        assertFalse(actionsPossibles.contient("AcE,27,21"));
        assertFalse(actionsPossibles.contient("AlG,23,20"));

    }

    public void testActionsPossibles_niveau5() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU4);
        // sur le plateau initial : 27 pions noirs et 20 pions blancs
        int niveau = 5;
        // 1 - joueur noir
        char couleur = Case.CAR_NOIR;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        actionsPossibles.afficher();
        //pose sur case vide : possible
        assertTrue(actionsPossibles.contient("PaP,28,20"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PbA,28,20"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PaA,28,20"));
        // pose sur case de même couleur et hauteur > 1 et hauteur < 4 : possible
        assertTrue(actionsPossibles.contient("PlE,28,20"));
        // pose sur une case de meme couleur et de hauteur > 4 : impossible
        assertFalse(actionsPossibles.contient("PcK,28,20"));
        // test pour l'activation 
        assertTrue(actionsPossibles.contient("AlE,27,17"));
        assertTrue(actionsPossibles.contient("AcK,27,18"));
        assertTrue(actionsPossibles.contient("AbA,27,20"));
        assertFalse(actionsPossibles.contient("AbL,27,18"));
        assertFalse(actionsPossibles.contient("AlF,27,19"));
        assertFalse(actionsPossibles.contient("AmE,26,20"));
        // test pour la pose sur une case voisine à un adversaire 
        assertTrue(actionsPossibles.contient("PaB,29,20"));
        assertTrue(actionsPossibles.contient("PiH,28,20"));
        assertFalse(actionsPossibles.contient("PmK,29,20"));

        // 2 - joueur blanc
        couleur = Case.CAR_BLANC;
        // on lance actionsPossibles
        actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        actionsPossibles = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // pose sur case vide : possible
        actionsPossibles.afficher();
        assertTrue(actionsPossibles.contient("PpP,27,21"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PaG,27,21"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PbA,27,21"));
        // pose sur case de même couleur et hauteur > 1 et de hauteur < 4 : possible
        assertTrue(actionsPossibles.contient("PmF,27,21"));
        // pose sur une case de meme couleur et de hauteur >= 4 : impossible
        assertFalse(actionsPossibles.contient("PlF,27,21"));
        // test pour l'activation 
        assertTrue(actionsPossibles.contient("AmF,24,20"));
        assertTrue(actionsPossibles.contient("ApG,24,20"));
        assertTrue(actionsPossibles.contient("AaA,26,20"));
        assertFalse(actionsPossibles.contient("AcL,26,20"));
        assertFalse(actionsPossibles.contient("AcE,27,21"));
        assertFalse(actionsPossibles.contient("AlG,23,20"));
        //test pour la pose sur une case voisine à un adversaire 
        assertTrue(actionsPossibles.contient("PkF,27,22"));
        assertTrue(actionsPossibles.contient("PbD,27,21"));
        assertFalse(actionsPossibles.contient("PaH,27,22"));

    }

    public void testActionsPossibles_niveau6() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU4);
        // sur le plateau initial : 27 pions noirs et 20 pions blancs
        int niveau = 6;
        // 1 - joueur noir
        char couleur = Case.CAR_NOIR;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        actionsPossibles.afficher();
        //pose sur case vide : possible
        assertTrue(actionsPossibles.contient("PaP,28,20"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PbA,28,20"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PaA,28,20"));
        // pose sur case de même couleur et hauteur > 1 et hauteur < 4 : possible
        assertTrue(actionsPossibles.contient("PlE,28,20"));
        // pose sur une case de meme couleur et de hauteur > 4 : impossible
        assertFalse(actionsPossibles.contient("PcK,28,20"));
        // test pour l'activation 
        assertTrue(actionsPossibles.contient("AlE,27,15"));
        assertTrue(actionsPossibles.contient("AnG,27,19"));
        assertTrue(actionsPossibles.contient("AcK,27,16"));
        // test pour la pose sur une case voisine à un adversaire 
        assertTrue(actionsPossibles.contient("PaB,29,20"));
        assertTrue(actionsPossibles.contient("PiH,28,20"));
        assertFalse(actionsPossibles.contient("PmK,29,20"));

        // 2 - joueur blanc
        couleur = Case.CAR_BLANC;
        // on lance actionsPossibles
        actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        actionsPossibles = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // pose sur case vide : possible
        actionsPossibles.afficher();
        assertTrue(actionsPossibles.contient("PpP,27,21"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PaG,27,21"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PbA,27,21"));
        // pose sur case de même couleur et hauteur > 1 et de hauteur < 4 : possible
        assertTrue(actionsPossibles.contient("PmF,27,21"));
        // pose sur une case de meme couleur et de hauteur >= 4 : impossible
        assertFalse(actionsPossibles.contient("PlF,27,21"));
        // test pour l'activation 
        assertTrue(actionsPossibles.contient("AlF,19,20"));
        assertTrue(actionsPossibles.contient("AmF,23,20"));
        assertTrue(actionsPossibles.contient("ApG,21,20"));
        //test pour la pose sur une case voisine à un adversaire 
        assertTrue(actionsPossibles.contient("PkF,27,22"));
        assertTrue(actionsPossibles.contient("PbD,27,21"));
        assertFalse(actionsPossibles.contient("PaH,27,22"));

    }

    public void testActionsPossibles_niveau7() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU4);
        // sur le plateau initial : 27 pions noirs et 20 pions blancs
        int niveau = 7;
        // 1 - joueur noir
        char couleur = Case.CAR_NOIR;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        actionsPossibles.afficher();
        //pose sur case vide : possible
        assertTrue(actionsPossibles.contient("PaP,28,20"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PbA,28,20"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PaA,28,20"));
        // pose sur case de même couleur et hauteur > 1 et hauteur < 4 : possible
        assertTrue(actionsPossibles.contient("PlE,28,20"));
        // pose sur une case de meme couleur et de hauteur > 4 : impossible
        assertFalse(actionsPossibles.contient("PcK,28,20"));
        // test pour l'activation 
        assertTrue(actionsPossibles.contient("AlE,27,17"));
        assertTrue(actionsPossibles.contient("AnG,27,20"));
        assertTrue(actionsPossibles.contient("AcK,27,17"));
        // test pour la pose sur une case voisine à un adversaire 
        assertTrue(actionsPossibles.contient("PaB,29,20"));
        assertTrue(actionsPossibles.contient("PiH,28,20"));
        assertFalse(actionsPossibles.contient("PmK,29,20"));

        // 2 - joueur blanc
        couleur = Case.CAR_BLANC;
        // on lance actionsPossibles
        actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        actionsPossibles = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // pose sur case vide : possible
        actionsPossibles.afficher();
        assertTrue(actionsPossibles.contient("PpP,27,21"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PaG,27,21"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PbA,27,21"));
        // pose sur case de même couleur et hauteur > 1 et de hauteur < 4 : possible
        assertTrue(actionsPossibles.contient("PmF,27,21"));
        // pose sur une case de meme couleur et de hauteur >= 4 : impossible
        assertFalse(actionsPossibles.contient("PlF,27,21"));
        // test pour l'activation 
        assertTrue(actionsPossibles.contient("AlF,22,20"));
        assertTrue(actionsPossibles.contient("AmF,24,20"));
        assertTrue(actionsPossibles.contient("ApG,22,20"));
        //test pour la pose sur une case voisine à un adversaire 
        assertTrue(actionsPossibles.contient("PkF,27,22"));
        assertTrue(actionsPossibles.contient("PbD,27,21"));
        assertFalse(actionsPossibles.contient("PaH,27,22"));

    }

    public void testActionsPossibles_niveau8() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU4);
        // sur le plateau initial : 27 pions noirs et 20 pions blancs
        int niveau = 8;
        // 1 - joueur noir
        char couleur = Case.CAR_NOIR;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        actionsPossibles.afficher();
        //pose sur case vide : possible
        assertTrue(actionsPossibles.contient("PaP,28,20"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PbA,28,20"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PaA,28,20"));
        // pose sur case de même couleur et hauteur > 1 et hauteur < 4 : possible
        assertTrue(actionsPossibles.contient("PlE,28,20"));
        // pose sur une case de meme couleur et de hauteur > 4 : impossible
        assertFalse(actionsPossibles.contient("PcK,28,20"));
        // test pour l'activation 
        assertTrue(actionsPossibles.contient("AlE,27,17"));
        assertTrue(actionsPossibles.contient("AnG,27,20"));
        assertTrue(actionsPossibles.contient("AcK,27,17"));
        // test pour la pose sur une case voisine à un adversaire 
        assertTrue(actionsPossibles.contient("PaB,29,20"));
        assertTrue(actionsPossibles.contient("PiH,28,20"));
        assertFalse(actionsPossibles.contient("PmK,29,20"));
        // test pour la fusion 
        assertTrue(actionsPossibles.contient("FhK,24,20"));
        assertTrue(actionsPossibles.contient("FkE,27,20"));
        assertTrue(actionsPossibles.contient("FcK,26,20"));

        // 2 - joueur blanc
        couleur = Case.CAR_BLANC;
        // on lance actionsPossibles
        actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        actionsPossibles = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // pose sur case vide : possible
        actionsPossibles.afficher();
        assertTrue(actionsPossibles.contient("PpP,27,21"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PaG,27,21"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PbA,27,21"));
        // pose sur case de même couleur et hauteur > 1 et de hauteur < 4 : possible
        assertTrue(actionsPossibles.contient("PmF,27,21"));
        // pose sur une case de meme couleur et de hauteur >= 4 : impossible
        assertFalse(actionsPossibles.contient("PlF,27,21"));
        // test pour l'activation 
        assertTrue(actionsPossibles.contient("AlF,22,20"));
        assertTrue(actionsPossibles.contient("AmF,24,20"));
        assertTrue(actionsPossibles.contient("ApG,22,20"));
        //test pour la pose sur une case voisine à un adversaire 
        assertTrue(actionsPossibles.contient("PkF,27,22"));
        assertTrue(actionsPossibles.contient("PbD,27,21"));
        assertFalse(actionsPossibles.contient("PaH,27,22"));
        // test pour la fusion 
        assertTrue(actionsPossibles.contient("FlF,27,16"));
        assertTrue(actionsPossibles.contient("FpG,27,20"));
        assertTrue(actionsPossibles.contient("FmF,27,16"));
        assertTrue(actionsPossibles.contient("FcE,27,20"));

    }

    public void testActionsPossibles_niveau9() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU4);
        // sur le plateau initial : 27 pions noirs et 20 pions blancs
        int niveau = 9;
        // 1 - joueur noir
        char couleur = Case.CAR_NOIR;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        actionsPossibles.afficher();
        //pose sur case vide : possible
        assertTrue(actionsPossibles.contient("PaP,28,20"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PbA,28,20"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PaA,28,20"));
        // pose sur case de même couleur et hauteur > 1 et hauteur < 4 : possible
        assertTrue(actionsPossibles.contient("PlE,28,20"));
        // pose sur une case de meme couleur et de hauteur > 4 : impossible
        assertFalse(actionsPossibles.contient("PcK,28,20"));
        // test pour l'activation 
        assertTrue(actionsPossibles.contient("AlE,27,17"));
        assertTrue(actionsPossibles.contient("AnG,27,20"));
        assertTrue(actionsPossibles.contient("AcK,27,17"));
        // test pour la pose sur une case voisine à un adversaire 
        assertTrue(actionsPossibles.contient("PaB,29,20"));
        assertTrue(actionsPossibles.contient("PiH,28,20"));
        assertFalse(actionsPossibles.contient("PmK,29,20"));
        // test pour la fusion 
        assertTrue(actionsPossibles.contient("FhK,24,20"));
        assertTrue(actionsPossibles.contient("FkE,27,20"));
        assertTrue(actionsPossibles.contient("FcK,26,20"));

        // 2 - joueur blanc
        couleur = Case.CAR_BLANC;
        // on lance actionsPossibles
        actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        actionsPossibles = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // pose sur case vide : possible
        actionsPossibles.afficher();
        assertTrue(actionsPossibles.contient("PpP,27,21"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PaG,27,21"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PbA,27,21"));
        // pose sur case de même couleur et hauteur > 1 et de hauteur < 4 : possible
        assertTrue(actionsPossibles.contient("PmF,27,21"));
        // pose sur une case de meme couleur et de hauteur >= 4 : impossible
        assertFalse(actionsPossibles.contient("PlF,27,21"));
        // test pour l'activation 
        assertTrue(actionsPossibles.contient("AlF,22,20"));
        assertTrue(actionsPossibles.contient("AmF,24,20"));
        assertTrue(actionsPossibles.contient("ApG,22,20"));
        //test pour la pose sur une case voisine à un adversaire 
        assertTrue(actionsPossibles.contient("PkF,27,22"));
        assertTrue(actionsPossibles.contient("PbD,27,21"));
        assertFalse(actionsPossibles.contient("PaH,27,22"));
        // test pour la fusion 
        assertTrue(actionsPossibles.contient("FlF,27,16"));
        assertTrue(actionsPossibles.contient("FpG,27,20"));
        assertTrue(actionsPossibles.contient("FmF,27,16"));
        assertTrue(actionsPossibles.contient("FcE,27,20"));

        // Test pour l'action chaton kamikaze
        assertTrue(actionsPossibles.contient("CO,17,11"));
        assertTrue(actionsPossibles.contient("CE,19,11"));
        assertTrue(actionsPossibles.contient("CN,19,9"));
        assertTrue(actionsPossibles.contient("CS,18,15"));

    }

    public void testActionsPossibles_niveau10() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU10);
        // sur le plateau initial : 3 pions noirs et 19 pions blancs
        int niveau = 10;
        // 1 - joueur noir
        char couleur = Case.CAR_NOIR;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        actionsPossibles.afficher();
        //pose sur case vide : possible
        assertTrue(actionsPossibles.contient("PaP,4,19"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PbD,4,19"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PbA,4,19"));
        // pose sur une case de meme couleur et de niveau >= 4 : impossible
        assertFalse(actionsPossibles.contient("PbC,4,19"));
        // test pour l'activation 
        assertTrue(actionsPossibles.contient("AbC,3,13"));
        assertTrue(actionsPossibles.contient("AbD,3,19"));
        // test pour la pose sur une case voisine à un adversaire 
        assertTrue(actionsPossibles.contient("PdB,5,19"));
        assertTrue(actionsPossibles.contient("PdD,5,19"));
        assertFalse(actionsPossibles.contient("PgC,5,19"));
        // test pour la fusion 
        assertTrue(actionsPossibles.contient("FbD,3,19"));
        assertTrue(actionsPossibles.contient("FbC,2,19"));
        assertFalse(actionsPossibles.contient("FbE,2,19"));

        // 2 - joueur blanc
        couleur = Case.CAR_BLANC;
        // on lance actionsPossibles
        actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        actionsPossibles = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // pose sur case vide : possible
        actionsPossibles.afficher();
        assertTrue(actionsPossibles.contient("PbJ,3,20"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PaD,3,20"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PbC,3,20"));
        // pose sur case de même couleur et hauteur > 1 et de hauteur < 4 : possible
        assertTrue(actionsPossibles.contient("PdC,3,20"));
        // pose sur une case de meme couleur et de niveau >= 4 : impossible
        assertFalse(actionsPossibles.contient("PcB,3,20"));
        // test pour l'activation 
        assertTrue(actionsPossibles.contient("AaD,2,19"));
        assertTrue(actionsPossibles.contient("AdC,3,19"));
        assertFalse(actionsPossibles.contient("AcB,1,19"));
        //test pour la pose sur une case voisine à un adversaire 
        assertTrue(actionsPossibles.contient("PbB,3,21"));
        assertFalse(actionsPossibles.contient("PcC,3,19"));
        assertTrue(actionsPossibles.contient("PcE,3,21"));
        // test pour la fusion 
        assertTrue(actionsPossibles.contient("FaD,3,17"));
        assertTrue(actionsPossibles.contient("FbE,3,15"));
        assertTrue(actionsPossibles.contient("FcD,3,11"));
        assertTrue(actionsPossibles.contient("FfC,3,18"));

        // Test pour l'action chaton kamikaze
        assertTrue(actionsPossibles.contient("CO,3,7"));
        assertTrue(actionsPossibles.contient("CE,3,6"));
        assertTrue(actionsPossibles.contient("CN,1,9"));
        assertTrue(actionsPossibles.contient("CS,3,3"));

    }

    public void testActionsPossibles_niveau11() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU10);
        // sur le plateau initial : 3 pions noirs et 19 pions blancs
        int niveau = 11;
        // 1 - joueur noir
        char couleur = Case.CAR_NOIR;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        actionsPossibles.afficher();
        //pose sur case vide : possible
        assertTrue(actionsPossibles.contient("PaP,4,19"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PbD,4,19"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PbA,4,19"));
        // pose sur une case de meme couleur et de niveau >= 4 : impossible
        assertFalse(actionsPossibles.contient("PbC,4,19"));
        // test pour l'activation 
        assertTrue(actionsPossibles.contient("AbC,3,13"));
        assertTrue(actionsPossibles.contient("AbD,3,19"));
        // test pour la pose sur une case voisine à un adversaire 
        assertTrue(actionsPossibles.contient("PdB,5,19"));
        assertTrue(actionsPossibles.contient("PdD,5,19"));
        assertFalse(actionsPossibles.contient("PgC,5,19"));
        // test pour la fusion 
        assertTrue(actionsPossibles.contient("FbD,3,19"));
        assertTrue(actionsPossibles.contient("FbC,2,19"));
        assertFalse(actionsPossibles.contient("FbE,2,19"));
        //test pour l'action magie
        assertTrue(actionsPossibles.contient("MbC,3,19"));
        assertTrue(actionsPossibles.contient("MbD,3,19"));

        // 2 - joueur blanc
        couleur = Case.CAR_BLANC;
        // on lance actionsPossibles
        actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        actionsPossibles = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // pose sur case vide : possible
        actionsPossibles.afficher();
        assertTrue(actionsPossibles.contient("PbJ,3,20"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PaD,3,20"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PbC,3,20"));
        // pose sur case de même couleur et hauteur > 1 et de hauteur < 4 : possible
        assertTrue(actionsPossibles.contient("PdC,3,20"));
        // pose sur une case de meme couleur et de niveau >= 4 : impossible
        assertFalse(actionsPossibles.contient("PcB,3,20"));
        // test pour l'activation 
        assertTrue(actionsPossibles.contient("AaD,2,19"));
        assertTrue(actionsPossibles.contient("AdC,3,19"));
        assertFalse(actionsPossibles.contient("AcB,1,19"));
        //test pour la pose sur une case voisine à un adversaire 
        assertTrue(actionsPossibles.contient("PbB,3,21"));
        assertFalse(actionsPossibles.contient("PcC,3,19"));
        assertTrue(actionsPossibles.contient("PcE,3,21"));
        // test pour la fusion 
        assertTrue(actionsPossibles.contient("FaD,3,17"));
        assertTrue(actionsPossibles.contient("FbE,3,15"));
        assertTrue(actionsPossibles.contient("FcD,3,11"));
        assertTrue(actionsPossibles.contient("FfC,3,18"));
        //Test pour l'action magie
        assertTrue(actionsPossibles.contient("MbA,3,19"));
        assertTrue(actionsPossibles.contient("MaD,3,19"));
        assertTrue(actionsPossibles.contient("McD,3,19"));

        // Test pour l'action chaton kamikaze
        assertTrue(actionsPossibles.contient("CO,3,7"));
        assertTrue(actionsPossibles.contient("CE,3,6"));
        assertTrue(actionsPossibles.contient("CN,1,9"));
        assertTrue(actionsPossibles.contient("CS,3,3"));

    }
    
    public void testActionsPossibles_niveau12() {
        JoueurTowa joueur = new JoueurTowa();
        // sur le plateau initial : 3 pions noirs et 19 pions blancs
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU10);
        // sur le plateau 12 : 36 pions noirs et 23 blancs
        Case[][] plateau1 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU12);
        
        int niveau = 12;
        // 1 - joueur noir
        char couleur = Case.CAR_NOIR;
        // on lance actionsPossibles
        String[] actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        ActionsPossibles actionsPossibles
                = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        actionsPossibles.afficher();
        //pose sur case vide : possible
        assertTrue(actionsPossibles.contient("PaP,4,19"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PbD,4,19"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PbA,4,19"));
        // pose sur une case de meme couleur et de niveau >= 4 : impossible
        assertFalse(actionsPossibles.contient("PbC,4,19"));
        // test pour l'activation 
        assertTrue(actionsPossibles.contient("AbC,3,13"));
        assertTrue(actionsPossibles.contient("AbD,3,19"));
        // test pour la pose sur une case voisine à un adversaire 
        assertTrue(actionsPossibles.contient("PdB,5,19"));
        assertTrue(actionsPossibles.contient("PdD,5,19"));
        assertFalse(actionsPossibles.contient("PgC,5,19"));
        // test pour la fusion 
        assertTrue(actionsPossibles.contient("FbD,3,19"));
        assertTrue(actionsPossibles.contient("FbC,2,19"));
        assertFalse(actionsPossibles.contient("FbE,2,19"));
        //test pour l'action magie
        assertTrue(actionsPossibles.contient("MbC,3,19"));
        assertTrue(actionsPossibles.contient("MbD,3,19"));
        //test pour la nature 
        String[] actionsPossiblesDepuisPlateau12 = joueur.actionsPossibles(plateau1, couleur, niveau);
        ActionsPossibles actionsPossibles12
                = new ActionsPossibles(actionsPossiblesDepuisPlateau12);
        assertTrue(actionsPossibles12.contient("MbA,36,23"));
        assertFalse(actionsPossibles12.contient("PgD,36,23"));
        assertFalse(actionsPossibles12.contient("PhH,36,23"));
        
        
        // 2 - joueur blanc
        couleur = Case.CAR_BLANC;
        // on lance actionsPossibles
        actionsPossiblesDepuisPlateau = joueur.actionsPossibles(plateau, couleur, niveau);
        actionsPossibles = new ActionsPossibles(actionsPossiblesDepuisPlateau);
        // pose sur case vide : possible
        actionsPossibles.afficher();
        assertTrue(actionsPossibles.contient("PbJ,3,20"));
        // pose sur case de même couleur : possible
        assertTrue(actionsPossibles.contient("PaD,3,20"));
        // pose sur case de couleur opposée : impossible
        assertFalse(actionsPossibles.contient("PbC,3,20"));
        // pose sur case de même couleur et hauteur > 1 et de hauteur < 4 : possible
        assertTrue(actionsPossibles.contient("PdC,3,20"));
        // pose sur une case de meme couleur et de niveau >= 4 : impossible
        assertFalse(actionsPossibles.contient("PcB,3,20"));
        // test pour l'activation 
        assertTrue(actionsPossibles.contient("AaD,2,19"));
        assertTrue(actionsPossibles.contient("AdC,3,19"));
        assertFalse(actionsPossibles.contient("AcB,1,19"));
        //test pour la pose sur une case voisine à un adversaire 
        assertTrue(actionsPossibles.contient("PbB,3,21"));
        assertFalse(actionsPossibles.contient("PcC,3,19"));
        assertTrue(actionsPossibles.contient("PcE,3,21"));
        // test pour la fusion 
        assertTrue(actionsPossibles.contient("FaD,3,17"));
        assertTrue(actionsPossibles.contient("FbE,3,15"));
        assertTrue(actionsPossibles.contient("FcD,3,11"));
        assertTrue(actionsPossibles.contient("FfC,3,18"));
        //Test pour l'action magie
        assertTrue(actionsPossibles.contient("MbA,3,19"));
        assertTrue(actionsPossibles.contient("MaD,3,19"));
        assertTrue(actionsPossibles.contient("McD,3,19"));

        // Test pour l'action chaton kamikaze
        assertTrue(actionsPossibles.contient("CO,3,7"));
        assertTrue(actionsPossibles.contient("CE,3,6"));
        assertTrue(actionsPossibles.contient("CN,1,9"));
        assertTrue(actionsPossibles.contient("CS,3,3"));
        
        //test pour la nature
        assertFalse(actionsPossibles12.contient("MiI,36,23"));
        assertFalse(actionsPossibles12.contient("PgD,36,23"));
        assertFalse(actionsPossibles12.contient("PhH,36,23"));
    }

    @Test
    public void testNbPions() {
        // à décommenter le moment venu...

        // plateau1 : 0 noir, 0 blanc
        Case[][] plateau1 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
        assertEquals(0, JoueurTowa.nbPions(plateau1).nbPionsNoirs);
        assertEquals(0, JoueurTowa.nbPions(plateau1).nbPionsBlancs);
        // plateau2 : 27 noirs, 20 blancs
        Case[][] plateau2 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        assertEquals(27, JoueurTowa.nbPions(plateau2).nbPionsNoirs);
        assertEquals(20, JoueurTowa.nbPions(plateau2).nbPionsBlancs);

    }

    @Test
    public void testPosePossible() {
        JoueurTowa joueur = new JoueurTowa();
        
        Case[][] plateau1 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
        assertTrue( joueur.posePossible(plateau1, Coordonnees.depuisCars('f', 'D'), Case.CAR_NOIR));
        assertTrue( joueur.posePossible(plateau1, Coordonnees.depuisCars('h', 'E'), Case.CAR_NOIR));
        
        Case[][] plateau2 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        assertTrue( joueur.posePossible(plateau2, Coordonnees.depuisCars('a', 'B'), Case.CAR_NOIR));
        assertFalse( joueur.posePossible(plateau2, Coordonnees.depuisCars('a', 'G'), Case.CAR_NOIR));
        assertTrue( joueur.posePossible(plateau2, Coordonnees.depuisCars('a', 'N'), Case.CAR_BLANC));
        assertFalse( joueur.posePossible(plateau2, Coordonnees.depuisCars('b', 'A'), Case.CAR_BLANC));
        assertTrue( joueur.posePossible(plateau2, Coordonnees.depuisCars('b', 'H'), Case.CAR_BLANC));
        assertTrue(joueur.posePossible(plateau2, Coordonnees.depuisCars('m', 'F'), Case.CAR_BLANC));
        assertTrue(joueur.posePossible(plateau2, Coordonnees.depuisCars('b', 'H'), Case.CAR_BLANC));
        assertFalse(joueur.posePossible(plateau2, Coordonnees.depuisCars('l', 'F'), Case.CAR_BLANC));
        assertFalse(joueur.posePossible(plateau2, Coordonnees.depuisCars('c', 'K'), Case.CAR_NOIR));
        assertTrue(joueur.posePossible(plateau2, Coordonnees.depuisCars('l', 'E'), Case.CAR_NOIR));
        assertTrue(joueur.posePossible(plateau2, Coordonnees.depuisCars('j', 'H'), Case.CAR_NOIR));
        
        Case[][] plateau3 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU10);
        //la pose est toujours impossible sur les cases dotées d'une tour énemie
        assertFalse(joueur.posePossible(plateau3, Coordonnees.depuisCars('b', 'A'), Case.CAR_NOIR));
        assertFalse(joueur.posePossible(plateau3, Coordonnees.depuisCars('b', 'D'), Case.CAR_BLANC));
        //la pose est impossible car la tour présente est déjà de niveau 4
        assertFalse(joueur.posePossible(plateau3, Coordonnees.depuisCars('b', 'C'), Case.CAR_NOIR));
        assertFalse(joueur.posePossible(plateau3, Coordonnees.depuisCars('c', 'B'), Case.CAR_BLANC));
        //la pose est possible puisque la tour n'est que de niveau 2. Un seul pion y serait posé 
        assertTrue(joueur.posePossible(plateau3, Coordonnees.depuisCars('b', 'D'), Case.CAR_NOIR));
        assertTrue(joueur.posePossible(plateau3, Coordonnees.depuisCars('d', 'C'), Case.CAR_BLANC));
        //la pose est impossible car l'altitude de ces cases est de 4 
        assertFalse(joueur.posePossible(plateau3, Coordonnees.depuisCars('h', 'B'), Case.CAR_NOIR));
        assertFalse(joueur.posePossible(plateau3, Coordonnees.depuisCars('e', 'D'), Case.CAR_BLANC));
        //la pose est possible et résulterait en la pose de 2 pions.
        assertTrue(joueur.posePossible(plateau3, Coordonnees.depuisCars('d', 'D'), Case.CAR_NOIR));
        assertTrue(joueur.posePossible(plateau3, Coordonnees.depuisCars('b', 'B'), Case.CAR_BLANC));
        //la pose est impossible car la case est d'altitude 3 et une tour ennemie se trouve sur une case adjacente et impliquerait la pose de 2 pions, ce qui dépasserait la limite de 4 sur le niveau 
        assertFalse(joueur.posePossible(plateau3, Coordonnees.depuisCars('g', 'C'), Case.CAR_NOIR));
        
        Case[][] plateau4 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU12);
        assertFalse(joueur.posePossible(plateau4, Coordonnees.depuisCars('g', 'D'), Case.CAR_NOIR));
        assertFalse(joueur.posePossible(plateau4, Coordonnees.depuisCars('h', 'H'), Case.CAR_BLANC));
    }

    @Test
    public void testActivationPossible() {
        JoueurTowa joueur = new JoueurTowa();
        Case[][] plateau4 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU4);
        
        assertTrue(joueur.activationPossible(plateau4, Coordonnees.depuisCars('a', 'A'), Case.CAR_BLANC));
        assertTrue(joueur.activationPossible(plateau4, Coordonnees.depuisCars('m', 'F'), Case.CAR_BLANC));
        assertTrue(joueur.activationPossible(plateau4, Coordonnees.depuisCars('c', 'L'), Case.CAR_BLANC));
        assertFalse(joueur.activationPossible(plateau4, Coordonnees.depuisCars('j', 'A'), Case.CAR_BLANC));
        assertFalse(joueur.activationPossible(plateau4, Coordonnees.depuisCars('i', 'B'), Case.CAR_BLANC));
        assertFalse(joueur.activationPossible(plateau4, Coordonnees.depuisCars('k', 'F'), Case.CAR_BLANC));

        assertTrue(joueur.activationPossible(plateau4, Coordonnees.depuisCars('b', 'A'), Case.CAR_NOIR));
        assertTrue(joueur.activationPossible(plateau4, Coordonnees.depuisCars('c', 'K'), Case.CAR_NOIR));
        assertTrue(joueur.activationPossible(plateau4, Coordonnees.depuisCars('o', 'F'), Case.CAR_NOIR));
        assertFalse(joueur.activationPossible(plateau4, Coordonnees.depuisCars('g', 'C'), Case.CAR_NOIR));
        assertFalse(joueur.activationPossible(plateau4, Coordonnees.depuisCars('a', 'B'), Case.CAR_NOIR));
        assertFalse(joueur.activationPossible(plateau4, Coordonnees.depuisCars('c', 'C'), Case.CAR_NOIR));

    }

    /**
     * Test de la fonction ajoutActionPose.
     */
    @Test
    public void testAjoutActionPose() {
        JoueurTowa joueur = new JoueurTowa();
        ActionsPossibles actions = new ActionsPossibles();
        NbPions nbPions = new NbPions(0, 0);
        // pour l'instant pas d'action possible
        assertEquals(0, actions.nbActions);
        // on crée le tableau d'actions et on en ajoute une
        Coordonnees coord = Coordonnees.depuisCars('f', 'D');
        joueur.ajoutActionPose(coord, actions,
                nbPions, Case.CAR_NOIR, 1);
        // l'action est devenue possible
        assertTrue(actions.contient("PfD,1,0"));
        // une action possible mais qui n'a pas encore été ajoutée
        assertFalse(actions.contient("PbH,1,0"));
        // pour l'instant une seule action possible
        assertEquals(1, actions.nbActions);
        // ajout d'une deuxième action possible
        joueur.ajoutActionPose(Coordonnees.depuisCars('b', 'H'), actions,
                nbPions, Case.CAR_NOIR, 2);
        // l'action a bien été ajoutée
        assertTrue(actions.contient("PbH,2,0"));
        // désormais, deux actions possibles
        assertEquals(2, actions.nbActions);

        // on crée le tableau d'actions et on en ajoute une
        joueur.ajoutActionPose(Coordonnees.depuisCars('f', 'D'), actions,
                nbPions, Case.CAR_BLANC, 1);
        // l'action est devenue possible
        assertTrue(actions.contient("PfD,0,1"));
        // une action possible mais qui n'a pas encore été ajoutée
        assertFalse(actions.contient("PbH,0,1"));
        // pour l'instant une seule action possible
        assertEquals(3, actions.nbActions);
        // ajout d'une deuxième action possible
        joueur.ajoutActionPose(Coordonnees.depuisCars('b', 'H'), actions,
                nbPions, Case.CAR_BLANC, 2);
        // l'action a bien été ajoutée
        assertTrue(actions.contient("PbH,0,2"));
        // désormais, deux actions possibles
        assertEquals(4, actions.nbActions);

    }

    @Test
    public void testAjoutActionActivation() {
        JoueurTowa joueur = new JoueurTowa();
        ActionsPossibles actions = new ActionsPossibles();
        NbPions nbPions = new NbPions(10, 10);
        // pour l'instant pas d'action possible
        assertEquals(0, actions.nbActions);
        // on crée le tableau d'actions et on en ajoute une
        joueur.ajoutActionActivation(Coordonnees.depuisCars('f', 'D'), actions,
                nbPions, Case.CAR_NOIR, 4);
        // l'action est devenue possible
        assertTrue(actions.contient("AfD,10,6"));
        //une action possible mais qui n'a pas encore été ajoutée
        assertFalse(actions.contient("AbH,1,0"));
        // pour l'instant une seule action possible
        assertEquals(1, actions.nbActions);
        // ajout d'une deuxième action possible
        joueur.ajoutActionActivation(Coordonnees.depuisCars('b', 'H'), actions,
                nbPions, Case.CAR_NOIR, 1);
        // l'action a bien été ajoutée
        assertTrue(actions.contient("AbH,10,9"));
        // désormais, deux actions possibles
        assertEquals(2, actions.nbActions);

        // on crée le tableau d'actions et on en ajoute une
        joueur.ajoutActionActivation(Coordonnees.depuisCars('f', 'D'), actions,
                nbPions, Case.CAR_BLANC, 3);
        // l'action est devenue possible
        assertTrue(actions.contient("AfD,7,10"));
        // une action possible mais qui n'a pas encore été ajoutée
        assertFalse(actions.contient("AbH,0,1"));
        // pour l'instant une seule action possible
        assertEquals(3, actions.nbActions);
        // ajout d'une deuxième action possible
        joueur.ajoutActionActivation(Coordonnees.depuisCars('b', 'H'), actions,
                nbPions, Case.CAR_BLANC, 2);
        // l'action a bien été ajoutée
        assertTrue(actions.contient("AbH,8,10"));
        // désormais, deux actions possibles
        assertEquals(4, actions.nbActions);

    }

    @Test
    public void testAjoutActionFusion() {
        JoueurTowa joueur = new JoueurTowa();
        ActionsPossibles actions = new ActionsPossibles();
        NbPions nbPions = new NbPions(10, 10);
        // pour l'instant pas d'action possible
        assertEquals(0, actions.nbActions);
        // on crée le tableau d'actions et on en ajoute une
        joueur.ajoutActionFusion(Coordonnees.depuisCars('f', 'D'), actions,
                nbPions, Case.CAR_NOIR, 4);
        // l'action est devenue possible
        assertTrue(actions.contient("FfD,6,10"));
        //une action possible mais qui n'a pas encore été ajoutée
        assertFalse(actions.contient("FbH,1,0"));
        // pour l'instant une seule action possible
        assertEquals(1, actions.nbActions);
        // ajout d'une deuxième action possible
        joueur.ajoutActionFusion(Coordonnees.depuisCars('b', 'H'), actions,
                nbPions, Case.CAR_NOIR, 1);
        // l'action a bien été ajoutée
        assertTrue(actions.contient("FbH,9,10"));
        // désormais, deux actions possibles
        assertEquals(2, actions.nbActions);

        // on crée le tableau d'actions et on en ajoute une
        joueur.ajoutActionFusion(Coordonnees.depuisCars('f', 'D'), actions,
                nbPions, Case.CAR_BLANC, 3);
        // l'action est devenue possible
        assertTrue(actions.contient("FfD,10,7"));
        // une action possible mais qui n'a pas encore été ajoutée
        assertFalse(actions.contient("FbH,0,1"));
        // pour l'instant une seule action possible
        assertEquals(3, actions.nbActions);
        // ajout d'une deuxième action possible
        joueur.ajoutActionFusion(Coordonnees.depuisCars('b', 'H'), actions,
                nbPions, Case.CAR_BLANC, 2);
        // l'action a bien été ajoutée
        assertTrue(actions.contient("FbH,10,8"));
        // désormais, deux actions possibles
        assertEquals(4, actions.nbActions);

    }

    @Test
    public void testAjoutActionKamikaze() {
        JoueurTowa joueur = new JoueurTowa();
        ActionsPossibles actions = new ActionsPossibles();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU9);
        NbPions nbPions = JoueurTowa.nbPions(plateau);
        // pour l'instant pas d'action possible
        assertEquals(0, actions.nbActions);
        // on crée le tableau d'actions et on en ajoute une
        joueur.ajoutActionKamikaze(actions, nbPions, JoueurTowa.OUEST, plateau);
        // l'action est devenue possible
        assertTrue(actions.contient("CO,7,3"));
        // pour l'instant une seule action possible
        assertEquals(1, actions.nbActions);
        // ajout d'une deuxième action possible
        joueur.ajoutActionKamikaze(actions, nbPions, JoueurTowa.EST, plateau);
        // l'action a bien été ajoutée
        assertTrue(actions.contient("CE,6,7"));
        // désormais, deux actions possibles
        assertEquals(2, actions.nbActions);
        // on crée le tableau d'actions et on en ajoute une
        joueur.ajoutActionKamikaze(actions, nbPions, JoueurTowa.NORD, plateau);
        // l'action est devenue possible
        assertTrue(actions.contient("CN,8,5"));
        // pour l'instant une seule action possible
        assertEquals(3, actions.nbActions);
        // ajout d'une deuxième action possible
        joueur.ajoutActionKamikaze(actions, nbPions, JoueurTowa.SUD, plateau);
        // l'action a bien été ajoutée
        assertTrue(actions.contient("CS,7,3"));
        // désormais, deux actions possibles
        assertEquals(4, actions.nbActions);

    }

    @Test
    public void testAjoutActionMagie() {
        JoueurTowa joueur = new JoueurTowa();
        ActionsPossibles actions = new ActionsPossibles();
        NbPions nbPions = new NbPions(10, 10);
        // pour l'instant pas d'action possible
        assertEquals(0, actions.nbActions);
        // on crée le tableau d'actions et on en ajoute une
        joueur.ajoutActionMagie(Coordonnees.depuisCars('f', 'D'), actions,
                nbPions);
        // l'action est devenue possible
        assertTrue(actions.contient("MfD,10,10"));
        //une action possible mais qui n'a pas encore été ajoutée
        assertFalse(actions.contient("MbH,1,0"));
        // pour l'instant une seule action possible
        assertEquals(1, actions.nbActions);
        // ajout d'une deuxième action possible
        joueur.ajoutActionMagie(Coordonnees.depuisCars('b', 'H'), actions,
                nbPions);
        // l'action a bien été ajoutée
        assertTrue(actions.contient("MbH,10,10"));
        // désormais, deux actions possibles
        assertEquals(2, actions.nbActions);

        // on crée le tableau d'actions et on en ajoute une
        joueur.ajoutActionMagie(Coordonnees.depuisCars('f', 'D'), actions,
                nbPions);
        // l'action est devenue possible
        assertTrue(actions.contient("MfD,10,10"));
        // une action possible mais qui n'a pas encore été ajoutée
        assertFalse(actions.contient("MbH,0,1"));
        // pour l'instant une seule action possible
        assertEquals(3, actions.nbActions);
        // ajout d'une deuxième action possible
        joueur.ajoutActionMagie(Coordonnees.depuisCars('b', 'H'), actions,
                nbPions);
        // l'action a bien été ajoutée
        assertTrue(actions.contient("MbH,10,10"));
        // désormais, deux actions possibles
        assertEquals(4, actions.nbActions);

    }

    @Test
    public void testAdjacente() {

        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU7);
        // test pour l'activation
        Coordonnees coordTest1 = Coordonnees.depuisCars('l', 'F');
        assertEquals(5, JoueurTowa.adjacente(coordTest1, plateau, ACTIVATION));

        Coordonnees coordTest2 = Coordonnees.depuisCars('c', 'K');
        assertEquals(3, JoueurTowa.adjacente(coordTest2, plateau, ACTIVATION));

        Coordonnees coordTest3 = Coordonnees.depuisCars('p', 'G');
        assertEquals(5, JoueurTowa.adjacente(coordTest3, plateau, ACTIVATION));

        Coordonnees coordTest4 = Coordonnees.depuisCars('l', 'F');
        assertEquals(5, JoueurTowa.adjacente(coordTest4, plateau, ACTIVATION));
        
        // test pour la fusion 
        Coordonnees coordTest5 = Coordonnees.depuisCars('c', 'K');
        assertEquals(1, JoueurTowa.adjacente(coordTest5, plateau, FUSION));

        Coordonnees coordTest6 = Coordonnees.depuisCars('p', 'G');
        assertEquals(0, JoueurTowa.adjacente(coordTest6, plateau, FUSION));

        Coordonnees coordTest7 = Coordonnees.depuisCars('m', 'F');
        assertEquals(4, JoueurTowa.adjacente(coordTest7, plateau, FUSION));

        Coordonnees coordTest8 = Coordonnees.depuisCars('h', 'K');
        assertEquals(3, JoueurTowa.adjacente(coordTest8, plateau, FUSION));

        Coordonnees coordTest9 = Coordonnees.depuisCars('c', 'E');
        assertEquals(0, JoueurTowa.adjacente(coordTest9, plateau, FUSION));
        
        //Test pour le niveau 10
        Case[][] plateau2 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU10);
        
        Coordonnees coordTest10 = Coordonnees.depuisCars('d', 'C');
        assertEquals(9, JoueurTowa.adjacente(coordTest10, plateau2, FUSION));
        
        Coordonnees coordTest11 = Coordonnees.depuisCars('b', 'C');
        assertEquals(6, JoueurTowa.adjacente(coordTest11, plateau2, ACTIVATION));

    }

    @Test
    public void testdiagonaleActive() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU7);
        
        Coordonnees coordTest1 = Coordonnees.depuisCars('c', 'K');
        assertEquals(1, JoueurTowa.diagonaleActive(coordTest1, plateau));

        Coordonnees coordTest2 = Coordonnees.depuisCars('l', 'E');
        assertEquals(2, JoueurTowa.diagonaleActive(coordTest2, plateau));

        Coordonnees coordTest3 = Coordonnees.depuisCars('l', 'F');
        assertEquals(2, JoueurTowa.diagonaleActive(coordTest3, plateau));
        
        //test pour le niveau 10
        Case[][] plateau2 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU10);
        
        Coordonnees coordTest4 = Coordonnees.depuisCars('b', 'C');
        assertEquals(1, JoueurTowa.diagonaleActive(coordTest4, plateau2));
    }

    @Test
    public void testdiagonaleFusion() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU7);
        
        Coordonnees coordTest1 = Coordonnees.depuisCars('c', 'K');
        assertEquals(0, JoueurTowa.diagonaleFusion(coordTest1, plateau));

        Coordonnees coordTest2 = Coordonnees.depuisCars('l', 'E');
        assertEquals(0, JoueurTowa.diagonaleFusion(coordTest2, plateau));

        Coordonnees coordTest3 = Coordonnees.depuisCars('l', 'F');
        assertEquals(1, JoueurTowa.diagonaleFusion(coordTest3, plateau));

        Coordonnees coordTest4 = Coordonnees.depuisCars('n', 'F');
        assertEquals(1, JoueurTowa.diagonaleFusion(coordTest4, plateau));
    }

    @Test
    public void testligneActive() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU7);
        
        Coordonnees coordTest1 = Coordonnees.depuisCars('c', 'K');
        assertEquals(2, JoueurTowa.ligneActive(coordTest1, plateau));

        Coordonnees coordTest2 = Coordonnees.depuisCars('b', 'A');
        assertEquals(0, JoueurTowa.ligneActive(coordTest2, plateau));

        Coordonnees coordTest3 = Coordonnees.depuisCars('m', 'F');
        assertEquals(1, JoueurTowa.ligneActive(coordTest3, plateau));

        Coordonnees coordTest4 = Coordonnees.depuisCars('a', 'A');
        assertEquals(0, JoueurTowa.ligneActive(coordTest4, plateau));
        
        //test pour le niveau 10
        Case[][] plateau2 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU10);
        
        Coordonnees coordTest5 = Coordonnees.depuisCars('b', 'C');
        assertEquals(3, JoueurTowa.ligneActive(coordTest5, plateau2));

    }

    @Test
    public void testligneFusion() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU7);
        
        Coordonnees coordTest1 = Coordonnees.depuisCars('c', 'K');
        assertEquals(0, JoueurTowa.ligneFusion(coordTest1, plateau));

        Coordonnees coordTest2 = Coordonnees.depuisCars('b', 'A');
        assertEquals(0, JoueurTowa.ligneFusion(coordTest2, plateau));

        Coordonnees coordTest3 = Coordonnees.depuisCars('n', 'F');
        assertEquals(3, JoueurTowa.ligneFusion(coordTest3, plateau));

        Coordonnees coordTest4 = Coordonnees.depuisCars('a', 'A');
        assertEquals(1, JoueurTowa.ligneFusion(coordTest4, plateau));

        Coordonnees coordTest5 = Coordonnees.depuisCars('o', 'F');
        assertEquals(2, JoueurTowa.ligneFusion(coordTest5, plateau));

        Coordonnees coordTest6 = Coordonnees.depuisCars('n', 'G');
        assertEquals(2, JoueurTowa.ligneFusion(coordTest6, plateau));

        Coordonnees coordTest7 = Coordonnees.depuisCars('c', 'E');
        assertEquals(1, JoueurTowa.ligneFusion(coordTest7, plateau));
    }

    @Test
    public void testColonneActive() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU7);
        
        Coordonnees coordTest1 = Coordonnees.depuisCars('l', 'F');
        assertEquals(0, JoueurTowa.colonneActive(coordTest1, plateau));

        Coordonnees coordTest2 = Coordonnees.depuisCars('l', 'E');
        assertEquals(1, JoueurTowa.colonneActive(coordTest2, plateau));

        Coordonnees coordTest3 = Coordonnees.depuisCars('p', 'G');
        assertEquals(2, JoueurTowa.colonneActive(coordTest3, plateau));

        Coordonnees coordTest4 = Coordonnees.depuisCars('a', 'P');
        assertEquals(0, JoueurTowa.colonneActive(coordTest4, plateau));
        
        //test pour le niveau 10
        Case[][] plateau2 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU10);
        
        Coordonnees coordTest5 = Coordonnees.depuisCars('b', 'C');
        assertEquals(2, JoueurTowa.colonneActive(coordTest5, plateau2));
    }

    @Test
    public void testcolonneFusion() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU7);
        
        Coordonnees coordTest1 = Coordonnees.depuisCars('l', 'F');
        assertEquals(2, JoueurTowa.colonneFusion(coordTest1, plateau));

        Coordonnees coordTest2 = Coordonnees.depuisCars('m', 'F');
        assertEquals(4, JoueurTowa.colonneFusion(coordTest2, plateau));

        Coordonnees coordTest3 = Coordonnees.depuisCars('p', 'G');
        assertEquals(0, JoueurTowa.colonneFusion(coordTest3, plateau));

        Coordonnees coordTest4 = Coordonnees.depuisCars('a', 'P');
        assertEquals(0, JoueurTowa.colonneFusion(coordTest4, plateau));
    }

    @Test
    public void testEstDansTableau() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
        assertTrue(JoueurTowa.estDansPlateau(new Coordonnees(0, 13), plateau));
        assertTrue(JoueurTowa.estDansPlateau(new Coordonnees(5, 12), plateau));
        assertTrue(JoueurTowa.estDansPlateau(new Coordonnees(6, 7), plateau));
        assertFalse(JoueurTowa.estDansPlateau(new Coordonnees(18, 20), plateau));
        assertFalse(JoueurTowa.estDansPlateau(new Coordonnees(12, 17), plateau));
        assertFalse(JoueurTowa.estDansPlateau(new Coordonnees(18, 6), plateau));
    }

    @Test
    public void testVoisines() {

        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU4);
        char couleurJoueurN = Case.CAR_NOIR;
        Coordonnees coordTest1 = Coordonnees.depuisCars('b', 'K');
        assertEquals(2, JoueurTowa.voisines(coordTest1, plateau, couleurJoueurN));

        Coordonnees coordTest2 = Coordonnees.depuisCars('g', 'K');
        assertEquals(1, JoueurTowa.voisines(coordTest2, plateau, couleurJoueurN));
        
        Coordonnees coordTest3 = Coordonnees.depuisCars('a', 'E');
        assertEquals(1, JoueurTowa.voisines(coordTest3, plateau, couleurJoueurN));

        char couleurJoueurB = Case.CAR_BLANC;
        Coordonnees coordTest4 = Coordonnees.depuisCars('h', 'B');
        assertEquals(2, JoueurTowa.voisines(coordTest4, plateau, couleurJoueurB));

        Coordonnees coordTest5 = Coordonnees.depuisCars('b', 'G');
        assertEquals(1, JoueurTowa.voisines(coordTest5, plateau, couleurJoueurB));

        Coordonnees coordTest6 = Coordonnees.depuisCars('b', 'P');
        assertEquals(1, JoueurTowa.voisines(coordTest6, plateau, couleurJoueurB));

    }

    @Test
    public void testOuest() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU9);
        assertEquals(10, JoueurTowa.ouest(Case.CAR_NOIR, plateau));
        assertEquals(8, JoueurTowa.ouest(Case.CAR_BLANC, plateau));
    }

    @Test
    public void testEst() {
        
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU9);
        assertEquals(11, JoueurTowa.est(Case.CAR_NOIR, plateau));
        assertEquals(4, JoueurTowa.est(Case.CAR_BLANC, plateau));

        Case[][] plateau2 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU4);
        assertEquals(8, JoueurTowa.est(Case.CAR_NOIR, plateau2));
        assertEquals(9, JoueurTowa.est(Case.CAR_BLANC, plateau2));

        Case[][] plateau3 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU7);
        assertEquals(8, JoueurTowa.est(Case.CAR_NOIR, plateau3));
        assertEquals(8, JoueurTowa.est(Case.CAR_BLANC, plateau3));

        Case[][] plateau4 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        assertEquals(6, JoueurTowa.est(Case.CAR_NOIR, plateau4));
        assertEquals(9, JoueurTowa.est(Case.CAR_BLANC, plateau4));

    }

    @Test
    public void testNord() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU9);
        assertEquals(9, JoueurTowa.nord(Case.CAR_NOIR, plateau));
        assertEquals(6, JoueurTowa.nord(Case.CAR_BLANC, plateau));
    }

    @Test
    public void testSud() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU9);
        assertEquals(10, JoueurTowa.sud(Case.CAR_NOIR, plateau));
        assertEquals(8, JoueurTowa.sud(Case.CAR_BLANC, plateau));
    }

    @Test
    public void testNiveau() {
        Case case1 = new Case(Case.CAR_NOIR, 1, 3, ' ');
        Case case2 = new Case(Case.CAR_NOIR, 5, 3, ' ');
        Case case3 = new Case(Case.CAR_BLANC, 2, 0, ' ');
        assertEquals(4, niveau(case1));
        assertEquals(8, niveau(case2));
        assertEquals(2, niveau(case3));
    }

    @Test
    public void testSymetrique() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU11);
        Coordonnees coord1 = Coordonnees.depuisCars('a', 'A');
        Coordonnees coordTest1 = Coordonnees.depuisCars('p', 'P');
        assertEquals(plateau[coordTest1.ligne][coordTest1.colonne], JoueurTowa.symetrique(coord1, plateau));

        Coordonnees coord2 = Coordonnees.depuisCars('p', 'A');
        Coordonnees coordTest2 = Coordonnees.depuisCars('a', 'P');
        assertEquals(plateau[coordTest2.ligne][coordTest2.colonne], JoueurTowa.symetrique(coord2, plateau));

        Coordonnees coord3 = Coordonnees.depuisCars('g', 'H');
        Coordonnees coordTest3 = Coordonnees.depuisCars('j', 'I');
        assertEquals(plateau[coordTest3.ligne][coordTest3.colonne], JoueurTowa.symetrique(coord3, plateau));

        Coordonnees coord4 = Coordonnees.depuisCars('i', 'G');
        Coordonnees coordTest4 = Coordonnees.depuisCars('h', 'J');
        assertEquals(plateau[coordTest4.ligne][coordTest4.colonne], JoueurTowa.symetrique(coord4, plateau));

    }

    @Test
    public void testActionMagiePossible() {
        Case[][] plateau11 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU11);
        JoueurTowa joueur = new JoueurTowa();
        Coordonnees coord1 = Coordonnees.depuisCars('g', 'H');
        assertTrue(joueur.actionMagiePossible(plateau11, coord1, Case.CAR_BLANC));

        Coordonnees coord2 = Coordonnees.depuisCars('i', 'G');
        assertTrue(joueur.actionMagiePossible(plateau11, coord2, Case.CAR_BLANC));

        Coordonnees coord3 = Coordonnees.depuisCars('i', 'H');
        assertFalse(joueur.actionMagiePossible(plateau11, coord3, Case.CAR_BLANC));

        Coordonnees coord4 = Coordonnees.depuisCars('e', 'J');
        assertFalse(joueur.actionMagiePossible(plateau11, coord4, Case.CAR_BLANC));
        
        Case[][] plateau12 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU12);
        Coordonnees coord5 = Coordonnees.depuisCars('i', 'I');
        assertFalse(joueur.actionMagiePossible(plateau12, coord5, Case.CAR_BLANC));
        
        Coordonnees coord6 = Coordonnees.depuisCars('b', 'A');
        assertTrue(joueur.actionMagiePossible(plateau12, coord6, Case.CAR_NOIR));

    }

    /**
     * Un plateau de base, sous forme de chaîne. Pour construire une telle
     * chaîne depuis votre sortie.log, déclarez simplement : final String
     * MON_PLATEAU = ""; puis copiez le plateau depuis votre sortie.log, et
     * collez-le entre les guillemets. Puis Alt+Shift+f pour mettre en forme.
     */
    final String PLATEAU_NIVEAU1
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "o|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "p|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+";

    final String PLATEAU_NIVEAU2
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |   |   |   |B1 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|N1 |   |   |   |   |   |   |B1 |   |   |   |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |B1 |   |B1 |   |   |   |   |   |N5 |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |B1 |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|B1 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |B1 |   |   |   |   |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |   |N1 |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |N1 |N1 |   |   |   |   |   |   |   |   |   |N1 |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |N1 |   |   |   |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |N1 |   |   |   |   |N2 |   |   |   |   |B1 |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |N3 |B4 |B1 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |B1 |B2 |N1 |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |N1 |N1 |N2 |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "o|   |N1 |   |   |   |   |   |N1 |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "p|   |   |   |   |   |   |B1 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";

    final String PLATEAU_NIVEAU4
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|B2 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|N1 |   |   |   |   |   |   |B1 |   |   |   |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |B1 |   |B1 |   |   |   |   |   |N5 |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|B1 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |B1 |   |   |   |   |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |   |N1 |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |N1 |N1 |   |   |   |   |   |   |   |   |   |N1 |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |N1 |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |N1 |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |N3 |B4 |B1 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |B1 |B2 |N1 |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |N1 |N1 |N2 |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "o|   |N1 |   |   |   |N2 |   |N1 |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "p|   |   |   |   |   |   |B3 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";

    final String PLATEAU_NIVEAU7
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|B2 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |B1 |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|N1 |   |   |   |   |   |   |B1 |   |   |   |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |B1 |   |B1 |   |   |   |   |   |N5 |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|B1 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |   |   |   |   |   |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |   |N1 |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |N1 |N1 |   |   |   |   |   |   |   |   |   |N1 |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |N1 |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |N1 |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |N3 |B4 |B1 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |B1 |B2 |N1 |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |N1 |N1 |N2 |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "o|   |N1 |   |   |   |N2 |   |N1 |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "p|   |   |   |   |   |   |B3 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";

    final String PLATEAU_NIVEAU9
            = "  A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|N4 |   |   |   |   |   |   |   |   |   |   |N1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |   |   |B3 |   |N2 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |B4 |B3 |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|   |   |   |   |   |   |B1 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |   |   |   |   |   |   |   |N2 |   |N4 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "o|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "p|   |   |   |   |   |   |   |   |   |   |   |N4 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+";

    final String PLATEAU_NIVEAU10
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |B12|   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|B3 |   |N22|N11|B3 |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |B31|  4|B4 |   |   |  2|   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |B21|  2|   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|   |   |   |  4|   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |B3 |   |   |  3|   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |  3|   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |  4|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "o|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "p|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+";

    final String PLATEAU_NIVEAU11
            = "  A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|   |   |   |   |   |   |   |   |   |B13|   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |   |   |   |   |   |B12|   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |  3|   |   |N1 |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |   |   |   |   |   |B1 |B2 |   |   |B2 |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |   |   |B1 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "o|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "p|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+";

    final String PLATEAU_NIVEAU12
            = "  A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |   |   |N4 |B1 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|N1 |   |   |   |   |   |   |B1 |   |   |   |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |B1 |   |B1 |   |   |   |   |   |N1 |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |B1 |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|B1 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |   |   |N2 |   |B11|   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+E--+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |B2 |   |   |   |   |  2|   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+E--+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |B4 |   |  3|  3|   |N1 |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |N1 |N1 |   |   |   |   |B22|B1 |   |   |   |N1 |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |N1 |   |N2 |   |   |B1 |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |N1 |   |   |   |   |N2 |   |   |   |   |B1 |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|N1 |N4 |   |   |N3 |   |B1 |   |   |   |   |   |   |   |   |N1 |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |B1 |   |   |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |N1 |N1 |N2 |   |   |N1 |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "o|   |N1 |   |   |   |   |   |N1 |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "p|   |   |   |   |   |   |B1 |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+";
}
