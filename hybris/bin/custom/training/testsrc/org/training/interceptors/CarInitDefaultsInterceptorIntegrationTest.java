/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.interceptors;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.training.enums.FuelType;
import org.training.model.CarModel;


/**
 *
 */
@IntegrationTest
public class CarInitDefaultsInterceptorIntegrationTest extends ServicelayerTransactionalTest
{

	@Resource
	private ProductService productService;

	@Resource
	private CatalogVersionService catalogVersionService;

	@Before
	public void setup() throws Exception
	{
		createCoreData();
	}

	@Test
	public void testOnInitDefaults() throws ImpExException
	{
		importCsv("/training/test/CarInitDefaultsInterceptorIntegrationTest.impex", "utf-8");

		final CarModel car = (CarModel) productService.getProductForCode("car0001");

		Assert.assertEquals(FuelType.GASOLINE, car.getFuelType());
		Assert.assertEquals(Integer.valueOf(0), car.getKw());
		Assert.assertEquals(catalogVersionService.getCatalogVersion("trainingCatalog", "Staged"), car.getCatalogVersion());
	}

}
