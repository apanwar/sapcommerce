/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.facade.converters;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.converters.Populator;

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
public class CarPopulatorTest
{

	private Populator<CarModel, CarData> carPopulator;

	@Mock
	private CarModel carModel;

	@Before
	public void setup()
	{
		carPopulator = new CarPopulator();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testPopulate()
	{
		Mockito.when(carModel.getChasisNumber()).thenReturn("mockchasisnumbere");
		Mockito.when(carModel.getEngineNumber()).thenReturn("mockenginenumber");
		final CarData carData = new CarData();
		carPopulator.populate(carModel, carData);

		Assert.assertEquals(carModel.getChasisNumber(), carData.getChasisNumber());
		Assert.assertEquals(carModel.getEngineNumber(), carData.getEngineNumber());
	}

}
