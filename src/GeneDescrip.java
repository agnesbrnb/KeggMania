/**
 * GeneDescrip.java
 * */

import javax.swing.JTextArea;
import javax.swing.JOptionPane;

import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

import java.net.URL;

import java.util.Scanner;

/**
 * Permet de recuperer la description du gene cible et de l'ecrire dans 
 * un JTextArea.
 * 
 * @author Agnes Barnabe & Martin Davy
 * */

@SuppressWarnings("serial")
public class GeneDescrip extends JTextArea {
	
	/** Variables */
	
	private	String sp;
	private	String gene;
	
	/** Methodes */
	
	/**
	 * Enregistre le fichier texte decrivant le gene.
	 * */
	public void getGene(){
		try {
			URL url = new URL("http://rest.kegg.jp/get/"+sp+":"+gene);
		    Scanner s = new Scanner(url.openStream());
		    s.useDelimiter("\\s*///\\s*");
     		String geneText = s.next();
     		PrintWriter genepw = new PrintWriter(new File (Cadre.path+"/src/genetxt/"+sp+gene+".txt"));

			genepw.println(geneText);

			genepw.close();
			s.close();
		} catch (IOException e) {
			System.out.println("ERREUR : gene IOException");
			JOptionPane.showMessageDialog(null,
				"Mauvaise entrée pour la recherche.\n Pour rappel, suivre le modèle suivant : \"eco\" \"XXXXX\" où X est un chiffre.",
				"ATTENTION UTILISATEUR !",
				JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * Attribue le texte au gene correspondant.
	 * @param nouvGene nouvelle valeur de reaction
	 * */
	public void setGene(String nouvSp ,String nouvGene) {
		gene = nouvGene;
		sp = nouvSp;
		
		File file = new File(Cadre.path+"/src/genetxt/"+sp+gene+".txt");
		String geneText = "";

		try {
			// Si le texte n'existe pas en local
			if(!file.exists()) {
				getGene();
			} 
			BufferedReader br = new BufferedReader(new FileReader(file));

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				geneText += sCurrentLine+"\n";
			}
			br.close();

			this.setText(geneText);
			this.setEditable(false);

		} catch(IOException ex) {
			System.out.println("ERREUR : gene le texte n'existe pas");
		}
	} 
}
