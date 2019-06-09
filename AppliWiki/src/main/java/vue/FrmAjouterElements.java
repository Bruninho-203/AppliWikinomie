package vue;

import metier.Recherche;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmAjouterElements extends JFrame{
    private JLabel lblNom;
    private JTextField txtNom;
    private JButton btnAjouter;
    private JPanel panel;
    private JLabel lblStellar;
    private JTextField txtMontant;
    private JTextField txtCle;
    private JLabel lblCle;
    private JTextField txtPublique;
    private JLabel lblPublique;
    private JTextField txtSecrete;
    private static FrmAjouterElements frmAjouterElements = null;
    private static FrmMain frmMain;
    private FrmAjouterElements(){

        setTitle("Nouvel élément (Observatory)");
        setType(Type.UTILITY); setLocationRelativeTo(null);
        setContentPane(panel); pack();
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Recherche().ajouterElements(txtNom.getText(),txtSecrete.getText(),txtPublique.getText(),txtMontant.getText(), frmAjouterElements);
            }
        });
    }

    public static FrmAjouterElements getInstance(){
        if(frmAjouterElements == null){frmAjouterElements = new FrmAjouterElements();}
        return frmAjouterElements;
    }
}
