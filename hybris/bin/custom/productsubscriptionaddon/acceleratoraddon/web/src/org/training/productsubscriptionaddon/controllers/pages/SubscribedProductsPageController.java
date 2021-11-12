/**
 *
 */
package org.training.productsubscriptionaddon.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author panwa
 *
 */
@Controller
@Scope("tenant")
@RequestMapping("/my-account")
public class SubscribedProductsPageController extends AbstractSearchPageController
{

}
