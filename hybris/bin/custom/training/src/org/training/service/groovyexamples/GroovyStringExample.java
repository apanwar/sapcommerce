/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.service.groovyexamples;

import de.hybris.platform.scripting.engine.ScriptExecutable;
import de.hybris.platform.scripting.engine.ScriptExecutionResult;
import de.hybris.platform.scripting.engine.ScriptingLanguagesService;
import de.hybris.platform.scripting.engine.content.ScriptContent;
import de.hybris.platform.scripting.engine.content.impl.SimpleScriptContent;


/**
 *
 */
public class GroovyStringExample
{

	private ScriptingLanguagesService scriptingLanguagesService;

	public void execute()
	{


		final String content = "import org.training.model.CarModel;\r\n" + "import org.training.enums.FuelType;\r\n" + "\r\n"
				+ "car = modelService.create(CarModel.class);\r\n" + "\r\n" + "car.setCode(\"00001\");\r\n"
				+ "car.setName(\"Honda Amaze\");\r\n" + "car.setChasisNumber(\"GJHGJK36546156\");\r\n"
				+ "car.setEngineNumber(\"NJK654646\");\r\n" + "car.setKw(2000);\r\n" + "car.setModel(2021);\r\n"
				+ "car.setFuelType(FuelType.GASOLINE);\r\n" + "\r\n" + "modelService.save(car);\r\n" + "";

		final String engineName = "groovy";

		final ScriptContent scriptContent = new SimpleScriptContent(engineName, content);

		final ScriptExecutable executable = scriptingLanguagesService.getExecutableByContent(scriptContent);

		final ScriptExecutionResult executionResult = executable.execute();

		System.out.println(executionResult.getScriptResult());


	}

}
