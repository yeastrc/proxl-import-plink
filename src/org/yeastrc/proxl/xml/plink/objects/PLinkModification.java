package org.yeastrc.proxl.xml.plink.objects;

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



	private String name;
	private double monoisotopicMass;
	private double averageMass;
	private boolean isMonolink = false;
}
