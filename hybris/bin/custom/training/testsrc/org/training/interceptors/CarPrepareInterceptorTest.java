/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.interceptors;

import static org.mockito.Mockito.atLeastOnce;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;

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
public class CarPrepareInterceptorTest
{

	private PrepareInterceptor<CarModel> carPrepareInterceptor;

	@Mock
	private CarModel car;

	@Mock
	private InterceptorContext context;

	@Before
	public void setup()
	{
		carPrepareInterceptor = new CarPrepareInterceptor();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testOnPrepare_Check() throws InterceptorException
	{
		Mockito.when(car.getCode()).thenReturn("car0001");
		Mockito.when(car.getName()).thenReturn("car0001");
		Mockito.when(car.getDescription()).thenReturn("car0001");
		carPrepareInterceptor.onPrepare(car, context);

		Mockito.verify(car, atLeastOnce()).setApprovalStatus(ArticleApprovalStatus.CHECK);
	}

	@Test
	public void testOnPrepare_Approved() throws InterceptorException
	{
		Mockito.when(car.getCode()).thenReturn("car0001");
		Mockito.when(car.getName()).thenReturn("Honda Amaze");
		Mockito.when(car.getDescription()).thenReturn("The entry level sedan from Honda");
		carPrepareInterceptor.onPrepare(car, context);

		Mockito.verify(car, atLeastOnce()).setApprovalStatus(ArticleApprovalStatus.APPROVED);
	}

}
