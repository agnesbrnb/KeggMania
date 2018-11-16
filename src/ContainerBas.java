/**
 * ContainerHaut.java
 * */

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.io.IOException;

/**
 * Construit le container inferieur de l'interface
 * 
 * @author Agnes Barnabe & Martin Davy
 * */

@SuppressWarnings("serial")
public class ContainerBas extends JPanel {
	
	/* Variable */
	protected MonLabel voie;
	protected BandePath bandeB;
	protected Rectangle monRectangle;
	protected ReactionInfo containerRea;
	
	/* Constructeur */
	
	public ContainerBas() throws IOException {
		
		this.setLayout(new BorderLayout());		
		
		/* Bande du haut */
		bandeB = new BandePath();
		this.add(bandeB, BorderLayout.NORTH);
		
		/* Rectangle dans monLabel */
		monRectangle = new Rectangle();
		
		/* Container voieM */						
		voie = new MonLabel();
		
		JScrollPane scrollm = new JScrollPane(voie);
		scrollm.setPreferredSize(new Dimension(600,400));
		this.add(scrollm, BorderLayout.CENTER);		
		
		/* Zone de droite */
		containerRea = new ReactionInfo();
						
		this.add(containerRea, BorderLayout.EAST);
	}
}
