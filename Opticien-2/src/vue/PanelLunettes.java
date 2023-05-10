package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Lunette;
import controleur.Monture;
import controleur.Tableau;
import controleur.Verre;
import modele.Modele;

public class PanelLunettes extends PanelDeBase implements ActionListener{
	private JPanel panelForm = new JPanel(); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JTextField txtLibelle = new JTextField(); 
	private JTextField txtCouleur = new JTextField(); 
	private JTextField txtGenre = new JTextField();
	private JTextField txtMarque = new JTextField(); 
	private JTextField txtPrix = new JTextField();
	private JTextField txtQuantite = new JTextField();
	//private JTextField txtDisponibilite = new JTextField(); 
	private JComboBox<String> txtDisponibilite = new JComboBox<String>();

	
	private JComboBox<String> txtIdVerre = new JComboBox<String>();
	private JComboBox<String> txtIdMonture = new JComboBox<String>();
	



	
	private JTable uneTable ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau; 
	
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");
	private JPanel panelRechercher = new JPanel();
	
	private JPanel panelCount = new JPanel();


	

	public PanelLunettes()
	{
		super(Color.GRAY);
		
		this.panelForm.setBounds(10, 40, 400, 420);//10,40,250,240 //10,60,500,400
		this.panelForm.setLayout(new GridLayout(10, 2));
		this.panelForm.add(new JLabel("Libelle : ")); 
		this.panelForm.add(this.txtLibelle); 
		this.panelForm.add(new JLabel("Couleur : ")); 
		this.panelForm.add(this.txtCouleur);
		this.panelForm.add(new JLabel("Genre : ")); 
		this.panelForm.add(this.txtGenre);
		this.panelForm.add(new JLabel("Marque: ")); 
		this.panelForm.add(this.txtMarque);
		this.panelForm.add(new JLabel("Prix: ")); 
		this.panelForm.add(this.txtPrix);
		this.panelForm.add(new JLabel("Quantite: ")); 
		this.panelForm.add(this.txtQuantite);
		this.panelForm.add(new JLabel("Disponibilite: ")); 
		this.panelForm.add(this.txtDisponibilite);
		this.panelForm.add(new JLabel("ID Verre : ")); 
		this.panelForm.add(this.txtIdVerre); 
		this.panelForm.add(new JLabel("ID Monture : ")); 
		this.panelForm.add(this.txtIdMonture); 
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer);
		this.panelForm.setVisible(true);
		this.add(this.panelForm); 
		
		
		//mettre le nb lunette
		//this.panelCount.setBounds(100, 100, 500, 200);
		//this.panelCount.setLayout(new GridLayout(4, 1));
		
		
		
		
		//construction panel rechercher
		this.panelRechercher.setBounds(600, 40, 400, 40);//290,40,400,30 //650,60,400,40
		this.panelRechercher.setLayout(new GridLayout(1,2));
		this.panelRechercher.add(new JLabel("Filtrer les lunettes par :"));
		this.panelRechercher.add(this.txtMot);
		this.panelRechercher.add(this.btRechercher);
		this.add(panelRechercher);
		
		String entetes[] = {"idLunette", "Libelle", "Couleur", "Genre", "Marque", "Prix", "Quantite", "Disponibilite", "IdVerre", "idMonture"};
		this.unTableau = new Tableau (getLunettes(""), entetes); 

		
		this.uneTable = new JTable(this.unTableau); 
		this.uneScroll = new JScrollPane(this.uneTable); 
		this.uneScroll.setBounds(500,100,650,350);//290,40,400,240 ; 650, 100, 400, 400
		this.add(this.uneScroll);
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				int numLigne = uneTable.getSelectedRow();
				
				
				
