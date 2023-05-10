package vue;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import controleur.Tableau;
import controleur.TableauTech;
import modele.Modele;

public class PanelTech extends PanelDeBase{
	private JTable uneTable;
	private Tableau unTableau;
	private JScrollPane uneScroll;
	
	public PanelTech()
	{
		super(Color.BLUE);
		String entetes[] = {"Nom", "Prenom", "Adresse", "Email", "Diplome"};
		//this.unTableau = new Tableau (this.getTableauTech(""), entetes);
		this.uneTable = new JTable(this.unTableau);
		this.uneScroll = new JScrollPane(this.uneTable);
		this.uneScroll.setBounds(40, 20, 700, 300);
		this.add(this.uneScroll);
	}
	
	/*public Object [][] getTableauTech (String mot)
	{
		//methode qui transforme l'ArrayList des pilotes en une matrice [][]
		
		ArrayList<TableauTech> lesTableauxTechs = Modele.selectAllTableauTech(); 
		Object matrice [][] = new Object [lesTableauxTechs.size()][5];
		int i=0; 
		for (TableauTech unTab : lesTableauxTechs)
		{
			matrice[i][0] = unTab.getNom(); 
			matrice[i][1] = unTab.getPrenom(); 
			matrice[i][2] = unTab.getAdresse(); 
			matrice[i][3] = unTab.getEmail(); 
			matrice[i][4] = unTab.getDiplome();  
			i++; 
		}
		return matrice; 
	}*/

}
