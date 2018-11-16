/**
 * ListReaction.java
 **/

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Dimension;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;

import java.net.URL;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Permet de creer la liste des reactions impliquant le gene selectionné
 * 
 * @author Agnes Barnabe & Martin Davy
 **/

@SuppressWarnings("serial")
public class ListReaction extends JPanel {

	/* Variables */
	
	private String sp;
	private String gene;
	protected String[] array ;
	protected JList<String> list = new JList<String>();
		
	/* Constructeur */
	
	public ListReaction() {
		JTextArea init = new JTextArea();
		init.setEditable(false);
		init.setPreferredSize(new Dimension(300,110));
		this.add(init);
	}
	
	/* Methodes */
	
	/**
	 * Attribue les valeurs a la liste avec un scroll.
	 * 
	 * @param nouvSp la nouvelle valeur de sp
	 * @param nouvGene la nouvelle valeur de gene
	 * @param myContainerBas le container du bas
	 * */
	public void setInvoReac(String nouvSp, String nouvGene) {
		sp = nouvSp; gene = nouvGene; 
		File file = new File (Cadre.path+"/src/listreac/"+sp+gene+".txt");
		
		try {
			// Si le ficher n'existe pas en local
			if(!file.exists()) {
				getInvoReac();
			} 
			Scanner s = new Scanner(file);
			s.useDelimiter("\\s*\n\\s*");
			array = s.next().split("/");
			s.close();
						
			list = new JList<String>(array);
			JScrollPane scrollPane = new JScrollPane(list);
			scrollPane.setPreferredSize(new Dimension(300,100));
			
			list.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					int index = list.locationToIndex(e.getPoint());
					String reaction = array[index].split(" @ ")[0];
					String code = array[index].split(" @ ")[1];
					
					// Ecriture dans les zones de texte
					Cadre.myContainerBas.bandeB.specieTxt.setText(sp);
					Cadre.myContainerBas.bandeB.mapTxt.setText(code.replace("eco",""));
					
					// Affichage de l'image et réinitialisation du rectangle
					Cadre.myContainerBas.voie.setImg(code);
					Cadre.myContainerBas.monRectangle.largeur = 0; 
					Cadre.myContainerBas.monRectangle.hauteur = 0;
					Cadre.myContainerBas.monRectangle.setCode(sp,code.replace("eco",""));
					
					// Affichage info sur la reaction
					Cadre.myContainerBas.containerRea.descriptionRea.setReac(reaction);
					Cadre.myContainerBas.containerRea.listePane.setInvoGene(sp,reaction);
				}
			});
			
			this.removeAll();
			this.add(scrollPane);
			
		}	 catch(IOException ex) {
			System.out.println("ERREUR : le texte n'existe pas");
		}	
	}
		
	/**
	 * Enregistre le fichier texte des correspondances entre gene et reactions
	 **/
	public void getInvoReac() {
		
		File file = new File(Cadre.path+"/src/genetxt/"+sp+gene+".txt");
		try {
			Scanner s = new Scanner(file);
					
			String currentLine, reacInvolv = "", code;
			int j, taille;
			ArrayList<String> rectCoor = new ArrayList<String>();
			String[] reacSplit;	PrintWriter listReaction;
			
			
			while(s.findInLine("PATHWAY") == null) {
				s.nextLine();
			} // Ligne avant PATHWAY
			
			// Recupere les codes des voies metaboliques dans une JList
			ArrayList<String> codeVoie = new ArrayList<String>();
			while(s.findInLine("Metabolic pathways")==null) {
				codeVoie.add(s.next());
				s.nextLine();
			}			
			
			s.close();
			
			for( int i=0; i < codeVoie.size(); i++) {
				
				code = codeVoie.get(i).split(sp)[1];
				rectCoor.clear();
				
				URL url = new URL("http://rest.kegg.jp/get/"+sp+code+"/conf");
				s = new Scanner(url.openStream());
											
				// Initialisation de la ligne courante
				currentLine = s.nextLine(); 
				
				// Cherche les coordonnees des reactions correspondant au gene cible
				while(s.hasNextLine()) {
					if(currentLine.matches(".+"+gene+".+")) {
						rectCoor.add(currentLine.split("\t")[0]);
					}
					currentLine = s.nextLine();															
				}	
				s.close();
				
				url = new URL("http://rest.kegg.jp/get/map"+code+"/conf");
				s = new Scanner(url.openStream());
				
				currentLine = s.nextLine();
				
				while(s.hasNextLine()) {
					if(rectCoor.contains(currentLine.split("\t")[0])) {
						reacSplit = currentLine.split(", ");
						taille = reacSplit.length -1; j=0;
						do {
							reacInvolv += reacSplit[taille-j]+" @ "+codeVoie.get(i)+"/";
							j++;
						} while(reacSplit[taille-j].matches("R.+"));
					}
					currentLine = s.nextLine();
				}		
				s.close();
				
			}
			reacInvolv += "\n";
			listReaction = new PrintWriter(new File (Cadre.path+"/src/listreac/"+sp+gene+".txt"));
			listReaction.println(reacInvolv);
			listReaction.close();
			
		} catch (IOException e) {
			System.out.println("ERREUR : IOException dans rechCode");
		}		
		
	}	
	
} // ListReaction.java

