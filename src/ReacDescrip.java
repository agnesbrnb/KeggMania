/**
 * ReacDescrip.java
 * */

import javax.swing.JTextArea;

import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

import java.net.URL;

import java.util.Scanner;

/**
 * Permet de recuperer la description de la reaction cible et de l'ecrire
 * dans un JTextArea.
 * 
 * @author Agnes Barnabe & Martin Davy
 * */

@SuppressWarnings("serial")
public class ReacDescrip extends JTextArea {
	
	/* Variables */
	
	protected String reaction = null;
	
	/* Methodes */
	
	/**
	 * Attribue le texte de la reaction correspondante.
	 * @param nouvReaction nouvelle valeur de reaction
	 * */
	public void setReac(String nouvReaction) {
		reaction = nouvReaction;
		
		File file = new File(Cadre.path+"/src/reatxt/"+reaction+".txt");
		String reacText = "";

		try {
			// Si le texte n'existe pas en local
			if(!file.exists()) {
				getReac();
			} 
			BufferedReader br = new BufferedReader(new FileReader(file));
			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				reacText += sCurrentLine+"\n";
			}
			br.close();

			this.setText(reacText);
			this.setEditable(false);

		} catch(IOException ex) {
			System.out.println("ERREUR : le texte n'existe pas");
		}
	} 
	
	/**
	 * Enregistre le fichier texte decrivant la reaction.
	 * */
	public void getReac(){
		try {
			URL url = new URL("http://rest.kegg.jp/get/rn:"+reaction);
		    Scanner s = new Scanner(url.openStream());
		    s.useDelimiter("\\s*///\\s*");
     		String reacText = s.next();
     		PrintWriter reactionpw = new PrintWriter(new File (Cadre.path+"/src/reatxt/"+reaction+".txt"));

			reactionpw.println(reacText);

			reactionpw.close();
			s.close();
			
		} catch (IOException e) {
			System.out.println("ERREUR : IOException");
		}	
	}
	
}
