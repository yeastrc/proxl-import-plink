package org.yeastrc.proxl.xml.plink.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.yeastrc.proxl.xml.plink.objects.PLinkModification;
import org.yeastrc.proxl.xml.plink.objects.PLinkPeptide;
import org.yeastrc.proxl.xml.plink.objects.PLinkReportedPeptide;
import org.yeastrc.proxl.xml.plink.reader.PLinkConstants;
import org.yeastrc.proxl.xml.plink.reader.PLinkSearchParameters;

/**
 * Some utility methods for working with reported peptides.
 * 
 * @author Michael Riffle
 * @date Mar 23, 2016
 *
 */
public class PLinkReportedPeptideUtils {

	/**
	 * Get the reported peptide object based on the supplied peptide string and type of peptide
	 * @param plinkPeptideString The peptide, as reported by plink (e.g. "NTLQPVEKALNDAKMDK(14)-KSNISEKTK(1):0")
	 * @param peptideType The type, as defined in PLinkConstants (e.g. PLinkConstants.LINK_TYPE_CROSSLINK)
	 * @return The populated reported peptide object
	 * @throws Exception
	 */
	public static PLinkReportedPeptide getReportedPeptide( String plinkPeptideString, String plinkModString, int peptideType, PLinkSearchParameters params ) throws Exception {
		
		if ( peptideType == PLinkConstants.LINK_TYPE_CROSSLINK ) {
			return getReportedPeptideForCrosslink( plinkPeptideString, plinkModString, params );
		}
		
		if( peptideType == PLinkConstants.LINK_TYPE_LOOPLINK ) {
			return getReportedPeptideForLooplink( plinkPeptideString, plinkModString, params );
		}
		
		if( peptideType == PLinkConstants.LINK_TYPE_MONOLINK ) {
			return getReportedPeptideForMonolink( plinkPeptideString, plinkModString, params );
		}
		
		if( peptideType == PLinkConstants.LINK_TYPE_UNLINKED ) {
			return getReportedPeptideForUnlinked( plinkPeptideString, plinkModString, params );
		}
		
		throw new Exception( "unknown peptide type: " + peptideType );
	}

