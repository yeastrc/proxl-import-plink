package org.yeastrc.proxl.xml.plink.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.yeastrc.proxl.xml.plink.objects.PLinkResult;
import org.yeastrc.proxl.xml.plink.utils.PLinkUtils;

/**
 * Get the results of the supplied type based on the supplied parameters
 * @author mriffle
 *
 */
public class PLinkResultsLoader {

	private static PLinkResultsLoader _INSTANCE = new PLinkResultsLoader();
	public static PLinkResultsLoader getInstance() { return _INSTANCE; }
	
	private PLinkResultsLoader() { }
	
	/**
	 * Read and return all results from the plink analysis for the given type of peptide
	 * @param params The params file for the search
	 * @param dataDirectory Optional, the directory in which the data live. If null, the value from the plink.ini file will be used
	 * @param type The type of the peptide (e.g. PLinkConstants.LINK_TYPE_CROSSLINK)
	 * @return All of the corresponding results
	 * @throws Exception
	 */
	private Collection<PLinkResult> getResults( PLinkSearchParameters params, String dataDirectory, int type ) throws Exception {
		Collection<PLinkResult> results = new ArrayList<PLinkResult>();
		
		if( dataDirectory == null )
			dataDirectory = PLinkUtils.getOutputDirectory( params );
		
		String subdir = "1.sample/search/";
		subdir += PLinkUtils.getSearchTitle( params ) + "_";
		subdir += PLinkConstants.PLINK_NAME_FOR_TYPE.get( type ) + "_";
		subdir += "combine";
		
		File fullDataDirectory = new File( dataDirectory, subdir );
		if( !fullDataDirectory.exists() )
			throw new Exception( "can not find data directory: " + fullDataDirectory );
		
		String filename = PLinkUtils.getSearchTitle( params );
		filename += "_" + PLinkConstants.PLINK_NAME_FOR_TYPE.get( type ) + "_";
		filename += "combine.spectra.xls";
		
		File dataFile = new File( fullDataDirectory, filename );
		if( !dataFile.exists() )
			throw new Exception( "can not find data file: " + dataFile );
		
		PLinkResultsFileReader plReader = null;
		
		try {
			plReader = PLinkResultsFileReader.getPLinkResultsFileReader( dataFile, type, params );
			
			PLinkResult result = plReader.readNextResult();
		
			while( result != null ) {
				results.add( result );
				result = plReader.readNextResult();
			}

		} finally {
			if( plReader != null )
				plReader.close();
		}
				
		return results;
	}
	
	/**
	 * Read and return all results from the plink analysis
	 * @param params The params file for the search
	 * @param dataDirectory Optional, the directory in which the data live. If null, the value from the plink.ini file will be used
	 * @return All of the corresponding results
	 * @throws Exception
	 * @param params
	 * @param dataDirectory
	 * @return
	 * @throws Exception
	 */
	public Collection<PLinkResult> getAllResults( PLinkSearchParameters params, String dataDirectory ) throws Exception {
		Collection<PLinkResult> results = new ArrayList<PLinkResult>();
		
		results.addAll( this.getResults( params, dataDirectory, PLinkConstants.LINK_TYPE_CROSSLINK ) );

		if( PLinkUtils.getNoninterexport( params ) ) {
			results.addAll( this.getResults( params, dataDirectory, PLinkConstants.LINK_TYPE_LOOPLINK ) );
			results.addAll( this.getResults( params, dataDirectory, PLinkConstants.LINK_TYPE_MONOLINK ) );
			results.addAll( this.getResults( params, dataDirectory, PLinkConstants.LINK_TYPE_UNLINKED ) );
		}
		
		return results;
	}
	
}
