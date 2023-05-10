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
import controleur.Magasin;
import controleur.Reservation;
import controleur.Tableau;
import controleur.TableauCli;
import controleur.TableauReserv;
import modele.Modele;

public class PanelReservations extends PanelDeBase implements ActionListener{
	private JPanel panelForm = new JPanel(); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JTextField txtHoraire = new JTextField(); 

	private JComboBox<String> txtIdLunette = new JComboBox<String>();
	
	//private JTextField txtPrix = new JTextField(); 


	private JTable uneTable ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau; 
	
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");
	private JPanel panelRechercher = new JPanel();
	
	public PanelReservations() {
		super(Color.gray);
		
		this.panelForm.setBounds(10, 60, 500, 400);
		this.panelForm.setLayout(new GridLayout(3, 2));
		this.panelForm.add(new JLabel("Horaire : ")); 
		this.panelForm.add(this.txtHoraire); 
		this.panelForm.add(new JLabel("ID Lunette : ")); 
		this.panelForm.add(this.txtIdLunette);
		//this.panelForm.add(new JLabel("Prix : ")); 
		//this.panelForm.add(this.txtPrix); 
		this.panelForm.setVisible(true);
		this.add(this.panelForm); 
		
		

		//construction panel rechercher
		this.panelRechercher.setBounds(650, 60, 400, 40);//290,40,400,30
		this.panelRechercher.setLayout(new GridLayout(1,2));
		this.panelRechercher.add(new JLabel("Filtrer les reservations par :"));
		this.panelRechercher.add(this.txtMot);
		this.panelRechercher.add(this.btRechercher);
		this.add(panelRechercher);

		
		String entetes[] = {"idReservation", "Horaire","idLunette"};
		this.unTableau = new Tableau (getReservations(""), entetes); 
		
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer);
		
		
		this.uneTable = new JTable(this.unTableau); 
		this.uneScroll = new JScrollPane(this.uneTable); 
		this.uneScroll.setBounds(650,100,400,400);
		this.add(this.uneScroll);
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {

				int numLigne = uneTable.getSelectedRow();
				if(e.getClickCount() == 2)//vsi le nombre de clic sur le resultat = 2 alors on supprimera
				{
					int idreservation = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
					int retour = JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer la Reservation",
							"Suppression de la Reservation", JOptionPane.YES_NO_OPTION); 
					
					if(retour == 0) //si apres 2 clic on touche sur la sortie int 0 on supprime
					{
						Modele.deleteReservation(idreservation);
						unTableau.supprimerLigne(numLigne);
					}
				}

				else if(e.getClickCount() == 1)//vsi le nombre de clic sur le resultat = 1 alors on modifie
				{
					int idlunette = Integer.parseInt(unTableau.getValueAt(numLigne, 2).toString());// j 'ai fait ça pour convertir idverre en int comme idlunette
					txtHoraire.setText(unTableau.getValueAt(numLigne, 1).toString());
					//txtPrix.setText(unTableau.getValueAt(numLigne, 3).toString());


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

	
	}
	
	private Object[][] getReservations(String string) {
		
		ArrayList<Reservation> lesReservations = Modele.selectAllReservations();
		Object matrice [][] = new Object [lesReservations.size()][3];
		int i=0; 
		for (Reservation uneReservation : lesReservations)
		{
			matrice[i][0] = uneReservation.getIdreservation(); 
			matrice[i][1] = uneReservation.getHoraire(); 
			matrice[i][2] = uneReservation.getIdlunette(); 


			i++; 
		}
		return matrice; 
	}
	
	/*public void remplirCombos()
	{
		ArrayList<Lunettte> lesLunettes = Modele.selectAllLunettes();
		this.txtIdLunette.removeAllItems();
		
		for(Lunette uneLunette : lesLunettes)
		{
			this.txtIdLunette.addItem(uneLunette.getIdlunette()+ "-" + uneLunette.getVision());//dans mon combobox j'ai pris le champs pas le AS 			
		}
		
	}*/
	
	
	public void remplirCombos()
	{
		ArrayList<TableauReserv> lesTabReservs = Modele.selectAllTabReserv();
		//this.txtIdPersonne.removeAllItems();
		
		for(TableauReserv unTabReserv : lesTabReservs)
		{
			this.txtIdLunette.addItem(unTabReserv.getIdlunette()+ "-" + unTabReserv.getIdlunette());
			//this.txtIdLunette.removeAllItems();
		}
		
	}
	

	public void viderChamps () {
		this.txtHoraire.setText("");
		
	}
	
	public void traitement (int choix) {
		String horaire = this.txtHoraire.getText(); 
		//String prix = this.txtPrix.getText();
		
		String chaine = this.txtIdLunette.getSelectedItem().toString();
		String tab[] = chaine.split("-");
		int idlunette = Integer.parseInt(tab[0]);
		
		
		if (choix ==1 ) {
			//instancier la classe Reservation 
			Reservation uneReservation = new Reservation (horaire, idlunette);
			
			//insertion du Reservation dans la BDD 
			Modele.insertReservation(uneReservation);
			JOptionPane.showMessageDialog(this, "Insertion réussie");
			
			uneReservation = Modele.selectWhereReservation(horaire, idlunette); 
			
			//actualisation de l'affichage 
			Object ligne[] = {uneReservation.getIdreservation(), uneReservation.getHoraire(), uneReservation.getIdlunette()};
			this.unTableau.ajouterLigne(ligne);
			
			
		} else {
			int numLigne = uneTable.getSelectedRow(); 
			int idreservation = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
			
			//instancier la classe Reservation 
			Reservation uneReservation = new Reservation (idreservation, horaire, idlunette);
			
			//Mise à jour dans la base 
			Modele.updateReservation(uneReservation);
			
			//Mise à jour de l'affichage 
			Object ligne[] = {uneReservation.getIdreservation(), uneReservation.getHoraire(),uneReservation.getIdlunette()};
			this.unTableau.ajouterLigne(ligne);
			JOptionPane.showMessageDialog(this, "Modification réussie");
		}
		
		this.btEnregistrer.setText("Enregistrer");
		//vider les champs après insertion 
		this.viderChamps();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
