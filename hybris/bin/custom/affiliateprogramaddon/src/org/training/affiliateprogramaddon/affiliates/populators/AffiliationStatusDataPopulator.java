/**
 *
 */
package org.training.affiliateprogramaddon.affiliates.populators;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.training.affiliateprogramaddon.enums.AffiliationStatus;


/**
 * @author panwa
 *
 */
public class AffiliationStatusDataPopulator implements Populator<CustomerModel, CustomerData>
{

	@Override
	public void populate(final CustomerModel source, final CustomerData target) throws ConversionException
	{
		if (source.getAffiliationStatus() != null)
		{

			target.setAffiliationStatus(AffiliationStatus.valueOf(source.getAffiliationStatus().getCode()));
		}

	}

}
