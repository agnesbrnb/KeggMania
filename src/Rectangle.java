/**
 * Rectangle.java
 * */

import javax.swing.JLabel;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.URL;

import java.util.Scanner;

/**
 * Permet d'intéragir avec l'image d'une voie metabolique et d'encadrer
 * la reaction selectionnee en rouge.
 * 
 * @author Agnes BARNABE & Martin DAVY
 * */

@SuppressWarnings("serial")
public class Rectangle extends JLabel {
	
	/** Variables */
	
	protected String sp;
	protected String code;
	
	protected int x = 0;
	protected int y = 0;
	protected int largeur = 0;
	protected int hauteur = 0;
	
	/** Methodes */
	
	
	/**
	 * Retourne le code de la reaction si le point est dans un rectangle correspondant à
	 * une reaction, un String vide sinon.
	 * 
	 * @param curseurX abscisse du curseur
	 * @param curseurY ordonnee du curseur
	 * 
	 * @return String
	 * */
	public String contains(double curseurX, double curseurY) {

		File file = new File (Cadre.path+"/src/rectangle/"+sp+code+".txt");
		try {
			if(!file.exists()){
				getRectangles();
			}
			
			String[] temp; String tmp,reaction; 
			int rectXG,rectYG,rectXD,rectYD;
			Scanner s = new Scanner(file);
			
			while(s.hasNextLine()){
				temp = s.nextLine().split(";");
				reaction = temp[2];
				tmp = temp[0].replace("(", "");
				tmp = tmp.replace(")","");
				
				rectXG = Integer.parseInt(tmp.split(",")[0]);
				rectYG = Integer.parseInt(tmp.split(",")[1]);
				
				tmp = temp[1].replace("(", "");
				tmp = tmp.replace(")", "");
				temp = tmp.split(",");
				
				rectXD = Integer.parseInt(temp[0]);
				rectYD = Integer.parseInt(temp[1]);

				// Definition des variables de l'instance
				this.x = rectXG;
				this.y = rectYG;
				this.largeur = rectXD-rectXG;
				this.hauteur = rectYD-rectYG;
				
				if(curseurX < rectXD && curseurX > rectXG &&
						curseurY < rectYD && curseurY > rectYG) {
					return reaction;
				}
				
			}
			s.close();
									
		} catch (IOException e) {
			System.out.println("Erreur le fichier rectangle "+code+" n'exite pas");
		}
		return "";
		
	}
		
	/**
	 * Enregistre le fichier texte decrivant la reaction.
	 * */
	public void getRectangles(){
		try {
			URL url = new URL("http://rest.kegg.jp/get/map"+code+"/conf");
		    Scanner s = new Scanner(url.openStream());

		    while(s.findInLine("rect ") == null) {
				s.nextLine();
			} // Ligne avant rect
		    
		    String[] splitLine; String aEcrire = "";
	    	
		    while(s.hasNextLine() && s.findInLine("pathway") == null) {
		    	if(s.findInLine("rect ") != null){
			    	splitLine = s.nextLine().split(" ");
			    	aEcrire = aEcrire + splitLine[0] + ";" +
	   					 splitLine[1].split("\t")[0] + ";" +
	   					 splitLine[splitLine.length-1]+ "\n";
		    	} else {
		    		s.nextLine();
		    	}
		    	
		    }
		    
		    PrintWriter rectangle = new PrintWriter(new File (Cadre.path+"/src/rectangle/"+sp+code+".txt"));
		    rectangle.print(aEcrire);
			rectangle.close();
			s.close();
			
		} catch (IOException e) {
			System.out.println("ERREUR : IOException dans le rectangle");
		}	
	}
	
	/**
	 * Fixe les valeurs concernant la voie metabolique
	 * 
	 * @param nouvSp nouvelle valeur de sp
	 * @param nouvCode nouvelle valeur de code
	 * */
	public void setCode(String nouvSp, String nouvCode){
		sp = nouvSp;
		code = nouvCode;
	}
}
