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

import controleur.Magasin;
import controleur.Rdv;
import controleur.Tableau;
import controleur.TableauCli;
import modele.Modele;

public class PanelRdvs extends PanelDeBase implements ActionListener{
	private JPanel panelForm = new JPanel(); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JTextField txtMotif = new JTextField(); 
	private JTextField txtDaterdv = new JTextField(); 
	private JTextField txtHeuredebut = new JTextField();
	private JTextField txtHeurefin = new JTextField(); 
	

	private JComboBox<String> txtIdPersonne = new JComboBox<String>();
	private JComboBox<String> txtIdMagasin = new JComboBox<String>();
	
	private JTable uneTable ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau; 
	
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");
	private JPanel panelRechercher = new JPanel();
	

	public PanelRdvs() {
		super(Color.GRAY);
		
		this.panelForm.setBounds(10, 40, 400, 420);//10,40,250,240, 10,60,500,400
		this.panelForm.setLayout(new GridLayout(7, 2));
		this.panelForm.add(new JLabel("Motif : ")); 
		this.panelForm.add(this.txtMotif); 
		this.panelForm.add(new JLabel("Date rdv : ")); 
		this.panelForm.add(this.txtDaterdv);
		this.panelForm.add(new JLabel("Heure debut: ")); 
		this.panelForm.add(this.txtHeuredebut);
		this.panelForm.add(new JLabel("Heure fin: ")); 
		this.panelForm.add(this.txtHeurefin);
		this.panelForm.add(new JLabel("ID Client: ")); 
		this.panelForm.add(this.txtIdPersonne);
		this.panelForm.add(new JLabel("ID Magasin: ")); 
		this.panelForm.add(this.txtIdMagasin);
		this.panelForm.setVisible(true);
		this.add(this.panelForm); 
		
		
		//construction panel rechercher
		this.panelRechercher.setBounds(600, 40, 400, 40);//650,60,400,40
		this.panelRechercher.setLayout(new GridLayout(1,2));
		this.panelRechercher.add(new JLabel("Filtrer les lunettes par :"));
		this.panelRechercher.add(this.txtMot);
		this.panelRechercher.add(this.btRechercher);
		this.add(panelRechercher);
		
		String entetes[] = {"idRdv", "Motif", "DateRDV", "HeureDebut", "HeureFin", "idClient", "idMagasin"};
		this.unTableau = new Tableau(getRdvs(""), entetes);
	
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer);
		
		this.uneTable = new JTable(this.unTableau); 
		this.uneScroll = new JScrollPane(this.uneTable); 
		this.uneScroll.setBounds(500,100,650,350);//290,40,400,240 ; 650, 130, 400, 400
		this.add(this.uneScroll);
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
				int numLigne = uneTable.getSelectedRow();
				if(e.getClickCount() == 2)
				{
					int idrdv = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
					int retour = JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer le Rdv",
							"Suppression du Rdv", JOptionPane.YES_NO_OPTION); 
					
