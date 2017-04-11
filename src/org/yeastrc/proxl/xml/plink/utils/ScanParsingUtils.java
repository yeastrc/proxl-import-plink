package org.yeastrc.proxl.xml.plink.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Some utility methods for parsing scan variables from the reported scan information in plink results files.
 * 
 * @author Michael Riffle
 * @date Mar 23, 2016
 *
 */
public class ScanParsingUtils {

	private static final Pattern r = Pattern.compile( "^.+\\.\\d+\\.(\\d+)\\.(\\d+)$" );
	
	/**
	 * Get the charge from the reported scan. E.g.: Q_2013_1010_RJ_07.14315.14315.4
	 * Would return 4
	 * @param reportedScan The reported scan from the plink results file, in the form of Q_2013_1010_RJ_07.14315.14315.4
	 * @return The charge parsed from the scan
	 * @throws Exception
	 */
	public static int getChargeFromReportedScan( String reportedScan ) throws Exception {
				
		// first split on spaces, then check each element's syntax for expected syntax above
		String[] elements = reportedScan.split( " " );
		
		for( String element : elements ) {
			
			Matcher m = r.matcher( element );
			if( m.matches() ) {
			
				return Integer.parseInt( m.group( 2 ) );
			}
		}
		
		throw new Exception( "Could not find expected syntax for reporting scan information. Got: " + reportedScan );
	}
	
	/**
	 * Get the scan number from the reported scan. E.g.: Q_2013_1010_RJ_07.14315.14315.4
	 * Would return 14315. Always uses the second to last value after spliting on "."
	 * @param reportedScan The reported scan from the plink results file, in the form of Q_2013_1010_RJ_07.14315.14315.4
	 * @return The scan number
	 * @param reportedScan
	 * @return
	 * @throws Exception
	 */
	public static int getScanNumberFromReportedScan( String reportedScan ) throws Exception {
		
		// first split on spaces, then check each element's syntax for expected syntax above
		String[] elements = reportedScan.split( " " );
		
		for( String element : elements ) {
			
			Matcher m = r.matcher( element );
			if( m.matches() ) {
			
				return Integer.parseInt( m.group( 1 ) );
			}
		}
		
		throw new Exception( "Could not find expected syntax for reporting scan information. Got: " + reportedScan );
	}
	
}
