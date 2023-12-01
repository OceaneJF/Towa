package towa;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Joueur implémentant les actions possibles à partir d'un plateau, pour un
 * niveau donné.
 */
public class JoueurTowa implements IJoueurTowa {

    public static final boolean FUSION = false;
    public static final boolean ACTIVATION = true;
    public static final int OUEST = 1;
    public static final int EST = 2;
    public static final int NORD = 3;
    public static final int SUD = 4;

    /**
     * Cette méthode renvoie, pour un plateau donné et un joueur donné, toutes
     * les actions possibles pour ce joueur.
     *
     * @param plateau le plateau considéré
     * @param couleurJoueur couleur du joueur
     * @param niveau le niveau de la partie à jouer
     * @return l'ensemble des actions possibles
     */
    @Override
    public String[] actionsPossibles(Case[][] plateau, char couleurJoueur, int niveau) {
        // afficher l'heure de lancement
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        System.out.println("actionsPossibles : lancement le " + format.format(new Date()));
        // se préparer à stocker les actions possibles
        ActionsPossibles actions = new ActionsPossibles();
        // on compte le nombre de pions sur le plateau avant action
        NbPions nbPions = nbPions(plateau);
        // pour chaque ligne
        for (int lig = 0; lig < Coordonnees.NB_LIGNES; lig++) {
            // pour chaque colonne
            for (int col = 0; col < Coordonnees.NB_COLONNES; col++) {
                Coordonnees coord = new Coordonnees(lig, col);
                // si la pose d'un pion de cette couleur est possible sur cette case
                if (posePossible(plateau, coord, couleurJoueur)) {
                    // on ajoute l'action dans les actions possibles
                    ajoutActionPose(coord, actions, nbPions, couleurJoueur, voisines(coord, plateau, couleurJoueur));
                }
                if (poseActivePossible(plateau, coord, couleurJoueur)) {
                    ajoutPoseActive(coord, actions, nbPions, couleurJoueur, adjacente(coord, plateau, ACTIVATION));
                    ajoutPoseFusion(coord, actions, nbPions, couleurJoueur, adjacente(coord, plateau, FUSION));
                }
            }
        }
        int[] directions = {OUEST, EST, NORD, SUD};
        for (int direction : directions) {
            ajoutKamikaze(actions, nbPions, direction, plateau);
        }
        System.out.println("actionsPossibles : fin");
        return actions.nettoyer();
    }

    /**
     * Indique s'il est possible de poser un pion sur une case pour ce plateau,
     * ce joueur, dans ce niveau.
     *
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @param couleur couleur du joueur
     * @return vrai ssi la pose d'un pion sur cette case est autorisée dans ce
     * niveau
     */
    boolean posePossible(Case[][] plateau, Coordonnees coord, char couleur) {
        return (plateau[coord.ligne][coord.colonne].couleur == couleur
                || plateau[coord.ligne][coord.colonne].couleur == Case.CAR_VIDE)
                && plateau[coord.ligne][coord.colonne].hauteur < 4; // TODO à vous de jouer !
    }

    /**
     * Indique s'il est possible d'activer une tour sur une case pour ce
     * plateau, ce joueur, dans ce niveau.
     *
     * @param plateau le plateau
     * @param coord coordonnées de la case à considérer
     * @param couleur couleur du joueur
     * @return vrai ssi l'activation d'une tour sur cette case est autorisée
     * dans ce niveau
     */
    boolean poseActivePossible(Case[][] plateau, Coordonnees coord, char couleur) {
        return plateau[coord.ligne][coord.colonne].couleur == couleur && plateau[coord.ligne][coord.colonne].couleur != Case.CAR_VIDE;
    }

