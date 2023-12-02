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
        testActionsPossibles_niveau10();
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
//        Case[][] plateau1 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
//        assertTrue( joueur.posePossible(plateau1, Coordonnees.depuisCars('f', 'D'), Case.CAR_NOIR));
//        assertTrue( joueur.posePossible(plateau1, Coordonnees.depuisCars('h', 'E'), Case.CAR_NOIR));
        //Case[][] plateau2 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
//        assertTrue( joueur.posePossible(plateau2, Coordonnees.depuisCars('a', 'B'), Case.CAR_NOIR));
//        assertFalse( joueur.posePossible(plateau2, Coordonnees.depuisCars('a', 'G'), Case.CAR_NOIR));
//        assertTrue( joueur.posePossible(plateau2, Coordonnees.depuisCars('c', 'K'), Case.CAR_NOIR));
//        assertTrue( joueur.posePossible(plateau2, Coordonnees.depuisCars('a', 'N'), Case.CAR_BLANC));
//        assertFalse( joueur.posePossible(plateau2, Coordonnees.depuisCars('b', 'A'), Case.CAR_BLANC));
//        assertTrue( joueur.posePossible(plateau2, Coordonnees.depuisCars('b', 'H'), Case.CAR_BLANC));
//        assertTrue(joueur.posePossible(plateau2, Coordonnees.depuisCars('m', 'F'), Case.CAR_BLANC));
//        assertTrue(joueur.posePossible(plateau2, Coordonnees.depuisCars('b', 'H'), Case.CAR_BLANC));
//        assertFalse(joueur.posePossible(plateau2, Coordonnees.depuisCars('l', 'F'), Case.CAR_BLANC));
//        assertFalse(joueur.posePossible(plateau2, Coordonnees.depuisCars('c', 'K'), Case.CAR_NOIR));
//        assertTrue(joueur.posePossible(plateau2, Coordonnees.depuisCars('l', 'E'), Case.CAR_NOIR));
//        assertTrue(joueur.posePossible(plateau2, Coordonnees.depuisCars('j', 'H'), Case.CAR_NOIR));
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
    }

    @Test
    public void testActivePossible() {
        JoueurTowa joueur = new JoueurTowa();
//        Case[][] plateau1 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU1);
//        assertTrue( joueur.posePossible(plateau1, Coordonnees.depuisCars('f', 'D'), Case.CAR_NOIR));
//        assertTrue( joueur.posePossible(plateau1, Coordonnees.depuisCars('h', 'E'), Case.CAR_NOIR));
        Case[][] plateau4 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU4);
