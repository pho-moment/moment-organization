package com.moment.gen;

public class GenMain {
	public static void main(String[] args) {
		String configFile = "/generatorConfig.xml";
		try {

			 String[] tableNames = new String[] { "admin"};
			GenMybatisFiles.gen(configFile, tableNames);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
