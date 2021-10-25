/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.facade.converters;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.training.facade.ProductData;


/**
 *
 */
@UnitTest
public class ProductPopulatorTest
{

	private Populator<ProductModel, ProductData> productPopulator;

	@Mock
	private ProductModel productModel;

	@Before
	public void setup()
	{
		productPopulator = new ProductPopulator();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testPopulate()
	{
		Mockito.when(productModel.getCode()).thenReturn("mockProductCode");
		Mockito.when(productModel.getName()).thenReturn("Mock Product");
		final ProductData productData = new ProductData();
		productPopulator.populate(productModel, productData);

		Assert.assertEquals(productModel.getCode(), productData.getId());
		Assert.assertEquals(productModel.getName(), productData.getName());
	}

}
