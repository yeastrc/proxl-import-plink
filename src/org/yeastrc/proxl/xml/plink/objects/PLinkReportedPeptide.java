package org.yeastrc.proxl.xml.plink.objects;

public class PLinkReportedPeptide {
	
	public PLinkPeptide getPeptide1() {
		return peptide1;
	}

	public void setPeptide1(PLinkPeptide peptide1) {
		this.peptide1 = peptide1;
	}

	public PLinkPeptide getPeptide2() {
		return peptide2;
	}

	public void setPeptide2(PLinkPeptide peptide2) {
		this.peptide2 = peptide2;
	}

	public int getPosition1() {
		return position1;
	}

	public void setPosition1(int position1) {
		this.position1 = position1;
	}

	public int getPosition2() {
		return position2;
	}

	public void setPosition2(int position2) {
		this.position2 = position2;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	private PLinkPeptide peptide1;
	private PLinkPeptide peptide2;
	
	private int position1;
	private int position2;
	
	private int type;
	
}
