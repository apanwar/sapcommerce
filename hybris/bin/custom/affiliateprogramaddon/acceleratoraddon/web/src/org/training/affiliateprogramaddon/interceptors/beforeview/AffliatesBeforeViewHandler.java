/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.affiliateprogramaddon.interceptors.beforeview;

import de.hybris.platform.acceleratorstorefrontcommons.interceptors.BeforeViewHandler;
import de.hybris.platform.commercefacades.customer.CustomerFacade;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.training.affiliateprogramaddon.enums.AffiliationStatus;


/**
 * Filter to load the appropriate Cms page slots into the model
 */
public class AffliatesBeforeViewHandler implements BeforeViewHandler
{
	private static final Logger LOG = Logger.getLogger(AffliatesBeforeViewHandler.class);

	@Resource(name = "customerFacade")
	private CustomerFacade customerFacade;


	@Override
	public void beforeView(final HttpServletRequest request, final HttpServletResponse response, final ModelAndView modelAndView)
	{
		if (customerFacade.getCurrentCustomer().getAffiliationStatus() == AffiliationStatus.APPROVED)
		{
			modelAndView.addObject("myAffiliateId", customerFacade.getCurrentCustomer().getCustomerId());
		}

	}


}
