/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.interceptors;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.LoadInterceptor;

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
public class CarLoadInterceptorTest
{

	private LoadInterceptor<CarModel> carLoadInterceptor;

	@Mock
	private CarModel car;

	@Mock
	private InterceptorContext context;

	@Before
	public void setup()
	{
		carLoadInterceptor = new CarLoadInterceptor();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testOnLoad_NameNotAvailable() throws InterceptorException
	{
		Mockito.when(car.getCode()).thenReturn("car0001");
		carLoadInterceptor.onLoad(car, context);
		Mockito.verify(car, atLeastOnce()).setName("car0001");
	}

	@Test
	public void testOnLoad_NameAvailable() throws InterceptorException
	{
		Mockito.when(car.getCode()).thenReturn("car0001");
		Mockito.when(car.getName()).thenReturn("Honda Amaze");
		carLoadInterceptor.onLoad(car, context);
		Mockito.verify(car, never()).setName("car0001");
	}

	@Test
	public void testOnLoad_DescriptionNotAvailable() throws InterceptorException
	{
		Mockito.when(car.getName()).thenReturn("Honda Amaze");
		carLoadInterceptor.onLoad(car, context);
		Mockito.verify(car, atLeastOnce()).setDescription("Honda Amaze");
	}

	@Test
	public void testOnLoad_DescriptionAvailable() throws InterceptorException
	{
		Mockito.when(car.getName()).thenReturn("Honda Amaze");
		Mockito.when(car.getDescription()).thenReturn("The entry level sedan from Honda.");
		carLoadInterceptor.onLoad(car, context);
		Mockito.verify(car, never()).setDescription("Honda Amaze");
	}

}
