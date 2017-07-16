package com.sanvito.pokereval.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

import com.sanvito.pokereval.controller.DecisionController;

/**
 * Created by Nitin on 08-11-2015.
 */
@Configuration
@ComponentScan(value = "com.sanvito.pokereval", excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class),
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
public class SpringConfig {

	static {
		InputStream is = DecisionController.class.getClassLoader().getResourceAsStream("dll/pbots_calc.dll");
		String tempDir = System.getProperty("java.io.tmpdir");
		File targetFile = new File(tempDir + "/pbots_calc.dll");
		try {
			java.nio.file.Files.copy(is, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		IOUtils.closeQuietly(is);
		System.setProperty("java.library.path", tempDir);
	}

	// @Bean(name = "jsonpCallbackFilter")
	// public JsonpCallbackFilter getJsonpCallbackFilter() {
	// return new JsonpCallbackFilter();
	// }
}