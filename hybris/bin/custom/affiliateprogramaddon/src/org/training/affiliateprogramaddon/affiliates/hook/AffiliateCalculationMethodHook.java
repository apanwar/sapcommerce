/**
 *
 */
package org.training.affiliateprogramaddon.affiliates.hook;

import de.hybris.platform.commerceservices.order.hook.CommerceCartCalculationMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.session.SessionService;

/**
 * @author panwa
 *
 */
public class AffiliateCalculationMethodHook implements CommerceCartCalculationMethodHook
{

	private SessionService sessionService;

	@Override
	public void afterCalculate(final CommerceCartParameter parameter)
	{
		// XXX Auto-generated method stub
	}

	@Override
	public void beforeCalculate(final CommerceCartParameter parameter)
	{
		final String affiliateId = sessionService.getAttribute("affiliateId");
		final CartModel cart = parameter.getCart();
		if (null != affiliateId && null == cart.getAffiliateId())
		{
			parameter.getCart().setAffiliateId(affiliateId);
		}
	}


	/**
	 * @param sessionService
	 *           the sessionService to set
	 */
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

}
