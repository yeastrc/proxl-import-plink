package org.yeastrc.proxl.xml.plink.annotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.yeastrc.proxl_import.api.xml_dto.DescriptivePsmAnnotationType;
import org.yeastrc.proxl_import.api.xml_dto.FilterDirectionType;
import org.yeastrc.proxl_import.api.xml_dto.FilterablePsmAnnotationType;

public class PSMAnnotationTypes {

	public static final String ANNOTATION_TYPE_SCORE = "E-value";
	public static final String ANNOTATION_TYPE_CALC_MASS = "Calculated Mass";
	public static final String ANNOTATION_TYPE_DELTA_MASS = "Delta Mass";
	public static final String ANNOTATION_TYPE_PPM = "Delta Mass (PPM)";	
	
	/**
	 * Get the list of filterable PSM annotation types in StavroX data
	 * @return
	 */
	public static List<FilterablePsmAnnotationType> getFilterablePsmAnnotationTypes() {
		List<FilterablePsmAnnotationType> types = new ArrayList<FilterablePsmAnnotationType>();

		{
			FilterablePsmAnnotationType type = new FilterablePsmAnnotationType();
			type.setName( ANNOTATION_TYPE_SCORE );
			type.setDescription( "PLink Expect Value - Number of hits you can expect of this quality by chance." );
			type.setDefaultFilterValue( new BigDecimal( "0.01" ) );
			type.setDefaultFilter( true );
			type.setFilterDirection( FilterDirectionType.BELOW );
			
			types.add( type );
		}
		
		return types;
	}
	
	/**
	 * Get the list of descriptive (non-filterable) PSM annotation types in StavroX data
	 * @return
	 */
	public static List<DescriptivePsmAnnotationType> getDescriptivePsmAnnotationTypes() {
		List<DescriptivePsmAnnotationType> types = new ArrayList<DescriptivePsmAnnotationType>();
		
		{
			DescriptivePsmAnnotationType type = new DescriptivePsmAnnotationType();
			type.setName( ANNOTATION_TYPE_CALC_MASS );
			type.setDescription( type.getName() );
			
			types.add( type );
		}

		{
			DescriptivePsmAnnotationType type = new DescriptivePsmAnnotationType();
			type.setName( ANNOTATION_TYPE_DELTA_MASS );
			type.setDescription( type.getName() );
			
			types.add( type );
		}
		
		{
			DescriptivePsmAnnotationType type = new DescriptivePsmAnnotationType();
			type.setName( ANNOTATION_TYPE_PPM );
			type.setDescription( type.getName() );
			
			types.add( type );
		}
		
		return types;		
	}
	
}
