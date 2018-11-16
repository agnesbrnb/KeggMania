/**
 * ImageReac.java
 * */
 
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;

import java.net.URL;

/**
 * Classe permettant de traiter les images des réactions.
 * 
 * @author Agnes Barnabe & Martin Davy
 * */

@SuppressWarnings("serial")
public class ImageReac extends JFrame {
	
	/* Variables */
	
	private String reaction;
	private ImageIcon image;
		
	/* Constructeur */
	
	public ImageReac(String nouvReac) {
		reaction = nouvReac;
		
		setTitle("Reaction : "+reaction);
		setLocationRelativeTo(null);
		
		this.add(setReac());
	}
	
	/* Methodes */
	
	/**
	 * Fixe l'image de la reaction correspondante dans le JLabel.
	 * 
	 * @param nouvSp nouvelle valeur de sp
	 * @param nouvOrg nouvelle valeur de org
	 * @return JLabel contenant l'image
	 * */
	public JLabel setReac() {
		JLabel label = new JLabel();
		
		File file = new File(Cadre.path+"/src/img/"+reaction+".gif");
		
		try {
			// Si l'image n'existe pas en local
			if(!file.exists()) {
				getReac();
			} 
			image = new ImageIcon(Cadre.path+"/src/img/"+reaction+".gif");
			label.setIcon(image);
			this.setSize(image.getIconWidth(), image.getIconHeight()+15);
			
		} catch(IOException ex) {
			System.out.println("ERREUR : l'image n'existe pas");
		}
		
		return label;
	}
	
	/**
	 * Enregistre l'image de la reaction.
	*/
	public void getReac() throws IOException {
		// Si l'image n'existe pas en local
	
		BufferedImage image = null;
		URL url = new URL("http://rest.kegg.jp/get/rn:"+reaction+"/image");
		image = ImageIO.read(url);
		ImageIO.write(image, "gif", new File(Cadre.path+"/src/img/"+reaction+".gif"));
	
	} 
}
