package org.yeastrc.proxl.xml.plink.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.yeastrc.proxl.xml.plink.objects.PLinkResult;
import org.yeastrc.proxl.xml.plink.reader.PLinkConstants;
import org.yeastrc.proxl.xml.plink.reader.PLinkResultsLoader;
import org.yeastrc.proxl.xml.plink.reader.PLinkSearchParameters;
import org.yeastrc.proxl.xml.plink.reader.PLinkSearchParametersLoader;
import org.yeastrc.proxl.xml.plink.utils.PLinkUtils;

public class MainProgram {

//	private static final String TEST_PLINK_INI = "C:\\Users\\Valued Customer\\Dropbox\\plink_output\\pLink.ini";
	private static final String TEST_PLINK_INI = "F:\\Dropbox\\plink_output\\pLink.ini";
	private static final String PLINK_BIN_DIRECTORY = "F:\\Dropbox\\pLink\\pLink_release";
	private static final String PLINK_DATA_DIRECTORY = "F:\\Dropbox\\plink_output";

	public static void main( String[] args ) throws Exception {
		
		
		PLinkSearchParameters params = PLinkSearchParametersLoader.getInstance().getPLinkSearch( TEST_PLINK_INI, PLINK_BIN_DIRECTORY );
		Collection<PLinkResult> results = PLinkResultsLoader.getInstance().getAllResults( params, PLINK_DATA_DIRECTORY );
		
		
		
		
		/*
		for( PLinkResult result : results ) {
			if( result.getType() == PLinkConstants.LINK_TYPE_MONOLINK ) {
				System.out.println( "Reported peptide:\t" + result.getReportedPeptide().toString() );
				System.out.println( "E-value:\t" + result.getEvalue() );
				System.out.println( "Scan number:\t" + result.getScanNumber() );
				System.out.println( "Charge:\t" + result.getCharge() );
				System.out.println( "Calculated mass:\t" + result.getCalculatedMass() );
				System.out.println( "Delta mass:\t" + result.getDeltaMass() );
				System.out.println( "Delta mass ppm:\t" + result.getDeltaMassPPM() );
			}
		}*/
		
		
	}
	
}
