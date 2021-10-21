/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.interceptors;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.ModelRemovalException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.training.model.CarModel;

/**
 *
 */
@IntegrationTest
public class CarRemoveInterceptorIntegrationTest extends ServicelayerTransactionalTest
{
	@Resource
	private ModelService modelService;

	@Resource
	private ProductService productService;

	@Before
	public void setup() throws Exception
	{
		createCoreData();
	}

	@Test(expected = UnknownIdentifierException.class)
	public void testOnRemove() throws ImpExException
	{
		importCsv("/training/test/CarRemoveInterceptorIntegrationTest.impex", "utf-8");

		CarModel car = (CarModel) productService.getProductForCode("car0001");

		modelService.remove(car);

		car = (CarModel) productService.getProductForCode("car0001");
	}

	@Test(expected = ModelRemovalException.class)
	public void testOnRemove_ActiveCar() throws ImpExException
	{
		importCsv("/training/test/CarRemoveInterceptorIntegrationTest.impex", "utf-8");

		final CarModel car = (CarModel) productService.getProductForCode("car0002");

		modelService.remove(car);
	}

	@Test(expected = ModelRemovalException.class)
	public void testOnRemove_NoDates() throws ImpExException
	{
		importCsv("/training/test/CarRemoveInterceptorIntegrationTest.impex", "utf-8");

		final CarModel car = (CarModel) productService.getProductForCode("car0003");

		modelService.remove(car);
	}

}