				if(e.getClickCount() == 2)//vsi le nombre de clic sur le resultat = 2 alors on supprimera
				{
					int idlunette = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());//mmettre idpersonne maybe
					int retour = JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer le Tecg",
							"Suppression de le Tech", JOptionPane.YES_NO_OPTION); 
					
					if(retour == 0) //si apres 2 clic on touche sur la sortie int 0 on supprime
					{
						Modele.deleteLunette(idlunette);
						unTableau.supprimerLigne(numLigne);
					}
				}

				else if(e.getClickCount() == 1)//vsi le nombre de clic sur le resultat = 1 alors on modifie
				{
					int idverre = Integer.parseInt(unTableau.getValueAt(numLigne, 8).toString());// j 'ai fait �a pour convertir idverre en int comme idlunette
					int idmonture = Integer.parseInt(unTableau.getValueAt(numLigne, 9).toString());//plus haut dans le if.getClick
					
					txtLibelle.setText(unTableau.getValueAt(numLigne, 1).toString());
					txtCouleur.setText(unTableau.getValueAt(numLigne, 2).toString());
					txtGenre.setText(unTableau.getValueAt(numLigne, 3).toString());
					txtMarque.setText(unTableau.getValueAt(numLigne, 4).toString());
					txtPrix.setText(unTableau.getValueAt(numLigne, 5).toString());
					txtQuantite.setText(unTableau.getValueAt(numLigne, 6).toString());
					//txtDisponibilite.setText(unTableau.getValueAt(numLigne, 7).toString());
					//txtIdVerre.setText(unTableau.getValueAt(numLigne, 8).toString());
					//txtIdMonture.setText(unTableau.getValueAt(numLigne, 9).toString());

					btEnregistrer.setText("Modifier");
				}
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		

		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btRechercher.addActionListener(this);
		
	}
	
	
	
	private Object[][] getLunettes(String string) {
		//methode qui transforme l'ArrayList des Lunettes en une matrice [][]
				ArrayList<Lunette> lesLunettes = Modele.selectAllLunettes(); // j ai du duppliquer selectAllLunette en retirant la var mot de la fonction et de la requete de la fonction 
				Object matrice [][] = new Object [lesLunettes.size()][10];
				int i=0; 
				for (Lunette uneLunette : lesLunettes)
				{
					matrice[i][0] = uneLunette.getIdlunette(); 
					matrice[i][1] = uneLunette.getLibelle(); 
					matrice[i][2] = uneLunette.getCouleur(); 
					matrice[i][3] = uneLunette.getGenre(); 
					matrice[i][4] = uneLunette.getMarque(); 
					matrice[i][5] = uneLunette.getPrix(); 
					matrice[i][6] = uneLunette.getQuantite(); 
					matrice[i][7] = uneLunette.getDisponibilite();
					matrice[i][8] = uneLunette.getIdverre(); 
					matrice[i][9] = uneLunette.getIdmonture(); 

					i++; 
				}
				return matrice; 
	}
	
	//ESSAYER D AFFICHER LE NB TOTAL DE LUNETTE DANS LE PANEL LUNETTE 
	
	public void actualiser()
	{
		this.panelCount.removeAll();
		int nbLunettes = Modele.countLunette();
	 
		this.panelCount.add(new JLabel("Nombre de lunettte :" + nbLunettes));
		
		this.panelCount.setVisible(true);//rajout ici
		//this.add(this.panelCount);
		
	}


	public void remplirCombos()
	{
		ArrayList<Verre> lesVerres = Modele.selectAllVerres("");
		this.txtIdVerre.removeAllItems();
		
		for(Verre unVerre : lesVerres)
		{
			this.txtIdVerre.addItem(unVerre.getIdverre()+ "-" + unVerre.getVision());//dans mon combobox j'ai pris le champs pas le AS 			
		}
		
		ArrayList<Monture> lesMontures = Modele.selectAllMontures();
		for(Monture uneMonture : lesMontures)
		{
			this.txtIdMonture.addItem(uneMonture.getIdmonture()+ "-" + uneMonture.getMatiereM());

		}
	}
	
	public void dispoCombos()
	{
		 
		this.txtDisponibilite.removeAllItems();
	 	this.txtDisponibilite.addItem("rupture ");//dans mon combobox j'ai pris le champs pas le AS 			
		this.txtDisponibilite.addItem("en magasin");
		this.txtDisponibilite.addItem("en cours");
	}
	

	
	
	
	public void viderChamps () {
		this.txtLibelle.setText("");
		this.txtCouleur.setText("");
		this.txtGenre.setText("");
		this.txtMarque.setText("");
		this.txtQuantite.setText("");
		
	}
	public void traitement (int choix) {
		String libelle = this.txtLibelle.getText(); 
		String couleur = this.txtCouleur.getText(); 
		String genre = this.txtGenre.getText();
		String marque = this.txtMarque.getText();
		String disponibilite = this.txtDisponibilite.getSelectedItem().toString();
		
		String prix = this.txtPrix.getText();
		int prix2 = Integer.parseInt(prix);// je convertit le String en int
		
		String quantite = this.txtQuantite.getText();
		int quantite2 = Integer.parseInt(quantite);

		
		String chaine = this.txtIdVerre.getSelectedItem().toString();
		String tab[] = chaine.split("-");
		int idverre = Integer.parseInt(tab[0]);// pour la clef etrangere de ta table c'est pareille faut faire �a aussi
		
		chaine = this.txtIdMonture.getSelectedItem().toString();
		tab = chaine.split("-");
		int idmonture = Integer.parseInt(tab[0]);
		
		if (choix ==1 ) {
			//instancier la classe Lunette 
			Lunette uneLunette = new Lunette (libelle, couleur, genre, marque,  prix2, quantite2, disponibilite, idverre, idmonture ); //idLunette = 0
			
			//insertion du Lunette dans la BDD 
			Modele.insertLunette(uneLunette);
			JOptionPane.showMessageDialog(this, "Insertion r�ussie");
			
			uneLunette = Modele.selectWhereLunette(libelle, genre); 
			
			//actualisation de l'affichage 
			Object ligne[] = {uneLunette.getIdlunette(), uneLunette.getLibelle(), uneLunette.getCouleur(), 
					uneLunette.getGenre(), uneLunette.getMarque(), uneLunette.getPrix(), uneLunette.getQuantite(), uneLunette.getDisponibilite(),uneLunette.getIdverre(), uneLunette.getIdmonture()};
			this.unTableau.ajouterLigne(ligne);
			
			
		} else {
			int numLigne = uneTable.getSelectedRow(); 
			int idLunette = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
			
			//instancier la classe Lunette 
			Lunette uneLunette = new Lunette (idLunette, libelle, couleur, genre, marque,  prix2, quantite2, disponibilite, idverre, idmonture);
			
			//Mise � jour dans la base 
			Modele.updateLunette(uneLunette);
			
			//Mise � jour de l'affichage 
			Object ligne[] = {uneLunette.getIdlunette(), uneLunette.getLibelle(), uneLunette.getCouleur(), 
					uneLunette.getGenre(), uneLunette.getMarque(), uneLunette.getPrix(), uneLunette.getQuantite(), uneLunette.getDisponibilite(),uneLunette.getIdverre(), uneLunette.getIdmonture()};
			this.unTableau.ajouterLigne(ligne);
			JOptionPane.showMessageDialog(this, "Modification r�ussie");
		}
		
		this.btEnregistrer.setText("Enregistrer");
		//vider les champs apr�s insertion 
		this.viderChamps();
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == this.btAnnuler)
		{
			this.viderChamps (); 
		}
		else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Enregistrer"))
		{
			this.traitement (1);
		}
		else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Modifier"))
		{
			this.traitement (2);
		}
		else if(e.getSource() == this.btRechercher)
		{
			String mot = this.txtMot.getText(); 
			this.unTableau.setDonnees(this.getLunettes(mot));
			
		}
		
	}

}
