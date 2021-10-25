/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.validation;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.validation.exceptions.HybrisConstraintViolation;
import de.hybris.platform.validation.model.constraints.ConstraintGroupModel;
import de.hybris.platform.validation.services.ValidationService;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.training.model.CarModel;

/**
 *
 */
@IntegrationTest
public class CarModelExplicitValidationIntegrationTest extends ServicelayerTransactionalTest
{

	@Resource
	private ValidationService validationService;

	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Before
	public void setup() throws Exception
	{
		createCoreData();
	}

	@Test
	public void testValidate() throws ImpExException
	{
		importCsv("/training/test/CarModelExplicitValidationIntegrationTest.impex", "utf-8");

		validationService.reloadValidationEngine();

		final CarModel car = new CarModel();

		car.setCode("12345");

		final Set<HybrisConstraintViolation> violations = validationService.validate(car, Collections.emptyList());

		Assert.assertNotNull(violations);
		Assert.assertFalse(violations.isEmpty());

	}

	@Test
	public void testValidateProperty() throws ImpExException
	{
		importCsv("/training/test/CarModelExplicitValidationIntegrationTest.impex", "utf-8");

		validationService.reloadValidationEngine();

		final CarModel car = new CarModel();

		car.setCode("12345");
		car.setChasisNumber("bdvsdjk");

		final Set<HybrisConstraintViolation> violations = validationService.validateProperty(car, CarModel.CHASISNUMBER,
				Collections.emptyList());

		Assert.assertNotNull(violations);
		Assert.assertFalse(violations.isEmpty());

	}

	@Test
	public void testValidateValue() throws ImpExException
	{
		importCsv("/training/test/CarModelExplicitValidationIntegrationTest.impex", "utf-8");

		validationService.reloadValidationEngine();

		final String chasisNumber = "hjkhkj";

		final Set<HybrisConstraintViolation> violations = validationService.validateValue(CarModel.class, CarModel.CHASISNUMBER,
				chasisNumber, Collections.emptyList());

		Assert.assertNotNull(violations);
		Assert.assertFalse(violations.isEmpty());

	}

	@Test
	public void testValidateConstraintGroup() throws ImpExException
	{
		importCsv("/training/test/CarModelExplicitValidationIntegrationTest.impex", "utf-8");

		validationService.reloadValidationEngine();

		final CarModel car = new CarModel();

		car.setCode("12345");



		final Set<HybrisConstraintViolation> violations = validationService.validate(car,
				getConstraintsForId("trainingConstraintGroup"));

		Assert.assertNotNull(violations);
		Assert.assertFalse(violations.isEmpty());

	}

	@Test
	public void testValidatePropertyConstraintGroup() throws ImpExException
	{
		importCsv("/training/test/CarModelExplicitValidationIntegrationTest.impex", "utf-8");

		validationService.reloadValidationEngine();

		final CarModel car = new CarModel();

		car.setCode("12345");
		car.setEngineNumber("bdvsdjk");

		final Set<HybrisConstraintViolation> violations = validationService.validateProperty(car, CarModel.ENGINENUMBER,
				getConstraintsForId("trainingConstraintGroup"));

		Assert.assertNotNull(violations);
		Assert.assertFalse(violations.isEmpty());

	}

	@Test
	public void testValidateValueConstraintGroup() throws ImpExException
	{
		importCsv("/training/test/CarModelExplicitValidationIntegrationTest.impex", "utf-8");

		validationService.reloadValidationEngine();

		final String chasisNumber = "hjkhkj";

		final Set<HybrisConstraintViolation> violations = validationService.validateValue(CarModel.class, CarModel.ENGINENUMBER,
				chasisNumber, getConstraintsForId("trainingConstraintGroup"));

		Assert.assertNotNull(violations);
		Assert.assertFalse(violations.isEmpty());

	}

	private Collection<ConstraintGroupModel> getConstraintsForId(final String id)
	{

		final FlexibleSearchQuery query = new FlexibleSearchQuery("SELECT {PK} FROM {ConstraintGroup} WHERE {id}=?id");
		query.addQueryParameter("id", id);
		final SearchResult<ConstraintGroupModel> searchResult = flexibleSearchService.search(query);
		return searchResult.getResult();
	}

}
