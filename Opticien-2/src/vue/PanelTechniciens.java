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

import controleur.Tableau;
//import controleur.TableauTech;
import controleur.Technicien;
import controleur.Personne;
import modele.Modele;

public class PanelTechniciens extends PanelDeBase implements ActionListener{
	
	private JPanel panelForm = new JPanel(); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JTextField txtNom = new JTextField(); 
	private JTextField txtPrenom = new JTextField(); 
	private JTextField txtAdresse = new JTextField();
	private JTextField txtEmail = new JTextField(); 
	private JTextField txtDiplome = new JTextField();
	//private JComboBox<String> txtIdpersonne  = new JComboBox<String>();
	

	
	private JTable uneTable ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau; 
	
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");
	private JPanel panelRechercher = new JPanel();

	public PanelTechniciens() {
		super(Color.gray);
		
		//construction du panel Form
		this.panelForm.setBounds(10, 40, 400, 420);//10,40,400,400
		this.panelForm.setLayout(new GridLayout(6, 2));
		this.panelForm.add(new JLabel("Nom Tech : ")); 
		this.panelForm.add(this.txtNom); 
		this.panelForm.add(new JLabel("Pr�nom Tech : ")); 
		this.panelForm.add(this.txtPrenom);
		this.panelForm.add(new JLabel("Adresse Tech : ")); 
		this.panelForm.add(this.txtAdresse);
		this.panelForm.add(new JLabel("Email : ")); 
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(new JLabel("Diplome : ")); 
		this.panelForm.add(this.txtDiplome);
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer);
		this.panelForm.setVisible(true);
		this.add(this.panelForm); 
		
		
		//construction panel rechercher
		this.panelRechercher.setBounds(600, 40, 400, 40);//290,40,400,30
		this.panelRechercher.setLayout(new GridLayout(1,2));
		this.panelRechercher.add(new JLabel("Filtrer les techniciens par :"));
		this.panelRechercher.add(this.txtMot);
		this.panelRechercher.add(this.btRechercher);
		this.add(panelRechercher);
		
		String entetes [] = {"idPersonne", "Nom", "Pr�nom", "Adresse", "Email", "Diplome"};
		this.unTableau = new Tableau(getTechniciens(""), entetes);
		
		
		this.uneTable = new JTable(this.unTableau); 
		this.uneScroll = new JScrollPane(this.uneTable); 
		this.uneScroll.setBounds(500,100,650,350);//290,40,400,240 ; 550, 130, 600, 450
		this.add(this.uneScroll); 
		
		this.uneTable.addMouseListener(new MouseListener()
				{

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
					

						int numLigne = uneTable.getSelectedRow();
						if(e.getClickCount() == 2)//vsi le nombre de clic sur le resultat = 2 alors on supprimera
						{
							int idPersonne = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());//mmettre idpersonne maybe
							int retour = JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer le Tech",
									"Suppression de le Tech", JOptionPane.YES_NO_OPTION); 
							
							if(retour == 0) //si apres 2 clic on touche sur la sortie int 0 on supprime
							{
								Modele.deleteTechnicien(idPersonne);
								unTableau.supprimerLigne(numLigne);
							}
						}
						
						else if(e.getClickCount() == 1)//vsi le nombre de clic sur le resultat = 1 alors on modifie
						{
							txtNom.setText(unTableau.getValueAt(numLigne, 1).toString());
							txtPrenom.setText(unTableau.getValueAt(numLigne, 2).toString());
							txtAdresse.setText(unTableau.getValueAt(numLigne, 3).toString());
							txtEmail.setText(unTableau.getValueAt(numLigne, 4).toString());
							txtDiplome.setText(unTableau.getValueAt(numLigne, 5).toString());
							btEnregistrer.setText("Modifier");
						}
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
						
					}
			
				});
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btRechercher.addActionListener(this);


		
	}
	
	
	public Object [][] getTechniciens(String mot)
	{
		//methode qui transforme l'ArrayList des Techniciens en une matrice [][]
		
		// remplacer les TableauxTech par Technicien
		ArrayList<Technicien> lesTechs = Modele.selectAllTechniciens(mot); 
		Object matrice [][] = new Object [lesTechs.size()][6];
		int i=0; 
		for (Technicien unTech : lesTechs) //echanger tab par tech
		{
			matrice[i][0] = unTech.getIdpersonne(); 
			matrice[i][1] = unTech.getNom(); 
			matrice[i][2] = unTech.getPrenom(); 
			matrice[i][3] = unTech.getAdresse(); 
			matrice[i][4] = unTech.getEmail(); 
			matrice[i][5] = unTech.getDiplome();  
			i++; 
		}
		return matrice; 
	}
	
	public void viderChamps () {
		
		this.txtPrenom.setText("");
		this.txtNom.setText("");
		this.txtAdresse.setText("");
		this.txtEmail.setText("");
		this.txtDiplome.setText("");
	}
	public void traitement (int choix) {
		
		String nom = this.txtNom.getText(); 
		String prenom = this.txtPrenom.getText();
		String email = this.txtEmail.getText(); 
		String adresse = this.txtAdresse.getText(); 
		String diplome = this.txtDiplome.getText(); 
		
		if (choix ==1 ) {
			
			//instancier la classe TableauTech vu qu on travail dans la vue
			//j'ai remplacer tableautech par technicien
			Technicien unTech = new Technicien(nom, prenom, adresse,email, diplome); //idTechnicien = 0
			//insertion du Technicien dans la BDD 
			
			
			Modele.insertTechnicien(unTech);//insertion procedure via la requete call
			JOptionPane.showMessageDialog(this, "Insertion r�ussie");
			unTech = Modele.selectWhereTechnicien(email, diplome); //on peut recuperer idpersonne via cette methode selectWTech
			
			//actualisation de l'affichage 
			Object ligne[] = {unTech.getIdpersonne(), unTech.getNom(), unTech.getPrenom(), 
					unTech.getAdresse(),unTech.getEmail(), unTech.getDiplome()};
			this.unTableau.ajouterLigne(ligne);
			
		} else {
			
			int numLigne = uneTable.getSelectedRow(); 
			int idPersonne = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
			//instancier la classe Technicien 
			Technicien unTech = new Technicien(idPersonne, nom, prenom, adresse, email, diplome);
			//Mise � jour dans la base 
			Modele.updateTechnicien(unTech);
			//Mise � jour de l'affichage 
			Object ligne[] = {unTech.getIdpersonne(), unTech.getNom(), unTech.getPrenom(), 
					unTech.getAdresse(),unTech.getEmail(), unTech.getDiplome()};
			this.unTableau.ajouterLigne(ligne);
			
			
			unTableau.modifierLigne(numLigne, ligne); 
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
			this.viderChamps(); 
		}
		else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Enregistrer"))
		{
			this.traitement(1);
		}
		else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Modifier"))
		{
			this.traitement(2);
		}
		
		else if(e.getSource() == this.btRechercher)
		{
			String mot = this.txtMot.getText(); 
			this.unTableau.setDonnees(this.getTechniciens(mot));
		}
		
	}

	

}
