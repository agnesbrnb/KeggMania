/**
 * ListGene.java
 **/

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Dimension;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;

import java.net.URL;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Permet de creer la liste des gènes impliques dans une reaction.
 * 
 * @author Agnes Barnabe & Martin Davy
 **/

@SuppressWarnings("serial")
public class ListGene extends JPanel {

	/* Variables */
	
	private String sp;
	private String reaction;
	
	protected String[] array ;
	protected JList<String> list = new JList<String>();
		
	/* Constructeur */
	
	public ListGene() {
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
	 * */
	public void setInvoGene(String nouvSp, String nouvReac) {
		sp = nouvSp; reaction = nouvReac;
		File file = new File (Cadre.path+"/src/listgene/"+reaction+".txt");
		
		try {
			// Si le ficher n'existe pas en local
			if(!file.exists()) {
				getInvoGene();
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
					
					Cadre.myContainerHaut.bandeH.specieTxt.setText(sp);
					Cadre.myContainerHaut.bandeH.geneTxt.setText(array[index]);
					
					WorkerHaut worker = new WorkerHaut(sp, array[index]);
					worker.execute();
				}
			});
			
			this.removeAll();
			this.add(scrollPane);
			
		}catch(IOException ex) {
			System.out.println("ERREUR : le texte n'existe pas");
		}	
	}
		
	/**
	 * Enregistre le fichier texte des correspondances entre la reaction et les genes
	 **/
	public void getInvoGene() {
		
		File file = new File(Cadre.path+"/src/reatxt/"+reaction+".txt");
		try {
			
			/* Variables */
			Scanner s = new Scanner(file);
					
			String currentLine, geneInvolv = "", code, b;
			ArrayList<String> rectCoor = new ArrayList<String>();
			PrintWriter listReaction; 
			
			
			while(s.findInLine("PATHWAY") == null) {
				s.nextLine();
			} // Ligne avant PATHWAY
			
			code = s.next().replace("rn", "");
			
			s.close();
			
			URL url = new URL("http://rest.kegg.jp/get/map"+code+"/conf");
			s = new Scanner(url.openStream());
										
			// Initialisation de la ligne courante
			currentLine = s.nextLine(); 
			
			// Cherche les coordonnees des reactions correspondant a la reaction cible
			while(s.hasNextLine()) {
				if(currentLine.matches(".+"+reaction+".+")) {
					rectCoor.add(currentLine.split("\t")[0]);
				}
				currentLine = s.nextLine();															
			}	
			s.close();
			
			url = new URL("http://rest.kegg.jp/get/"+sp+code+"/conf");
			s = new Scanner(url.openStream());
			
			//s.skip(".+\\?");
			currentLine = s.nextLine();
			String regex = "b[0-9]{4}";
			
			while(s.hasNextLine()) {
				if(rectCoor.contains(currentLine.split("\t")[0]) ) {
					Matcher matcher = Pattern.compile(regex).matcher(currentLine);
					while (matcher.find()) {
						b = matcher.group();
						if (!geneInvolv.matches(".*"+b+".*")) {
							geneInvolv = geneInvolv + b+"/";
						}
					}
				}
				//s.skip(".+\\?");
				currentLine = s.nextLine();
			}		
			s.close();
				
			
			geneInvolv += "\n";
			listReaction = new PrintWriter(new File (Cadre.path+"/src/listgene/"+reaction+".txt"));
			listReaction.println(geneInvolv);
			listReaction.close();
			
		} catch (IOException e) {
			System.out.println("ERREUR : IOException dans rechCode");
		}		
		
	}	
	
} // ListReaction.java

