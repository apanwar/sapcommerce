/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.cronjobs;

import de.hybris.platform.core.model.user.EmployeeModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.training.dao.CarDao;
import org.training.model.CarModel;


/**
 *
 */
public class AssignDefaultMechanicServiceLayerJob extends AbstractJobPerformable<CronJobModel>
{

	private CarDao carDao;

	private String defaultMechanicUid;

	private UserService userService;

	@Override
	public PerformResult perform(final CronJobModel cronJob)
	{
		final List<CarModel> carsWithoutDefaultMechanic = carDao.getCarsWithoutDefaultMechanic();

		if (CollectionUtils.isNotEmpty(carsWithoutDefaultMechanic))
		{
			final EmployeeModel defaultMechanic = (EmployeeModel) userService.getUserForUID(defaultMechanicUid);

			carsWithoutDefaultMechanic.forEach(car -> car.setDefaultMechanic(defaultMechanic));

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

	/**
	 * @param defaultMechanicUid
	 *           the defaultMechanicUid to set
	 */
	public void setDefaultMechanicUid(final String defaultMechanicUid)
	{
		this.defaultMechanicUid = defaultMechanicUid;
	}

	/**
	 * @param userService
	 *           the userService to set
	 */
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

}
