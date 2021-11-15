package org.training.facades.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.converters.populator.SearchResultVariantProductPopulator;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;



public class ProductListCountryOfOriginPopulator extends SearchResultVariantProductPopulator
{

	@Override
	public void populate(final SearchResultValueData source, final ProductData target) throws ConversionException
	{
		if (source.getValues() == null)
		{
			return;
		}

		final Object countryOfOrigin = source.getValues().get("countryOfOrigin");


		if (null != countryOfOrigin)
		{
			target.setCountryOfOrigin(countryOfOrigin.toString());
		}
	}


}
