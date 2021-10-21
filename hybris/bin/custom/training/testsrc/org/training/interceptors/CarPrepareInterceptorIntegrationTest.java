/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.interceptors;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.training.model.CarModel;

/**
 *
 */
@IntegrationTest
public class CarPrepareInterceptorIntegrationTest extends ServicelayerTransactionalTest
{

	@Resource
	private ProductService productService;

	@Resource
	private CommonI18NService commonI18NService;

	@Before
	public void setup() throws Exception
	{
		createCoreData();
		commonI18NService.setCurrentLanguage(commonI18NService.getLanguage("en"));
	}

	@Test
	public void testApprovalStatus_Check() throws ImpExException
	{
		importCsv("/training/test/CarPrepareInterceptorIntegrationTest.impex", "utf-8");


		final CarModel car = (CarModel) productService.getProductForCode("car0004");

		Assert.assertEquals(ArticleApprovalStatus.CHECK, car.getApprovalStatus());
	}

	@Test
	public void testApprovalStatus_CHECK_Car002() throws ImpExException
	{
		importCsv("/training/test/CarPrepareInterceptorIntegrationTest.impex", "utf-8");


		final CarModel car = (CarModel) productService.getProductForCode("car0002");

		Assert.assertEquals(ArticleApprovalStatus.CHECK, car.getApprovalStatus());
	}

	@Test
	public void testApprovalStatus_APPROVED_Car003() throws ImpExException
	{
		importCsv("/training/test/CarPrepareInterceptorIntegrationTest.impex", "utf-8");


		final CarModel car = (CarModel) productService.getProductForCode("car0003");

		Assert.assertEquals(ArticleApprovalStatus.APPROVED, car.getApprovalStatus());
	}

}
