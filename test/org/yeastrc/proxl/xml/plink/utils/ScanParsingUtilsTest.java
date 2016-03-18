package org.yeastrc.proxl.xml.plink.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScanParsingUtilsTest {

	@Test
	public void testGetChargeFromReportedScan() {
		
		try {
			assertEquals( 4, ScanParsingUtils.getChargeFromReportedScan( "Q_2013_1010_RJ_07.14315.14315.4" ) );
			assertEquals( 3, ScanParsingUtils.getChargeFromReportedScan( "Q_2013_1010_RJ_07.23458.23458.3" ) );
			
		} catch( Exception e ) {
			fail( e.getMessage() );
		}
	}

	@Test
	public void testGetScanNumberFromReportedScan() {
		try {
			assertEquals( 14315, ScanParsingUtils.getScanNumberFromReportedScan( "Q_2013_1010_RJ_07.14315.14315.4" ) );
			assertEquals( 23458, ScanParsingUtils.getScanNumberFromReportedScan( "Q_2013_1010_RJ_07.23458.23458.3" ) );
			
		} catch( Exception e ) {
			fail( e.getMessage() );
		}
	}

}
