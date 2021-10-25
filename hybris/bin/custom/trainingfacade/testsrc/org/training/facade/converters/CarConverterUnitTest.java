/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.facade.converters;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.training.facade.CarData;
import org.training.model.CarModel;

/**
 *
 */
@UnitTest
public class CarConverterUnitTest
{

	private Converter<CarModel, CarData> carConverter;

	@Mock
	private CarModel carModel;

	@Before
	public void setup()
	{
		carConverter = new CarConverter();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testConvert()
	{

		Mockito.when(carModel.getCode()).thenReturn("mockCarCode");
		Mockito.when(carModel.getName()).thenReturn("Mock Car");
		Mockito.when(carModel.getChasisNumber()).thenReturn("mockchesisnumber");
		Mockito.when(carModel.getEngineNumber()).thenReturn("mockenginenumber");

		final CarData carData = carConverter.convert(carModel);

		Assert.assertNotNull(carData);
		Assert.assertEquals(carModel.getCode(), carData.getId());
		Assert.assertEquals(carModel.getName(), carData.getName());
		Assert.assertEquals(carModel.getChasisNumber(), carData.getChasisNumber());
		Assert.assertEquals(carModel.getEngineNumber(), carData.getEngineNumber());

	}

	@Test
	public void testConvertWithCarData()
	{

		Mockito.when(carModel.getCode()).thenReturn("mockCarCode");
		Mockito.when(carModel.getName()).thenReturn("Mock Car");
		Mockito.when(carModel.getChasisNumber()).thenReturn("mockchesisnumber");
		Mockito.when(carModel.getEngineNumber()).thenReturn("mockenginenumber");

		final CarData carData = new CarData();
		carConverter.convert(carModel, carData);

		Assert.assertNotNull(carData);
		Assert.assertEquals(carModel.getCode(), carData.getId());
		Assert.assertEquals(carModel.getName(), carData.getName());
		Assert.assertEquals(carModel.getChasisNumber(), carData.getChasisNumber());
		Assert.assertEquals(carModel.getEngineNumber(), carData.getEngineNumber());

	}

}