//        assertTrue( joueur.posePossible(plateau2, Coordonnees.depuisCars('a', 'B'), Case.CAR_NOIR));
//        assertFalse( joueur.posePossible(plateau2, Coordonnees.depuisCars('a', 'G'), Case.CAR_NOIR));
//        assertTrue( joueur.posePossible(plateau2, Coordonnees.depuisCars('c', 'K'), Case.CAR_NOIR));
//        assertTrue( joueur.posePossible(plateau2, Coordonnees.depuisCars('a', 'N'), Case.CAR_BLANC));
//        assertFalse( joueur.posePossible(plateau2, Coordonnees.depuisCars('b', 'A'), Case.CAR_BLANC));
//        assertTrue( joueur.posePossible(plateau2, Coordonnees.depuisCars('b', 'H'), Case.CAR_BLANC));
        assertTrue(joueur.poseActivePossible(plateau4, Coordonnees.depuisCars('a', 'A'), Case.CAR_BLANC));
        assertTrue(joueur.poseActivePossible(plateau4, Coordonnees.depuisCars('m', 'F'), Case.CAR_BLANC));
        assertTrue(joueur.poseActivePossible(plateau4, Coordonnees.depuisCars('c', 'L'), Case.CAR_BLANC));
        assertFalse(joueur.poseActivePossible(plateau4, Coordonnees.depuisCars('j', 'A'), Case.CAR_BLANC));
        assertFalse(joueur.poseActivePossible(plateau4, Coordonnees.depuisCars('i', 'B'), Case.CAR_BLANC));
        assertFalse(joueur.poseActivePossible(plateau4, Coordonnees.depuisCars('k', 'F'), Case.CAR_BLANC));

        assertTrue(joueur.poseActivePossible(plateau4, Coordonnees.depuisCars('b', 'A'), Case.CAR_NOIR));
        assertTrue(joueur.poseActivePossible(plateau4, Coordonnees.depuisCars('c', 'K'), Case.CAR_NOIR));
        assertTrue(joueur.poseActivePossible(plateau4, Coordonnees.depuisCars('o', 'F'), Case.CAR_NOIR));
        assertFalse(joueur.poseActivePossible(plateau4, Coordonnees.depuisCars('g', 'C'), Case.CAR_NOIR));
        assertFalse(joueur.poseActivePossible(plateau4, Coordonnees.depuisCars('a', 'B'), Case.CAR_NOIR));
        assertFalse(joueur.poseActivePossible(plateau4, Coordonnees.depuisCars('c', 'C'), Case.CAR_NOIR));

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
    public void testAjoutPoseActive() {
        JoueurTowa joueur = new JoueurTowa();
        ActionsPossibles actions = new ActionsPossibles();
        NbPions nbPions = new NbPions(10, 10);
        // pour l'instant pas d'action possible
        assertEquals(0, actions.nbActions);
        // on crée le tableau d'actions et on en ajoute une
        joueur.ajoutPoseActive(Coordonnees.depuisCars('f', 'D'), actions,
                nbPions, Case.CAR_NOIR, 4);
        // l'action est devenue possible
        assertTrue(actions.contient("AfD,10,6"));
        //une action possible mais qui n'a pas encore été ajoutée
        assertFalse(actions.contient("AbH,1,0"));
        // pour l'instant une seule action possible
        assertEquals(1, actions.nbActions);
        // ajout d'une deuxième action possible
        joueur.ajoutPoseActive(Coordonnees.depuisCars('b', 'H'), actions,
                nbPions, Case.CAR_NOIR, 1);
        // l'action a bien été ajoutée
        assertTrue(actions.contient("AbH,10,9"));
        // désormais, deux actions possibles
        assertEquals(2, actions.nbActions);

        // on crée le tableau d'actions et on en ajoute une
        joueur.ajoutPoseActive(Coordonnees.depuisCars('f', 'D'), actions,
                nbPions, Case.CAR_BLANC, 3);
        // l'action est devenue possible
        assertTrue(actions.contient("AfD,7,10"));
        // une action possible mais qui n'a pas encore été ajoutée
        assertFalse(actions.contient("AbH,0,1"));
        // pour l'instant une seule action possible
        assertEquals(3, actions.nbActions);
        // ajout d'une deuxième action possible
        joueur.ajoutPoseActive(Coordonnees.depuisCars('b', 'H'), actions,
                nbPions, Case.CAR_BLANC, 2);
        // l'action a bien été ajoutée
        assertTrue(actions.contient("AbH,8,10"));
        // désormais, deux actions possibles
        assertEquals(4, actions.nbActions);

    }

    @Test
    public void testAjoutPoseFusion() {
        JoueurTowa joueur = new JoueurTowa();
        ActionsPossibles actions = new ActionsPossibles();
        NbPions nbPions = new NbPions(10, 10);
        // pour l'instant pas d'action possible
        assertEquals(0, actions.nbActions);
        // on crée le tableau d'actions et on en ajoute une
        joueur.ajoutPoseFusion(Coordonnees.depuisCars('f', 'D'), actions,
                nbPions, Case.CAR_NOIR, 4);
        // l'action est devenue possible
        assertTrue(actions.contient("FfD,6,10"));
        //une action possible mais qui n'a pas encore été ajoutée
        assertFalse(actions.contient("FbH,1,0"));
        // pour l'instant une seule action possible
        assertEquals(1, actions.nbActions);
        // ajout d'une deuxième action possible
        joueur.ajoutPoseFusion(Coordonnees.depuisCars('b', 'H'), actions,
                nbPions, Case.CAR_NOIR, 1);
        // l'action a bien été ajoutée
        assertTrue(actions.contient("FbH,9,10"));
        // désormais, deux actions possibles
        assertEquals(2, actions.nbActions);

        // on crée le tableau d'actions et on en ajoute une
        joueur.ajoutPoseFusion(Coordonnees.depuisCars('f', 'D'), actions,
                nbPions, Case.CAR_BLANC, 3);
        // l'action est devenue possible
        assertTrue(actions.contient("FfD,10,7"));
        // une action possible mais qui n'a pas encore été ajoutée
        assertFalse(actions.contient("FbH,0,1"));
        // pour l'instant une seule action possible
        assertEquals(3, actions.nbActions);
        // ajout d'une deuxième action possible
        joueur.ajoutPoseFusion(Coordonnees.depuisCars('b', 'H'), actions,
                nbPions, Case.CAR_BLANC, 2);
        // l'action a bien été ajoutée
        assertTrue(actions.contient("FbH,10,8"));
        // désormais, deux actions possibles
        assertEquals(4, actions.nbActions);

    }

    @Test
    public void testAjoutKamikaze() {
        JoueurTowa joueur = new JoueurTowa();
        ActionsPossibles actions = new ActionsPossibles();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU9);
        NbPions nbPions = JoueurTowa.nbPions(plateau);
        // pour l'instant pas d'action possible
        assertEquals(0, actions.nbActions);
        // on crée le tableau d'actions et on en ajoute une
        joueur.ajoutKamikaze(actions, nbPions, JoueurTowa.OUEST, plateau);
        // l'action est devenue possible
        assertTrue(actions.contient("CO,7,3"));
        // pour l'instant une seule action possible
        assertEquals(1, actions.nbActions);
        // ajout d'une deuxième action possible
        joueur.ajoutKamikaze(actions, nbPions, JoueurTowa.EST, plateau);
        // l'action a bien été ajoutée
        assertTrue(actions.contient("CE,6,7"));
        // désormais, deux actions possibles
        assertEquals(2, actions.nbActions);
        // on crée le tableau d'actions et on en ajoute une
        joueur.ajoutKamikaze(actions, nbPions, JoueurTowa.NORD, plateau);
        // l'action est devenue possible
        assertTrue(actions.contient("CN,8,5"));
        // pour l'instant une seule action possible
        assertEquals(3, actions.nbActions);
        // ajout d'une deuxième action possible
        joueur.ajoutKamikaze(actions, nbPions, JoueurTowa.SUD, plateau);
        // l'action a bien été ajoutée
        assertTrue(actions.contient("CS,7,3"));
        // désormais, deux actions possibles
        assertEquals(4, actions.nbActions);

    }

    @Test
    public void testAdjacente() {

        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU7);
        Coordonnees coordTest1 = Coordonnees.depuisCars('l', 'F');
        assertEquals(5, JoueurTowa.adjacente(coordTest1, plateau, ACTIVATION));

        Coordonnees coordTest2 = Coordonnees.depuisCars('c', 'K');
        assertEquals(3, JoueurTowa.adjacente(coordTest2, plateau, ACTIVATION));

        Coordonnees coordTest3 = Coordonnees.depuisCars('p', 'G');
        assertEquals(5, JoueurTowa.adjacente(coordTest3, plateau, ACTIVATION));

        Coordonnees coordTest4 = Coordonnees.depuisCars('l', 'F');
        assertEquals(5, JoueurTowa.adjacente(coordTest4, plateau, ACTIVATION));

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
    public void testestDansTableau() {
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
        char couleurJoueurN = 'N';
        Coordonnees coordTest1 = new Coordonnees(1, 10);
        assertEquals(2, JoueurTowa.voisines(coordTest1, plateau, couleurJoueurN));

        Coordonnees coordTest2 = new Coordonnees(6, 10);
        assertEquals(1, JoueurTowa.voisines(coordTest2, plateau, couleurJoueurN));

        Coordonnees coordTest3 = new Coordonnees(0, 4);
        assertEquals(1, JoueurTowa.voisines(coordTest3, plateau, couleurJoueurN));

        char couleurJoueurB = 'B';
        Coordonnees coordTest4 = new Coordonnees(7, 1);
        assertEquals(2, JoueurTowa.voisines(coordTest4, plateau, couleurJoueurB));

        Coordonnees coordTest5 = new Coordonnees(1, 6);
        assertEquals(1, JoueurTowa.voisines(coordTest5, plateau, couleurJoueurB));

        Coordonnees coordTest6 = new Coordonnees(1, 15);
        assertEquals(1, JoueurTowa.voisines(coordTest6, plateau, couleurJoueurB));

    }

    @Test
    public void testOuest() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU9);
        assertEquals(10, JoueurTowa.ouest('N', plateau));
        assertEquals(8, JoueurTowa.ouest('B', plateau));
    }

    @Test
    public void testEst() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU9);
        assertEquals(11, JoueurTowa.est('N', plateau));
        assertEquals(4, JoueurTowa.est('B', plateau));

        Case[][] plateau2 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU4);
        assertEquals(8, JoueurTowa.est('N', plateau2));
        assertEquals(9, JoueurTowa.est('B', plateau2));

        Case[][] plateau3 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU7);
        assertEquals(8, JoueurTowa.est('N', plateau3));
        assertEquals(8, JoueurTowa.est('B', plateau3));

        Case[][] plateau4 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        assertEquals(6, JoueurTowa.est('N', plateau4));
        assertEquals(9, JoueurTowa.est('B', plateau4));

    }

    @Test
    public void testNord() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU9);
        assertEquals(9, JoueurTowa.nord('N', plateau));
        assertEquals(6, JoueurTowa.nord('B', plateau));
    }

    @Test
    public void testSud() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU9);
        assertEquals(10, JoueurTowa.sud('N', plateau));
        assertEquals(8, JoueurTowa.sud('B', plateau));
    }
    
    @Test
    public void testNiveau() {
        Case case1 =new Case('N',1,3,' ');
        Case case2 =new Case('N',5,3,' ');
        Case case3 =new Case('B',2,0,' ');
        assertEquals(4,niveau(case1));
        assertEquals(8,niveau(case2));
        assertEquals(2,niveau(case3));
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
}
