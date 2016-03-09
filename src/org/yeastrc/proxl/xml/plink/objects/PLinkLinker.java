package org.yeastrc.proxl.xml.plink.objects;

public class PLinkLinker {
	
	public PLinkLinker() { }
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstLinkedResidueMotif() {
		return firstLinkedResidueMotif;
	}

	public void setFirstLinkedResidueMotif(String firstLinkedResidueMotif) {
		this.firstLinkedResidueMotif = firstLinkedResidueMotif;
	}

	public String getSecondLinkedResidueMotif() {
		return secondLinkedResidueMotif;
	}

	public void setSecondLinkedResidueMotif(String secondLinkedResidueMotif) {
		this.secondLinkedResidueMotif = secondLinkedResidueMotif;
	}

	public Double getMonoCrosslinkMass() {
		return monoCrosslinkMass;
	}

	public void setMonoCrosslinkMass(Double monoCrosslinkMass) {
		this.monoCrosslinkMass = monoCrosslinkMass;
	}

	public Double getAverageCrosslinkMass() {
		return averageCrosslinkMass;
	}

	public void setAverageCrosslinkMass(Double averageCrosslinkMass) {
		this.averageCrosslinkMass = averageCrosslinkMass;
	}

	public Double getMonoMonolinkMass() {
		return monoMonolinkMass;
	}

	public void setMonoMonolinkMass(Double monoMonolinkMass) {
		this.monoMonolinkMass = monoMonolinkMass;
	}

	public Double getAverageMonolinkMass() {
		return averageMonolinkMass;
	}

	public void setAverageMonolinkMass(Double averageMonolinkMass) {
		this.averageMonolinkMass = averageMonolinkMass;
	}




	private String name;
	private String firstLinkedResidueMotif;
	private String secondLinkedResidueMotif;
	private Double monoCrosslinkMass;
	private Double averageCrosslinkMass;
	private Double monoMonolinkMass;
	private Double averageMonolinkMass;
	
}
