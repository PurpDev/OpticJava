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

import controleur.Tableau;
import controleur.Verre;
import modele.Modele;

public class PanelVerres extends PanelDeBase implements ActionListener{
	
	private JPanel panelForm = new JPanel(); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JTextField txtVision = new JTextField(); 
	private JTextField txtCouleurV = new JTextField(); 
	private JTextField txtMatiereV = new JTextField(); 
	private JTextField txtLaboratoire = new JTextField();

	private JTable uneTable ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau; 
	
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");
	private JPanel panelRechercher = new JPanel();
	
	
	public PanelVerres() {
		
		super(Color.gray);
		
		//construction du panelForme
		this.panelForm.setBounds(10, 40, 400, 420);//10,40,250,240
		this.panelForm.setLayout(new GridLayout(5, 2));
		this.panelForm.add(new JLabel("Vision du verre : ")); 
		this.panelForm.add(this.txtVision); 
		this.panelForm.add(new JLabel("Couleur du verre : ")); 
		this.panelForm.add(this.txtCouleurV);
		this.panelForm.add(new JLabel("Matiere du verre : ")); 
		this.panelForm.add(this.txtMatiereV);
		this.panelForm.add(new JLabel("Nom du laboratoire : ")); 
		this.panelForm.add(this.txtLaboratoire);
		
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer);
		this.panelForm.setVisible(true);
		this.add(this.panelForm); 
		
		
		//construction panel rechercher
		this.panelRechercher.setBounds(600, 40, 400, 40);//290,40,400,30
		this.panelRechercher.setLayout(new GridLayout(1,3));
		this.panelRechercher.add(new JLabel("Filtrer les verres par :"));
		this.panelRechercher.add(this.txtMot);
		this.panelRechercher.add(this.btRechercher);
		this.add(panelRechercher);
		
		//construction de la Jscroll lister les pilotes 
		String entetes[] = {"idVerre", "Vision", "CouleurV", "MatiereV", "Laboratoire"};
		this.unTableau = new Tableau(getVerres(""), entetes);
		
		this.uneTable = new JTable(this.unTableau); 
		this.uneScroll = new JScrollPane(this.uneTable); 
		this.uneScroll.setBounds(500,100,650,350);//290,40,400,240
		this.add(this.uneScroll); 
		

		//traitement de la suppression 
		this.uneTable.addMouseListener(new MouseListener() 
		{

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
				int numLigne = uneTable.getSelectedRow(); 
				if(e.getClickCount() == 2)
				{
					int idVerre = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
					int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer le verre",
							"Suppression du Verre", JOptionPane.YES_NO_OPTION);
				
					if(retour == 0)
					{
						Modele.deleteVerre(idVerre);
						unTableau.supprimerLigne(numLigne);
					}
				
				}
				
				else if(e.getClickCount() == 1)
				{
					txtVision.setText(unTableau.getValueAt(numLigne, 1).toString());
					txtCouleurV.setText(unTableau.getValueAt(numLigne, 2).toString());
					txtMatiereV.setText(unTableau.getValueAt(numLigne, 3).toString());
					txtLaboratoire.setText(unTableau.getValueAt(numLigne, 4).toString());
					btEnregistrer.setText("Modifier");

				
				}
				
			}
			
		});//mettre la parenthese ici bloque l erreur pk?
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btRechercher.addActionListener(this);
		
	}
	
	public Object [][] getVerres(String mot)
	{
		//methode qui transforme l'ArrayList des pilotes en une matrice [][]
		ArrayList<Verre> lesVerres = Modele.selectAllVerres(mot);
		Object matrice [][] = new Object [lesVerres.size()][5];
		int i = 0;
		for(Verre unVerre: lesVerres)
		{
			matrice[i][0] = unVerre.getIdverre();
			matrice[i][1] = unVerre.getVision();
			matrice[i][2] = unVerre.getCouleurV();
			matrice[i][3] = unVerre.getMatiereV();
			matrice[i][4] = unVerre.getLaboratoire();
			i++;

		}
		return matrice;
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
			this.unTableau.setDonnees(this.getVerres(mot));
			
		}
	}
	
	public void viderChamps () {
		this.txtVision.setText("");
		this.txtCouleurV.setText("");
		this.txtMatiereV.setText("");
		this.txtLaboratoire.setText("");
	}
	
	public void traitement(int choix)
	{
		String vision = this.txtVision.getText(); 
		String couleurV = this.txtCouleurV.getText(); 
		String matiereV = this.txtMatiereV.getText(); 
		String laboratoire = this.txtLaboratoire.getText(); 
		
		if (choix ==1 ) {
			
			//instancier la classe Verre 
			Verre unVerre = new Verre(vision, couleurV, matiereV, laboratoire); //idpilote = 0
			
			//insertion du pilote dans la BDD 
			Modele.insertVerre(unVerre);
			JOptionPane.showMessageDialog(this, "Insertion r�ussie");
			
			unVerre = Modele.selectWhereVerre(vision, matiereV); 
			
			//actualisation de l'affichage 
			Object ligne[] = {unVerre.getIdverre(), unVerre.getVision(), unVerre.getCouleurV(), 
					unVerre.getMatiereV(), unVerre.getLaboratoire()};
			this.unTableau.ajouterLigne(ligne);
			
		} else {
			
			int numLigne = uneTable.getSelectedRow(); 
			int idVerre = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
			
			//instancier la classe Verre 
			Verre unVerre = new Verre (idVerre, vision, couleurV, matiereV, laboratoire);
			
			//Mise � jour dans la base 
			Modele.updateVerre(unVerre);
			
			//Mise � jour de l'affichage 
			Object ligne[] = {unVerre.getIdverre(), unVerre.getVision(), unVerre.getCouleurV(), 
					unVerre.getMatiereV(), unVerre.getLaboratoire()};
			unTableau.modifierLigne(numLigne, ligne); 
			JOptionPane.showMessageDialog(this, "Modification r�ussie");
		}
		
		this.btEnregistrer.setText("Enregistrer");
		
		//vider les champs apr�s insertion 
		this.viderChamps();
	}
	
	
	
	

}
