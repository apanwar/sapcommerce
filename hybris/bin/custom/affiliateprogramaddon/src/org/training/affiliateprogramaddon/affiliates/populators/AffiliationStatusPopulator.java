/**
 *
 */
package org.training.affiliateprogramaddon.affiliates.populators;

import de.hybris.platform.commercefacades.user.converters.populator.CustomerReversePopulator;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.training.affiliateprogramaddon.enums.AffiliationStatusType;


/**
 * @author panwa
 *
 */
public class AffiliationStatusPopulator implements Populator<CustomerData, CustomerModel>
{
	@Override
	public void populate(final CustomerData source, final CustomerModel target) throws ConversionException
	{
		if (source.getAffiliationStatus() != null)
		{

			target.setAffiliationStatus(AffiliationStatusType.valueOf(source.getAffiliationStatus().name()));
		}

	}

}
