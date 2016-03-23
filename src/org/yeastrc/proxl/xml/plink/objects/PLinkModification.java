package org.yeastrc.proxl.xml.plink.objects;

import java.util.Collection;

/**
 * Represents a plink modification as defined by the syntax in the modify.ini file
 * 
 * @author Michael Riffle
 * @date Mar 23, 2016
 *
 */
public class PLinkModification {
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMonoisotopicMass() {
		return monoisotopicMass;
	}
	public void setMonoisotopicMass(double monoisotopicMass) {
		this.monoisotopicMass = monoisotopicMass;
	}
	public double getAverageMass() {
		return averageMass;
	}
	public void setAverageMass(double averageMass) {
		this.averageMass = averageMass;
	}
	public boolean isMonolink() {
		return isMonolink;
	}
	public void setMonolink(boolean isMonolink) {
		this.isMonolink = isMonolink;
	}
	public Collection<String> getResidues() {
		return residues;
	}
	public void setResidues(Collection<String> residues) {
		this.residues = residues;
	}



	private String name;
	private double monoisotopicMass;
	private double averageMass;
	private boolean isMonolink = false;
	private Collection<String> residues;
}
