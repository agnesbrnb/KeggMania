/**
 * ReactionInfo.java
 * */
  
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color; 
import java.awt.Dimension;

/**
 * Cette classe permet d'afficher les informations sur la reaction.
 * 
 * @author Agnes Barnabe & Martin Davy
 * */

@SuppressWarnings("serial")
public class ReactionInfo extends JPanel {
	
	/** Variable */
	
	protected ReacDescrip descriptionRea;
	protected ListGene listePane;
	
	/** Constructeur */
	
	public ReactionInfo() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		descriptionRea = new ReacDescrip();
		JScrollPane scroll = new JScrollPane(descriptionRea);
		scroll.setPreferredSize(new Dimension(300,200));
		this.add(scroll, descriptionRea);
		
		JLabel involvGen = new JLabel("Involves gene(s)");
		involvGen.setOpaque(true); involvGen.setBackground(new Color(254,191,210));
		this.add(involvGen);
		
		listePane = new ListGene();
		this.add(listePane);	
	}
}
