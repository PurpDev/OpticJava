package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Opticien;
import controleur.User;
import modele.Modele;

public class VueGenerale extends JFrame implements ActionListener {
	
	//creation du panel menu
	private JPanel panelMenu = new JPanel();
	//private JButton btPersonne = new JButton();
	
	//creation des bouttons
	private JButton btProfil = new JButton("Profil");
	private JButton btClient = new JButton("Clients");
	private JButton btTechnicien = new JButton("Techniciens");
	private JButton btMagasin = new JButton("Magasins");
	private JButton btVerre = new JButton("Verres");
	private JButton btMonture = new JButton("Montures");
	private JButton btLunette = new JButton("Lunettes");
	private JButton btRdv = new JButton("RDV");
	private JButton btReservation = new JButton("Reservations");
	private JButton btStats = new JButton("Stats");
	private JButton btBord = new JButton("Dashbord");
	private JButton btDeconnexion = new JButton("Deconnexion");


	//Creation des Panels
	
	private JPanel panelProfil = new JPanel();
	private PanelClients aPanelClients = new PanelClients();
	private PanelTechniciens aPanelTechniciens = new PanelTechniciens();
	private PanelMagasins aPanelMagasins = new PanelMagasins();
	private PanelVerres aPanelVerres = new PanelVerres();
	private PanelMontures aPanelMontures = new PanelMontures();
	private PanelLunettes aPanelLunettes = new PanelLunettes();
	private PanelRdvs aPanelRdvs = new PanelRdvs();
	private PanelReservations aPanelReservations = new PanelReservations();
	private PanelStats aPanelStats = new PanelStats();
	private PanelBords aPanelBords = new PanelBords();
	
	private JPanel panelCount = new JPanel();
	
	
	
	public void actualiser()
	{
		this.panelCount.removeAll();
		int nbLunettes = Modele.countLunette();
	 
		this.panelCount.add(new JLabel("Nombre de lunettte :" + nbLunettes));
		
		this.panelCount.setVisible(true);//rajout ici
		//this.add(this.panelCount);
		
	}


	
	
	public VueGenerale(User unUser)//rajout d un user
	{
		this.setTitle("Administration des Opticiens de la Paris");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(150, 100, 1450, 700);// 200, 100, 800, 400
		this.setLayout(null);
		
		//this.setSize(1500, 900);
		
		this.setResizable(false);
		this.getContentPane().setBackground(Color.CYAN);
		
		//construction du panel
		this.panelMenu.setLayout(new GridLayout(1, 7));
		this.panelMenu.setBounds(20, 20, 1370, 60);//20,20,760,40
		this.panelMenu.add(this.btProfil);
		this.panelMenu.add(this.btClient);
		this.panelMenu.add(this.btTechnicien);
		this.panelMenu.add(this.btMagasin);
		this.panelMenu.add(this.btVerre);
		this.panelMenu.add(this.btMonture);
		this.panelMenu.add(this.btLunette);
		this.panelMenu.add(this.btRdv);
		this.panelMenu.add(this.btReservation);
		this.panelMenu.add(this.btStats);
		this.panelMenu.add(this.btBord);
		this.panelMenu.add(this.btDeconnexion);
		
		this.panelMenu.setBackground(Color.CYAN);
		this.add(this.panelMenu);
		
		//construction du panel Profil
		this.panelProfil.setBounds(100, 100, 500, 200);
		this.panelProfil.setLayout(new GridLayout(4, 1));
		this.panelProfil.add(new JLabel("Nom User   :" + unUser.getNom()));
		this.panelProfil.add(new JLabel("Prenom User   :" + unUser.getPrenom()));
		this.panelProfil.add(new JLabel("Email User   :" + unUser.getEmail()));
		this.panelProfil.add(new JLabel("Role User   :" + unUser.getTyperole()));
		this.panelProfil.setVisible(true);//rajout ici faux ou true
		this.add(panelProfil);
		
		
		
		//ajout des panel dans la fenetre
		this.add(this.aPanelClients);
		this.add(aPanelTechniciens);
		this.add(aPanelMagasins);
		this.add(aPanelVerres);
		this.add(aPanelMontures);
		this.add(aPanelLunettes);
		this.add(aPanelRdvs);
		this.add(aPanelReservations);
		this.add(aPanelStats);
		this.add(aPanelBords);
		
		//rendre les boutons cliquable
		
		this.btClient.addActionListener(this);
		this.btTechnicien.addActionListener(this);
		this.btMagasin.addActionListener(this);
		this.btVerre.addActionListener(this);
		this.btMonture.addActionListener(this);
		this.btLunette.addActionListener(this);
		this.btRdv.addActionListener(this);
		this.btReservation.addActionListener(this);
		this.btStats.addActionListener(this);
		this.btBord.addActionListener(this);
		this.btDeconnexion.addActionListener(this);
		
		
		
		
		this.setVisible(true);

	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btDeconnexion)
		{
			Opticien.detruireVueGenerale();
			Opticien.rendreVisibleVueConnexion(true);
		}
		
