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

import controleur.Magasin;
import controleur.Tableau;
import modele.Modele;

public class PanelMagasins extends PanelDeBase implements ActionListener{
	
	private JPanel panelForm = new JPanel(); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JTextField txtNom = new JTextField(); 
	private JTextField txtAdresse = new JTextField();
	private JTextField txtEmail = new JTextField(); 
	private JTextField txtTelephone = new JTextField();
	//private JComboBox<String> txtIdpersonne  = new JComboBox<String>();
	

	
	private JTable uneTable ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau; 
	
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");
	private JPanel panelRechercher = new JPanel();
	
	public PanelMagasins()
	{
		super(Color.gray);
		
		//construction du panel Form
		this.panelForm.setBounds(10, 40, 400, 420);//10,40,250,240 //10,60,400,400
		this.panelForm.setLayout(new GridLayout(5, 2));
		this.panelForm.add(new JLabel("Nom Mag : ")); 
		this.panelForm.add(this.txtNom); 
		this.panelForm.add(new JLabel("Adresse Mag : ")); 
		this.panelForm.add(this.txtAdresse);
		this.panelForm.add(new JLabel("Email Mag: ")); 
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(new JLabel("Telephone Mag: ")); 
		this.panelForm.add(this.txtTelephone);
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer);
		this.panelForm.setVisible(true);
		this.add(this.panelForm); 
		
		
		//construction panel rechercher
		this.panelRechercher.setBounds(600, 40, 400, 40);//290,40,400,30 //600,60,400,40
		this.panelRechercher.setLayout(new GridLayout(1,2));
		this.panelRechercher.add(new JLabel("Filtrer les mag par :"));
		this.panelRechercher.add(this.txtMot);
		this.panelRechercher.add(this.btRechercher);
		this.add(panelRechercher);
		
		//construction de la Jscroll lister les montures 
		String entetes [] = {"idMagasin", "Nom", "Adresse", "Email", "Telephone"};
		this.unTableau = new Tableau(getMagasins(""), entetes);
		
		
		this.uneTable = new JTable(this.unTableau); 
		this.uneScroll = new JScrollPane(this.uneTable); 
		this.uneScroll.setBounds(500,100,650,350);//290,40,400,240 ; 650, 100, 400, 450
		this.add(this.uneScroll); 
		
		this.uneTable.addMouseListener(new MouseListener()
				{

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
						int numLigne = uneTable.getSelectedRow();
						if(e.getClickCount() == 2)//vsi le nombre de clic sur le resultat = 2 alors on supprimera
						{
							int idMagasin = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());//mmettre idpersonne maybe
							int retour = JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer le Magasin",
									"Suppression du Magasin", JOptionPane.YES_NO_OPTION); 
							
							if(retour == 0) //si apres 2 clic on touche sur la sortie int 0 on supprime
							{
								Modele.deleteMagasin(idMagasin);
								unTableau.supprimerLigne(numLigne);
							}
						}
						
						else if(e.getClickCount() == 1)//vsi le nombre de clic sur le resultat = 1 alors on modifie
						{
							txtNom.setText(unTableau.getValueAt(numLigne, 1).toString());
							txtAdresse.setText(unTableau.getValueAt(numLigne, 2).toString());
							txtEmail.setText(unTableau.getValueAt(numLigne, 3).toString());
							txtTelephone.setText(unTableau.getValueAt(numLigne, 4).toString());
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

		
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btRechercher.addActionListener(this);

	}
	
	
	public Object [][] getMagasins (String mot)
	{
		//methode qui transforme l'ArrayList des Magasins en une matrice [][]
		ArrayList<Magasin> lesMagasins = Modele.selectAllMagasins(mot); 
		Object matrice [][] = new Object [lesMagasins.size()][5];
		int i=0; 
		for (Magasin unMagasin : lesMagasins)
		{
			matrice[i][0] = unMagasin.getIdmagasin(); 
			matrice[i][1] = unMagasin.getNom(); 
			matrice[i][2] = unMagasin.getAdresse(); 
			matrice[i][3] = unMagasin.getEmail(); 
			matrice[i][4] = unMagasin.getTelephone();  
			i++; 
		}
		return matrice; 
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
			this.unTableau.setDonnees(this.getMagasins(mot));
			
		}
		
	}
	
	public void viderChamps () {
		this.txtNom.setText("");
		this.txtAdresse.setText("");
		this.txtEmail.setText("");
		this.txtTelephone.setText("");
	}
	public void traitement (int choix) {
		String nom = this.txtNom.getText(); 
		String email = this.txtEmail.getText(); 
		String adresse = this.txtAdresse.getText(); 
		String telephone = this.txtTelephone.getText(); 
		
		if (choix ==1 ) {
			//instancier la classe Magasin 
			Magasin unMagasin = new Magasin (nom, adresse, email, telephone); //idMagasin = 0
			//insertion du Magasin dans la BDD 
			Modele.insertMagasin(unMagasin);
			JOptionPane.showMessageDialog(this, "Insertion r�ussie");
			unMagasin = Modele.selectWhereMagasin(nom, telephone); 
			
			//actualisation de l'affichage 
			Object ligne[] = {unMagasin.getIdmagasin(), unMagasin.getNom(),
					unMagasin.getAdresse(),unMagasin.getEmail(), unMagasin.getTelephone()};
			this.unTableau.ajouterLigne(ligne);
		} else {
			int numLigne = uneTable.getSelectedRow(); 
			int idMagasin = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
			//instancier la classe Magasin 
			Magasin unMagasin = new Magasin (idMagasin, nom, adresse, email, telephone);
			//Mise � jour dans la base 
			Modele.updateMagasin(unMagasin);
			//Mise � jour de l'affichage 
			Object ligne[] = {unMagasin.getIdmagasin(), unMagasin.getNom(), 
				unMagasin.getAdresse(),unMagasin.getEmail(), unMagasin.getTelephone()};
			
			unTableau.modifierLigne(numLigne, ligne); 
			JOptionPane.showMessageDialog(this, "Modification r�ussie");
		}
		
		this.btEnregistrer.setText("Enregistrer");
		//vider les champs apr�s insertion 
		this.viderChamps();
	}

}
