package vue;

import java.awt.Color;

import javax.swing.JPanel;

public abstract class PanelDeBase extends JPanel {
	
	public PanelDeBase(Color uneCouleur)
	{
		this.setBounds(100, 100, 1200, 500);//40,40,720,320
		this.setBackground(uneCouleur);
		this.setLayout(null);
		
		//this.setSize(1300, 800);
		
		this.setVisible(false);
	}

}
