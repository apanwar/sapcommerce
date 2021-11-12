/**
 *
 */
package org.training.affiliateprogramaddon.affiliates.service.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateIfSingleResult;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;
import static java.lang.String.format;

import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Collections;
import java.util.List;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.training.affiliateprogramaddon.affiliates.dao.AffiliatesDao;
import org.training.affiliateprogramaddon.affiliates.service.AffiliatesService;
import org.training.affiliateprogramaddon.model.AffiliateComissionModel;

/**
 * @author panwa
 *
 */
public class DefaultAffiliateService implements AffiliatesService
{
	private AffiliatesDao affiliatesDao;

	private UserService userService;

	@Override
	public List<CustomerModel> getUnapprovedAffiliates()
	{
		return affiliatesDao.findUnapprovedAffiliates();
	}

	@Override
	public CustomerModel getCustomerByCustomerId(final String customerId)
	{
		validateParameterNotNull(customerId, "Parameter customerId must not be null");
		final List<CustomerModel> customers = affiliatesDao.findCustomersByCustomerId(customerId);
		validateIfSingleResult(customers, format("Customer with customerId '%s' not found!", customerId),
				format("Customer id '%s' is not unique, %d customers found!", customerId, Integer.valueOf(customers.size())));
		return customers.get(0);
	}

	@Override
	public List<AffiliateComissionModel> getAffiliateIncome() {
		UserModel currentUser = userService.getCurrentUser();
		if(currentUser instanceof CustomerModel && !userService.isAnonymousUser(currentUser)) {
			return affiliatesDao.findAffiliateIncome((CustomerModel)currentUser);
		}else{
			return Collections.emptyList();
		}
	}

	/**
	 * @param affiliatesDao
	 *           the affiliatesDao to set
	 */
	public void setAffiliatesDao(final AffiliatesDao affiliatesDao)
	{
		this.affiliatesDao = affiliatesDao;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