		else if(e.getSource() == btProfil)
		{
			this.panelProfil.setVisible(true);
			this.aPanelClients.setVisible(false);
			this.aPanelTechniciens.setVisible(false);
			this.aPanelMagasins.setVisible(false);
			this.aPanelVerres.setVisible(false);
			this.aPanelMontures.setVisible(false);
			this.aPanelLunettes.setVisible(false);
			this.aPanelRdvs.setVisible(false);
			this.aPanelReservations.setVisible(false);
			this.aPanelStats.setVisible(false);
			this.aPanelBords.setVisible(false);

		}
		
		else if(e.getSource() == btClient)
		{
			this.panelProfil.setVisible(false);
			this.aPanelClients.setVisible(true);
			this.aPanelTechniciens.setVisible(false);
			this.aPanelMagasins.setVisible(false);
			this.aPanelVerres.setVisible(false);
			this.aPanelMontures.setVisible(false);
			this.aPanelLunettes.setVisible(false);
			this.aPanelRdvs.setVisible(false);
			this.aPanelReservations.setVisible(false);
			this.aPanelStats.setVisible(false);
			this.aPanelBords.setVisible(false);


			
		}
		
		else if(e.getSource() == btTechnicien)
		{
			this.panelProfil.setVisible(false);
			this.aPanelClients.setVisible(false);
			this.aPanelTechniciens.setVisible(true);
			this.aPanelMagasins.setVisible(false);
			this.aPanelVerres.setVisible(false);
			this.aPanelMontures.setVisible(false);
			this.aPanelLunettes.setVisible(false);
			this.aPanelRdvs.setVisible(false);
			this.aPanelReservations.setVisible(false);
			this.aPanelStats.setVisible(false);
			this.aPanelBords.setVisible(false);


			
		}
		
		else if(e.getSource() == btMagasin)
		{
			this.panelProfil.setVisible(false);
			this.aPanelClients.setVisible(false);
			this.aPanelTechniciens.setVisible(false);
			this.aPanelMagasins.setVisible(true);
			this.aPanelVerres.setVisible(false);
			this.aPanelMontures.setVisible(false);
			this.aPanelLunettes.setVisible(false);
			this.aPanelRdvs.setVisible(false);
			this.aPanelReservations.setVisible(false);
			this.aPanelStats.setVisible(false);
			this.aPanelBords.setVisible(false);


			
		}
		
		else if(e.getSource() == btVerre)
		{
			this.panelProfil.setVisible(false);
			this.aPanelClients.setVisible(false);
			this.aPanelTechniciens.setVisible(false);
			this.aPanelMagasins.setVisible(false);
			this.aPanelVerres.setVisible(true);
			this.aPanelMontures.setVisible(false);
			this.aPanelLunettes.setVisible(false);
			this.aPanelRdvs.setVisible(false);
			this.aPanelReservations.setVisible(false);
			this.aPanelStats.setVisible(false);
			this.aPanelBords.setVisible(false);


			
		}
		
