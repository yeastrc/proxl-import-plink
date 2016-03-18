package org.yeastrc.proxl.xml.plink.objects;

public class PLinkResult {
	
	public int getScanNumber() {
		return scanNumber;
	}
	public void setScanNumber(int scanNumber) {
		this.scanNumber = scanNumber;
	}
	public int getCharge() {
		return charge;
	}
	public void setCharge(int charge) {
		this.charge = charge;
	}
	public PLinkReportedPeptide getReportedPEptide() {
		return reportedPEptide;
	}
	public void setReportedPEptide(PLinkReportedPeptide reportedPEptide) {
		this.reportedPEptide = reportedPEptide;
	}
	public double getEvalue() {
		return evalue;
	}
	public void setEvalue(double evalue) {
		this.evalue = evalue;
	}
	public double getCalculatedMass() {
		return calculatedMass;
	}
	public void setCalculatedMass(double calculatedMass) {
		this.calculatedMass = calculatedMass;
	}
	public double getDeltaMass() {
		return deltaMass;
	}
	public void setDeltaMass(double deltaMass) {
		this.deltaMass = deltaMass;
	}
	public double getDeltaMassPPM() {
		return deltaMassPPM;
	}
	public void setDeltaMassPPM(double deltaMassPPM) {
		this.deltaMassPPM = deltaMassPPM;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	private int scanNumber;
	private int charge;
	private PLinkReportedPeptide reportedPEptide;
	private double evalue;		// the score reported by plink
	private double calculatedMass;
	private double deltaMass;
	private double deltaMassPPM;
	private int type;
	
}
