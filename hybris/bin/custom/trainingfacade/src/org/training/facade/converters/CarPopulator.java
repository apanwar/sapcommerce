/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.facade.converters;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.training.facade.CarData;
import org.training.model.CarModel;

/**
 *
 */
public class CarPopulator implements Populator<CarModel, CarData>
{

	@Override
	public void populate(final CarModel source, final CarData target) throws ConversionException
	{
		target.setChasisNumber(source.getChasisNumber());
		target.setEngineNumber(source.getEngineNumber());
	}

}
