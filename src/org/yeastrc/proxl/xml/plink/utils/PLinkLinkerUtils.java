package org.yeastrc.proxl.xml.plink.utils;

import org.yeastrc.proxl.xml.plink.objects.PLinkLinker;

/**
 * Some utility methods for working with linkers.
 * 
 * @author Michael Riffle
 * @date Mar 23, 2016
 *
 */
public class PLinkLinkerUtils {

	/**
	 * Get a PLinkLinker object populated according to the data presented in the space delimited
	 * text associated with a linker, as found in the xlink.ini file
	 *
	 * @param pLinkLinkerTextFormat
	 * @return
	 * @throws Exception
	 */
	public static PLinkLinker getPLinkLinker( String name, String pLinkLinkerTextFormat ) throws Exception {
		PLinkLinker linker = new PLinkLinker();
		
		String[] fields = pLinkLinkerTextFormat.split( " " );
		if( fields.length != 6 )
			throw new Exception( "Did not get six fields in linker representation: " + pLinkLinkerTextFormat );
		
		linker.setName( name );
		linker.setFirstLinkedResidueMotif( fields[ 0 ] );
		linker.setSecondLinkedResidueMotif( fields[ 1 ] );
		linker.setMonoCrosslinkMass( Double.valueOf( fields[ 2 ] ) );

		if( !fields[ 3 ].equals( "" ) && !fields[ 3 ].equals( "0" ) )
			linker.setAverageCrosslinkMass( Double.valueOf( fields[ 3 ] ) );
		
		if( !fields[ 4 ].equals( "" ) && !fields[ 4 ].equals( "0" ) )
			linker.setMonoMonolinkMass( Double.valueOf( fields[ 4 ] ) );

		if( !fields[ 5 ].equals( "" ) && !fields[ 5 ].equals( "0" ) )
			linker.setAverageMonolinkMass( Double.valueOf( fields[ 5 ] ) );
		
		return linker;
	}
	
}
