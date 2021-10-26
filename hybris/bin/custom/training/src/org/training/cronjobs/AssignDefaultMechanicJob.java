/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.cronjobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.util.Config;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.training.dao.CarDao;
import org.training.model.AssignDefaultMechanicCronJobModel;
import org.training.model.CarModel;


/**
 *
 */
public class AssignDefaultMechanicJob extends AbstractJobPerformable<AssignDefaultMechanicCronJobModel>
{
	private static final Logger LOGGER = Logger.getLogger(AssignDefaultMechanicJob.class);

	private CarDao carDao;

	@Override
	public PerformResult perform(final AssignDefaultMechanicCronJobModel cronJob)
	{

		LOGGER.debug("Starting execution of AssignDefaultMechanicJob");

		final List<CarModel> carsWithoutDefaultMechanic = carDao.getCarsWithoutDefaultMechanic();

		if (CollectionUtils.isNotEmpty(carsWithoutDefaultMechanic))
		{
			LOGGER.debug("Number of cars without default mechanic assigned: " + carsWithoutDefaultMechanic.size());
			carsWithoutDefaultMechanic.forEach(car -> car.setDefaultMechanic(cronJob.getDefaultMechanic()));
			try
			{
				Config.setParameter("db.log.active", "true");
				modelService.saveAll(carsWithoutDefaultMechanic);
				Config.setParameter("db.log.active", "false");
			}
			catch (final ModelSavingException exception)
			{
				LOGGER.error("Error occured while setting the default mechanic,", exception);
			}
		}
		else
		{
			LOGGER.debug("There are no cars without default mechanic assigned.");

		}
		LOGGER.info("AssignDefaultMechanicJob executed succefully");
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
