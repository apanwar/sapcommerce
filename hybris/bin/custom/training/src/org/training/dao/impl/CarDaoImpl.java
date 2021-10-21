/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.dao.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.List;

import org.training.dao.CarDao;
import org.training.model.CarModel;


/**
 *
 */
public class CarDaoImpl implements CarDao
{

	private FlexibleSearchService flexibleSearchService;

	@Override
	public List<CarModel> getCarsForCode(final String code)
	{
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery("SELECT {PK} FROM {Car} WHERE {code}=?carCode");
		fsq.addQueryParameter("carCode", code);

		final SearchResult<CarModel> searchResults = flexibleSearchService.search(fsq);

		return searchResults.getResult();
	}

	/**
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

	@Override
	public CarModel getCarForCode(final String code, final CatalogVersionModel catalogVersion)
	{
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery(
				"SELECT {PK} FROM {Car} WHERE {code}=?carCode AND {catalogVerison}= ?catalogVersion");
		fsq.addQueryParameter("carCode", code);
		fsq.addQueryParameter("catalogVersion", catalogVersion);

		final SearchResult<CarModel> searchResults = flexibleSearchService.search(fsq);

		final List<CarModel> cars = searchResults.getResult();
		if (cars.isEmpty())
		{
			return null;
		}
		else
		{
			return cars.get(0);
		}
	}

}
