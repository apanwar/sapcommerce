/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.interceptors;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.RemoveInterceptor;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.training.model.CarModel;

/**
 *
 */
@UnitTest
public class CarRemoveInterceptorTest
{

	private RemoveInterceptor<CarModel> carRemoveInterceptor;

	@Mock
	private CarModel car;

	@Mock
	private InterceptorContext context;

	@Before
	public void setup()
	{
		carRemoveInterceptor = new CarRemoveInterceptor();
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = InterceptorException.class)
	public void testOnRemove_ActiveCar() throws InterceptorException
	{
		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		Mockito.when(car.getOnlineDate()).thenReturn(calendar.getTime());
		calendar.add(Calendar.DAY_OF_MONTH, 60);
		Mockito.when(car.getOfflineDate()).thenReturn(calendar.getTime());
		carRemoveInterceptor.onRemove(car, context);
	}

	@Test(expected = InterceptorException.class)
	public void testOnRemove_DatesNull() throws InterceptorException
	{
		carRemoveInterceptor.onRemove(car, context);

	}

}
