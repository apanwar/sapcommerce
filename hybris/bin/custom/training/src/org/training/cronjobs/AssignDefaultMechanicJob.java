/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.cronjobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.training.dao.CarDao;
import org.training.model.AssignDefaultMechanicCronJobModel;
import org.training.model.CarModel;


/**
 *
 */
public class AssignDefaultMechanicJob extends AbstractJobPerformable<AssignDefaultMechanicCronJobModel>
{

	private CarDao carDao;

	@Override
	public PerformResult perform(final AssignDefaultMechanicCronJobModel cronJob)
	{
		final List<CarModel> carsWithoutDefaultMechanic = carDao.getCarsWithoutDefaultMechanic();

		if (CollectionUtils.isNotEmpty(carsWithoutDefaultMechanic))
		{
			carsWithoutDefaultMechanic.forEach(car -> car.setDefaultMechanic(cronJob.getDefaultMechanic()));

			modelService.saveAll(carsWithoutDefaultMechanic);
		}

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);

	}

	/**
	 * @param carDao
	 *           the carDao to set
	 */
	public void setCarDao(final CarDao carDao)
	{
		this.carDao = carDao;
	}

}
