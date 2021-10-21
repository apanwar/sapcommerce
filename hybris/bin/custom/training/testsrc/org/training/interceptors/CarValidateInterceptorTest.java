/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.interceptors;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

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
public class CarValidateInterceptorTest
{

	private ValidateInterceptor<CarModel> carValidateInterceptor;

	@Mock
	private CarModel car;

	@Mock
	private InterceptorContext context;

	@Before
	public void setup()
	{
		carValidateInterceptor = new CarValidateInterceptor();
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = InterceptorException.class)
	public void testOnValidate() throws InterceptorException
	{
		Mockito.when(car.getUnit()).thenReturn(null);
		carValidateInterceptor.onValidate(car, context);
	}

}
