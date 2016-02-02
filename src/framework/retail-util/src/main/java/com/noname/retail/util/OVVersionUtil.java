package com.noname.retail.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OVVersionUtil {

	public static final String ORIGINAL_VERSION = "4.1.1";
	public static final String OV412R02_VERSION = "4.1.2R02";

	private static List<String> versionHistory = new ArrayList<String>();
	static{
		versionHistory.add(ORIGINAL_VERSION);
		versionHistory.add(OV412R02_VERSION);
	}

	public static List<String> getVersionHistory() {
		return versionHistory;
	}
	public static <T> T retrieveMatchProcessor(String version, List<String> ovVersionHistory, Map<String, T> processors){
		T rs = null;
		if (ovVersionHistory.contains(version)) {
			rs = processors.get(version);
			int index = ovVersionHistory.indexOf(version);
			while (index >= 0 && rs == null) {
				rs = processors.get(ovVersionHistory.get(index));
				index--;
			}
		}
		return rs;
	}
	public static void main(String a[]){
		List<String> ovVersionHistory = new ArrayList<>();
		ovVersionHistory.add("a");
		ovVersionHistory.add("b");
		ovVersionHistory.add("c");
		ovVersionHistory.add("d");
		ovVersionHistory.add("e");
		ovVersionHistory.add("f");

		List<String> serviceVersionHistory = new ArrayList<>();
		serviceVersionHistory.add("a");

		serviceVersionHistory.add("c");

		serviceVersionHistory.add("f");

		Map<String, Integer> processors = new HashMap<>();
		processors.put("a", 1);

		Integer rs = retrieveMatchProcessor("f", ovVersionHistory, processors);
		System.out.println(rs);
	}
}