	/**
	 * Get the reported peptide for a crosslink peptide reported by plink
	 * @param plinkPeptideString
	 * @param plinkModString
	 * @param params
	 * @return
	 * @throws Exception
	 */
	private static PLinkReportedPeptide getReportedPeptideForCrosslink( String plinkPeptideString, String plinkModString, PLinkSearchParameters params ) throws Exception {
		
		PLinkReportedPeptide rp = new PLinkReportedPeptide();
		rp.setType( PLinkConstants.LINK_TYPE_CROSSLINK );
		
		// example peptide string: EMNQMTHGDNNEVKR(14)-MTKTKGEK(1):0
		String fields[] = plinkPeptideString.split( ":" );
		if( fields.length != 2 ) {
			throw new Exception( "Unexpected syntax for peptide: " + plinkPeptideString );
		}

		if( !fields[ 1 ].equals( "0" ) ) {
			throw new Exception( "Only unlabelled searches are currently supported by proxl." );
		}
		
		
		String[] sequences = fields[ 0 ].split( "-" );
		if( sequences.length != 2 )
			throw new Exception( "Did not get two sequences from crosslinked sequence pair: " + plinkPeptideString );
		
		Pattern p = Pattern.compile( "^([A-Z]+)\\((\\d+)\\)$" );

		for( int i = 0; i < 2; i++ ) {
			String sequence = sequences[ i ];
			
			Matcher m = p.matcher( sequence );
			
			if( !m.matches() ) {
				throw new Exception( "Unexpected syntax for looplink peptide: " + sequence );
			}
			
			String parsedSequence = m.group( 1 );
			int position = Integer.parseInt( m.group( 2 ) );
			
			if( position > parsedSequence.length() )
				throw new Exception( "Linked position is outside of peptide: " + plinkPeptideString );
			
			PLinkPeptide pep = new PLinkPeptide();
			pep.setSequence( parsedSequence );
			
			if( i == 0 ) {
				rp.setPeptide1( pep );
				rp.setPosition1( position );
			} else {
				rp.setPeptide2( pep );
				rp.setPosition2( position );
			}
		}


		/*
		 * Add dynamic mods to the two peptides:
		 * 
		 * Mods are not reported separately for each peptide, but altogether with the index of mods in the second
		 * peptide starting at the length of the first peptide + 1.
		 * E.g. for crosslink: EMNQMTHGDNNEVKR(14)-MTKTKGEK(1):0
		 * and reported mods: 2,M(Oxidation[M]);5,M(Oxidation[M]);18,K(Methyl[K]);23,K(Methyl[K]);
		 * The first peptide has length 15. Any reported mods > 15 are in the second peptide. 18 is
		 * position 3 in the second peptide, 23 is position 8 in the second peptide
		 */		
		Map<Integer, Collection<PLinkModification>> mods = ModificationLookupUtils.getDynamicModificationsFromModString( plinkModString, params );
		Map<Integer, Collection<PLinkModification>> mods1 = new HashMap<Integer, Collection<PLinkModification>>();	// mods belonging to peptide1
		Map<Integer, Collection<PLinkModification>> mods2 = new HashMap<Integer, Collection<PLinkModification>>();	// mods belonging to peptide2
		for( int modPosition : mods.keySet() ) {
			Map<Integer, Collection<PLinkModification>> tmods = null;
			int position = modPosition;
			
			if( modPosition > rp.getPeptide1().getSequence().length() ) {
				position -= rp.getPeptide1().getSequence().length();
				tmods = mods2;
			} else {
				tmods = mods1;
			}
			
			tmods.put( position, mods.get( modPosition ) );
		}
		
		rp.getPeptide1().setMods( mods1 );
		rp.getPeptide2().setMods( mods2 );
		
		
		// ensure peptide1 is never alphabetically greater than peptide1
		if( rp.getPeptide1().getSequence().compareTo( rp.getPeptide2().getSequence() ) > 0 ) {
			
			PLinkPeptide tmpPeptide = rp.getPeptide1();
			int tmpPosition = rp.getPosition1();
			
			rp.setPeptide1( rp.getPeptide2() );
			rp.setPosition1( rp.getPosition2() );
			
			rp.setPeptide2( tmpPeptide );
			rp.setPosition2( tmpPosition);
			
		} else if( rp.getPeptide1().getSequence().equals( rp.getPeptide2().getSequence() ) ) {
			if( rp.getPosition1() > rp.getPosition2() ) {
				int tmpPosition = rp.getPosition1();
				
				rp.setPosition1( rp.getPosition2() );
				rp.setPosition2( tmpPosition );
			}
		}
		
		return rp;
	}
	
	/**
	 * Get the reported peptide for a monolink peptide reported by plink
	 * @param plinkPeptideString
	 * @param plinkModString
	 * @param params
	 * @return
	 * @throws Exception
	 */
	private static PLinkReportedPeptide getReportedPeptideForMonolink( String plinkPeptideString, String plinkModString, PLinkSearchParameters params ) throws Exception {
		
		PLinkReportedPeptide rp = new PLinkReportedPeptide();
		rp.setType( PLinkConstants.LINK_TYPE_MONOLINK );
		
		// example peptide string: VFAPEEISAMVLGKMK(14):0
		String fields[] = plinkPeptideString.split( ":" );
		if( fields.length != 2 ) {
			throw new Exception( "Unexpected syntax for peptide: " + plinkPeptideString );
		}

		if( !fields[ 1 ].equals( "0" ) ) {
			throw new Exception( "Only unlabelled searches are currently supported by proxl." );
		}
		
		String sequence = fields[ 0 ];
		Pattern p = Pattern.compile( "^([A-Z]+)\\((\\d+)\\)$" );
		Matcher m = p.matcher( sequence );
		
		if( !m.matches() ) {
			throw new Exception( "Unexpected syntax for looplink peptide: " + sequence );
		}
		
		String parsedSequence = m.group( 1 );
		int position1 = Integer.parseInt( m.group( 2 ) );
		
		
		if( position1 > parsedSequence.length() )
			throw new Exception( "The parsed position from: " + plinkPeptideString + " is greater than the length of the parsed sequence." );
		
		Map<Integer, Collection<PLinkModification>> mods = ModificationLookupUtils.getDynamicModificationsFromModString( plinkModString, params );
		
		// add monolink as a mod
		if( !mods.containsKey( position1 ) )
			mods.put( position1, new ArrayList<PLinkModification>() );
		
		PLinkModification monoLinkMod = new PLinkModification();
		monoLinkMod.setAverageMass( params.getLinker().getAverageMonolinkMass() );
		monoLinkMod.setMonoisotopicMass( params.getLinker().getMonoMonolinkMass() );
		monoLinkMod.setMonolink( true );
		monoLinkMod.setName( "monolink" );
		
		mods.get( position1 ).add( monoLinkMod );
		
		PLinkPeptide pep = new PLinkPeptide();
		pep.setSequence( parsedSequence );
		pep.setMods( mods );
		
		rp.setPeptide1( pep );
		rp.setPosition1( position1 );
		
		return rp;
	}
	
