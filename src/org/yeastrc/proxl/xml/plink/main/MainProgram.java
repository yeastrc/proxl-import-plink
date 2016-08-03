package org.yeastrc.proxl.xml.plink.main;

import jargs.gnu.CmdLineParser;
import jargs.gnu.CmdLineParser.IllegalOptionValueException;
import jargs.gnu.CmdLineParser.UnknownOptionException;

import java.io.File;
import java.util.Collection;

import org.yeastrc.proxl.xml.plink.builder.XMLBuilder;
import org.yeastrc.proxl.xml.plink.objects.PLinkResult;
import org.yeastrc.proxl.xml.plink.reader.PLinkResultsLoader;
import org.yeastrc.proxl.xml.plink.reader.PLinkSearchParameters;
import org.yeastrc.proxl.xml.plink.reader.PLinkSearchParametersLoader;

/**
 * Run the program.
 * @author Michael Riffle
 * @date Mar 23, 2016
 *
 */
public class MainProgram {

	
	public void convertSearch( String plinkINI, String plinkBinDirectory, String plinkDataDirectory, String outfile, String fastaFilePath ) throws Exception {
		
		PLinkSearchParameters params = PLinkSearchParametersLoader.getInstance().getPLinkSearch( plinkINI, plinkBinDirectory );
		Collection<PLinkResult> results = PLinkResultsLoader.getInstance().getAllResults( params, plinkDataDirectory );
		
		XMLBuilder builder = new XMLBuilder();
		builder.buildAndSaveXML(params, results, new File( outfile ), fastaFilePath );
		
	}
	
	public static void main( String[] args ) throws Exception {
		
		if( args.length < 1 || args[ 0 ].equals( "-h" ) ) {
			printHelp();
			System.exit( 0 );
		}
		
		CmdLineParser cmdLineParser = new CmdLineParser();
        
		CmdLineParser.Option plinkINIOpt = cmdLineParser.addStringOption( 'i', "ini" );	
		CmdLineParser.Option outfileOpt = cmdLineParser.addStringOption( 'o', "out" );	
		CmdLineParser.Option binDirectoryOpt = cmdLineParser.addStringOption( 'b', "bin" );	
		CmdLineParser.Option dataDirectoryOpt = cmdLineParser.addStringOption( 'd', "data" );
		CmdLineParser.Option fastaFileOpt = cmdLineParser.addStringOption( 'f', "fasta" );

        // parse command line options
        try { cmdLineParser.parse(args); }
        catch (IllegalOptionValueException e) {
        	printHelp();
            System.exit( 1 );
        }
        catch (UnknownOptionException e) {
           printHelp();
           System.exit( 1 );
        }
		
        String iniFile = (String)cmdLineParser.getOptionValue( plinkINIOpt );
        String outFile = (String)cmdLineParser.getOptionValue( outfileOpt );
        String binDirectory = (String)cmdLineParser.getOptionValue( binDirectoryOpt );
        String dataDirectory = (String)cmdLineParser.getOptionValue( dataDirectoryOpt );
        String fastaFilePath = (String)cmdLineParser.getOptionValue( fastaFileOpt );
        
        MainProgram mp = new MainProgram();
        mp.convertSearch( iniFile, binDirectory, dataDirectory, outFile, fastaFilePath );
        
	}
	
	/**
	 * Print helpt to STD OUT
	 */
	private static void printHelp() {
		
		System.out.println( "Description: Convert the results of a pLink analysis to a ProXL XML file suitable for import into ProXL." );
		System.out.println( "             Designed to work only with single, label-free searches that used a single cross-linker.\n" );
		
		System.out.println( "Usage: java -jar plink2ProxlXML.jar -i path -o path [-b path ] [-d path]\n" );

		System.out.println( "Example: java -jar plink2ProxlXML.jar -i c:\\plink_runs\\pLink.ini -o \"c:\\out put\\mySearch.proxl.xml\"" );
		System.out.println( "         java -jar plink2ProxlXML.jar -i c:/plink_runs/search/pLink.ini -o c:/output/mySearch.proxl.xml");
		System.out.println( "         java -jar plink2ProxlXML.jar -i /dir/pLink.ini -o /dir/mySearch.proxl.xml -b /dir/plink_install/release -d /dir/searches/search\n");
		
		System.out.println( "Options:" );
		System.out.println( "\t-i\t[Required] Full path to pLink.ini file used in the search." );
		System.out.println( "\t-o\t[Required] Full path to use for the outputfile (including file name)." );
		System.out.println( "\t-f\t[Optional] Full path to FASTA file used in the experiment." );
		System.out.println( "\t\t           If not present, value from pLink.ini is used." );
		System.out.println( "\t-b\t[Optional] Full path to the pLink binary directory, where modify.ini and xlink.ini may be found." );
		System.out.println( "\t\t           If not present, value from pLink.ini is used." );
		System.out.println( "\t-d\t[Optional] Full path to the data output directory for pLink results. This directory typically" );
		System.out.println( "\t\t           contains 0.index, 1.sample, and 2.report directories." );
		System.out.println( "\t\t           If not present, value from pLink.ini is used." );
		
	}
}
