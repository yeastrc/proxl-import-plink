package org.yeastrc.proxl.xml.plink.objects;

import java.util.Collection;
import java.util.Map;

public class PLinkPeptide {
	
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public Map<Integer, Collection<PLinkModification>> getMods() {
		return mods;
	}
	public void setMods(Map<Integer, Collection<PLinkModification>> mods) {
		this.mods = mods;
	}
	
	private String sequence;
	private Map<Integer, Collection<PLinkModification>> mods;
	
}
