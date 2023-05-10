package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Opticien;
import controleur.User;

//comme vue connex herite de jframe alors explicitement le this fait reference a l objet j de type JFrame 
public class VueConnexion extends JFrame implements ActionListener , KeyListener{
	
	private JPanel unPanel = new JPanel();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btSeConnecter = new JButton("Se Connecter");
	private JTextField txtEmail = new JTextField("a@gmail.com");
	private JPasswordField txtMdp = new JPasswordField("123");
	
	public VueConnexion()
	{
		this.setTitle("Connexion au log in Opticien ");
		this.setBounds(200, 100,1100,500);
		this.getContentPane().setBackground(Color.CYAN);
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Construction du panel
		
		this.unPanel.setBounds(750,50,300,180);
		this.unPanel.setLayout(new GridLayout(3,2));
		this.unPanel.setBackground(Color.CYAN);
		this.unPanel.add(new JLabel("Email:"));
		this.unPanel.add(this.txtEmail);
		this.unPanel.add(new JLabel("MDP:"));
		this.unPanel.add(this.txtMdp);
		this.unPanel.add(this.btAnnuler);
		this.unPanel.add(this.btSeConnecter);
		
		//rendre boutton ecoutable

		this.btAnnuler.addActionListener(this);
		this.btSeConnecter.addActionListener(this);
		
		this.txtEmail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		
		
		//ajout image
		ImageIcon uneImage = new ImageIcon("src/image/logoOpticManager.png");
		JLabel logo = new JLabel(uneImage);
		logo.setBounds(40, 50, 520, 380); //(10, 50, 320, 180);
		this.add(logo);
		
		this.add(this.unPanel);//ajout du panel dans la fenetre
		
		this.setVisible(true);
		
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == this.btAnnuler)
		{
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		}
		else if(e.getSource() == this.btSeConnecter)
		{
			traitement();
		}
		
	}
	
	public void traitement()
	{
		String email = this.txtEmail.getText();
		String mdp = new String (this.txtMdp.getPassword());
		
		User unUser = Opticien.selectWhereUser(email, mdp);
		
		if(unUser == null)
		{
			JOptionPane.showMessageDialog(this, "Verifier vos identifiants");
			this.txtEmail.setText("");
			this.txtMdp.setText("");
			
		}
		
		else
		{
			JOptionPane.showMessageDialog(this, "Bienvenu"
					+ unUser.getNom() + " " + unUser.getPrenom()
					+ "\n\n Vous avez le rôle :" + unUser.getTyperole());
			
			//Lancer app VueGeneral
			Opticien.creerVueGenerale(unUser);//rajout de User
			
			//rendre invisible vueConnexion
			Opticien.rendreVisibleVueConnexion(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == e.VK_ENTER) {
			traitement (); 
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
