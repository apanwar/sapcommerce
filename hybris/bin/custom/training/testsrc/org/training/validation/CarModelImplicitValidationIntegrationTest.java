/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.validation;

import static org.junit.Assert.assertNotNull;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.product.UnitService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.validation.services.ValidationService;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.training.dao.CarDao;
import org.training.model.CarModel;

/**
 *
 */
@IntegrationTest
public class CarModelImplicitValidationIntegrationTest extends ServicelayerTransactionalTest
{

	@Resource
	private ModelService modelService;

	@Resource
	private UnitService unitService;

	@Resource
	private ValidationService validationService;

	@Resource
	private CarDao carDao;

	@Resource
	private CatalogVersionService catalogVersionService;

	@Before
	public void setup() throws Exception
	{
		createCoreData();

		importCsv("/training/test/constraints.impex", "utf-8");
		validationService.reloadValidationEngine();
	}

	@Test(expected = ModelSavingException.class)
	public void testValidate_ModelNull() throws ImpExException
	{

		final CarModel car = modelService.create(CarModel.class);

		car.setCode("12345");
		car.setName("Car Name");
		car.setUnit(unitService.getUnitForCode("pieces"));
		car.setKw(1000);

		modelService.save(car);

	}

	@Test(expected = ModelSavingException.class)
	public void testValidate_ModelLessOrEqual2012() throws ImpExException
	{

		final CarModel car = modelService.create(CarModel.class);

		car.setCode("12345");
		car.setName("Car Name");
		car.setUnit(unitService.getUnitForCode("pieces"));
		car.setKw(1000);
		car.setModel(2012);

		modelService.save(car);

	}

	@Test
	public void testValidate_ModelGreaterThan2012() throws ImpExException
	{

		final CarModel car = modelService.create(CarModel.class);

		car.setCode("12345");
		car.setName("Car Name");
		car.setUnit(unitService.getUnitForCode("pieces"));
		car.setKw(1000);
		car.setModel(2013);

		modelService.save(car);

		assertNotNull(carDao.getCarForCode("12345", catalogVersionService.getCatalogVersion("trainingCatalog", "Staged")));

	}

	@Test(expected = ModelSavingException.class)
	public void testValidate_KwLessOrEqual500() throws ImpExException
	{

		final CarModel car = modelService.create(CarModel.class);

		car.setCode("12345");
		car.setName("Car Name");
		car.setUnit(unitService.getUnitForCode("pieces"));
		car.setKw(490);
		car.setModel(2015);

		modelService.save(car);

	}

	@Test
	public void testValidate_KwGreaterThan500() throws ImpExException
	{

		final CarModel car = modelService.create(CarModel.class);

		car.setCode("12345");
		car.setName("Car Name");
		car.setUnit(unitService.getUnitForCode("pieces"));
		car.setKw(1000);
		car.setModel(2013);

		modelService.save(car);

		assertNotNull(carDao.getCarForCode("12345", catalogVersionService.getCatalogVersion("trainingCatalog", "Staged")));

	}

}
