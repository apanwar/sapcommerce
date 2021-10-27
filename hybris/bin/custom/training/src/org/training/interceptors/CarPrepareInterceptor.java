/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.interceptors;

import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;

import org.apache.commons.lang3.StringUtils;
import org.training.model.CarModel;

/**
 *
 */
public class CarPrepareInterceptor implements PrepareInterceptor<CarModel>
{

	@Override
	public void onPrepare(final CarModel car, final InterceptorContext context) throws InterceptorException
	{

		if ((car.getApprovalStatus() != ArticleApprovalStatus.UNAPPROVED) && (null == car.getName() || null == car.getDescription()
				|| (StringUtils.equals(car.getCode(), car.getName()) && StringUtils.equals(car.getCode(), car.getDescription()))))
		{
			car.setApprovalStatus(ArticleApprovalStatus.UNAPPROVED);
		}
		else
		{
			car.setApprovalStatus(ArticleApprovalStatus.CHECK);
		}

	}

}
