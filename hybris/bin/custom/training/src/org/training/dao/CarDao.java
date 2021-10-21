/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.dao;

import de.hybris.platform.catalog.model.CatalogVersionModel;

import java.util.List;

import org.training.model.CarModel;


/**
 *
 */
public interface CarDao
{

	List<CarModel> getCarsForCode(String code);

	CarModel getCarForCode(String code, CatalogVersionModel catalogVersionModel);

}
