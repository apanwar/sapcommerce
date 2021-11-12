/**
 *
 */
package org.training.affiliateprogramaddon.affiliates.dao;

import de.hybris.platform.core.model.user.CustomerModel;
import org.training.affiliateprogramaddon.model.AffiliateComissionModel;

import java.util.List;


/**
 * @author panwa
 *
 */
public interface AffiliatesDao
{

	List<CustomerModel> findUnapprovedAffiliates();

	List<CustomerModel> findCustomersByCustomerId(String customerId);

    List<AffiliateComissionModel> findAffiliateIncome(CustomerModel currentUser);
}
