pLink 1.x to ProXL XML Converter
==================================

Use this program to convert the results of a pLink 1.x cross-linking analysis to Proxl XML suitable for import into the proxl web application.

Note: If you are using pLink 2.x, please go to [pLink 2.x converter](https://github.com/yeastrc/proxl-import-plink2)

How To Run
-------------
1. Download the [latest release](https://github.com/yeastrc/proxl-import-plink/releases).
2. Run the program ``java -jar plink1toProxlXML.jar`` with no arguments to see the possible parameters.
3. Run the program, e.g., ``java -jar plink1toProxlXML.jar -i c:/plink_runs/search/pLink.ini -o c:/output/output.proxl.xml``

In the above example, ``output.proxl.xml`` will be created and be suitable for import into ProXL.

For more information on importing data into Proxl, please see the [Proxl Import Documentation](http://proxl-web-app.readthedocs.io/en/latest/using/upload_data.html).

More Information About Proxl
-----------------------------
For more information about Proxl, visit http://proxl-ms.org/.
