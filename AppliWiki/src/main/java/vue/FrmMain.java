package vue;

import metier.Recherche;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TreeMap;

public class FrmMain extends JFrame {
    private JPanel panel;
    private JButton btnRecherche;
    private JButton btnAjouterElements;
    private JButton btnAjouterValeurs;
    private JTextArea taResultat;
    private ButtonGroup grpRecherche;
    private TreeMap<String,String> results;
private JRadioButton radio;
    public FrmMain() {
        setType(Type.UTILITY); setContentPane(panel); pack(); setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Application données Amazon");
        ajouterObservatories();
        btnRecherche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer key = null; Double value = null;
                String selected = null;
                try {
                    selected = grpRecherche.getSelection().getActionCommand();

                    results = new Recherche().get(selected, FrmMain.this);
                } catch(Exception ex){
                    JOptionPane.showMessageDialog(FrmMain.this,
                            "Aucun champ de recherche sélectionné !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                //afficherResultat(results);
                for (String i : results.keySet()) {
                    key = Integer.valueOf(i);
                    value = Double.valueOf(results.get(i));
                    dataset.setValue(value, "Number", key);
                }
                JFreeChart chart = ChartFactory.createBarChart(selected,"Years", "Values",dataset, PlotOrientation.VERTICAL,false,true,false);
                CategoryPlot plot = chart.getCategoryPlot();
                plot.setRangeGridlinePaint(Color.black);
                ChartFrame chartFrame = new ChartFrame("Values per year for " + selected,chart);
                chartFrame.setVisible(true);
                chartFrame.setSize(1000,700);
            }
        });

        btnAjouterElements.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmAjouterElements.getInstance().setVisible(true);
            }
        });

        btnAjouterValeurs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmAjouterValeurs.getInstance().setVisible(true);
            }
        });

    }

    public void ajouterObservatories(){
        ArrayList<String> observatories = new Recherche().getObservatories();
        for(String obs: observatories) {
            radio = new JRadioButton(obs);
            radio.setActionCommand(obs);
            grpRecherche.add(radio);
            setLayout(new FlowLayout());
            add(radio);
            pack();
        }
    }


}
