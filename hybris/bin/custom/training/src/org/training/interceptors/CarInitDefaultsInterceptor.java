/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.interceptors;

import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.interceptor.InitDefaultsInterceptor;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;

import org.training.enums.FuelType;
import org.training.model.CarModel;

/**
 *
 */
public class CarInitDefaultsInterceptor implements InitDefaultsInterceptor<CarModel>
{

	private CatalogVersionService catalogVersionService;

	private CatalogService catalogService;

	@Override
	public void onInitDefaults(final CarModel car, final InterceptorContext arg1) throws InterceptorException
	{

		car.setFuelType(FuelType.GASOLINE);

		car.setKw(0);

		CatalogVersionModel catalogVersion = null;
		try
		{
			catalogVersion = catalogVersionService.getCatalogVersion("trainingCatalog", "Staged");
		}
		catch (final UnknownIdentifierException unknownIdentifierException)
		{
			catalogVersion = catalogVersionService.getCatalogVersion(catalogService.getDefaultCatalog().getId(), "Staged");
		}
		finally
		{
			car.setCatalogVersion(catalogVersion);
		}
	}

	/**
	 * @param catalogVersionService
	 *           the catalogVersionService to set
	 */
	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}

	/**
	 * @param catalogService
	 *           the catalogService to set
	 */
	public void setCatalogService(final CatalogService catalogService)
	{
		this.catalogService = catalogService;
	}

}
