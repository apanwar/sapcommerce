/**
 *
 */
package org.training.facades.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 * @author piychaur
 *
 */
public class ProductListContactlessdeliveryPopulator implements Populator<SearchResultValueData, ProductData>
{
	@Override
	public void populate(final SearchResultValueData source, final ProductData target) throws ConversionException
	{
		if (source.getValues() == null)
		{
			return;
		}

		final Integer countryOfOrigin = (Integer) source.getValues().get("contactlessdelivery");

		if (null != countryOfOrigin)
		{
			target.setContactlessdelivery(countryOfOrigin);
		}
	}
}

