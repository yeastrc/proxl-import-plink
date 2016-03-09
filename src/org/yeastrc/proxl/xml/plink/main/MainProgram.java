package org.yeastrc.proxl.xml.plink.main;

import org.yeastrc.proxl.xml.plink.reader.PLinkSearchParameters;
import org.yeastrc.proxl.xml.plink.reader.PLinkSearchParametersLoader;

public class MainProgram {

//	private static final String TEST_PLINK_INI = "C:\\Users\\Valued Customer\\Dropbox\\plink_output\\pLink.ini";
	private static final String TEST_PLINK_INI = "F:\\Dropbox\\pLink\\pLink.ini";
	private static final String PLINK_BIN_DIRECTORY = "F:\\Dropbox\\pLink\\pLink_release";

	public static void main( String[] args ) throws Exception {
		
		
		PLinkSearchParameters params = PLinkSearchParametersLoader.getInstance().getPLinkSearch( TEST_PLINK_INI, PLINK_BIN_DIRECTORY );
		System.out.println( params.getLinker().getName() );
		System.out.println( params.getLinker().getMonoCrosslinkMass() );
		
	}
	
}
