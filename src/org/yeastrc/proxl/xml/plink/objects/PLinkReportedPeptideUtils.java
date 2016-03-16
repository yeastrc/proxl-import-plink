package org.yeastrc.proxl.xml.plink.objects;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.yeastrc.proxl.xml.plink.reader.ModificationLookupUtils;
import org.yeastrc.proxl.xml.plink.reader.PLinkConstants;
import org.yeastrc.proxl.xml.plink.reader.PLinkSearchParameters;

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
			//return getReportedPeptideForCrosslink( plinkPeptideString, plinkModString );
		}
		
		if( peptideType == PLinkConstants.LINK_TYPE_LOOPLINK ) {
			//return getReportedPeptideForLooplink( plinkPeptideString, plinkModString );
		}
		
		if( peptideType == PLinkConstants.LINK_TYPE_MONOLINK ) {
			//return getReportedPeptideForMonolink( plinkPeptideString, plinkModString );
		}
		
		if( peptideType == PLinkConstants.LINK_TYPE_UNLINKED ) {
			return getReportedPeptideForUnlinked( plinkPeptideString, plinkModString, params );
		}
		
		throw new Exception( "unknown peptide type: " + peptideType );
	}

	/**
	 * Get the reported peptide for a looplink peptide reported by plink
	 * @param plinkPeptideString
	 * @param plinkModString
	 * @param params
	 * @return
	 * @throws Exception
	 */
	private static PLinkReportedPeptide getReportedPeptideForMonolink( String plinkPeptideString, String plinkModString, PLinkSearchParameters params ) throws Exception {
		
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
				
		Map<Integer, Collection<PLinkModification>> mods = ModificationLookupUtils.getDynamicModificationsFromModString( plinkModString, params );
		
		PLinkReportedPeptide rp = new PLinkReportedPeptide();
		
		PLinkPeptide pep = new PLinkPeptide();
		pep.setSequence( sequence );
		pep.setMods( mods );
		
		rp.setPeptide1( pep );
		rp.setPosition1( position1 );
		rp.setPosition2( position2 );
		rp.setType( PLinkConstants.LINK_TYPE_LOOPLINK );
		
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

		// example peptide string: QRVESHFDLELRASVMHDIVDMMPEGIK:-1
		String fields[] = plinkPeptideString.split( ":" );
		if( fields.length != 2 ) {
			throw new Exception( "Unexpected syntax for peptide: " + plinkPeptideString );
		}

		String sequence = fields[ 0 ];
		
		Map<Integer, Collection<PLinkModification>> mods = ModificationLookupUtils.getDynamicModificationsFromModString( plinkModString, params );
		
		PLinkReportedPeptide rp = new PLinkReportedPeptide();
		
		PLinkPeptide pep = new PLinkPeptide();
		pep.setSequence( sequence );
		pep.setMods( mods );
		
		rp.setPeptide1( pep );
		rp.setType( PLinkConstants.LINK_TYPE_UNLINKED );
		
		return rp;
	}
	
	
	
	

	
}
