/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.dao;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.training.model.CarModel;

/**
 *
 */
@IntegrationTest
public class CarDaoIntegrationTest extends ServicelayerTransactionalTest
{

	@Resource
	private CarDao carDao;

	@Resource
	private CommonI18NService commonI18NService;

	@Before
	public void setup() throws Exception
	{
		createCoreData();
		commonI18NService.setCurrentLanguage(commonI18NService.getLanguage("en"));
	}

	@Test
	public void testGetCarsForCode() throws ImpExException
	{
		importCsv("/training/test/CarDaoIntegrationTest.impex", "utf-8");

		final List<CarModel> cars = carDao.getCarsForCode("car0001");

		Assert.assertNotNull(cars);
		Assert.assertFalse(cars.isEmpty());
		Assert.assertTrue(cars.size() == 1);
		Assert.assertEquals("Audi A4", cars.get(0).getName());
	}

	@Test
	public void testGetCarsForCode_NotFound() throws ImpExException
	{
		importCsv("/training/test/CarDaoIntegrationTest.impex", "utf-8");

		final List<CarModel> cars = carDao.getCarsForCode("car0002");

		Assert.assertNotNull(cars);
		Assert.assertTrue(cars.isEmpty());
	}

}
