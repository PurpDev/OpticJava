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

import controleur.Monture;
import controleur.Tableau;
import modele.Modele;

public class PanelMontures extends PanelDeBase implements ActionListener{
	
	
	private JPanel panelForm = new JPanel(); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JTextField txtForme = new JTextField(); 
	private JTextField txtMatiereM = new JTextField(); 
	
	
	private JTable uneTable ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau; 
	
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");
	private JPanel panelRechercher = new JPanel();

	public PanelMontures() {
		super(Color.GRAY);
		
		this.panelForm.setBounds(10, 40, 400, 420);//10,60,400,400
		this.panelForm.setLayout(new GridLayout(3, 2));
		this.panelForm.add(new JLabel("Forme lunette : ")); 
		this.panelForm.add(this.txtForme); 
		this.panelForm.add(new JLabel("Matiere monture : ")); 
		this.panelForm.add(this.txtMatiereM);
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer);
		this.panelForm.setVisible(true);
		this.add(this.panelForm);
		
		//construction panel rechercher
		this.panelRechercher.setBounds(600, 40, 400, 40); //650,60,400,40
		this.panelRechercher.setLayout(new GridLayout(1,3));
		this.panelRechercher.add(new JLabel("Filtrer les pilotes par :"));
		this.panelRechercher.add(this.txtMot);
		this.panelRechercher.add(this.btRechercher);
		this.add(panelRechercher);
		
		//construction de la Jscroll lister les montures 
		String entetes [] = {"idMonture", "Forme", "MatiereM"};
		this.unTableau = new Tableau (getMontures(""), entetes); 
		
		this.uneTable = new JTable(this.unTableau); 
		this.uneScroll = new JScrollPane(this.uneTable); 
		this.uneScroll.setBounds(500,100,650,350);//650,100,400,400
		this.add(this.uneScroll); 
		
		this.uneTable.addMouseListener(new MouseListener()
				{

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						int numLigne = uneTable.getSelectedRow(); 
						if(e.getClickCount() == 2)
						{
							int idMonture = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
							int retour = JOptionPane.showConfirmDialog(null,"Voulez-vous supprimer le Monture",
									"Suppression de la Monture", JOptionPane.YES_NO_OPTION); 
							if (retour == 0)
							{
								Modele.deleteMonture(idMonture); 
								unTableau.supprimerLigne(numLigne); 
							}
						}
						else if (e.getClickCount() == 1)
						{
							txtForme.setText(unTableau.getValueAt(numLigne, 1).toString());
							txtMatiereM.setText(unTableau.getValueAt(numLigne, 2).toString());
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
	
	
	
	public Object [][] getMontures(String mot)
	{
		ArrayList<Monture> lesMontures = Modele.selectAllMontures(mot); 
		Object matrice [][] = new Object [lesMontures.size()][3];
		int i=0; 
		for (Monture uneMonture : lesMontures)
		{
			matrice[i][0] = uneMonture.getIdmonture(); 
			matrice[i][1] = uneMonture.getForme(); 
			matrice[i][2] = uneMonture.getMatiereM(); 
			 
			i++; 
		}
		return matrice; 
	}
	
	public void viderChamps () {
		this.txtForme.setText("");
		this.txtMatiereM.setText("");
	
	}
	
	public void traitement (int choix) {
		String forme = this.txtForme.getText(); 
		String matiereM = this.txtMatiereM.getText(); 
		
		
		if (choix ==1 ) {
						//instancier la classe Monture 
						Monture uneMonture = new Monture (forme, matiereM); //idMonture = 0
						//insertion du Monture dans la BDD 
						Modele.insertMonture(uneMonture);
						JOptionPane.showMessageDialog(this, "Insertion r�ussie");
						uneMonture = Modele.selectWhereMonture(forme, matiereM); 
						
						//actualisation de l'affichage 
						Object ligne[] = {uneMonture.getIdmonture(), uneMonture.getForme(), uneMonture.getMatiereM()};
						this.unTableau.ajouterLigne(ligne);
		} else {
			int numLigne = uneTable.getSelectedRow(); 
			int idMonture = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
			//instancier la classe Monture 
			Monture uneMonture = new Monture (idMonture, forme, matiereM);
			//Mise � jour dans la base 
			Modele.updateMonture(uneMonture);
			//Mise � jour de l'affichage 
			Object ligne[] = {uneMonture.getIdmonture(), uneMonture.getForme(), uneMonture.getMatiereM()};
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
			this.unTableau.setDonnees(this.getMontures(mot));
			
		}
	}
	
	



}
