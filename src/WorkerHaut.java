/**
 * WorkerHaut.java
 * */

import javax.swing.SwingWorker;
import javax.swing.JButton;

import java.lang.Double; 

/**
 * Classe permettant de realiser un travail en arriere plan.
 * 
 * @author Agnes Barnabe & Martin Davy
 * */

public class WorkerHaut extends SwingWorker<Double,Void>{
	JButton searchGen;
	GeneID geneID;
	GeneDescrip descripG;
	ListReaction liste;
	
	private String sp;
	private String code;
	
	/* Constructeur */
	
	// Permet de passer en argument les elements de Chronometre2
	public WorkerHaut ( String sp, String code) {
		super();
		this.searchGen = Cadre.myContainerHaut.bandeH.searchGen;
		this.geneID = Cadre.myContainerHaut.geneID;
		this.descripG = Cadre.myContainerHaut.containerGen.descriptionGene;
		this.liste = Cadre.myContainerHaut.containerGen.listePane;
		this.sp = sp;
		this.code = code;
	}
	
	/* Methodes */
	
	/**
	 * Implemente doInBackground pour effectuer un travail.
	 * 
	 * @return Double
	 * */
	@Override
	public Double doInBackground() {
		searchGen.setEnabled(false);
		searchGen.setText("En cours...");
		descripG.setGene(sp,code);	
		liste.setInvoReac(sp,code);
		geneID.miseAJour(sp,code);	
		
		double random = 0;
        return random;
	}
	
	/**
	 * Implemente done.
	 * */
	@Override
	protected void done() {		
		searchGen.setEnabled(true);
		searchGen.setText("Search");
	}
}
