package org.yeastrc.proxl.xml.plink.utils;

import java.io.File;

import org.yeastrc.proxl.xml.plink.reader.PLinkSearchParameters;

/**
 * Some utility methods for querying specific parameters from the INI files.
 * 
 * @author Michael Riffle
 * @date Mar 23, 2016
 *
 */
public class PLinkUtils {

	public static String getOutputDirectory( PLinkSearchParameters params ) throws Exception {
		String dir = params.getPlinkINI().getConfig().getString( "pLink/output.path" );
		
		if( dir == null )
			throw new Exception( "could not get output directory..." );
		
		return dir;
	}
	
	public static String getSearchTitle( PLinkSearchParameters params ) throws Exception {
		String dir = params.getPlinkINI().getConfig().getString( "pLink/sample1.spectra.title" );
		
		if( dir == null )
			throw new Exception( "could not get search title..." );
		
		return dir;
	}
	
	public static boolean getNoninterexport( PLinkSearchParameters params ) throws Exception {
		return params.getPlinkINI().getConfig().getBoolean( "pLink/noninterexport" );
	}
	
	public static String getFASTAFilename( PLinkSearchParameters params ) throws Exception {
		File f = new File( params.getPlinkINI().getConfig().getString( "pLink/database.path" ) );
		return f.getName();
	}
	
}
