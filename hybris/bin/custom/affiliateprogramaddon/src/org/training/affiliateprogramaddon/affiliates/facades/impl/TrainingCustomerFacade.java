/**
 *
 */
package org.training.affiliateprogramaddon.affiliates.facades.impl;

import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.model.user.CustomerModel;
import org.training.affiliateprogramaddon.affiliates.populators.AffiliationStatusPopulator;
import org.training.affiliateprogramaddon.enums.AffiliationStatus;

/**
 * @author panwa
 *
 */
public class TrainingCustomerFacade extends DefaultCustomerFacade
{
	private AffiliationStatusPopulator affiliationStatusPopulator;

	@Override
	public void updateProfile(final CustomerData customerData) throws DuplicateUidException
	{
		validateDataBeforeUpdate(customerData);

		final String name = getCustomerNameStrategy().getName(customerData.getFirstName(), customerData.getLastName());
		final CustomerModel customer = getCurrentSessionCustomer();
		if (customerData.getAffiliationStatus()== AffiliationStatus.REQUESTED)
		{
			affiliationStatusPopulator.populate(customerData,customer);
		}
		customer.setOriginalUid(customerData.getDisplayUid());
		getCustomerAccountService().updateProfile(customer, customerData.getTitleCode(), name, customerData.getUid());
	}

	public void setAffiliationStatusPopulator(AffiliationStatusPopulator affiliationStatusPopulator) {
		this.affiliationStatusPopulator = affiliationStatusPopulator;
	}
}
