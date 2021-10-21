/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.interceptors;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.interceptor.InitDefaultsInterceptor;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.training.enums.FuelType;
import org.training.model.CarModel;

/**
 *
 */
@UnitTest
public class CarInitDefaultsInterceptorTest
{

	@InjectMocks
	private InitDefaultsInterceptor<CarModel> carInitDefaultsInterceptor;

	@Mock
	private CarModel car;

	@Mock
	private InterceptorContext context;

	@Mock
	private CatalogVersionService catalogVersionService;

	@Mock
	private CatalogVersionModel catalogVersion;

	@Mock
	private CatalogVersionModel defaultCatalogVersion;

	@Mock
	private CatalogService catalogService;

	@Mock
	private CatalogModel defaultCatalog;


	@Before
	public void setUp()
	{
		carInitDefaultsInterceptor = new CarInitDefaultsInterceptor();
		MockitoAnnotations.initMocks(this);
		Mockito.when(catalogService.getDefaultCatalog()).thenReturn(defaultCatalog);
		Mockito.when(defaultCatalog.getId()).thenReturn("Default");
	}

	@Test
	public void testOnInitDefaults_setFuelType() throws InterceptorException
	{
		carInitDefaultsInterceptor.onInitDefaults(car, context);
		Mockito.verify(car, atLeastOnce()).setFuelType(FuelType.GASOLINE);
	}

	@Test
	public void testOnInitDefaults_setKw() throws InterceptorException
	{
		carInitDefaultsInterceptor.onInitDefaults(car, context);
		Mockito.verify(car, atLeastOnce()).setKw(0);
	}

	@Test
	public void testOnInitDefaults_setCatalogversion_TrainingCatalogStagedExists() throws InterceptorException
	{
		Mockito.when(catalogVersionService.getCatalogVersion("trainingCatalog", "Staged")).thenReturn(catalogVersion);
		carInitDefaultsInterceptor.onInitDefaults(car, context);
		Mockito.verify(car, atLeastOnce()).setCatalogVersion(catalogVersion);
	}

	@Test
	public void testOnInitDefaults_setCatalogversion_TrainingCatalogStagedDoesNotExists() throws InterceptorException
	{
		Mockito.when(catalogVersionService.getCatalogVersion("trainingCatalog", "Staged"))
				.thenThrow(UnknownIdentifierException.class);

		Mockito.when(catalogVersionService.getCatalogVersion("Default", "Staged")).thenReturn(defaultCatalogVersion);

		carInitDefaultsInterceptor.onInitDefaults(car, context);

		Mockito.verify(car, never()).setCatalogVersion(catalogVersion);
		Mockito.verify(car, atLeastOnce()).setCatalogVersion(defaultCatalogVersion);
	}

}
