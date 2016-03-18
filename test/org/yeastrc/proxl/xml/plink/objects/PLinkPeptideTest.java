package org.yeastrc.proxl.xml.plink.objects;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class PLinkPeptideTest {

	@Test
	public void testToString() {

		PLinkPeptide peptide = new PLinkPeptide();
		
		peptide.setSequence( "PEPTIDE" );

		assertEquals( peptide.toString(), "PEPTIDE" );
				
		Map<Integer, Collection<PLinkModification>> mods = new HashMap<Integer, Collection<PLinkModification>>();
		
		mods.put( 1, new ArrayList<PLinkModification>() );
		mods.put( 2, new ArrayList<PLinkModification>() );
		mods.put( 5, new ArrayList<PLinkModification>() );
		mods.put( 7, new ArrayList<PLinkModification>() );
		
		peptide.setMods( new HashMap<Integer, Collection<PLinkModification>>() );
		
		assertEquals( peptide.toString(), "PEPTIDE" );
		
		PLinkModification mod1 = new PLinkModification();
		mod1.setAverageMass( 16.23456 );
		mod1.setMonoisotopicMass(15.123456789 );
		mod1.setName( "testmod1" );
		
		PLinkModification mod2 = new PLinkModification();
		mod2.setAverageMass( 23.23456 );
		mod2.setMonoisotopicMass(24.123456789 );
		mod2.setName( "testmod2" );
		
		PLinkModification mod3 = new PLinkModification();
		mod3.setAverageMass( 155.23456 );
		mod3.setMonoisotopicMass(154.123444389 );
		mod3.setMonolink( true );
		mod3.setName( "testmod3" );

		peptide.getMods().put( 1, new ArrayList<PLinkModification>() );
		peptide.getMods().get( 1 ).add( mod1 );
		assertEquals( peptide.toString(), "P[15.1235]EPTIDE" );
		
		peptide.getMods().put( 2, new ArrayList<PLinkModification>() );
		peptide.getMods().get( 2 ).add( mod2 );
		assertEquals( peptide.toString(), "P[15.1235]E[24.1235]PTIDE" );
		
		peptide.getMods().get( 2 ).add( mod1 );
		assertEquals( peptide.toString(), "P[15.1235]E[15.1235,24.1235]PTIDE" );
		
		peptide.getMods().put( 5, new ArrayList<PLinkModification>() );
		peptide.getMods().get( 5 ).add( mod3 );
		assertEquals( peptide.toString(), "P[15.1235]E[15.1235,24.1235]PTI[154.1234]DE" );

		peptide.getMods().get( 5 ).add( mod2 );
		peptide.getMods().get( 5 ).add( mod1 );
		assertEquals( peptide.toString(), "P[15.1235]E[15.1235,24.1235]PTI[15.1235,24.1235,154.1234]DE" );
		
		peptide.getMods().put( 7, new ArrayList<PLinkModification>() );
		peptide.getMods().get( 7 ).add( mod2 );
		assertEquals( peptide.toString(), "P[15.1235]E[15.1235,24.1235]PTI[15.1235,24.1235,154.1234]DE[24.1235]" );
		
	}

}
