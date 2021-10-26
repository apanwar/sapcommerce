/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.cronjobs;

import static org.junit.Assert.assertNotNull;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.product.UnitService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.cronjob.CronJobService;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.training.dao.CarDao;
import org.training.model.CarModel;


/**
 *
 */
@IntegrationTest
public class AssignDefaultMechanicCronJobTest extends ServicelayerTransactionalTest
{

	@Resource
	private ModelService modelService;

	@Resource
	private UnitService unitService;

	@Resource
	private CarDao carDao;

	@Resource
	private CatalogVersionService catalogVersionService;

	@Resource
	private CronJobService cronJobService;

	@Before
	public void setup() throws Exception
	{
		createCoreData();
		importCsv("/training/test/AssignDefaultMechanicCronJobTest.impex", "utf-8");
	}

	@Test
	public void testAssignDefaultMechanic() throws InterruptedException, ImpExException
	{

		final CarModel car = modelService.create(CarModel.class);

		car.setCode("12345");
		car.setName("Car Name");
		car.setDescription("Car Description");
		car.setUnit(unitService.getUnitForCode("pieces"));
		car.setKw(1000);
		car.setModel(2013);


		modelService.save(car);


		cronJobService.performCronJob(cronJobService.getCronJob("assignDefaultMechanicCronJob"), true);

		final CarModel updatedCar = carDao.getCarForCode(car.getCode(),
				catalogVersionService.getCatalogVersion("trainingCatalog", "Staged"));

		assertNotNull(updatedCar);
		assertNotNull(updatedCar.getDefaultMechanic());
	}


}
