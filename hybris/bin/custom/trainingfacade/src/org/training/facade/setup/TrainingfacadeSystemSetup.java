/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.facade.setup;

import static org.training.facade.constants.TrainingfacadeConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import org.training.facade.constants.TrainingfacadeConstants;
import org.training.facade.service.TrainingfacadeService;


@SystemSetup(extension = TrainingfacadeConstants.EXTENSIONNAME)
public class TrainingfacadeSystemSetup
{
	private final TrainingfacadeService trainingfacadeService;

	public TrainingfacadeSystemSetup(final TrainingfacadeService trainingfacadeService)
	{
		this.trainingfacadeService = trainingfacadeService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		trainingfacadeService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return TrainingfacadeSystemSetup.class.getResourceAsStream("/trainingfacade/sap-hybris-platform.png");
	}
}
