/**
 *
 */
package org.training.affiliateprogramaddon.affiliates.hook;

import de.hybris.platform.commerceservices.order.hook.CommercePlaceOrderMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.service.data.CommerceOrderResult;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.servicelayer.event.EventService;

import org.training.affiliateprogramaddon.affiliates.event.AffiliateCommissionEvent;


/**
 * @author panwa
 *
 */
public class AffiliatePlaceOrderMethodHook implements CommercePlaceOrderMethodHook
{

	private EventService eventService;

	@Override
	public void afterPlaceOrder(final CommerceCheckoutParameter parameter, final CommerceOrderResult result)
			throws InvalidCartException
	{
		if(null!=result.getOrder().getAffiliateId()) {
			final AffiliateCommissionEvent event = new AffiliateCommissionEvent();
			event.setOrder(result.getOrder());
			eventService.publishEvent(event);
		}
	}

	@Override
	public void beforePlaceOrder(final CommerceCheckoutParameter parameter) throws InvalidCartException
	{
		// XXX Auto-generated method stub

	}

	@Override
	public void beforeSubmitOrder(final CommerceCheckoutParameter parameter, final CommerceOrderResult result)
			throws InvalidCartException
	{
		// XXX Auto-generated method stub

	}

	/**
	 * @param eventService
	 *           the eventService to set
	 */
	public void setEventService(final EventService eventService)
	{
		this.eventService = eventService;
	}



}
