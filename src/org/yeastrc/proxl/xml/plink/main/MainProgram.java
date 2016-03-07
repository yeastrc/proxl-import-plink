package org.yeastrc.proxl.xml.plink.main;

import org.yeastrc.proxl.xml.plink.reader.PLinkINI;

public class MainProgram {

	private static final String TEST_PLINK_INI = "C:\\Users\\Valued Customer\\Dropbox\\plink_output\\pLink.ini";
	
	public static void main( String[] args ) throws Exception {
		
		
		PLinkINI plinkINI = new PLinkINI( TEST_PLINK_INI );
		
		
		System.out.println( plinkINI.getConfig().getString( "pLink/mod.variable.2" ) );
		
	}
	
}
