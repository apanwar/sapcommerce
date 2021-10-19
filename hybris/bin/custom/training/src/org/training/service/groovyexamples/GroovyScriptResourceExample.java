/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.service.groovyexamples;

import de.hybris.platform.scripting.engine.ScriptExecutable;
import de.hybris.platform.scripting.engine.ScriptExecutionResult;
import de.hybris.platform.scripting.engine.ScriptingLanguagesService;
import de.hybris.platform.scripting.engine.content.ScriptContent;
import de.hybris.platform.scripting.engine.content.impl.ResourceScriptContent;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;


/**
 *
 */
public class GroovyScriptResourceExample
{

	private ScriptingLanguagesService scriptingLanguagesService;

	public void execute()
	{

		final String engineName = "groovy";

		final Resource scriptResource = new FileSystemResource("C:/Create-Car.groovy");

		final ScriptContent scriptContent = new ResourceScriptContent(scriptResource);

		final ScriptExecutable executable = scriptingLanguagesService.getExecutableByContent(scriptContent);

		final ScriptExecutionResult executionResult = executable.execute();

		System.out.println(executionResult.getScriptResult());



	}

}
