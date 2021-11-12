/**
 *
 */
package org.training.affiliateprogramaddon.affiliates.dao.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.List;

import org.training.affiliateprogramaddon.affiliates.dao.AffiliatesDao;
import org.training.affiliateprogramaddon.enums.AffiliationStatusType;
import org.training.affiliateprogramaddon.model.AffiliateComissionModel;

/**
 * @author panwa
 *
 */
public class DefaultAffiliatesDao implements AffiliatesDao
{

	private FlexibleSearchService flexibleSearchService;

	@Override
	public List<CustomerModel> findUnapprovedAffiliates()
	{
		String query = "SELECT {" + CustomerModel.PK + "} FROM {" +
				CustomerModel._TYPECODE + "} WHERE {" + CustomerModel.AFFILIATIONSTATUS +
				"}=?affiliationStatus";
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery(query);
		fsq.addQueryParameter("affiliationStatus", AffiliationStatusType.REQUESTED);

		final SearchResult<CustomerModel> searchResult = flexibleSearchService.search(fsq);
		return searchResult.getResult();
	}

	@Override
	public List<CustomerModel> findCustomersByCustomerId(final String customerId)
	{
		String query = "SELECT {" + CustomerModel.PK + "} FROM {" +
				CustomerModel._TYPECODE + "} WHERE {" + CustomerModel.CUSTOMERID + "}=?customerId";
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery(query);
		fsq.addQueryParameter("customerId", customerId);

		final SearchResult<CustomerModel> searchResult = flexibleSearchService.search(fsq);

		return searchResult.getResult();
	}

	@Override
	public List<AffiliateComissionModel> findAffiliateIncome(CustomerModel currentUser) {
        String query = "SELECT {" + AffiliateComissionModel.PK + "} FROM {" +
                AffiliateComissionModel._TYPECODE + "} WHERE {" + AffiliateComissionModel.CUSTOMER + "}=?customer";
        final FlexibleSearchQuery fsq = new FlexibleSearchQuery(query);
        fsq.addQueryParameter("customer", currentUser);

        final SearchResult<AffiliateComissionModel> searchResult = flexibleSearchService.search(fsq);

        return searchResult.getResult();
	}

	/**
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}
}
