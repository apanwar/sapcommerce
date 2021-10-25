/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.facade.validation;

import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.validation.exceptions.HybrisConstraintViolation;
import de.hybris.platform.validation.services.ValidationService;

import java.util.Collections;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.training.facade.CarData;

/**
 *
 */
public class CarDataExplicitValidationIntegrationTest extends ServicelayerTransactionalTest
{

	@Resource
	private ValidationService validationService;

	@Before
	public void setup() throws Exception
	{
		createCoreData();
	}

	@Test
	public void testValidate() throws ImpExException
	{
		importCsv("/trainingfacade/test/CarDataExplicitValidationIntegrationTest.impex", "utf-8");

		validationService.reloadValidationEngine();

		final CarData car = new CarData();

		car.setId("12345");

		final Set<HybrisConstraintViolation> violations = validationService.validate(car, Collections.emptyList());

		Assert.assertNotNull(violations);
		Assert.assertFalse(violations.isEmpty());

	}

}
