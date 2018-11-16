/**
 * Tutoriel.java
 * */

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

/**
 * Affiche une image explicative du logiciel.
 * 
 * @author Agnès Barnabé & Martin Davy
 * */

@SuppressWarnings("serial")
public class Tutoriel extends JFrame implements ActionListener {

	/* Variables */
	
	private JButton go;
	private Container myContainer;
	
	/* Constructeur */
	public Tutoriel() throws IOException {

		// Information de la fenetre
		setTitle("Tutoriel KEGG-MANIA");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		// Creation du container principal
		myContainer = getContentPane();
		myContainer.setLayout(new BorderLayout());
		
		JLabel entetePane = new JLabel();
		ImageIcon entete = new ImageIcon(Cadre.path+"/src/img/tutoEntete2.png");
		entetePane.setIcon(entete);
		myContainer.add(entetePane, BorderLayout.NORTH);
		
		JLabel imgPane = new JLabel();
		ImageIcon image = new ImageIcon(Cadre.path+"/src/img/tuto.png");
		imgPane.setIcon(image);
		
		JScrollPane scroll = new JScrollPane(imgPane);
		scroll.setPreferredSize(new Dimension(entete.getIconWidth(),450));
		myContainer.add(scroll, BorderLayout.CENTER);
				
		go = new JButton("Je me lance !");
		go.addActionListener(this);
		go.setPreferredSize(new Dimension(entete.getIconWidth(),50));
		myContainer.add(go, BorderLayout.SOUTH);
		
		setSize(entete.getIconWidth(),800);
		setLocationRelativeTo(null);
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
			if(e.getSource() == go) {
				Cadre browser = new Cadre();
				browser.setVisible(true);
				this.dispose();
			} 		
		}catch(IOException ex) {
			System.out.println("IOException caught");
		}
	}
		
}
 
