package org.training.facade.converters;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.converters.Populator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.training.facades.populators.ProductListCountryOfOriginPopulator;


/**
 *
 */
@UnitTest
public class CarPopulatorTest
{

	private Populator<SearchResultValueData, ProductData> productListCountryOfOriginPopulator;

	@Mock
	private SearchResultValueData carModel;

	@Before
	public void setup()
	{
		productListCountryOfOriginPopulator = new ProductListCountryOfOriginPopulator();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testPopulate()
	{
		//		Mockito.when(carModel.getValues().get("countryOfOrigin")).thenReturn("US");
		//		//		Mockito.when(carModel.getEngineNumber()).thenReturn("mockenginenumber");
		//		final ProductData carData = new ProductData();
		//		productListCountryOfOriginPopulator.populate(carModel, carData);
		//
		//		Assert.assertEquals(carModel.getValues().get("countryOfOrigin"), carData.getCountryOfOrigin());
		//		//		Assert.assertEquals(carModel.getEngineNumber(), carData.getEngineNumber());
		System.out.println(carModel.getValues().get("countryOfOrigin"));
	}

}