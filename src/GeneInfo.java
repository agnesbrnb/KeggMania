/**
 * GeneInfo.java
 * */

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Color; 
import java.awt.Dimension;

/**
 * Permet de dessiner la zone d'information sur le gene.
 * 
 * @author Agnes Barnabe & Martin Davy
 * */

@SuppressWarnings("serial")
public class GeneInfo extends JPanel {
	
	/** Variables */
	
	protected GeneDescrip descriptionGene;
	protected ListReaction listePane;
	
	/** Constructeur */
	
	public GeneInfo() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		descriptionGene = new GeneDescrip();	
		JScrollPane scroll = new JScrollPane(descriptionGene);
		scroll.setPreferredSize(new Dimension(300,200));
		this.add(scroll, descriptionGene);
		
		JLabel involvRea = new JLabel("Involved in reaction(s)");
		involvRea.setOpaque(true); involvRea.setBackground(new Color(254,191,210));
		this.add(involvRea);
		
		listePane = new ListReaction();			
		this.add(listePane);
	}
}
