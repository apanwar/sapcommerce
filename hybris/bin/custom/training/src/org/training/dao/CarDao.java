/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.dao;

import de.hybris.platform.catalog.model.CatalogVersionModel;

import java.util.List;

import org.training.enums.FuelType;
import org.training.model.CarModel;


/**
 *
 */
public interface CarDao
{

	List<CarModel> getCarsForCode(String code);

	CarModel getCarForCode(String code, CatalogVersionModel catalogVersion);

	List<CarModel> getCarsForChasisNumber(String chasisNumber);

	CarModel getCarForChasisNumber(String chasisNumber, CatalogVersionModel catalogVersion);

	List<CarModel> getCarsForEngineNumber(String engineNumber);

	CarModel getCarForEngineNumber(String engineNumber, CatalogVersionModel catalogVersion);

	List<CarModel> getCarsForFuelType(FuelType fuelType);

	List<CarModel> getCarsForFuelType(FuelType fuelType, CatalogVersionModel catalogVersion);

	List<CarModel> getCarsForModelAndFuelType(String model, FuelType fuelType);

	List<CarModel> getCarsForModelAndFuelType(String model, FuelType fuelType, CatalogVersionModel catalogVersion);

}
