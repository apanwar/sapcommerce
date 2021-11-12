/**
 *
 */
package org.training.affiliateprogramaddon.affiliates.service;

import de.hybris.platform.core.model.user.CustomerModel;
import org.training.affiliateprogramaddon.model.AffiliateComissionModel;

import java.util.List;


/**
 * @author panwa
 *
 */
public interface AffiliatesService
{

	List<CustomerModel> getUnapprovedAffiliates();

	CustomerModel getCustomerByCustomerId(String customerId);

	List<AffiliateComissionModel> getAffiliateIncome();
}
