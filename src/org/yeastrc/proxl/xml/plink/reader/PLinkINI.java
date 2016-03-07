package org.yeastrc.proxl.xml.plink.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;

/**
 * Represents a pLink INI file.
 * 
 * @author Michael Riffle
 * @date Mar 5, 2016
 *
 */
public class PLinkINI {

	public PLinkINI( String filename ) throws ConfigurationException, FileNotFoundException, IOException {
		config = new INIConfiguration();
		config.setExpressionEngine( INIUtils.EXPRESSION_ENGINE );
		config.read( new FileReader( new File( filename ) ) );
	}
	
	
	public INIConfiguration getConfig() {
		return config;
	}


	private INIConfiguration config;
}
