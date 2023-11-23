package towa;

import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

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
        testActionsPossibles_niveau4();
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
        assertFalse(actionsPossibles.contient("Pck,28,20"));
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
        Case[][] plateau2 = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
//        assertTrue( joueur.posePossible(plateau2, Coordonnees.depuisCars('a', 'B'), Case.CAR_NOIR));
//        assertFalse( joueur.posePossible(plateau2, Coordonnees.depuisCars('a', 'G'), Case.CAR_NOIR));
//        assertTrue( joueur.posePossible(plateau2, Coordonnees.depuisCars('c', 'K'), Case.CAR_NOIR));
//        assertTrue( joueur.posePossible(plateau2, Coordonnees.depuisCars('a', 'N'), Case.CAR_BLANC));
//        assertFalse( joueur.posePossible(plateau2, Coordonnees.depuisCars('b', 'A'), Case.CAR_BLANC));
//        assertTrue( joueur.posePossible(plateau2, Coordonnees.depuisCars('b', 'H'), Case.CAR_BLANC));
        assertTrue(joueur.posePossible(plateau2, Coordonnees.depuisCars('m', 'F'), Case.CAR_BLANC));
        assertTrue(joueur.posePossible(plateau2, Coordonnees.depuisCars('b', 'H'), Case.CAR_BLANC));
        assertFalse(joueur.posePossible(plateau2, Coordonnees.depuisCars('l', 'F'), Case.CAR_BLANC));
        assertFalse(joueur.posePossible(plateau2, Coordonnees.depuisCars('c', 'K'), Case.CAR_NOIR));
        assertTrue(joueur.posePossible(plateau2, Coordonnees.depuisCars('l', 'E'), Case.CAR_NOIR));
        assertTrue(joueur.posePossible(plateau2, Coordonnees.depuisCars('j', 'H'), Case.CAR_NOIR));

        // à compléter...
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
        joueur.ajoutActionPose(Coordonnees.depuisCars('f', 'D'), actions,
                nbPions, Case.CAR_NOIR);
        // l'action est devenue possible
        assertTrue(actions.contient("PfD,1,0"));
        // une action possible mais qui n'a pas encore été ajoutée
        assertFalse(actions.contient("PbH,1,0"));
        // pour l'instant une seule action possible
        assertEquals(1, actions.nbActions);
        // ajout d'une deuxième action possible
        joueur.ajoutActionPose(Coordonnees.depuisCars('b', 'H'), actions,
                nbPions, Case.CAR_NOIR);
        // l'action a bien été ajoutée
        assertTrue(actions.contient("PbH,1,0"));
        // désormais, deux actions possibles
        assertEquals(2, actions.nbActions);

        // on crée le tableau d'actions et on en ajoute une
        joueur.ajoutActionPose(Coordonnees.depuisCars('f', 'D'), actions,
                nbPions, Case.CAR_BLANC);
        // l'action est devenue possible
        assertTrue(actions.contient("PfD,0,1"));
        // une action possible mais qui n'a pas encore été ajoutée
        assertFalse(actions.contient("PbH,0,1"));
        // pour l'instant une seule action possible
        assertEquals(3, actions.nbActions);
        // ajout d'une deuxième action possible
        joueur.ajoutActionPose(Coordonnees.depuisCars('b', 'H'), actions,
                nbPions, Case.CAR_BLANC);
        // l'action a bien été ajoutée
        assertTrue(actions.contient("PbH,0,1"));
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
    public void testadjacente() {

        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU4);
        Coordonnees coordTest1 = new Coordonnees(12, 5);
        NbPions nbPionsSansModif1 = JoueurTowa.nbPions(plateau);
        NbPions nbPions1 = JoueurTowa.nbPions(plateau);
        JoueurTowa.adjacente(coordTest1, plateau, nbPions1);
        assertEquals(nbPionsSansModif1.nbPionsNoirs - 3, nbPions1.nbPionsNoirs);

        Coordonnees coordTest2 = new Coordonnees(0, 0);
        NbPions nbPionsSansModif2 = JoueurTowa.nbPions(plateau);
        NbPions nbPions2 = JoueurTowa.nbPions(plateau);
        JoueurTowa.adjacente(coordTest2, plateau, nbPions2);
        assertEquals(nbPionsSansModif2.nbPionsNoirs - 1, nbPions2.nbPionsNoirs);

        Coordonnees coordTest3 = new Coordonnees(15, 6);
        NbPions nbPionsSansModif3 = JoueurTowa.nbPions(plateau);
        NbPions nbPions3 = JoueurTowa.nbPions(plateau);
        JoueurTowa.adjacente(coordTest3, plateau, nbPions3);
        assertEquals(nbPionsSansModif3.nbPionsNoirs - 3, nbPions3.nbPionsNoirs);

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
}
