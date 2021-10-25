/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.facade.converters;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.training.dao.CarDao;
import org.training.facade.CarData;
import org.training.model.CarModel;

/**
 *
 */
@IntegrationTest
public class CarConverterIntegrationTest extends ServicelayerTransactionalTest
{

	@Resource
	private Converter<CarModel, CarData> carConverter;

	@Resource
	private CarDao carDao;

	@Resource
	private CatalogVersionService catalogVersionService;

	@Resource
	private CommonI18NService commonI18NService;

	@Before
	public void setup() throws Exception
	{
		createCoreData();
		commonI18NService.setCurrentLanguage(commonI18NService.getLanguage("en"));
	}

	@Test
	public void testConvert() throws ImpExException
	{
		importCsv("/trainingfacade/test/CarConverterIntegrationTest.impex", "utf-8");

		final CarModel carModel = carDao.getCarForCode("car0001",
				catalogVersionService.getCatalogVersion("trainingCatalog", "Staged"));

		final CarData carData = carConverter.convert(carModel);

		Assert.assertNotNull(carData);
		Assert.assertEquals(carModel.getName(), carData.getName());

	}

}
