/**
 * MonLabel.java
 * */

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Dimension;

import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;

import java.net.URL;

/**
 * Classe permettant de traiter les images de la voie métabolique.
 * 
 * @author Agnes Barnabe & Martin Davy
 * */

@SuppressWarnings("serial")
public class MonLabel extends JLabel {
	
	/* Variables */
	
	private String code;
	private ImageIcon image;
	
	/* Methodes */
	
	/**
	 * Enregistre l'image de la voie metabolique.
	*/
	public void getVoie() throws IOException {
		// Si l'image n'existe pas en local
		BufferedImage image = null;
		URL url = new URL("http://rest.kegg.jp/get/"+code+"/image");
		image = ImageIO.read(url);
		ImageIO.write(image, "png", new File(Cadre.path+"/src/img/"+code+".png"));
	}
	
	/**
	 * Fixe l'image de la voie métabolique correspondante dans le JLabel.
	 * @param nouvCode nouvelle valeur de code
	 * */
	public void setImg(String nouvCode) {
		code = nouvCode;
		
		File file = new File(Cadre.path+"/src/img/"+code+".png");
		
		try {
			// Si l'image n'existe pas en local
			if(!file.exists()) {
				getVoie();
			} 
			image = new ImageIcon(Cadre.path+"/src/img/"+code+".png");
			setIcon(image);
		} catch(IOException ex) {
			System.out.println("ERREUR : l'image n'existe pas");
			JOptionPane.showMessageDialog(null,
				"Problème de connection internet ou d'entrée pour la recherche.\n Pour rappel, suivre le modèle suivant : \"eco\" \"XXXXX\" où X est un chiffre.",
				"ATTENTION UTILISATEUR !",
				JOptionPane.WARNING_MESSAGE);
			
		}
	}

	public void paintComponent(Graphics g){

		super.paintComponent(g); 
	    Graphics2D g2d = (Graphics2D) g;
	    g2d.setStroke(new BasicStroke(4.0f));
	    g2d.setColor(Color.RED);
	    g2d.drawRect(Cadre.myContainerBas.monRectangle.x
	    	,Cadre.myContainerBas.monRectangle.y
	    	,Cadre.myContainerBas.monRectangle.largeur
	    	,Cadre.myContainerBas.monRectangle.hauteur);
	    repaint();
	}
}
