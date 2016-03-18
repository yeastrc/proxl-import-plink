package org.yeastrc.proxl.xml.plink.objects;

import org.yeastrc.proxl.xml.plink.reader.PLinkConstants;

public class PLinkReportedPeptide {
	
	@Override
	public String toString() {

		if( this.getType() == PLinkConstants.LINK_TYPE_UNLINKED ) {
			return this.getPeptide1().toString();
		} else if( this.getType() == PLinkConstants.LINK_TYPE_MONOLINK ) {
			return this.getPeptide1().toString() + "(" + this.getPosition1() + ")";
		} else if( this.getType() == PLinkConstants.LINK_TYPE_LOOPLINK ) {
			return this.getPeptide1().toString() + "(" + this.getPosition1() + "," + this.getPosition2() + ")";
		} else if( this.getType() == PLinkConstants.LINK_TYPE_CROSSLINK ) {
			return this.getPeptide1().toString() + "(" + this.getPosition1() + ")" + "-" +
        		   this.getPeptide2().toString() + "(" + this.getPosition2() + ")";
		}
		
		return "Error: unknown peptide type";
		
	}
	
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
