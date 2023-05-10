package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Client;
//import controleur.Client;
import controleur.Personne;
import controleur.Tableau;
//import controleur.TableauCli;

import modele.Modele;

public class PanelClients extends PanelDeBase implements ActionListener {
	
	private JPanel panelForm = new JPanel(); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JTextField txtNom = new JTextField(); 
	private JTextField txtPrenom = new JTextField(); 
	private JTextField txtAdresse = new JTextField();
	private JTextField txtEmail = new JTextField(); 
	private JTextField txtAge = new JTextField();
	//private JIntField intAge = new JIntField();
	private JTextField txtTelephone = new JTextField();

	
	//private JComboBox<String> txtIdpersonne  = new JComboBox<String>();
	

	
	private JTable uneTable ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau; 
	
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");
	private JPanel panelRechercher = new JPanel();

	public PanelClients() {
		super(Color.GRAY);
		
		// TODO Auto-generated constructor stub
		
		//construction du panel Form
		this.panelForm.setBounds(10, 40, 400, 420);//10,40,250,240
		this.panelForm.setLayout(new GridLayout(7, 2));
		this.panelForm.add(new JLabel("Nom Cli : ")); 
		this.panelForm.add(this.txtNom); 
		this.panelForm.add(new JLabel("Pr�nom Cli : ")); 
		this.panelForm.add(this.txtPrenom);
		this.panelForm.add(new JLabel("Adresse Cli : ")); 
		this.panelForm.add(this.txtAdresse);
		this.panelForm.add(new JLabel("Email Cli: ")); 
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(new JLabel("Age Cli: ")); 
		this.panelForm.add(this.txtAge);
		this.panelForm.add(new JLabel("Tel Cli: ")); 
		this.panelForm.add(this.txtTelephone);
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer);
		this.panelForm.setVisible(true);
		this.add(this.panelForm); 
		
		
		//construction panel rechercher
		this.panelRechercher.setBounds(600, 40, 400, 40);//290,40,400,30
		this.panelRechercher.setLayout(new GridLayout(1,2));
		this.panelRechercher.add(new JLabel("Filtrer les clients par :"));
		this.panelRechercher.add(this.txtMot);
		this.panelRechercher.add(this.btRechercher);
		this.add(panelRechercher);
		
		String entetes [] = {"idPersonne", "Nom", "Pr�nom", "Adresse", "Email", "Age",  "Telephone"};
		this.unTableau = new Tableau(getClients(""), entetes);
		
		
		this.uneTable = new JTable(this.unTableau); 
		this.uneScroll = new JScrollPane(this.uneTable); 
		this.uneScroll.setBounds(500,100,650,350);//290,40,400,240 ; 550, 130, 600, 450
		this.add(this.uneScroll); 
		
		
		/*this.uneTable.addMouseListener(new MouseListener()
				{
			
				});
		
		*/
		
		this.uneTable.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int numLigne = uneTable.getSelectedRow();
				if(e.getClickCount() == 2)//vsi le nombre de clic sur le resultat = 2 alors on supprimera
				{
					int idPersonne = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());//mmettre idpersonne maybe
					int retour = JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer le Client",
							"Suppression de le Client", JOptionPane.YES_NO_OPTION); 
					
					if(retour == 0) //si apres 2 clic on touche sur la sortie int 0 on supprime
					{
						Modele.deleteClient(idPersonne);
						unTableau.supprimerLigne(numLigne);
					}
				}
				
				else if(e.getClickCount() == 1)//si le nombre de clic sur le resultat = 1 alors on modifie
				{
					txtNom.setText(unTableau.getValueAt(numLigne, 1).toString());
					txtPrenom.setText(unTableau.getValueAt(numLigne, 2).toString());
					txtAdresse.setText(unTableau.getValueAt(numLigne, 3).toString());
					txtEmail.setText(unTableau.getValueAt(numLigne, 4).toString());
					txtAge.setText(unTableau.getValueAt(numLigne, 5).toString());
					txtTelephone.setText(unTableau.getValueAt(numLigne, 6).toString());
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
	
	
	
	public Object [][] getClients (String mot)
	{
		//methode qui transforme l'ArrayList des TableauClis en une matrice [][]
		ArrayList<Client> lesClients = Modele.selectAllClients(mot); 
		Object matrice [][] = new Object [lesClients.size()][7];
		int i=0; 
		for (Client unClient : lesClients)
		{
			matrice[i][0] = unClient.getIdpersonne(); 
			matrice[i][1] = unClient.getNom(); 
			matrice[i][2] = unClient.getPrenom(); 
			matrice[i][3] = unClient.getAdresse(); 
			matrice[i][4] = unClient.getEmail(); 
			matrice[i][5] = unClient.getAge();
			matrice[i][6] = unClient.getTelephone();  

			i++; 
		}
		return matrice; 
	}
	
	public void viderChamps () {
		this.txtPrenom.setText("");
		this.txtNom.setText("");
		this.txtAdresse.setText("");
		this.txtEmail.setText("");
		this.txtAge.setText("");
		this.txtTelephone.setText("");
	}
	public void traitement (int choix) {
		String nom = this.txtNom.getText(); 
		String prenom = this.txtPrenom.getText();
		String email = this.txtEmail.getText(); 
		String adresse = this.txtAdresse.getText(); 
		String age = this.txtAge.getText();
		//int age2 = Integer.parseInt(age);
		String telephone = this.txtTelephone.getText();
		//TableauCli unCli = null;
		//Client unClient = null;
		
		//il faut emettre une condition pour que le client soit different de null comme dans l C_Client

		
		if (choix ==1 ) {
			
			//trouver comment convertir un int quand on inserer une donn�
			//int numLigne = uneTable.getSelectedRow(); 
			//int age = Integer.parseInt(unTableau.getValueAt(numLigne, 4).toString());

			//instancier la classe TableauCli
			
			//unCli = new TableauCli (nom, prenom, adresse, email, age2, telephone); //idTableauCli = 0
			
			Client unClient = new Client(nom, prenom, adresse, email, age, telephone);
			
			//insertion du TableauCli dans la BDD
		
			Modele.insertionClient(unClient);
			
			JOptionPane.showMessageDialog(this, "Insertion r�ussie");
			
			unClient = Modele.selectWhereClient(nom, telephone); 
			
			//unCli = Modele.selectWhereTabCli(nom, telephone); 

			
			//actualisation de l'affichage 
			Object ligne[] = {unClient.getIdpersonne(), unClient.getNom(), unClient.getPrenom(), 
					unClient.getAdresse(),unClient.getEmail(), unClient.getAge(), unClient.getTelephone()};
			this.unTableau.ajouterLigne(ligne);
		} else {
			
			int numLigne = uneTable.getSelectedRow(); 
			int idPersonne = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
			
			//instancier la classe TableauCli 
			//unCli = new TableauCli (idPersonne, nom, prenom, adresse, email, age2, telephone);
			
			Client unClient = new Client(idPersonne, nom, prenom, adresse, email, age, telephone);
			
			//Mise � jour dans la base 
			//Modele.updateTabCli(unCli);
			Modele.updateClient(unClient);
			
			//Mise � jour de l'affichage 
			Object ligne[] = {unClient.getIdpersonne(), unClient.getNom(), unClient.getPrenom(), 
				unClient.getAdresse(),unClient.getEmail(), unClient.getAge(),  unClient.getTelephone()};
			
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
			this.unTableau.setDonnees(this.getClients(mot));
		}
		
	}

	

}