					if(retour == 0) //si apres 2 clic on touche sur la sortie int 0 on supprime
					{
						Modele.deleteRdv(idrdv);
						unTableau.supprimerLigne(numLigne);
					}
				}

				else if(e.getClickCount() == 1)
				{
					

					int idpersonne = Integer.parseInt(unTableau.getValueAt(numLigne, 5).toString());// j 'ai fait �a pour convertir idverre en int comme idlunette
					int idmagasin = Integer.parseInt(unTableau.getValueAt(numLigne, 6).toString());
							
					txtMotif.setText(unTableau.getValueAt(numLigne, 1).toString());
					txtDaterdv.setText(unTableau.getValueAt(numLigne, 2).toString());
					txtHeuredebut.setText(unTableau.getValueAt(numLigne, 3).toString());
					txtHeurefin.setText(unTableau.getValueAt(numLigne, 4).toString());
					

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
		
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btRechercher.addActionListener(this);
		
		
	}
	

	private Object[][] getRdvs(String string) {
		// TODO Auto-generated method stub
		
		ArrayList<Rdv> lesRdvs = Modele.selectAllRdvs(); // j ai du duppliquer selectAllRdv en retirant la var mot de la fonction et de la requete de la fonction 
		Object matrice [][] = new Object [lesRdvs.size()][7];
		int i=0; 
		for (Rdv unRdv : lesRdvs)
		{
			matrice[i][0] = unRdv.getIdrdv(); 
			matrice[i][1] = unRdv.getMotif(); 
			matrice[i][2] = unRdv.getDateRdv(); 
			matrice[i][3] = unRdv.getHeuredebut(); 
			matrice[i][4] = unRdv.getHeurefin(); 
			matrice[i][5] = unRdv.getIdpersonne(); 
			matrice[i][6] = unRdv.getIdmagasin(); 

			i++; 
		}
		return matrice; 
	}
	
	public void remplirCombos()
	{
		ArrayList<TableauCli> lesTableauxClis = Modele.selectAllTabClis();
		this.txtIdPersonne.removeAllItems();
		
		for(TableauCli unTableauCli : lesTableauxClis)
		{
			this.txtIdPersonne.addItem(unTableauCli.getIdpersonne()+ "-" + unTableauCli.getNom());//dans mon combobox j'ai pris le champs pas le AS 			
		}
		
		ArrayList<Magasin> lesMagasins = Modele.selectAllMagasins("");
		for(Magasin unMagasin : lesMagasins)
		{
			this.txtIdMagasin.addItem(unMagasin.getIdmagasin()+ "-" + unMagasin.getNom());

			
		}
	}

	public void viderChamps () {
		this.txtMotif.setText("");
		this.txtDaterdv.setText("");
		this.txtHeuredebut.setText("");
		this.txtHeurefin.setText("");
		
		
	}
	public void traitement (int choix) {
		String motif = this.txtMotif.getText(); 
		String daterdv = this.txtDaterdv.getText(); 
		String heuredebut = this.txtHeuredebut.getText();
		String heurefin = this.txtHeurefin.getText();
		
		String chaine = this.txtIdPersonne.getSelectedItem().toString();
		String tab[] = chaine.split("-");
		int idpersonne = Integer.parseInt(tab[0]);
		
		chaine = this.txtIdMagasin.getSelectedItem().toString();
		tab = chaine.split("-");
		int idmagasin = Integer.parseInt(tab[0]);
		
		if (choix ==1 ) {
			//instancier la classe Rdv 
			Rdv unRdv = new Rdv (motif, daterdv, heuredebut, heurefin, idpersonne, idmagasin );
			
			//insertion du Rdv dans la BDD 
			Modele.insertRdv(unRdv);
			JOptionPane.showMessageDialog(this, "Insertion r�ussie");
			
			unRdv = Modele.selectWhereRdv(motif, daterdv); 
			
			//actualisation de l'affichage 
			Object ligne[] = {unRdv.getIdrdv(), unRdv.getMotif(), unRdv.getDateRdv(), 
					unRdv.getHeuredebut(), unRdv.getHeurefin(), unRdv.getIdpersonne(),unRdv.getIdmagasin()};
			this.unTableau.ajouterLigne(ligne);
			
			
		} else {
			int numLigne = uneTable.getSelectedRow(); 
			int idRdv = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
			
			//instancier la classe Rdv 
			Rdv unRdv = new Rdv (idRdv, motif, daterdv, heuredebut, heurefin, idpersonne, idmagasin );
			
			//Mise � jour dans la base 
			Modele.updateRdv(unRdv);
			
			//Mise � jour de l'affichage 
			Object ligne[] = {unRdv.getIdrdv(), unRdv.getMotif(), unRdv.getDateRdv(), 
					unRdv.getHeuredebut(), unRdv.getHeurefin(), unRdv.getIdpersonne(),unRdv.getIdmagasin()};
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
			this.unTableau.setDonnees (this.getRdvs(mot));
			
		}
		
	}
	
	

	
}
