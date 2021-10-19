/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.service.groovyexamples;

import de.hybris.platform.scripting.engine.ScriptExecutable;
import de.hybris.platform.scripting.engine.ScriptExecutionResult;
import de.hybris.platform.scripting.engine.ScriptingLanguagesService;
import de.hybris.platform.scripting.engine.content.ScriptContent;
import de.hybris.platform.scripting.engine.content.impl.ResourceScriptContent;

import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;


/**
 *
 */
public class GroovyScriptRemoteResourceExample
{

	private ScriptingLanguagesService scriptingLanguagesService;

	public void execute() throws MalformedURLException
	{

		final String engineName = "groovy";

		final Resource scriptResource = new UrlResource("https://github.com/apanwar/sapcommerce/blob/main/Create-Car.groovy");

		final ScriptContent scriptContent = new ResourceScriptContent(scriptResource);

		final ScriptExecutable executable = scriptingLanguagesService.getExecutableByContent(scriptContent);

		final ScriptExecutionResult executionResult = executable.execute();

		System.out.println(executionResult.getScriptResult());


	}

}
