/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.interceptors;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.product.UnitService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.training.model.CarModel;

/**
 *
 */
@IntegrationTest
public class CarValidateInterceptorIntegrationTest extends ServicelayerTransactionalTest
{

	@Resource
	private ModelService modelService;

	@Resource
	private ProductService productService;

	@Resource
	private UnitService unitService;

	@Before
	public void setup() throws Exception
	{
		createCoreData();
	}

	@Test(expected = ModelSavingException.class)
	public void testOnValidate_ValidationFailed() throws ImpExException
	{
		importCsv("/training/test/CarValidateInterceptorIntegrationTest.impex", "utf-8");
		final CarModel car = modelService.create(CarModel.class);
		car.setCode("carvalidate0001");
		car.setName("Test Car Name");
		car.setDescription("Test Car Description");
		modelService.save(car);
	}

	@Test
	public void testOnValidate() throws ImpExException
	{
		importCsv("/training/test/CarValidateInterceptorIntegrationTest.impex", "utf-8");
		final CarModel car = modelService.create(CarModel.class);
		car.setCode("carvalidate0001");
		car.setName("Test Car Name");
		car.setDescription("Test Car Description");
		car.setUnit(unitService.getUnitForCode("pieces"));
		modelService.save(car);

		Assert.assertNotNull(productService.getProductForCode("carvalidate0001"));
	}

}
