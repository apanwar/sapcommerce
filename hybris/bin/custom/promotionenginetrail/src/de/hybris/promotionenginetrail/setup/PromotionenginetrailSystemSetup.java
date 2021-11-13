/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.promotionenginetrail.setup;

import static de.hybris.promotionenginetrail.constants.PromotionenginetrailConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import de.hybris.promotionenginetrail.constants.PromotionenginetrailConstants;
import de.hybris.promotionenginetrail.service.PromotionenginetrailService;


@SystemSetup(extension = PromotionenginetrailConstants.EXTENSIONNAME)
public class PromotionenginetrailSystemSetup
{
	private final PromotionenginetrailService promotionenginetrailService;

	public PromotionenginetrailSystemSetup(final PromotionenginetrailService promotionenginetrailService)
	{
		this.promotionenginetrailService = promotionenginetrailService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		promotionenginetrailService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return PromotionenginetrailSystemSetup.class.getResourceAsStream("/promotionenginetrail/sap-hybris-platform.png");
	}
}
