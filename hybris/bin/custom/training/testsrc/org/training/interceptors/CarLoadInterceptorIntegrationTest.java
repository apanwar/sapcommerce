/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.interceptors;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.training.model.CarModel;

/**
 *
 */
@IntegrationTest
public class CarLoadInterceptorIntegrationTest extends ServicelayerTransactionalTest
{

	@Resource
	private ProductService productService;

	@Before
	public void setup() throws Exception
	{
		createCoreData();
	}

	@Test
	public void testOnLoadNameNotAvailable() throws ImpExException
	{
		importCsv("/training/test/CarLoadInterceptorIntegrationTest.impex", "utf-8");
		final CarModel car = (CarModel) productService.getProductForCode("car0001");

		Assert.assertEquals(car.getCode(), car.getName());
	}

	@Test
	public void testOnLoadDescriptionNotAvailable() throws ImpExException
	{
		importCsv("/training/test/CarLoadInterceptorIntegrationTest.impex", "utf-8");
		final CarModel car = (CarModel) productService.getProductForCode("car0002");

		Assert.assertEquals(car.getName(), car.getDescription());
	}

	@Test
	public void testOnLoadNameDescriptionNotAvailable() throws ImpExException
	{
		importCsv("/training/test/CarLoadInterceptorIntegrationTest.impex", "utf-8");
		final CarModel car = (CarModel) productService.getProductForCode("car0001");

		Assert.assertEquals(car.getCode(), car.getName());
		Assert.assertEquals(car.getCode(), car.getDescription());
	}

}
