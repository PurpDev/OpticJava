package controleur;

import javax.swing.table.AbstractTableModel;

public class Tableau extends AbstractTableModel {
	
	private String entetes[];
	private Object donnees[][]; //matrice donc 2 crochet et pas 1 seul
	
	public Tableau(Object donnees[][], String entetes[])
	{
		this.donnees = donnees;
		this.entetes = entetes;
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.donnees.length;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.entetes.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return this.donnees[rowIndex] [columnIndex];
	}
	
	@Override
	public String getColumnName(int column) {
		return this.entetes[column];
	}
	
	public void ajouterLigne(Object ligne[])
	{
		Object matrice [][] = new Object [this.donnees.length + 1][this.entetes.length]; 
		for (int i=0; i< this.donnees.length; i++)
		{
			matrice [i] = this.donnees[i];
		}
		matrice[this.donnees.length] = ligne; 
		//on ecrase apres la donnees avec la nouvelle matrice 
		this.donnees = matrice; 
		//on actualise l'affichage 
		this.fireTableDataChanged();
	}

	public void supprimerLigne(int numLigne) {
		Object matrice [][] = new Object [this.donnees.length - 1][this.entetes.length]; 
		int j =0; 
		for (int i=0; i <this.donnees.length; i++)
		{
			if (i != numLigne) {
				matrice[j] = this.donnees[i]; 
				j++;
			}
		}
		//on ecrase apres la donnees avec la nouvelle matrice 
		this.donnees = matrice; 
		//on actualise l'affichage 
		this.fireTableDataChanged();
	}

	public void modifierLigne(int numLigne, Object[] ligne) {
		Object matrice [][] = new Object [this.donnees.length][this.entetes.length];
		
		for (int i=0; i <this.donnees.length; i++)
		{
			if (i != numLigne) {
				matrice[i] = this.donnees[i]; 
			}else
			{
				matrice[i] = ligne; 
			}
		}
		//on ecrase apres la donnees avec la nouvelle matrice 
		this.donnees = matrice; 
		//on actualise l'affichage 
		this.fireTableDataChanged();
	}

	

	public void setDonnees(Object[][]matrice ) {
		//on ecrase apres la donnee avec la new matrice
		this.donnees = matrice;
		//on actualise affichage
		this.fireTableDataChanged();
		

	}

	

}
