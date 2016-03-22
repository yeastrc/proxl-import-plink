package org.yeastrc.proxl.xml.plink.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.yeastrc.proxl.xml.plink.objects.PLinkReportedPeptide;
import org.yeastrc.proxl.xml.plink.objects.PLinkResult;
import org.yeastrc.proxl.xml.plink.utils.PLinkReportedPeptideUtils;
import org.yeastrc.proxl.xml.plink.utils.ScanParsingUtils;

public class PLinkResultsFileReader {

	private PLinkResultsFileReader( File file, int type, PLinkSearchParameters params ) {
		this.file = file;
		this.type = type;
		this.params = params;
	}
	
	/**
	 * Get a new results file reader
	 * @param file The file to read
	 * @param type The type of links being read (e.g. PLinkConstants.LINK_TYPE_CROSSLINK)
	 * @return
	 */
	public static PLinkResultsFileReader getPLinkResultsFileReader( File file, int type, PLinkSearchParameters params ) {
		return new PLinkResultsFileReader( file, type, params );
	}
	
	/**
	 * Close this reader, be sure to do this
	 */
	public void close() {
		this.isClosed = true;
		
		if( this.br != null ) {
			try { this.br.close(); this.br = null; }
			catch( Exception e ) { ; }
		}
	}
	
	/**
	 * Read the next plink result from the results file
	 * @return the next plink result, null if they have all been returned
	 * @throws Exception
	 */
	public PLinkResult readNextResult() throws Exception {
	
		if( this.isClosed )
			throw new Exception( "Called readNextResult() on closed result file reader." );
		
		if( this.isDone )
			return null;
		
		if( this.br == null ) {
			this.br = new BufferedReader( new FileReader( this.file ) );
			this.br.readLine();		// throw away header line 1
			this.br.readLine();		// throw away header line 2
		}
		
		
		String line1 = this.br.readLine();
		if( line1 == null ) {
			this.isDone = true;
			return null;
		}
		
		String line2 = this.br.readLine();
		if( line2 == null ) {
			throw new Exception( "Got unexpected end of file after: " + line1 );
		}
		
		/*
		System.out.println( "Processing:" );
		System.out.println( "\t" + line1 );
		System.out.println( "\t" + line2 );
		*/
		
		PLinkResult result = new PLinkResult();
		result.setType( type );
		
		String[] fields = line1.split( "\\t" );
		result.setScanNumber( ScanParsingUtils.getScanNumberFromReportedScan( fields[ 1 ] ) );
		result.setCharge( ScanParsingUtils.getChargeFromReportedScan( fields[ 1 ] ) );
		
		fields = line2.split( "\\t" );
		
		String rp = fields[ 2 ];
		String mods = fields[ 4 ];
		
		PLinkReportedPeptide reportedPeptide = PLinkReportedPeptideUtils.getReportedPeptide( rp, mods, type, params);
		result.setReportedPeptide( reportedPeptide );
		
		result.setCalculatedMass( Double.parseDouble( fields[ 6 ] ) );
		result.setDeltaMass( Double.parseDouble( fields[ 7 ] ) );
		result.setDeltaMassPPM( Double.parseDouble( fields[ 8 ] ) );
		result.setEvalue( Double.parseDouble( fields[ 5 ] ) );
		
		return result;
	}
	
	
	private File file;
	private BufferedReader br;
	private boolean isDone = false;
	private int type;
	private boolean isClosed = false;
	private PLinkSearchParameters params;
}
