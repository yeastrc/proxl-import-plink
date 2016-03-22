package org.yeastrc.proxl.xml.plink.reader;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PLinkConstants {

	public static final String SEARCH_PROGRAM_NAME = "pLink";
	public static final String SEARCH_PROGRAM_VERSION = "Version Unknown";
	
	
	public static final int LINK_TYPE_MONOLINK = 0;
	public static final int LINK_TYPE_CROSSLINK = 1;
	public static final int LINK_TYPE_LOOPLINK = 2;
	public static final int LINK_TYPE_UNLINKED = 3;
	
	public static final String MODIFY_INI_FILENAME = "modify.ini";
	public static final String XLINK_INI_FILENAME = "xlink.ini";
	
	public static final String DATA_SUBDIRECTORY = "1.sample/search";
	
	/**
	 * The string with which plink refers to the types of linked peptides, used
	 * to find subdirectories for data
	 */
	public static final Map<Integer, String> PLINK_NAME_FOR_TYPE;	
	
	/**
	 * The crosslinkers that proxl currently knows how to deal with (e.g., linking rules)
	 */
	public static final Map<String, String> LINKER_MAP_PLINK2PROXL;
	
	static {
        Map<String, String> tempLinkerMap = new HashMap<String, String>();
        
        tempLinkerMap.put( "BS3", "bs3" );
        tempLinkerMap.put( "DSS",  "dss" );
        tempLinkerMap.put( "EDC-D",  "edc" );
        tempLinkerMap.put( "EDC-E",  "edc" );
        tempLinkerMap.put( "BS2G",  "bs2" );
        
        LINKER_MAP_PLINK2PROXL = Collections.unmodifiableMap(tempLinkerMap);
        
        
        
        
        Map<Integer, String> tempLinkTypeMap = new HashMap<Integer, String>();
        tempLinkTypeMap.put( LINK_TYPE_MONOLINK, "mono" );
        tempLinkTypeMap.put( LINK_TYPE_CROSSLINK, "inter" );
        tempLinkTypeMap.put( LINK_TYPE_LOOPLINK, "loop" );
        tempLinkTypeMap.put( LINK_TYPE_UNLINKED, "common" );
        
        PLINK_NAME_FOR_TYPE = Collections.unmodifiableMap( tempLinkTypeMap );
	}
	
	
	
	

	
}
