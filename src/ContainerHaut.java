/**
 * ContainerHaut.java
 * */

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.io.IOException;

/**
 * Construit le container superieur de l'interface
 * 
 * @author Agnes Barnabe & Martin Davy
 * */

@SuppressWarnings("serial")
public class ContainerHaut extends JPanel {
	
	/* Variable */
	protected BandeGen bandeH;
	protected GeneID geneID;
	protected GeneInfo containerGen;
	
	/* Constructeur */
	
	public ContainerHaut() throws IOException {		
		this.setLayout(new BorderLayout());
		
		bandeH = new BandeGen();
		this.add(bandeH, BorderLayout.NORTH);
	
		/* Container geneID */
		geneID = new GeneID();
		
		JScrollPane scrollh = new JScrollPane(geneID);
		scrollh.setPreferredSize(new Dimension(600,400));
		this.add(scrollh, BorderLayout.CENTER);	
							
		/* Zone de droite */
		containerGen = new GeneInfo();
						
		this.add(containerGen, BorderLayout.EAST);		

	}
}
