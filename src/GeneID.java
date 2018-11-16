/**
 * GeneID.java
 * */
 
import javax.swing.JOptionPane;
 
import org.fit.cssbox.swingbox.BrowserPane;

import java.io.IOException;

import java.net.URL;

/**
 * Classe permettant de traiter la page web.
 * 
 * @author Agnes Barnabe & Martin Davy
 * */

@SuppressWarnings("serial")
public class GeneID extends BrowserPane {
	
	/** Variables */
	
	private String sp;
	private String code;
	
	/** Methode */
	
	/**
	 * Modifie l'affichage avec une nouvelle espece.
	 * 
	 * @param nouvSp la nouvelle valeur pour sp
	 * @param nouvCode la nouvelle valeur pour code
	 * */
	public void miseAJour(String nouvSp, String nouvCode)  {
		sp = nouvSp;
		code = nouvCode;
		// Affiche la page internet
		try {
			String url = "http://www.kegg.jp/kegg-bin/show_genomemap?ORG="+sp+"&ACCESSION="+code;
			this.setPage(new URL(url));
		} catch(IOException e) {
			System.out.println("URL inaccessible");
			JOptionPane.showMessageDialog(null,
				"Pas de connection internet !",
				"ATTENTION UTILISATEUR !",
				JOptionPane.WARNING_MESSAGE);
		}
	}	
}
