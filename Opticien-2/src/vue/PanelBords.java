package vue;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import controleur.Tableau;
import controleur.TableauBord;
import modele.Modele;

public class PanelBords extends PanelDeBase{
	private JTable uneTable ;
    private Tableau unTableau;
    private JScrollPane uneScroll;

	public PanelBords()
	{
		super(Color.gray);
		String entetes  [] = {"Nom", "Prénom", "Diplome", "Adresse","Telephone","Nb de Tech"};
	    this.unTableau= new Tableau (this.getTableauBord(""), entetes);
        this.uneTable = new JTable(this.unTableau);
        this.uneScroll = new JScrollPane(this.uneTable); 
        this.uneScroll.setBounds(40, 20, 700, 300);
        this.add(this.uneScroll);
       
	}

	private Object[][] getTableauBord(String string) {
		
		 //methode qui transforme l'ArrayList des TableauBords en une matrice [][]
        ArrayList<TableauBord> lesTableauBords = Modele.selectAllTableauBord() ; 
        Object matrice [][] = new Object [lesTableauBords.size()][6];
        int i=0; 
        for (TableauBord unTab : lesTableauBords)
        {
            matrice[i][0] = unTab.getNom() ; 
            matrice[i][1] = unTab.getPrenom();
            matrice[i][2] = unTab.getDiplome(); 
            matrice[i][3] = unTab.getAdresse(); 
            matrice[i][4] = unTab.getTelephone(); 
            matrice[i][5] = unTab.getNbTech();
            i++; 
        }
        return matrice;
	}
	
	

}
