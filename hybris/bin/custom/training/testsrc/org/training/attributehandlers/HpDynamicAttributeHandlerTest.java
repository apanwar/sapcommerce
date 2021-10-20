/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.attributehandlers;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.training.model.CarModel;



@UnitTest
public class HpDynamicAttributeHandlerTest
{

	private DynamicAttributeHandler<Integer, CarModel> hpDynamicAttributeHandler;

	@Mock
	private CarModel carModel;

	@Before
	public void setUp()
	{
		hpDynamicAttributeHandler = new HpDynamicAttributeHandler();
		MockitoAnnotations.initMocks(this);

		Mockito.when(carModel.getKw()).thenReturn(2000);
	}

	@Test
	public void testGet() {
		final Integer expectedHpValue = carModel.getKw() * 1000 / 746;
		Assert.assertEquals(expectedHpValue, hpDynamicAttributeHandler.get(carModel));
	}

	@Test(expected = IllegalAccessError.class)
	public void testSet()
	{
		hpDynamicAttributeHandler.set(carModel, 1000);
	}

}
