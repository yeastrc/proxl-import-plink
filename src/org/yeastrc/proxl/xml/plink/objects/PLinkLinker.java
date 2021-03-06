package org.yeastrc.proxl.xml.plink.objects;

import org.yeastrc.proxl.xml.plink.reader.PLinkConstants;

/**
 * Represents a pLink cross-linker, as defined by the syntax in modify.ini
 * 
 * @author Michael Riffle
 * @date Mar 23, 2016
 *
 */
public class PLinkLinker {
	
	public PLinkLinker() { }
	
	/**
	 * Get the name used by proxl to identify the cross-linker used in this experiment
	 * @return
	 * @throws Exception
	 */
	public String getProxlName() throws Exception {
		if( !PLinkConstants.LINKER_MAP_PLINK2PROXL.containsKey( this.getName() ) )
			throw new Exception( "Can not map " + this.getName() + " to a ProXL linker." );
		
		return PLinkConstants.LINKER_MAP_PLINK2PROXL.get( this.getName() );
	}
	
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
