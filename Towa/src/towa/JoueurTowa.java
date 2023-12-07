package towa;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Joueur implémentant les actions possibles à partir d'un plateau, pour un
 * niveau donné.
 */
public class JoueurTowa implements IJoueurTowa {

    /**
     * Cette constatnte correspond à l'action de fusion
     */
    public static final boolean FUSION = false;

    /**
     * Cette fonction correspond à l'action d'activation
     */
    public static final boolean ACTIVATION = true;

    /**
     * Cette fonction correspond à la direction ouest pour l'action chat
     * kamikaze
     */
    public static final int OUEST = 1;

    /**
     * Cette fonction correspond à la direction est pour l'action chat kamikaze
     */
    public static final int EST = 2;

    /**
     * Cette fonction correspond à la direction ouest pour l'action chat
     * kamikaze
     */
    public static final int NORD = 3;

    /**
     * Cette fonction correspond à la direction ouest pour l'action chat
     * kamikaze
     */
    public static final int SUD = 4;

    /**
     * Cette fonction correspond à la direction ouest pour l'action chat
     * kamikaze
     */
    public static final int[][] direction = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

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
                //si l'activation ou la fusion d'un pion est possible sur cette case 
                if (activationPossible(plateau, coord, couleurJoueur)) {
                    //on ajoute l'action d'activation dans les actions possibles 
                    ajoutActionActivation(coord, actions, nbPions, couleurJoueur, adjacente(coord, plateau, ACTIVATION));
                    //on ajoute l'action de fusion dans les actions possibles 
                    ajoutActionFusion(coord, actions, nbPions, couleurJoueur, adjacente(coord, plateau, FUSION));
                    // Si l'action de magie sur une tour est possible sur cette case
                }
                if (actionMagiePossible(plateau, coord, couleurJoueur)) {
                    //on ajoute l'action de magie dans les actions possibles 
                    ajoutActionMagie(coord, actions, nbPions);
                }
            }
        }
        int[] directions = {OUEST, EST, NORD, SUD};
        //Pour chaque direction possible 
        for (int direction : directions) {
            //On ajoute une action chat kamikaze
            ajoutActionKamikaze(actions, nbPions, direction, plateau);
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
        Case casePlateau = plateau[coord.ligne][coord.colonne];
        boolean possible = false;
        //La pose est possible si : la couleur de la tour est la meme que celle du joueur
        if ((casePlateau.couleur == couleur
                // Ou si la case est vide 
                || !casePlateau.tourPresente())
                // Et si le niveau de la case est inférieur à 4
                && niveau(casePlateau) < 4
                // Et si la case est de nature terre
                && casePlateau.nature == Case.CAR_TERRE) {
            // Si le niveau n'est pas supérieur à 4 lorsque l'on pose le pions qur une case voisine d'un de ses adversaires
            if (voisines(coord, plateau, couleur) + niveau(casePlateau) <= 4) {
                possible = true;
            }
        }
        return possible;
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
    boolean activationPossible(Case[][] plateau, Coordonnees coord, char couleur) {
        Case casePlateau = plateau[coord.ligne][coord.colonne];
        return casePlateau.couleur == couleur && casePlateau.tourPresente();
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
                //Si il y a une tour et qu'elle est noir on incrémente le nombre de pions noirs sinon on incrémente le nombre de blancs 
                if (plateau[i][j].tourPresente()) {
                    if (plateau[i][j].couleur == Case.CAR_NOIR) {
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
    void ajoutActionActivation(Coordonnees coord, ActionsPossibles actions,
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
    void ajoutActionKamikaze(ActionsPossibles actions,
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
    void ajoutActionFusion(Coordonnees coord, ActionsPossibles actions,
            NbPions nbPions, char couleur, int decompte) {  // decompte correspond au nombre de pions que l'on veut retirer 
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
     * sont éliminés lorsque l'on active une tour ou lorsqu'on décide de faire
     * une fusion
     *
     * @param coord les coordonnées de la tour que l'on souhaite activer
     * @param plateau le plateau du jeu sur lequel se trouve la tour
     * @return le nombre de pions de l'adversaire qui sont éliminés lorsque l'on
     * active ou fusionne la tour dont les coordonnées sont passé en parametre
     */
    static int adjacente(Coordonnees coord, Case[][] plateau, boolean action) {
        int decompte = 0;
        Case casePlateau = plateau[coord.ligne][coord.colonne];
        /*Si il s'agit d'une action d'activation, le nombre de pions à retirer correspond à tous les pions adjacents énemis,
        sur la meme ligne et sur la meme colonne de hauteur strictement inférieur à la tour activé.*/
        if (action) {
            decompte = diagonaleActive(coord, plateau) + colonneActive(coord, plateau) + ligneActive(coord, plateau);
            //Si il s'agit d'une action de fusion:
        } else {
            //Le totale correspond à tous les pions amis adjacent à la tour que l'on souhaite fusionner 
            int totale = diagonaleFusion(coord, plateau) + colonneFusion(coord, plateau) + ligneFusion(coord, plateau);
            // Si le niveau de la tour est >= 4 alors le nombre de pions étant de la meme couleur que la tour à retirer du plateau correspond au totale 
            if (niveau(casePlateau) >= 4) {
                decompte = totale;
                /* Si il y a des pions amis adjacent à la tour, que le totale !=0 et que la somme du totale et du niveau de la tour est >4 
                alors le nombre de pions à retirer du plateau correspond au calcul ci-dessous*/
            } else if (totale != 0 && !((totale + niveau(casePlateau)) <= 4)) {
                decompte = totale - (4 - niveau(casePlateau));
            }
        }
        return decompte;
    }

    /**
     * Cette méthode permet de déterminber le nombre de pions adversaires qui se
     * trouve sur les diagonales adjacentes de la tour dont les coordonnées sont
     * passé en parametre et qui d'un niveau plus bas que celui-ci
     *
     * @param coord les coordonnées de la tour que l'on souhaite activer
     * @param plateau le plateau du jeu sur lequel se trouve la tour
     * @return le nombre de pions adversaires qui se trouve sur les diagonales
     * adjacentes de la tour que l'on souhaite activer et dont le niveau est
     * plus bas que celle-ci
     */
    static int diagonaleActive(Coordonnees coord, Case[][] plateau) {
        Case casePlateau = plateau[coord.ligne][coord.colonne];
        // Le decompte correspond au nombre de pions énemis présent sur les diagonales de la tour 
        int decompte = 0;
        for (int[] direction1 : direction) {
            //suivante correspond à toutes les cases adjacentes diagonal à la tour
            Coordonnees suivante = new Coordonnees(coord.ligne + direction1[0], coord.colonne + direction1[1]);
            if (estDansPlateau(suivante, plateau)) {
                if (plateau[suivante.ligne][suivante.colonne].tourPresente()
                        && plateau[suivante.ligne][suivante.colonne].couleur != casePlateau.couleur
                        && niveau(plateau[suivante.ligne][suivante.colonne]) < niveau(casePlateau)) {
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
        Case casePlateau = plateau[coord.ligne][coord.colonne];
        // Le decompte correspond au nombre de pions amis présent sur les diagonales de la tour 
        int decompte = 0;
        for (int[] direction1 : direction) {
            Coordonnees suivante = new Coordonnees(coord.ligne + direction1[0], coord.colonne + direction1[1]);
            if (estDansPlateau(suivante, plateau)) {
                if (plateau[suivante.ligne][suivante.colonne].tourPresente() 
                        && plateau[suivante.ligne][suivante.colonne].couleur == casePlateau.couleur) {
                    decompte += plateau[suivante.ligne][suivante.colonne].hauteur;
                }
            }
        }
        return decompte;
    }

    /**
     * Cette méthode permet de déterminber le nombre de pions adversaires qui se
     * trouve sur la meme colonne et le plus proche de chaque cotés de la tour
     * dont les coordonnées sont passé en parametre et dont le niveau est moins
     * haut que celle-ci
     *
     * @param coord les coordonnées de la tour que l'on souhaite activer
     * @param plateau le plateau du jeu sur lequel se trouve la tour
     * @return le nombre de pions adversaires qui se trouve sur la meme colonne
     * et le plus proche de la tour que l'on souhaite activer et dont le niveau
     * est moins haut que celle-ci
     */
    static int colonneActive(Coordonnees coord, Case[][] plateau) {
        Case casePlateau = plateau[coord.ligne][coord.colonne];
        // Le decompte correspond au nombre de pions énemis présent sur la colonne 
        int decompte = 0;
        //On compte le nombre de pions énemis présent sur la colonne en partant de la tour vers le haut (seulement la premiere tour)
        int i = coord.ligne - 1;
        Coordonnees coord1 = new Coordonnees(coord.ligne - 1, coord.colonne);
        if (estDansPlateau(coord1, plateau)) {
            while (!plateau[i][coord.colonne].tourPresente() && i > 0) {
                i--;
            }
            Case premiereTourHaut = plateau[i][coord.colonne];
            if (premiereTourHaut.couleur != casePlateau.couleur
                    && niveau(premiereTourHaut) < niveau(casePlateau)) {
                decompte += premiereTourHaut.hauteur;
            }
        }
        //On compte le nombre de pions énemis présent sur la colonne en partant de la tour vers le bas (seulement la premiere tour)
        int j = coord.ligne + 1;
        Coordonnees coord2 = new Coordonnees(coord.ligne + 1, coord.colonne);
        if (estDansPlateau(coord2, plateau)) {
            while (!plateau[j][coord.colonne].tourPresente() && j < plateau.length - 1) {
                j++;
            }
            Case premiereTourBas = plateau[j][coord.colonne];
            if (premiereTourBas.couleur != casePlateau.couleur
                    && niveau(premiereTourBas) < niveau(casePlateau)) {
                decompte += premiereTourBas.hauteur;
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
        Case casePlateau = plateau[coord.ligne][coord.colonne];
        // Le decompte correspond au nombre de pions amis présent sur la colonne 
        int decompte = 0;
        //On compte le nombre de pions amis présent sur la colonne en partant de la tour vers le haut (seulement la premiere tour)
        int i = coord.ligne - 1;
        Coordonnees coord1 = new Coordonnees(coord.ligne - 1, coord.colonne);
        if (estDansPlateau(coord1, plateau)) {
            while (!plateau[i][coord.colonne].tourPresente() && i > 0) {
                i--;
            }
            Case premiereTourHaut = plateau[i][coord.colonne];
            if (premiereTourHaut.couleur == casePlateau.couleur) {
                decompte += premiereTourHaut.hauteur;
            }
        }
        //On compte le nombre de pions amis présent sur la colonne en partant de la tour vers le bas (seulement la premiere tour)
        int j = coord.ligne + 1;
        Coordonnees coord2 = new Coordonnees(coord.ligne + 1, coord.colonne);
        if (estDansPlateau(coord2, plateau)) {
            while (!plateau[j][coord.colonne].tourPresente() && j < plateau.length - 1) {
                j++;
            }
            Case premiereTourBas = plateau[j][coord.colonne];
            if (premiereTourBas.couleur == casePlateau.couleur) {
                decompte += premiereTourBas.hauteur;
            }
        }
        return decompte;
    }

    /**
     * Cette méthode permet de déterminber le nombre de pions adversaires qui se
     * trouve sur la meme ligne et le plus proche des deux cotés de la tour dont
     * les coordonnées sont passé en parametre et dont le niveau est moins haut
     * que celle-ci
     *
     * @param coord les coordonnées de la tour que l'on souhaite activer
     * @param plateau le plateau du jeu sur lequel se trouve la tour
     * @return le nombre de pions adversaires qui se trouve sur la meme ligne et
     * le plus proche de la tour que l'on souhaite activer et dont le niveau est
     * moins haut que celle-ci
     */
    static int ligneActive(Coordonnees coord, Case[][] plateau) {
        Case casePlateau = plateau[coord.ligne][coord.colonne];
        // Le decompte correspond au nombre de pions énemis présent sur la ligne 
        int decompte = 0;
        //On compte le nombre de pions énemis présent sur la ligne en partant de la tour vers la gauche (seulement la premiere tour)
        int i = coord.colonne - 1;
        Coordonnees coord1 = new Coordonnees(coord.ligne, coord.colonne - 1);
        if (estDansPlateau(coord1, plateau)) {
            while (!plateau[coord.ligne][i].tourPresente() && i > 0) {
                i--;
            }
            Case premiereTourGauche = plateau[coord.ligne][i];
            if (premiereTourGauche.couleur != casePlateau.couleur
                    && niveau(premiereTourGauche) < niveau(casePlateau)) {
                decompte += premiereTourGauche.hauteur;
            }
        }
        //On compte le nombre de pions énemis présent sur la ligne en partant de la tour vers la droite (seulement la premiere tour)
        int j = coord.colonne + 1;
        Coordonnees coord2 = new Coordonnees(coord.ligne, coord.colonne + 1);
        if (estDansPlateau(coord2, plateau)) {
            while (!plateau[coord.ligne][j].tourPresente() && j < plateau.length - 1) {
                j++;
            }
            Case premiereTourDroite = plateau[coord.ligne][j];
            if (premiereTourDroite.couleur != casePlateau.couleur
                    && niveau(premiereTourDroite) < niveau(casePlateau)) {
                decompte += premiereTourDroite.hauteur;
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
        Case casePlateau = plateau[coord.ligne][coord.colonne];
        // Le decompte correspond au nombre de pions amis présent sur la ligne 
        int decompte = 0;
        //On compte le nombre de pions amis présent sur la ligne en partant de la tour vers la gauche (seulement la premiere tour)
        int i = coord.colonne - 1;
        Coordonnees coord1 = new Coordonnees(coord.ligne, coord.colonne - 1);
        if (estDansPlateau(coord1, plateau)) {
            while (!plateau[coord.ligne][i].tourPresente() && i > 0) {
                i--;
            }
            Case premiereTourGauche = plateau[coord.ligne][i];
            if (premiereTourGauche.couleur == casePlateau.couleur) {
                decompte += premiereTourGauche.hauteur;
            }
        }
        //On compte le nombre de pions amis présent sur la ligne en partant de la tour vers la droite (seulement la premiere tour)
        int j = coord.colonne + 1;
        Coordonnees coord2 = new Coordonnees(coord.ligne, coord.colonne + 1);
        if (estDansPlateau(coord2, plateau)) {
            while (!plateau[coord.ligne][j].tourPresente() && j < plateau.length - 1) {
                j++;
            }
            Case premiereTourDroite = plateau[coord.ligne][j];
            if (premiereTourDroite.couleur == casePlateau.couleur) {
                decompte += premiereTourDroite.hauteur;
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
        Case casePlateau = plateau[coord.ligne][coord.colonne];
        int ajoutHauteur = 1;
        // On initialise le tableau contenant toutes les directions 
        int[][] toutesDirections = {{1, 1}, {1, 0}, {1, -1}, {0, 1}, {0, -1}, {-1, 1}, {-1, 0}, {-1, -1}};
        for (int[] toutesDirection : toutesDirections) {
            Coordonnees suivante = new Coordonnees(coord.ligne + toutesDirection[0], coord.colonne + toutesDirection[1]);
            //Si la case est vide et que la suivante est une tour adversaire, on pose 2 pions 
            if (estDansPlateau(suivante, plateau)) {
                if (!casePlateau.tourPresente() && plateau[suivante.ligne][suivante.colonne].tourPresente() 
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
        //On parcour le tableau de l'est vers l'ouest
        for (Case[] plateau1 : plateau) {
            int j = 0;
            /*Des qu'on tombe sur une tour on vérifie sa couleur, si il s'agit de
            la meme couleur que celle du joueur on incrémente le décompte de la
            hauteur de cette tour et on passe à la ligne suivante */
            while (j < plateau.length - 1 && !plateau1[j].tourPresente()) {
                j++;
            }
            if (plateau1[j].couleur == couleurJoueur) {
                decompte += plateau1[j].hauteur;
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
        //On parcour le tableau de l'ouest vers l'est
        for (Case[] plateau1 : plateau) {
            int j = plateau.length - 1;
            /*Des qu'on tombe sur une tour on vérifie sa couleur, si il s'agit de
            la meme couleur que celle du joueur on incrémente le décompte de la
            hauteur de cette tour et on passe à la ligne suivante */
            while (j > 0 && !plateau1[j].tourPresente()) {
                j--;
            }
            if (plateau1[j].couleur == couleurJoueur) {
                decompte += plateau1[j].hauteur;
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
        //On parcour le tableau de l'ouest vers l'est
        for (int i = 0; i < plateau.length; i++) {
            int j = 0;
            /* Des qu'on tombe sur une tour on vérifie sa couleur, si il s'agit de
            la meme couleur que celle du joueur on incrémente le décompte de la
            hauteur de cette tour et on passe à la ligne suivante*/
            while (j < plateau.length - 1 && !plateau[j][i].tourPresente()) {
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
        //On parcour le tableau de l'ouest vers l'est
        for (int i = 0; i < plateau.length; i++) {
            int j = plateau.length - 1;
            /*Des qu'on tombe sur une tour on vérifie sa couleur, si il s'agit de
            la meme couleur que celle du joueur on incrémente le décompte de la
            hauteur de cette tour et on passe à la ligne suivante */
            while (j > 0 && !plateau[j][i].tourPresente()) {
                j--;
            }
            if (plateau[j][i].couleur == couleurJoueur) {
                decompte += plateau[j][i].hauteur;
            }
        }
        return decompte;
    }

    /**
     * Cette méthode permet de déterminer le niveau d'une case
     *
     * @param c La case dont on veut déterminer le niveau
     * @return le niveau de la case
     */
    static int niveau(Case c) {
        return c.hauteur + c.altitude;
    }

    /**
     * Ajout de l'action magie dans le tableau des actions possibles
     *
     * @param coord les coordonnes de la case
     * @param actions l'ensemble des actions possibles (en construction)
     * @param nbPions le nombre de pions sur le plateau
     * @param couleur la couleur du joueur
     */
    void ajoutActionMagie(Coordonnees coord, ActionsPossibles actions,
            NbPions nbPions) {
        String action;
        action = "M" + coord.carLigne() + coord.carColonne() + ","
                + (nbPions.nbPionsNoirs) + ","
                + (nbPions.nbPionsBlancs);
        actions.ajouterAction(action);
    }

    /**
     * Indique s'il est possible de faire l'action magie sur une tour sur une
     * case pour ce plateau, ce joueur, dans ce niveau.
     *
     * @param plateau le plateau de jeu
     * @param coord les coordonnees de la case
     * @param couleurJoueur la couleur du joueur
     * @return vrai s'il est possible de faire l'action magie sur une tour sur
     * une case pour ce plateau, ce joueur, dans ce niveau, faux sinon
     */
    boolean actionMagiePossible(Case[][] plateau, Coordonnees coord, char couleurJoueur) {
        Case casePlateau = plateau[coord.ligne][coord.colonne];
        boolean correct = false;
        // l'action magie est possible si il s'agit d'une tour amis
        if (casePlateau.couleur == couleurJoueur
                // si la case symétrique est vide et qu'il s'agit d'une case de nature terre 
                && !symetrique(coord, plateau).tourPresente() && symetrique(coord, plateau).nature == Case.CAR_TERRE
                // si la somme de la hauteur de la tour et de l'atitude de la case symétrique est <=4
                && ((casePlateau.hauteur + symetrique(coord, plateau).altitude)) <= 4) {
            correct = true;
        }
        return correct;
    }

    /**
     * Cette méthode permet de déterminer la case symétrique (par symétrie
     * centrale) à la case dont les coordonnées sont passées en parametre
     *
     * @param c les corrdonnées de la case dont on veut connaitre la case
     * symétrique
     * @param plateau le plateau de jeu
     * @return la case symétrique (par symétrie centrale) à la case dont les
     * coordonnées sont passées en parametre
     */
    static Case symetrique(Coordonnees c, Case[][] plateau) {
        return plateau[(plateau.length - 1) - c.ligne][(plateau.length - 1) - c.colonne];
    }
}
