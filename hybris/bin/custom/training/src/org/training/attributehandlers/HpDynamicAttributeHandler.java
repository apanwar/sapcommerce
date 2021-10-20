/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.attributehandlers;

import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

import org.training.model.CarModel;


/**
 *
 */
public class HpDynamicAttributeHandler implements DynamicAttributeHandler<Integer, CarModel>
{

	@Override
	public Integer get(final CarModel car)
	{
		return car.getKw() * 1000 / 746;
	}

	@Override
	public void set(final CarModel car, final Integer hpValue)
	{
		throw new IllegalAccessError("hp is not writable.");

	}

}
