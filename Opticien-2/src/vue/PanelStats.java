package vue;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Modele;

public class PanelStats extends PanelDeBase{
	
	private JPanel panelCount = new JPanel();


	public PanelStats() {
		
		
		super(Color.gray);
		
		this.panelCount.setBounds(100, 100, 500, 200);
		this.panelCount.setLayout(new GridLayout(4, 1));
		
		
		int nbTechniciens = Modele.countTechnicien();
		int nbClients = Modele.countClient();
		int nbLunettes = Modele.countLunette();
		int nbVerres = Modele.countVerre();
		int nbMontures = Modele.countMonture();

		int total = nbLunettes + nbVerres + nbMontures;
		
		this.panelCount.add(new JLabel("Nombre de technicien :" + nbTechniciens));
		this.panelCount.add(new JLabel("Nombre de client :" + nbClients));
		this.panelCount.add(new JLabel("Nombre de lunette :" + nbLunettes));
		this.panelCount.add(new JLabel("Nombre de verre  :" + nbVerres));
		this.panelCount.add(new JLabel("Nombre de monture   :" + nbMontures));
		this.panelCount.add(new JLabel("Total            :" + total));
		this.panelCount.setVisible(true);//rajout ici
		this.add(this.panelCount);
		
		
	}
	
	public void actualiser()
	{
		this.panelCount.removeAll();
		
		int nbTechniciens = Modele.countTechnicien();
		int nbClients = Modele.countClient();
		int nbLunettes = Modele.countLunette();
		int nbVerres = Modele.countVerre();
		int nbMontures = Modele.countMonture();

		int total = nbLunettes + nbVerres + nbMontures;
		
		this.panelCount.add(new JLabel("Nombre de technicien :" + nbTechniciens));
		this.panelCount.add(new JLabel("Nombre de client :" + nbClients));
		this.panelCount.add(new JLabel("Nombre de lunette :" + nbLunettes));
		this.panelCount.add(new JLabel("Nombre de verre  :" + nbVerres));
		this.panelCount.add(new JLabel("Nombre de monture   :" + nbMontures));
		this.panelCount.add(new JLabel("Total            :" + total));
		

		this.panelCount.setVisible(true);//rajout ici
		
	}

	

}