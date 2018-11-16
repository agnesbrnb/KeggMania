/**
 * BandePath.java
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
 * Permet de dessiner la bande du milieu de l'interface
 * 
 * @author Agnes Barnabe & Martin Davy
 * */

@SuppressWarnings("serial")
 public class BandePath extends JPanel {
	 
	 /* Variables */
	 protected JButton searchPath;
	 protected JTextField specieTxt;
	 protected JTextField mapTxt;
	 protected JButton imgRea;
	 
	 /* Constructeur */
	 
	 public BandePath() {
		this.setLayout(new BorderLayout());
		
		JPanel bandeau = new JPanel();
		bandeau.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		JLabel pathway = new JLabel("Pathway Browser");
		// Fixe des bordures noires		
		pathway.setOpaque(true); pathway.setBackground(new Color(254,191,210));
		bandeau.add(pathway);
		
		JLabel specieM = new JLabel("Species");		
		bandeau.add(specieM);
		
		specieTxt = new JTextField(4);
		bandeau.add(specieTxt);
		
		JLabel mapId = new JLabel("Map ID");
		bandeau.add(mapId);
		
		mapTxt = new JTextField(6);
		bandeau.add(mapTxt);
		
		this.add(bandeau, BorderLayout.WEST);
		
		JPanel bouton = new JPanel();
		bouton.setLayout(new FlowLayout(FlowLayout.TRAILING));
		searchPath = new JButton("Search");
		bouton.add(searchPath);	
			
		this.add(bouton, BorderLayout.CENTER);
		this.add(InfoRea(), BorderLayout.EAST);	
		
	 }
	 
	 public JPanel InfoRea() {
		
		JPanel infoReaPane = new JPanel();
		infoReaPane.setPreferredSize(new Dimension(305,0));
		infoReaPane.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		JLabel infoRea = new JLabel("Reaction information");
		infoRea.setOpaque(true); infoRea.setBackground(new Color(254,191,210));
		infoReaPane.add(infoRea);
				
		imgRea = new JButton("Image");
		infoReaPane.add(imgRea);
		
		return infoReaPane;
	}
	 
 }
