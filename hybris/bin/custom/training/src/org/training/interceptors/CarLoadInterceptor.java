/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.interceptors;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.LoadInterceptor;

import org.training.model.CarModel;

/**
 *
 */
public class CarLoadInterceptor implements LoadInterceptor<CarModel>
{

	@Override
	public void onLoad(final CarModel car, final InterceptorContext context) throws InterceptorException
	{

		if (null == car.getName())
		{
			car.setName(car.getCode());
		}

		if (null == car.getDescription())
		{
			car.setDescription(car.getName());
		}

	}

}
