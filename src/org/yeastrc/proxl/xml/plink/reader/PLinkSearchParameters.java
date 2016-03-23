package org.yeastrc.proxl.xml.plink.reader;

import org.yeastrc.proxl.xml.plink.ini.ParsedINIFile;
import org.yeastrc.proxl.xml.plink.objects.PLinkLinker;

/**
 * Access to the various INI files that define the search parameters of a plink search.
 * 
 * @author Michael Riffle
 * @date Mar 23, 2016
 *
 */
public class PLinkSearchParameters {
	
	public ParsedINIFile getPlinkINI() {
		return plinkINI;
	}
	public void setPlinkINI(ParsedINIFile plinkINI) {
		this.plinkINI = plinkINI;
	}
	public ParsedINIFile getModifyINI() {
		return modifyINI;
	}
	public void setModifyINI(ParsedINIFile modifyINI) {
		this.modifyINI = modifyINI;
	}
	public ParsedINIFile getXlinkINI() {
		return xlinkINI;
	}
	public void setXlinkINI(ParsedINIFile xlinkINI) {
		this.xlinkINI = xlinkINI;
	}
	
	
	
	public PLinkLinker getLinker() {
		return linker;
	}
	public void setLinker(PLinkLinker linker) {
		this.linker = linker;
	}



	private PLinkLinker linker;
	
	private ParsedINIFile plinkINI;
	private ParsedINIFile modifyINI;
	private ParsedINIFile xlinkINI;
	
}
