package vue;

import metier.Recherche;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAjouterValeurs extends JFrame{
    private JLabel lblValeurs;
    private JLabel lblElement;
    private JLabel lblTag;
    private JLabel lblValeur;
    private JTextField txtElement;
    private JTextField txtTag;
    private JTextField txtValeur;
    private JButton btnAjouter;
    private JPanel panel;
    private JTextField txtSecrete;
    private JLabel lblCle;
    private JTextField txtMontant;
    private JLabel lblStellar;
    private JTextField txtPublique;
    private JLabel lblPublique;

    private static FrmAjouterValeurs frmAjouterValeurs = null;

    private FrmAjouterValeurs(){

        setTitle("Nouvelle valeur (Tag+Value)");
        setType(Type.UTILITY); setLocationRelativeTo(null);
        setContentPane(panel); pack();
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Recherche().ajouterValeurs(txtElement.getText(), txtTag.getText(), txtValeur.getText(), txtSecrete.getText(), txtPublique.getText(), txtMontant.getText(), frmAjouterValeurs);
            }
        });
    }

    public static FrmAjouterValeurs getInstance(){
        if(frmAjouterValeurs == null){frmAjouterValeurs = new FrmAjouterValeurs();}
        return frmAjouterValeurs;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
