/**
 * Bienvenue.java
 * */
  
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.URLConnection;
import java.net.URL;

import java.io.IOException;

/**
 * Affiche un message d'accueil avant de lancer l'application
 * Compilation à  partir de la racine : javac -cp ./lib/swingbox-1.1-bin.jar:./src ./src/Bienvenue.java -d ./class 
 * Lancement à  partir de la racine : java -cp ./lib/swingbox-1.1-bin.jar:./class Bienvenue
 * 
 * @author Agnes Barnabe & Martin Davy
 * */

@SuppressWarnings("serial")
public class Bienvenue extends JFrame implements ActionListener {
	
	/* Variables */
	
	private JButton go;
	private JButton tuto;
	private Container myContainer;
	
	/* Constructeur */
	
	public Bienvenue() throws IOException {
		// Information de la fenetre
		setTitle("Bienvenue sur KEGG-MANIA !");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		// Creation du container principal
		myContainer = getContentPane();
		myContainer.setLayout(new BorderLayout());
								 
		JLabel img = new JLabel();
		ImageIcon icon = new ImageIcon(Cadre.path+"/src/img/accueil.png"); 
		img.setIcon(icon);
		myContainer.add(img,BorderLayout.CENTER);
				
		// Container des boutons
		JPanel contBouton = new JPanel();
		contBouton.setLayout(new BorderLayout());
		go = new JButton("Utilisateur aguerri");
		go.addActionListener(this);

		tuto = new JButton("Première visite ?");
		tuto.addActionListener(this);
		
		contBouton.add(go,BorderLayout.CENTER);
		contBouton.add(tuto,BorderLayout.EAST);
		
		setSize(icon.getIconWidth(),icon.getIconHeight()+50);
		setLocationRelativeTo(null);
		
		myContainer.add(contBouton, BorderLayout.SOUTH);
		this.setContentPane(myContainer);
	}
	
	/* Methodes */
	
	/**
	 * Implemente actionPerformed
	 * 
	 * @param ActionEvent e
	 * */
	public void actionPerformed(ActionEvent e) {
		try {
			// Si il y a internet
			if(pingUrl()) {
				
				if(e.getSource() == go) {
					Cadre browser = new Cadre();
					browser.setVisible(true);
					this.dispose();
				} else if (e.getSource() == tuto) {
					Tutoriel tuto = new Tutoriel();
					tuto.setVisible(true);
					this.dispose();
				}
			} else {
				// Sans connection internet l'application se ferme
				JOptionPane.showMessageDialog(null,
				"Pas de connection internet, l'expérience risque de ne pas être à la hauteur.\nTrouves un point d'accès et relance l'application !\nA Bientôt !",
				"ATTENTION UTILISATEUR !",
				JOptionPane.WARNING_MESSAGE);
				System.exit(0);	// Ferme l'application
			}
		}catch(IOException ex) {
			System.out.println("IOException caught");
		}
	}
	
	/**
	 * Ping un url pour tester la connection internet. Retourne vrai si
	 * il y a une connection, faux sinon.
	 * 
	 * @return boolean
	 * */
	public boolean pingUrl() {
		try {
			URLConnection ping = new URL("http://google.com").openConnection();
			ping.setConnectTimeout(3000);
			ping.connect();
			
			return true;
		} catch(IOException e) {
			return false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Bienvenue accueil = new Bienvenue();
		accueil.setVisible(true);
	}
}
