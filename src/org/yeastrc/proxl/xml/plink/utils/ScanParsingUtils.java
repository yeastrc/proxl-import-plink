package org.yeastrc.proxl.xml.plink.utils;

public class ScanParsingUtils {

	/**
	 * Get the charge from the reported scan. E.g.: Q_2013_1010_RJ_07.14315.14315.4
	 * Would return 4
	 * @param reportedScan The reported scan from the plink results file, in the form of Q_2013_1010_RJ_07.14315.14315.4
	 * @return The charge parsed from the scan
	 * @throws Exception
	 */
	public static int getChargeFromReportedScan( String reportedScan ) throws Exception {
		String[] fields = reportedScan.split( "\\." );
		
		if( fields.length < 4 )
			throw new Exception( "Got unexpected syntax for reported scan string: " + reportedScan );
		
		return Integer.parseInt( fields[ fields.length - 1 ] );
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
		String[] fields = reportedScan.split( "\\." );
		
		if( fields.length < 4 )
			throw new Exception( "Got unexpected syntax for reported scan string: " + reportedScan );
		
		return Integer.parseInt( fields[ fields.length - 2 ] );
	}
	
}
