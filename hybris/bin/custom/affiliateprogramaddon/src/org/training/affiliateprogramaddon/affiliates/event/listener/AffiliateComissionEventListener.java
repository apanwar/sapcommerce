/**
 *
 */
package org.training.affiliateprogramaddon.affiliates.event.listener;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;

import java.math.BigDecimal;

import org.training.affiliateprogramaddon.affiliates.event.AffiliateCommissionEvent;
import org.training.affiliateprogramaddon.affiliates.service.AffiliatesService;
import org.training.affiliateprogramaddon.model.AffiliateComissionModel;


/**
 * @author panwa
 *
 */
public class AffiliateComissionEventListener extends AbstractEventListener<AffiliateCommissionEvent>
{

	private AffiliatesService affiliatesService;

	private ModelService modelService;

	@Override
	protected void onEvent(final AffiliateCommissionEvent event)
	{

		final OrderModel order = event.getOrder();
		final CustomerModel customer = affiliatesService.getCustomerByCustomerId(order.getAffiliateId());

		final AffiliateComissionModel commission = modelService.create(AffiliateComissionModel.class);
		commission.setCustomer(customer);
		commission.setOrderNumber(order.getCode());
		commission.setCurrency(order.getCurrency());
		commission.setComissionAmount(new BigDecimal(order.getTotalPrice() * 0.03));
		modelService.save(commission);
	}

	/**
	 * @param affiliatesService
	 *           the affiliatesService to set
	 */
	public void setAffiliatesService(final AffiliatesService affiliatesService)
	{
		this.affiliatesService = affiliatesService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

}
