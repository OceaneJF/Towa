package towa;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Joueur implémentant les actions possibles à partir d'un plateau, pour un
 * niveau donné.
 */
public class JoueurTowa implements IJoueurTowa {

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
                    ajoutPoseActive(coord, actions, nbPions, couleurJoueur, adjacente(coord, plateau));
                }
            }
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
     * @param coord coordonnées de la case où poser un pion
     * @param actions l'ensemble des actions possibles (en construction)
     * @param nbPions nbPions le nombre de pions par couleur sur le plateau
     * avant l'activation
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
     * sont éliminés lorsque l'on active une tour, c'est à dire le nombre de
     * pions adversaires voisins
     *
     * @param coord les coordonnées de la tour que l'on souhaite activer
     * @param plateau le plateau du jeu sur lequel se trouve la tour
     * @return le nombre de pions de l'adversaire qui sont voisins a la tour du
     * joueur et qui sont moins haute que la tour du joueur
     */
    static int adjacente(Coordonnees coord, Case[][] plateau) {
        int decompte = 0;
        int[][] direction = {{1, 1}, {1, 0}, {1, -1}, {0, 1}, {0, -1}, {-1, 1}, {-1, 0}, {-1, -1}};
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
}
