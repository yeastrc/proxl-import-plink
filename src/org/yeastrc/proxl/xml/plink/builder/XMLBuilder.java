package org.yeastrc.proxl.xml.plink.builder;

import java.util.Collection;

import org.yeastrc.proxl.xml.plink.objects.PLinkResult;
import org.yeastrc.proxl.xml.plink.reader.PLinkConstants;
import org.yeastrc.proxl.xml.plink.reader.PLinkSearchParameters;
import org.yeastrc.proxl.xml.plink.utils.PLinkUtils;
import org.yeastrc.proxl_import.api.xml_dto.ProxlInput;
import org.yeastrc.proxl_import.api.xml_dto.SearchProgram;
import org.yeastrc.proxl_import.api.xml_dto.SearchProgramInfo;
import org.yeastrc.proxl_import.api.xml_dto.SearchPrograms;

public class XMLBuilder {

	public void buildAndSaveXML( PLinkSearchParameters params, Collection<PLinkResult> results, String fastaFilenameOverride ) throws Exception {

		ProxlInput proxlInputRoot = new ProxlInput();

		if( fastaFilenameOverride == null )
			proxlInputRoot.setFastaFilename( PLinkUtils.getFASTAFilename( params ) );
		else
			proxlInputRoot.setFastaFilename( fastaFilenameOverride );
		
		SearchProgramInfo searchProgramInfo = new SearchProgramInfo();
		proxlInputRoot.setSearchProgramInfo( searchProgramInfo );
		
		SearchPrograms searchPrograms = new SearchPrograms();
		searchProgramInfo.setSearchPrograms( searchPrograms );
		
		SearchProgram searchProgram = new SearchProgram();
		searchPrograms.getSearchProgram().add( searchProgram );
		
		searchProgram.setName( PLinkConstants.SEARCH_PROGRAM_NAME );
		searchProgram.setDisplayName( PLinkConstants.SEARCH_PROGRAM_NAME );
		searchProgram.setVersion( PLinkConstants.SEARCH_PROGRAM_VERSION );
		
		
	}
	
}
