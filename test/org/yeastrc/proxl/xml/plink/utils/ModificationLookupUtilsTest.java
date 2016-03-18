package org.yeastrc.proxl.xml.plink.utils;

import static org.junit.Assert.*;

import org.junit.Test;
import org.yeastrc.proxl.xml.plink.objects.PLinkModification;
import org.yeastrc.proxl.xml.plink.utils.ModificationLookupUtils;

public class ModificationLookupUtilsTest {

	@Test
	public void testParsePLinkModificationDefinition() {
		
		try {
			
			String testName = "N-Methyl_Protein";
			String testDefinition = "[ALL] PRO_N 14.015650 14.026560 0";
			
			PLinkModification testMod = ModificationLookupUtils.parsePLinkModificationDefinition( testName, testDefinition );
			assertEquals( testName, testMod.getName() );
			assertEquals( 14.015650, testMod.getMonoisotopicMass(), 0.0000001 );
			assertEquals( 14.026560, testMod.getAverageMass(), 0.0000001 );
			
			testName = "Phospho_STY";
			testDefinition = "STY NORMAL 79.966330 79.977200 2 97.976896 97.995200  0.000000 0.000000 ";
			
			testMod = ModificationLookupUtils.parsePLinkModificationDefinition( testName, testDefinition );
			assertEquals( testName, testMod.getName() );
			assertEquals( 79.966330, testMod.getMonoisotopicMass(), 0.0000001 );
			assertEquals( 79.977200, testMod.getAverageMass(), 0.0000001 );
			
		} catch( Exception e) {
			fail( e.getMessage() );
		}
		
	}

}