    /**
     * Nombre de pions sur le plateau, de chaque couleur.
     *
     * @param plateau le plateau
     * @return le nombre de pions sur le plateau, de chaque couleur
     */
    static NbPions nbPions(Case[][] plateau) {
        int nbPionsNoirs = 0;
        int nbPionsBlancs = 0;
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau.length; j++) {
                if (plateau[i][j].tourPresente()) {
                    if (plateau[i][j].couleur == 'N') {
                        nbPionsNoirs += plateau[i][j].hauteur;
                    } else {
                        nbPionsBlancs += plateau[i][j].hauteur;
                    }
                }
            }
        }
        return new NbPions(nbPionsNoirs, nbPionsBlancs);
    }

    /**
     * Ajout d'une action de pose dans l'ensemble des actions possibles.
     *
     * @param coord coordonnées de la case où poser un pion
     * @param actions l'ensemble des actions possibles (en construction)
     * @param nbPions le nombre de pions par couleur sur le plateau avant de
     * jouer l'action
     * @param couleur la couleur du pion à ajouter
     */
    void ajoutActionPose(Coordonnees coord, ActionsPossibles actions,
            NbPions nbPions, char couleur, int ajoutHauteur) {
        String action;
        if (couleur == Case.CAR_NOIR) {
            action = "P" + coord.carLigne() + coord.carColonne() + ","
                    + (nbPions.nbPionsNoirs + ajoutHauteur) + ","
                    + (nbPions.nbPionsBlancs);
        } else {
            action = "P" + coord.carLigne() + coord.carColonne() + ","
                    + (nbPions.nbPionsNoirs) + ","
                    + (nbPions.nbPionsBlancs + ajoutHauteur);
        }
        actions.ajouterAction(action);
    }

    /**
     * Ajout d'une action d'activation dans l'ensemble des actions possibles.
     *
     * @param coord coordonnées de la case où activer un pion
     * @param actions l'ensemble des actions possibles (en construction)
     * @param nbPions le nombre de pions par couleur sur le plateau avant
     * l'activation
     * @param couleur la couleur du pion que l'on veut activer
     * @param decompte le nombre de pions a supprimer suite à l'activation
     */
    void ajoutPoseActive(Coordonnees coord, ActionsPossibles actions,
            NbPions nbPions, char couleur, int decompte) {
        String action;
        if (couleur == Case.CAR_NOIR) {
            action = "A" + coord.carLigne() + coord.carColonne() + ","
                    + (nbPions.nbPionsNoirs) + ","
                    + (nbPions.nbPionsBlancs - decompte);
        } else {
            action = "A" + coord.carLigne() + coord.carColonne() + ","
                    + (nbPions.nbPionsNoirs - decompte) + ","
                    + (nbPions.nbPionsBlancs);
        }
        actions.ajouterAction(action);
    }

    /**
     * Ajout d'une action chat kamikaze dans l'ensembles des actions possible
     *
     * @param actions l'ensemble des actions possibles (en construction)
     * @param nbPions le nombre de pions par couleur sur le plateau avant
     * l'action chat kamikaze
     * @param direction la direction de l'action chat kamikaze
     * @param plateau le plateau de jeu
     */
    void ajoutKamikaze(ActionsPossibles actions,
            NbPions nbPions, int direction, Case[][] plateau) {
        String action = null;
        switch (direction) {
            case 1:
                action = "CO,"
                        + (nbPions.nbPionsNoirs - ouest(Case.CAR_NOIR, plateau)) + ","
                        + (nbPions.nbPionsBlancs - ouest(Case.CAR_BLANC, plateau));
                break;
            case 2:
                action = "CE,"
                        + (nbPions.nbPionsNoirs - est(Case.CAR_NOIR, plateau)) + ","
                        + (nbPions.nbPionsBlancs - est(Case.CAR_BLANC, plateau));
                break;
            case 3:
                action = "CN,"
                        + (nbPions.nbPionsNoirs - nord(Case.CAR_NOIR, plateau)) + ","
                        + (nbPions.nbPionsBlancs - nord(Case.CAR_BLANC, plateau));
                break;
            case 4:
                action = "CS,"
                        + (nbPions.nbPionsNoirs - sud(Case.CAR_NOIR, plateau)) + ","
                        + (nbPions.nbPionsBlancs - sud(Case.CAR_BLANC, plateau));
                break;
        }
        actions.ajouterAction(action);
    }

    /**
     * Ajout d'une action de fusion dans l'ensemble des actions possibles.
     *
     * @param coord coordonnées de la case où fusionner un pion
     * @param actions l'ensemble des actions possibles
     * @param nbPions le nombre de pions par couleur sur le plateau avant la
     * fusion
     * @param couleur la couleur du pion que l'on veut fusionner
     * @param decompte le nombre de pions a supprimer suite à la fusion
     */
    void ajoutPoseFusion(Coordonnees coord, ActionsPossibles actions,
            NbPions nbPions, char couleur, int decompte) {
        String action;
        if (couleur == Case.CAR_NOIR) {
            action = "F" + coord.carLigne() + coord.carColonne() + ","
                    + (nbPions.nbPionsNoirs - decompte) + ","
                    + (nbPions.nbPionsBlancs);
        } else {
            action = "F" + coord.carLigne() + coord.carColonne() + ","
                    + (nbPions.nbPionsNoirs) + ","
                    + (nbPions.nbPionsBlancs - decompte);
        }
        actions.ajouterAction(action);
    }

    /**
     * Cette méthode permet de vérifier si les coordonnées passé en paramètre
     * sont dans le plateau
     *
     * @param coord les coordonnée d'une case
     * @param plateau le plateau de jeu
     * @return vrai si les coordonnées sont dans l'intervalle du plateau faux
     * sinon
     */
    static boolean estDansPlateau(Coordonnees coord, Case[][] plateau) {
        return coord.ligne >= 0 && coord.ligne < plateau.length && coord.colonne >= 0 && coord.colonne < plateau.length;
    }

    /**
     * Cette méthode permet de déterminer le nombre de pions de l'adversaire qui
     * sont éliminés lorsque l'on active une tour
     *
     * @param coord les coordonnées de la tour que l'on souhaite activer
     * @param plateau le plateau du jeu sur lequel se trouve la tour
     * @return le nombre de pions de l'adversaire qui sont éliminés lorsque l'on
     * active la tour dont les coordonnées sont passé en parametre
     */
    static int adjacente(Coordonnees coord, Case[][] plateau, boolean action) {
        int decompte = 0;
        if (action) {
            decompte = diagonaleActive(coord, plateau) + colonneActive(coord, plateau) + ligneActive(coord, plateau);
        } else {
            int totale = diagonaleFusion(coord, plateau) + colonneFusion(coord, plateau) + ligneFusion(coord, plateau);
            if (plateau[coord.ligne][coord.colonne].hauteur >= 4) {
                decompte = totale;
            } else if (totale != 0 && !((totale + plateau[coord.ligne][coord.colonne].hauteur) <= 4)) {
                decompte = (diagonaleFusion(coord, plateau) + colonneFusion(coord, plateau) + ligneFusion(coord, plateau)) - (4 - plateau[coord.ligne][coord.colonne].hauteur);
            }
        }
        return decompte;
    }

    /**
     * Cette méthode permet de déterminber le nombre de pions adversaires qui se
     * trouve sur les diagonales adjacentes de la tour dont les coordonnées sont
     * passé en parametre et qui sont moins haute que celui-ci
     *
     * @param coord les coordonnées de la tour que l'on souhaite activer
     * @param plateau le plateau du jeu sur lequel se trouve la tour
     * @return le nombre de pions adversaires qui se trouve sur les diagonales
     * adjacentes de la tour que l'on souhaite activer et dont la hauteur est
     * moins haute que celle-ci
     */
    static int diagonaleActive(Coordonnees coord, Case[][] plateau) {
        int decompte = 0;
        int[][] direction = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int i = 0; i < direction.length; i++) {
            Coordonnees suivante = new Coordonnees(coord.ligne + direction[i][0], coord.colonne + direction[i][1]);
            if (estDansPlateau(suivante, plateau)) {

                if (plateau[suivante.ligne][suivante.colonne].tourPresente()
                        && plateau[suivante.ligne][suivante.colonne].couleur != plateau[coord.ligne][coord.colonne].couleur
                        && plateau[suivante.ligne][suivante.colonne].hauteur < plateau[coord.ligne][coord.colonne].hauteur) {

                    decompte += plateau[suivante.ligne][suivante.colonne].hauteur;
                }
            }
        }
        return decompte;
    }

    /**
     * Cette méthode permet de déterminer le nombre de pions amis qui se trouve
     * sur les diagonales adjacentes de la tour dont les coordonnées sont
     * passées en parametre
     *
     * @param coord les coordonnées de la tour que l'on souhaite activer
     * @param plateau le plateau du jeu sur lequel se trouve la tour
     * @return le nombre de pions amis qui se trouvent sur les diagonales
     * adjacentes de la tour que l'on souhaite fusionner
     */
    static int diagonaleFusion(Coordonnees coord, Case[][] plateau) {
        int decompte = 0;
        int[][] direction = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int i = 0; i < direction.length; i++) {
            Coordonnees suivante = new Coordonnees(coord.ligne + direction[i][0], coord.colonne + direction[i][1]);
            if (estDansPlateau(suivante, plateau)) {

                if (plateau[suivante.ligne][suivante.colonne].tourPresente()
                        && plateau[suivante.ligne][suivante.colonne].couleur == plateau[coord.ligne][coord.colonne].couleur) {

                    decompte += plateau[suivante.ligne][suivante.colonne].hauteur;
                }
            }
        }
        return decompte;
    }

    /**
     * Cette méthode permet de déterminber le nombre de pions adversaires qui se
     * trouve sur la meme colonne et le plus proche de chaque cotés de la tour
     * dont les coordonnées sont passé en parametre et dont la hauteur est moins
     * haute que celle-ci
     *
     * @param coord les coordonnées de la tour que l'on souhaite activer
     * @param plateau le plateau du jeu sur lequel se trouve la tour
     * @return le nombre de pions adversaires qui se trouve sur la meme colonne
     * et le plus proche de la tour que l'on souhaite activer et dont la hauteur
     * est moins haute que celle-ci
     */
    static int colonneActive(Coordonnees coord, Case[][] plateau) {
        int decompte = 0;
        int i = coord.ligne - 1;
        Coordonnees coord1 = new Coordonnees(coord.ligne - 1, coord.colonne);
        if (estDansPlateau(coord1, plateau)) {
            while (!plateau[i][coord.colonne].tourPresente() && i > 0) {
                i--;
            }
            if (plateau[i][coord.colonne].couleur != plateau[coord.ligne][coord.colonne].couleur
                    && plateau[i][coord.colonne].hauteur < plateau[coord.ligne][coord.colonne].hauteur) {
                decompte += plateau[i][coord.colonne].hauteur;
            }
        }
        int j = coord.ligne + 1;
        Coordonnees coord2 = new Coordonnees(coord.ligne + 1, coord.colonne);
        if (estDansPlateau(coord2, plateau)) {
            while (!plateau[j][coord.colonne].tourPresente() && j < plateau.length - 1) {
                j++;
            }
            if (plateau[j][coord.colonne].couleur != plateau[coord.ligne][coord.colonne].couleur
                    && plateau[j][coord.colonne].hauteur < plateau[coord.ligne][coord.colonne].hauteur) {
                decompte += plateau[j][coord.colonne].hauteur;
            }
        }
        return decompte;
    }

    /**
     * Cette méthode permet de déterminer combien de pions amis proches sont
     * présent sur la meme colonne que la tour dont les coordonnées sont passées
     * en paramètre
     *
     * @param coord les coordonnées de la tour que l'on souhaite fusionner
     * @param plateau le plateau du jeu sur lequel se trouve la tour
     * @return le nombre de pions amis proches qui sont présent sur la meme
     * colonne que la tour que l'on souhaite fusionner
     */
    static int colonneFusion(Coordonnees coord, Case[][] plateau) {
        int decompte = 0;
        int i = coord.ligne - 1;
        Coordonnees coord1 = new Coordonnees(coord.ligne - 1, coord.colonne);
        if (estDansPlateau(coord1, plateau)) {
            while (!plateau[i][coord.colonne].tourPresente() && i > 0) {
                i--;
            }
            if (plateau[i][coord.colonne].couleur == plateau[coord.ligne][coord.colonne].couleur) {
                decompte += plateau[i][coord.colonne].hauteur;
            }
        }
        int j = coord.ligne + 1;
        Coordonnees coord2 = new Coordonnees(coord.ligne + 1, coord.colonne);
        if (estDansPlateau(coord2, plateau)) {
            while (!plateau[j][coord.colonne].tourPresente() && j < plateau.length - 1) {
                j++;
            }
            if (plateau[j][coord.colonne].couleur == plateau[coord.ligne][coord.colonne].couleur) {
                decompte += plateau[j][coord.colonne].hauteur;
            }
        }
        return decompte;
    }

    /**
     * Cette méthode permet de déterminber le nombre de pions adversaires qui se
     * trouve sur la meme ligne et le plus proche des deux cotés de la tour dont
     * les coordonnées sont passé en parametre et dont la hauteur est moins
     * haute que celle-ci
     *
     * @param coord les coordonnées de la tour que l'on souhaite activer
     * @param plateau le plateau du jeu sur lequel se trouve la tour
     * @return le nombre de pions adversaires qui se trouve sur la meme ligne et
     * le plus proche de la tour que l'on souhaite activer et dont la hauteur
     * est moins haute que celle-ci
     */
    static int ligneActive(Coordonnees coord, Case[][] plateau) {
        int decompte = 0;
        int i = coord.colonne - 1;
        Coordonnees coord1 = new Coordonnees(coord.ligne, coord.colonne - 1);
        if (estDansPlateau(coord1, plateau)) {
            while (!plateau[coord.ligne][i].tourPresente() && i > 0) {
                i--;
            }
            if (plateau[coord.ligne][i].couleur != plateau[coord.ligne][coord.colonne].couleur
                    && plateau[coord.ligne][i].hauteur < plateau[coord.ligne][coord.colonne].hauteur) {
                decompte += plateau[coord.ligne][i].hauteur;
            }
        }
        int j = coord.colonne + 1;
        Coordonnees coord2 = new Coordonnees(coord.ligne, coord.colonne + 1);
        if (estDansPlateau(coord2, plateau)) {
            while (!plateau[coord.ligne][j].tourPresente() && j < plateau.length - 1) {
                j++;
            }
            if (plateau[coord.ligne][j].couleur != plateau[coord.ligne][coord.colonne].couleur
                    && plateau[coord.ligne][j].hauteur < plateau[coord.ligne][coord.colonne].hauteur) {
                decompte += plateau[coord.ligne][j].hauteur;
            }
        }
        return decompte;
    }

    /**
     * Cette méthode permet de déterminer combien de pions amis proches sont
     * présent sur la meme ligne que la tour dont les coordonnées sont passées
     * en paramètre
     *
     * @param coord les coordonnées de la tour que l'on souhaite fusionner
     * @param plateau le plateau du jeu sur lequel se trouve la tour
     * @return le nombre de pions amis proches qui sont présent sur la meme
     * ligne que la tour que l'on souhaite fusionner
     */
    static int ligneFusion(Coordonnees coord, Case[][] plateau) {
        int decompte = 0;
        int i = coord.colonne - 1;
        Coordonnees coord1 = new Coordonnees(coord.ligne, coord.colonne - 1);
        if (estDansPlateau(coord1, plateau)) {
            while (!plateau[coord.ligne][i].tourPresente() && i > 0) {
                i--;
            }
            if (plateau[coord.ligne][i].couleur == plateau[coord.ligne][coord.colonne].couleur) {
                decompte += plateau[coord.ligne][i].hauteur;
            }
        }
        int j = coord.colonne + 1;
        Coordonnees coord2 = new Coordonnees(coord.ligne, coord.colonne + 1);
        if (estDansPlateau(coord2, plateau)) {
            while (!plateau[coord.ligne][j].tourPresente() && j < plateau.length - 1) {
                j++;
            }
            if (plateau[coord.ligne][j].couleur == plateau[coord.ligne][coord.colonne].couleur) {
                decompte += plateau[coord.ligne][j].hauteur;
            }
        }
        return decompte;
    }

    /**
     * Cette méthode renvoie 2 si les coordonnées passées en paramètre
     * corresponde à un case vide voisines d'un pions adversaire et 1 sinon
     *
     * @param coord les coordonnées de la case ou on souhaite poser un pions
     * @param plateau le plateau de jeu
     * @param couleurJoueur la couleur du joueur
     * @return 2 si les coordonnées passées en paramètre corresponde à un case
     * vide voisines d'un pions adversaire et 1 sinon
     */
    static int voisines(Coordonnees coord, Case[][] plateau, char couleurJoueur) {
        int ajoutHauteur = 1;
        int[][] direction = {{1, 1}, {1, 0}, {1, -1}, {0, 1}, {0, -1}, {-1, 1}, {-1, 0}, {-1, -1}};
        for (int i = 0; i < direction.length; i++) {
            Coordonnees suivante = new Coordonnees(coord.ligne + direction[i][0], coord.colonne + direction[i][1]);
            if (estDansPlateau(suivante, plateau)) {
                if (plateau[coord.ligne][coord.colonne].couleur == Case.CAR_VIDE && plateau[suivante.ligne][suivante.colonne].tourPresente()
                        && plateau[suivante.ligne][suivante.colonne].couleur != couleurJoueur) {
                    ajoutHauteur = 2;
                }
            }
        }
        return ajoutHauteur;
    }

    /**
     * Cette fonction permet de déterminer le nombre de pions de la meme couleur
     * du joueur qui sont éliminé lors de l'action chat kamikase avec la
     * direction ouest CO
     *
     * @param couleurJoueur la couleur du joueur
     * @param plateau le plateau de jeu
     * @return le nombre de pions de la meme couleur du joueur qui sont éliminé
     * lors de l'action chat kamikase avec la direction ouest CO
     */
    static int ouest(char couleurJoueur, Case[][] plateau) {
        int decompte = 0;
        for (int i = 0; i < plateau.length; i++) {
            int j = 0;
            while (j < plateau.length-1 && plateau[i][j].couleur == Case.CAR_VIDE) {
                j++;
            }
            if (plateau[i][j].couleur == couleurJoueur) {
                decompte += plateau[i][j].hauteur;
            }
        }
        return decompte;
    }

    /**
     * Cette fonction permet de déterminer le nombre de pions de la meme couleur
     * du joueur qui sont éliminé lors de l'action chat kamikase avec la
     * direction est CE
     *
     * @param couleurJoueur la couleur du joueur
     * @param plateau le plateau de jeu
     * @return le nombre de pions de la meme couleur du joueur qui sont éliminé
     * lors de l'action chat kamikase avec la direction est CE
     */
    static int est(char couleurJoueur, Case[][] plateau) {
        int decompte = 0;
        for (int i = 0; i < plateau.length; i++) {
            int j = plateau.length - 1;
            while (j > 0 && plateau[i][j].couleur == Case.CAR_VIDE) {
                j--;
            }
            if (plateau[i][j].couleur == couleurJoueur) {
                decompte += plateau[i][j].hauteur;
            }
        }
        return decompte;
    }

    /**
     * Cette fonction permet de déterminer le nombre de pions de la meme couleur
     * du joueur qui sont éliminé lors de l'action chat kamikase avec la
     * direction nord CN
     *
     * @param couleurJoueur la couleur du joueur
     * @param plateau le plateau de jeu
     * @return le nombre de pions de la meme couleur du joueur qui sont éliminé
     * lors de l'action chat kamikase avec la direction nord CN
     */
    static int nord(char couleurJoueur, Case[][] plateau) {
        int decompte = 0;
        for (int i = 0; i < plateau.length; i++) {
            int j = 0;
            while (j < plateau.length -1 && plateau[j][i].couleur == Case.CAR_VIDE) {
                j++;
            }
            if (plateau[j][i].couleur == couleurJoueur) {
                decompte += plateau[j][i].hauteur;
            }
        }
        return decompte;
    }

    /**
     * Cette fonction permet de déterminer le nombre de pions de la meme couleur
     * du joueur qui sont éliminé lors de l'action chat kamikase avec la
     * direction sud CS
     *
     * @param couleurJoueur la couleur du joueur
     * @param plateau le plateau de jeu
     * @return le nombre de pions de la meme couleur du joueur qui sont éliminé
     * lors de l'action chat kamikase avec la direction sud CS
     */
    static int sud(char couleurJoueur, Case[][] plateau) {
        int decompte = 0;
        for (int i = 0; i < plateau.length; i++) {
            int j = plateau.length - 1;
            while (j > 0 && plateau[j][i].couleur == Case.CAR_VIDE) {
                j--;
            }
            if ( plateau[j][i].couleur == couleurJoueur) {
                decompte += plateau[j][i].hauteur;
            }
        }
        return decompte;
    }
}
