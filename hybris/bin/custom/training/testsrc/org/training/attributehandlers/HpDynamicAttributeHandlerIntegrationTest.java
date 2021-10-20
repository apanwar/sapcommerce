/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.attributehandlers;

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
public class HpDynamicAttributeHandlerIntegrationTest extends ServicelayerTransactionalTest
{

	@Resource
	private ProductService productService;

	@Before
	public void setup() throws Exception
	{
		createCoreData();
	}

	@Test
	public void testGetHp() throws ImpExException
	{
		importCsv("/training/test/testhpdynamicattributehandler.impex", "utf-8");

		final CarModel car = (CarModel) productService.getProductForCode("c0001");
		final Integer expectedHpValue = car.getKw() * 1000 / 746;

		Assert.assertEquals(expectedHpValue, car.getHp());

	}

}