		else if(e.getSource() == btMonture)
		{
			this.panelProfil.setVisible(false);
			this.aPanelClients.setVisible(false);
			this.aPanelTechniciens.setVisible(false);
			this.aPanelMagasins.setVisible(false);
			this.aPanelVerres.setVisible(false);
			this.aPanelMontures.setVisible(true);
			this.aPanelLunettes.setVisible(false);
			this.aPanelRdvs.setVisible(false);
			this.aPanelReservations.setVisible(false);
			this.aPanelStats.setVisible(false);
			this.aPanelBords.setVisible(false);


			
		}
		
		else if(e.getSource() == btLunette)
		{
			this.panelProfil.setVisible(false);
			this.aPanelClients.setVisible(false);
			this.aPanelTechniciens.setVisible(false);
			this.aPanelMagasins.setVisible(false);
			this.aPanelVerres.setVisible(false);
			this.aPanelMontures.setVisible(false);
			this.aPanelLunettes.setVisible(true);
			this.aPanelLunettes.remplirCombos();
			this.aPanelLunettes.dispoCombos();
			this.aPanelRdvs.setVisible(false);
			this.aPanelReservations.setVisible(false);
			this.aPanelStats.setVisible(false);
			this.aPanelBords.setVisible(false);

			
		}
		
		else if(e.getSource() == btRdv)
		{
			this.panelProfil.setVisible(false);
			this.aPanelClients.setVisible(false);
			this.aPanelTechniciens.setVisible(false);
			this.aPanelMagasins.setVisible(false);
			this.aPanelVerres.setVisible(false);
			this.aPanelMontures.setVisible(false);
			this.aPanelLunettes.setVisible(false);
			this.aPanelLunettes.remplirCombos();
			this.aPanelRdvs.setVisible(true);
			this.aPanelRdvs.remplirCombos();
			this.aPanelReservations.setVisible(false);
			this.aPanelStats.setVisible(false);
			this.aPanelBords.setVisible(false);

		}
		
		else if(e.getSource() == btReservation)
		{
			this.panelProfil.setVisible(false);
			this.aPanelClients.setVisible(false);
			this.aPanelTechniciens.setVisible(false);
			this.aPanelMagasins.setVisible(false);
			this.aPanelVerres.setVisible(false);
			this.aPanelMontures.setVisible(false);
			this.aPanelLunettes.setVisible(false);
			this.aPanelLunettes.remplirCombos();
			this.aPanelRdvs.setVisible(false);
			this.aPanelRdvs.remplirCombos();
			this.aPanelReservations.setVisible(true);
			this.aPanelReservations.remplirCombos();
			this.aPanelStats.setVisible(false);
			this.aPanelBords.setVisible(false);



		}
		
		else if(e.getSource() == btBord)
		{
			this.panelProfil.setVisible(false);
			this.aPanelClients.setVisible(false);
			this.aPanelTechniciens.setVisible(false);
			this.aPanelMagasins.setVisible(false);
			this.aPanelVerres.setVisible(false);
			this.aPanelMontures.setVisible(false);
			this.aPanelLunettes.setVisible(false);
			this.aPanelRdvs.setVisible(false);
			this.aPanelReservations.setVisible(false);
			this.aPanelBords.setVisible(true);
			this.aPanelStats.setVisible(false);

		}
		
		else if(e.getSource() == btStats)
		{
			this.panelProfil.setVisible(false);
			this.aPanelClients.setVisible(false);
			this.aPanelTechniciens.setVisible(false);
			this.aPanelMagasins.setVisible(false);
			this.aPanelVerres.setVisible(false);
			this.aPanelMontures.setVisible(false);
			this.aPanelLunettes.setVisible(false);
			this.aPanelRdvs.setVisible(false);
			this.aPanelReservations.setVisible(false);
			this.aPanelBords.setVisible(false);
			this.aPanelStats.setVisible(true);

		}
		
	}

}