/**
 * 
 * Cadre.java
 * 
 * */

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

import java.awt.Container;
import java.awt.Dimension;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import java.lang.System;

/**
 * Fenetre principale qui va contenir les différents éléments.
 * 
 * @author Agnes Barnabe & Martin Davy
 * */


@SuppressWarnings("serial")
public class Cadre extends JFrame implements ActionListener {
	
	/** Variables d'instance */
	
	private Container myContainer = null;	// Declaration du container general
	public static ContainerHaut myContainerHaut;
	public static ContainerBas myContainerBas;
	public static String path = System.getProperty("user.dir");
		
	/** Constructeur */
  
	public Cadre() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// Fermeture de la fenetre
		setTitle("KEGG-MANIA");
		setSize(900,800);
		setMinimumSize(new Dimension(780,500));
		setLocationRelativeTo(null);
		
		// Creation du container principal et ajout dans la fenetre
		myContainer = getContentPane();
		myContainer.setLayout(new BoxLayout(myContainer, BoxLayout.PAGE_AXIS));
		
		myContainerHaut = new ContainerHaut();		
		myContainerHaut.bandeH.searchGen.addActionListener(this);
		
		myContainerBas = new ContainerBas();	
		myContainerBas.bandeB.searchPath.addActionListener(this);
		myContainerBas.bandeB.imgRea.addActionListener(this);
				
		myContainerBas.voie.addMouseListener( new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				double curseurX = e.getPoint().getX();
				double curseurY = e.getPoint().getY();
				
				String codeReac = myContainerBas.monRectangle.contains(curseurX,curseurY);
				
				if(codeReac != "") {
					myContainerBas.containerRea.descriptionRea.setReac(codeReac);
					myContainerBas.containerRea.listePane.setInvoGene(myContainerBas.monRectangle.sp, codeReac);
				}
			}
		});
		
		myContainer.add(myContainerHaut);
		myContainer.add(myContainerBas);
		
		this.setContentPane(myContainer);		
	}
	
	/** Methode */
	
	/**
	 * Implemente actionPerformed pour definir les actions associees aux boutons.
	 * 
	 * @param ActionEvent e
	 * */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == myContainerHaut.bandeH.searchGen) {
			String spH = myContainerHaut.bandeH.specieTxt.getText();
			String codeH = myContainerHaut.bandeH.geneTxt.getText();
										
			WorkerHaut worker = new WorkerHaut(spH, codeH);
			worker.execute();
			
		}
		else if(e.getSource() == myContainerBas.bandeB.searchPath) {
			myContainerBas.monRectangle.largeur = 0; myContainerBas.monRectangle.hauteur = 0;
			String spB = myContainerBas.bandeB.specieTxt.getText();
			String codeB = myContainerBas.bandeB.mapTxt.getText();
			
			myContainerBas.voie.setImg(spB+codeB);
			myContainerBas.monRectangle.setCode(spB,codeB);
		}
		else if(e.getSource() == myContainerBas.bandeB.imgRea) {
			if(myContainerBas.containerRea.descriptionRea.reaction != null) {
				ImageReac image = new ImageReac(myContainerBas.containerRea.descriptionRea.reaction);
				image.setVisible(true);
			}
		}
	}
	
}