	/**
	 * Get the reported peptide for a looplink peptide reported by plink
	 * @param plinkPeptideString
	 * @param plinkModString
	 * @param params
	 * @return
	 * @throws Exception
	 */
	private static PLinkReportedPeptide getReportedPeptideForLooplink( String plinkPeptideString, String plinkModString, PLinkSearchParameters params ) throws Exception {
		
		PLinkReportedPeptide rp = new PLinkReportedPeptide();
		rp.setType( PLinkConstants.LINK_TYPE_LOOPLINK );
		
		// example peptide string: DEQGENDLAKASQNK(10)(15):0
		String fields[] = plinkPeptideString.split( ":" );
		if( fields.length != 2 ) {
			throw new Exception( "Unexpected syntax for peptide: " + plinkPeptideString );
		}

		if( !fields[ 1 ].equals( "0" ) ) {
			throw new Exception( "Only unlabelled searches are currently supported by proxl." );
		}
		
		String sequence = fields[ 0 ];
		Pattern p = Pattern.compile( "^([A-Z]+)\\((\\d+)\\)\\((\\d+)\\)$" );
		Matcher m = p.matcher( sequence );
		
		if( !m.matches() ) {
			throw new Exception( "Unexpected syntax for looplink peptide: " + sequence );
		}
		
		String parsedSequence = m.group( 1 );
		int position1 = Integer.parseInt( m.group( 2 ) );
		int position2 = Integer.parseInt( m.group( 3 ) );
		
		if( position1 > parsedSequence.length() || position2 > parsedSequence.length() )
			throw new Exception( "One of the parsed positions from: " + plinkPeptideString + " is greater than the length of the parsed sequence." );
		
		Map<Integer, Collection<PLinkModification>> mods = ModificationLookupUtils.getDynamicModificationsFromModString( plinkModString, params );
		
		
		PLinkPeptide pep = new PLinkPeptide();
		pep.setSequence( parsedSequence );
		pep.setMods( mods );
		
		rp.setPeptide1( pep );
		rp.setPosition1( position1 );
		rp.setPosition2( position2 );
		
		return rp;
	}
	
	/**
	 * Get the reported peptide for an unlinked peptide reported by plink
	 * @param plinkPeptideString
	 * @param plinkModString
	 * @return
	 * @throws Exception
	 */
	private static PLinkReportedPeptide getReportedPeptideForUnlinked( String plinkPeptideString, String plinkModString, PLinkSearchParameters params ) throws Exception {

		PLinkReportedPeptide rp = new PLinkReportedPeptide();
		rp.setType( PLinkConstants.LINK_TYPE_UNLINKED );
		
		// example peptide string: QRVESHFDLELRASVMHDIVDMMPEGIK:-1
		String fields[] = plinkPeptideString.split( ":" );
		if( fields.length != 2 ) {
			throw new Exception( "Unexpected syntax for peptide: " + plinkPeptideString );
		}

		String sequence = fields[ 0 ];
		
		Map<Integer, Collection<PLinkModification>> mods = ModificationLookupUtils.getDynamicModificationsFromModString( plinkModString, params );
				
		PLinkPeptide pep = new PLinkPeptide();
		pep.setSequence( sequence );
		pep.setMods( mods );
		
		rp.setPeptide1( pep );
		
		return rp;
	}
	
	
	
	

	
}
