/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.interceptors;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.RemoveInterceptor;

import java.util.Calendar;

import org.training.model.CarModel;

/**
 *
 */
public class CarRemoveInterceptor implements RemoveInterceptor<CarModel>
{

	@Override
	public void onRemove(final CarModel car, final InterceptorContext context) throws InterceptorException
	{
		final Calendar calendar = Calendar.getInstance();

		if ((car.getOnlineDate() == car.getOfflineDate())
				|| (null != car.getOfflineDate() && calendar.getTime().before(car.getOfflineDate())))
		{
			throw new InterceptorException("product is not yet expired.");
		}

	}

}
