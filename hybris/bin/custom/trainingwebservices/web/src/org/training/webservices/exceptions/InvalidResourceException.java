/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.webservices.exceptions;

import javax.servlet.ServletException;


public class InvalidResourceException extends ServletException
{

	private final String baseSiteId;

	/**
	 * @param baseSiteUid
	 */
	public InvalidResourceException(final String baseSiteUid)
	{
		super("Base site " + baseSiteUid + " doesn't exist");
		this.baseSiteId = baseSiteUid;
	}

	/**
	 * @return the baseSiteId
	 */
	public String getBaseSiteId()
	{
		return baseSiteId;
	}
}
