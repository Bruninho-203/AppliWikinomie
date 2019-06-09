package metier;

import dao.RechercheDAO;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class Recherche {
    TreeMap<String,String> results;

    public TreeMap<String,String> get(String observatory, Component frm){
        results = RechercheDAO.getResults(observatory,frm);
        return results;
    }

    public void ajouterElements(String nomObservatory, String secrete, String publique, String montant, Component frmAjouterElements){
        RechercheDAO.transfertStellar(publique,secrete,montant,frmAjouterElements);
        RechercheDAO.ajouterObservatory(nomObservatory,secrete, frmAjouterElements);

    }

    public void ajouterValeurs(String nomObservatory, String tag, String valeur, String secrete, String publique, String montant, Component frmAjouterValeurs){
        RechercheDAO.transfertStellar(publique,secrete,montant,frmAjouterValeurs);
        RechercheDAO.ajouterValeur(nomObservatory,tag,valeur,secrete, frmAjouterValeurs);
    }

    public ArrayList<String> getObservatories(){
        return RechercheDAO.getObservatories();
    }
}
