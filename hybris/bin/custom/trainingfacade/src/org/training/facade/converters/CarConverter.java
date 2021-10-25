/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.facade.converters;

import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import org.training.facade.CarData;
import org.training.model.CarModel;

/**
 *
 */
public class CarConverter implements Converter<CarModel, CarData>
{

	@Override
	public CarData convert(final CarModel model) throws ConversionException
	{
		final CarData carData = new CarData();
		carData.setId(model.getCode());
		carData.setName(model.getName());
		carData.setChasisNumber(model.getChasisNumber());
		carData.setEngineNumber(model.getEngineNumber());
		return carData;
	}

	@Override
	public CarData convert(final CarModel model, final CarData carData) throws ConversionException
	{
		carData.setId(model.getCode());
		carData.setName(model.getName());
		carData.setChasisNumber(model.getChasisNumber());
		carData.setEngineNumber(model.getEngineNumber());
		return carData;
	}
}
