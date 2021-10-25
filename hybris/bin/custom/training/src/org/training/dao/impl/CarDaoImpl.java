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
import org.training.enums.FuelType;
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

	@Override
	public CarModel getCarForCode(final String code, final CatalogVersionModel catalogVersion)
	{
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery(
				"SELECT {PK} FROM {Car} WHERE {code}=?carCode AND {catalogVersion}= ?catalogVersion");
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

	@Override
	public List<CarModel> getCarsForChasisNumber(final String chasisNumber)
	{
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery("SELECT {PK} FROM {Car} WHERE {chasisNumber}=?chasisNumber");
		fsq.addQueryParameter("chasisNumber", chasisNumber);

		final SearchResult<CarModel> searchResults = flexibleSearchService.search(fsq);

		return searchResults.getResult();
	}

	@Override
	public CarModel getCarForChasisNumber(final String chasisNumber, final CatalogVersionModel catalogVersion)
	{
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery(
				"SELECT {PK} FROM {Car} WHERE {chasisNumber}=?chasisNumber AND {catalogVerison}= ?catalogVersion");
		fsq.addQueryParameter("chasisNumber", chasisNumber);
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

	@Override
	public List<CarModel> getCarsForEngineNumber(final String engineNumber)
	{
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery("SELECT {PK} FROM {Car} WHERE {engineNumber}=?engineNumber");
		fsq.addQueryParameter("engineNumber", engineNumber);

		final SearchResult<CarModel> searchResults = flexibleSearchService.search(fsq);

		return searchResults.getResult();
	}

	@Override
	public CarModel getCarForEngineNumber(final String engineNumber, final CatalogVersionModel catalogVersion)
	{
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery(
				"SELECT {PK} FROM {Car} WHERE {engineNumber}=?engineNumber AND {catalogVerison}= ?catalogVersion");
		fsq.addQueryParameter("engineNumber", engineNumber);
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

	@Override
	public List<CarModel> getCarsForFuelType(final FuelType fuelType)
	{
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery("SELECT {PK} FROM {Car} WHERE {fuelType}=?fuelType");
		fsq.addQueryParameter("fuelType", fuelType);

		final SearchResult<CarModel> searchResults = flexibleSearchService.search(fsq);

		return searchResults.getResult();
	}

	@Override
	public List<CarModel> getCarsForFuelType(final FuelType fuelType, final CatalogVersionModel catalogVersion)
	{

		final FlexibleSearchQuery fsq = new FlexibleSearchQuery(
				"SELECT {PK} FROM {Car} WHERE {fuelType}=?fuelType AND {catalogVerison}= ?catalogVersion");
		fsq.addQueryParameter("fuelType", fuelType);
		fsq.addQueryParameter("catalogVersion", catalogVersion);
		final SearchResult<CarModel> searchResults = flexibleSearchService.search(fsq);

		return searchResults.getResult();
	}

	@Override
	public List<CarModel> getCarsForModelAndFuelType(final String model, final FuelType fuelType)
	{
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery(
				"SELECT {PK} FROM {Car} WHERE {model}=?model AND {fuelType}=?fuelType");
		fsq.addQueryParameter("model", model);
		fsq.addQueryParameter("fuelType", fuelType);

		final SearchResult<CarModel> searchResults = flexibleSearchService.search(fsq);

		return searchResults.getResult();
	}

	@Override
	public List<CarModel> getCarsForModelAndFuelType(final String model, final FuelType fuelType,
			final CatalogVersionModel catalogVersion)
	{
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery(
				"SELECT {PK} FROM {Car} WHERE {model}=?model AND {fuelType}=?fuelType AND {catalogVerison}= ?catalogVersion");
		fsq.addQueryParameter("model", model);
		fsq.addQueryParameter("fuelType", fuelType);
		fsq.addQueryParameter("catalogVersion", catalogVersion);

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


}
