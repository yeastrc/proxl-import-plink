package org.yeastrc.proxl.xml.plink.reader;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PLinkConstants {

	public static final String MODIFY_INI_FILENAME = "modify.ini";
	public static final String XLINK_INI_FILENAME = "xlink.ini";

	/**
	 * The crosslinkers that proxl currently knows how to deal with (e.g., linking rules)
	 */
	public static final Map<String, String> LINKER_MAP_PLINK2PROXL;
	
	static {
        Map<String, String> tMap = new HashMap<String, String>();
        
        tMap.put( "BS3", "bs3" );
        tMap.put( "DSS",  "dss" );
        tMap.put( "EDC-D",  "edc" );
        tMap.put( "EDC-E",  "edc" );
        tMap.put( "BS2G",  "bs2" );
        
        LINKER_MAP_PLINK2PROXL = Collections.unmodifiableMap(tMap);
	}
	
	
}
