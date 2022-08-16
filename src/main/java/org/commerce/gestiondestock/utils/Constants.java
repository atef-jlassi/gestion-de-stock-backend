package org.commerce.gestiondestock.utils;

public interface Constants {
    public static String APP_ROOT = "gestiondestock/v1";
    public static String COMMANDE_FOURNISSEUR_ENDPOINT = APP_ROOT + "/commandes-fournisseurs";
    public static String CRAETE_COMMANDE_FOURNISSEUR_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT+"/create";
    public static String FIND_COMMANDE_FOURNISSEUR_BY_ID_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT+"/{id}";
    public static String FIND_COMMANDE_FOURNISSEUR_BY_CODE_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT+"/byCode/{code}";
    public static String FIND_ALL_COMMANDE_FOURNISSEUR_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT;
    public static String DELETE_COMMANDE_FOURNISSEUR_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT + "/{id}";

    public static String FOURNISSEUR_ENDPOINT = APP_ROOT + "/fournisseurs";

    public static String ENTREPRISE_ENDPOINT = APP_ROOT + "/entreprises";

    public static String UTILISATEUR_ENDPOINT = APP_ROOT + "/utilisateurs";

    public static String VENTES_ENDPOINT = APP_ROOT + "/ventes";

}
