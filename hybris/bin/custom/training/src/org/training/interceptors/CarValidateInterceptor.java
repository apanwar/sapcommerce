/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.interceptors;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import org.training.model.CarModel;

/**
 *
 */
public class CarValidateInterceptor implements ValidateInterceptor<CarModel>
{

	@Override
	public void onValidate(final CarModel car, final InterceptorContext context) throws InterceptorException
	{

		if (null == car.getUnit())
		{
			throw new InterceptorException("unit cannot be null.");
		}

	}

}
