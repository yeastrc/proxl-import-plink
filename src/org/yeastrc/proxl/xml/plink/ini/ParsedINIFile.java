package org.yeastrc.proxl.xml.plink.ini;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.yeastrc.proxl.xml.plink.utils.INIUtils;

/**
 * Represents a parsed INI file
 * 
 * @author Michael Riffle
 * @date Mar 5, 2016
 *
 */
public class ParsedINIFile {

	public ParsedINIFile( String filename ) throws ConfigurationException, FileNotFoundException, IOException {
		config = new INIConfiguration();
		config.setExpressionEngine( INIUtils.EXPRESSION_ENGINE );
		config.read( new FileReader( new File( filename ) ) );
	}
	
	public ParsedINIFile( File file ) throws ConfigurationException, FileNotFoundException, IOException {
		config = new INIConfiguration();
		config.setExpressionEngine( INIUtils.EXPRESSION_ENGINE );
		config.read( new FileReader( file ) );
	}
	
	/**
	 * Gets an objects where properties from the INI file may be referenced as:
	 * "section_name/key_name" E.g., for:
	 * [Section]
	 * foo=bar
	 * number=6
	 * 
	 * The value for "foo" would be referenced as: iniFile.getConfig.getString( "Section/foo" )
	 * 
	 * The value for "number" would be referenced as: iniFile.getConfig.getInt( "Section/number" );
	 * 
	 * @return
	 */
	public INIConfiguration getConfig() {
		return config;
	}


	private INIConfiguration config;
}
