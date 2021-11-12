/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.affiliateprogramaddon.interceptors.beforecontroller;

import de.hybris.platform.acceleratorstorefrontcommons.interceptors.BeforeControllerHandler;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.servicelayer.session.SessionService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;


/**
 * Filter to load the appropriate Cms page slots into the model
 */
public class AffliatesBeforeControllerHandler implements BeforeControllerHandler
{
	/**
	 * 
	 */
	private static final String AFFILIATE_ID = "affiliateId";

	private static final Logger LOG = Logger.getLogger(AffliatesBeforeControllerHandler.class);

	@Resource(name = "customerFacade")
	private CustomerFacade customerFacade;

	@Resource
	private SessionService sessionService;

	@Override
	public boolean beforeController(final HttpServletRequest request, final HttpServletResponse response,
			final HandlerMethod handler) throws Exception
	{
		final String affiliateId = request.getParameter(AFFILIATE_ID);
		if (null != affiliateId && !affiliateId.equals(customerFacade.getCurrentCustomer().getCustomerId()))
		{
			sessionService.setAttribute(AFFILIATE_ID, affiliateId);
		}

		return true;
	}




}
