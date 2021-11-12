/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider.FieldType;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Required;


public class ContactlessDeliverySupportedValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider
{
	private FieldNameProvider fieldNameProvider;

	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
			final Object model) throws FieldValueProviderException
	{
		final ProductModel product = getProductModel(model);
		if (product == null)
		{
			return Collections.emptyList();
		}

		final Set<DeliveryModeModel> deliveryModes = product.getDeliveryModes();

		if (deliveryModes != null && !deliveryModes.isEmpty())
		{
			final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
			final List<String> deliveryModeCodes = deliveryModes.stream().map(deliveryMode -> deliveryMode.getCode())
					.collect(Collectors.toList());

			if (deliveryModeCodes.contains("fedex") || deliveryModeCodes.contains("ups"))
			{
				fieldValues.add(createFieldValue(true, indexedProperty));
			}
			return fieldValues;
		}
		else
		{
			return Collections.emptyList();
		}
	}

	protected FieldValue createFieldValue(final boolean contactlessDeliverySupported, final IndexedProperty indexedProperty)
	{
		final String fieldName = fieldNameProvider.getFieldName(indexedProperty, null, FieldType.INDEX);
		return new FieldValue(fieldName, contactlessDeliverySupported);
	}

	protected ProductModel getProductModel(final Object model)
	{
		final Object finalModel = model;
		if (model instanceof ProductModel)
		{
			return (ProductModel) model;
		}
		else
		{
			return null;
		}
	}

	@Required
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
	}

}
