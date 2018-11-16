/**
 * BandeGen.java
 * */

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color; 
import java.awt.FlowLayout;
import java.awt.Dimension;

/**
 * Permet de dessiner la bande superieure de l'interface
 * 
 * @author Agnes Barnabe & Martin Davy
 * */

@SuppressWarnings("serial")
public class BandeGen extends JPanel {
	
	/* Variable */
	protected JButton searchGen;
	protected JTextField specieTxt;
	protected JTextField geneTxt;
	
	/* Constructeur */
	
	public BandeGen() {
		this.setLayout(new BorderLayout());
		
		JPanel bandeau = new JPanel();
		bandeau.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		JLabel genome = new JLabel("Genome Browser");
		// Fixe des bordures noires
		genome.setOpaque(true); genome.setBackground(new Color(254,191,210));
		bandeau.add(genome);
		
		JLabel specieG = new JLabel("Species");
		bandeau.add(specieG);
		
		specieTxt = new JTextField(4);
		bandeau.add(specieTxt);
		
		JLabel geneId = new JLabel("Gene ID");
		bandeau.add(geneId);
		
		geneTxt = new JTextField(6);
		bandeau.add(geneTxt);
		
		this.add(bandeau, BorderLayout.WEST);
		
		JPanel bouton = new JPanel();
		bouton.setLayout(new FlowLayout(FlowLayout.TRAILING));
		searchGen = new JButton("Search");
		bouton.add(searchGen);	
			
		this.add(bouton, BorderLayout.CENTER);
				
		this.add(InfoGen(), BorderLayout.EAST);
	}
	
	public JPanel InfoGen() {
		
		JPanel infoGenPane = new JPanel();
		infoGenPane.setPreferredSize(new Dimension(305,0));
		infoGenPane.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		JLabel infoGen = new JLabel("Gene information");
		infoGen.setOpaque(true); infoGen.setBackground(new Color(254,191,210));
		
		infoGenPane.add(infoGen);
		
		return infoGenPane;
	}
}
