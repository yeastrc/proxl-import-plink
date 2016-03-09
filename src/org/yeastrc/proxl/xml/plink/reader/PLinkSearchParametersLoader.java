package org.yeastrc.proxl.xml.plink.reader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.yeastrc.proxl.xml.plink.ini.ParsedINIFile;
import org.yeastrc.proxl.xml.plink.objects.PLinkLinker;

public class PLinkSearchParametersLoader {
	
	public static PLinkSearchParametersLoader getInstance() { return _INSTANCE; }
	private static final PLinkSearchParametersLoader _INSTANCE = new PLinkSearchParametersLoader();
	private PLinkSearchParametersLoader() { }
	
	public PLinkSearchParameters getPLinkSearch( String pLinkINIFilename, String pLinkBinDirectory ) throws Exception {

		PLinkSearchParameters plinkSearch = new PLinkSearchParameters();

		// load and add the plink.ini to the search
		plinkSearch.setPlinkINI( new ParsedINIFile( pLinkINIFilename) );
		
		String plinkPath = null;
		if( pLinkBinDirectory == null )
			plinkPath = plinkSearch.getPlinkINI().getConfig().getString( "pLink/bin.path" );
		else
			plinkPath = pLinkBinDirectory;
		
		// load and add the modify.ini
		plinkSearch.setModifyINI( new ParsedINIFile( new File(plinkPath, PLinkConstants.MODIFY_INI_FILENAME ) ) );
		
		// load and add the xlink.ini
		plinkSearch.setXlinkINI( new ParsedINIFile( new File(plinkPath, PLinkConstants.XLINK_INI_FILENAME ) ) );

		// load the referenced linker
		
		Map<String, PLinkLinker> linkerMap = new HashMap<String, PLinkLinker>();
				
		int linkerCount = plinkSearch.getPlinkINI().getConfig().getInt( "pLink/linker.total" );
		for( int i = 1; i <= linkerCount; i++ ) {
			String linkerName = plinkSearch.getPlinkINI().getConfig().getString( "pLink/linker.name" + i );
			if( linkerName == null ) {
				throw new Exception( "Could not find linker name in pLink.ini for linker # " + i );
			}

			String linkerDefinition = plinkSearch.getXlinkINI().getConfig().getString( "xlink/" + linkerName );
			
			if( linkerDefinition == null ) {
				throw new Exception( "Could not find linker: \"" + linkerName + "\" in xlink.ini file." );
			}
			
			PLinkLinker linker = PLinkLinkerUtils.getPLinkLinker( linkerName,  linkerDefinition );
			linkerMap.put( linkerName,  linker );
		}
		
		if( linkerCount > 2 ) {
			throw new Exception( "Can not currently import data with multiple cross-linkers." );
		} else if( linkerCount == 2 ) {
			
			// EDC is an exception to the current multiple linker exception, as it really is one linker
			// and proxl knows how to handle EDC
			if( !linkerMap.containsKey( "EDC-D" ) || !linkerMap.containsKey( "EDC-E" ) ) {
				throw new Exception( "Can not currently import data with multiple cross-linkers." );
			}
		}
		
		// since we only support 1 link, just set the linker to the first linker found
		plinkSearch.setLinker( linkerMap.get( plinkSearch.getPlinkINI().getConfig().getString( "pLink/linker.name1" ) ) );
		
		// ensure proxl supports this linker
		if( !PLinkConstants.LINKER_MAP_PLINK2PROXL.containsKey( plinkSearch.getLinker().getName() ) ) {
			throw new Exception( "Proxl does not currently support linker: " + plinkSearch.getLinker().getName() );
		}
		
		
		
		return plinkSearch;
	}
	
}
