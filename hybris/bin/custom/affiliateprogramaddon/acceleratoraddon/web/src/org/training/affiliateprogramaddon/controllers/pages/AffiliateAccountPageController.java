package org.training.affiliateprogramaddon.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.order.data.OrderHistoryData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.training.affiliateprogramaddon.affiliates.facades.AffiliateFacade;
import org.training.affiliateprogramaddon.enums.AffiliationStatus;
import org.training.affiliateprogramaddon.forms.UpdateProfileForm;
import org.training.storefront.controllers.pages.AccountPageController;

import javax.annotation.Resource;

@Component
@RequestMapping("/my-account")
public class AffiliateAccountPageController extends AccountPageController {

    private static final String AFFILIATE_INCOME_CMS_PAGE = "affiliateIncome";
    @Resource
    private AffiliateFacade affiliateFacade;

    @Override
    public String editProfile(final Model model) throws CMSItemNotFoundException
    {
        String page = super.editProfile(model);
        final CustomerData customerData = customerFacade.getCurrentCustomer();
        final UpdateProfileForm updateProfileForm = new UpdateProfileForm();

        updateProfileForm.setTitleCode(customerData.getTitleCode());
        updateProfileForm.setFirstName(customerData.getFirstName());
        updateProfileForm.setLastName(customerData.getLastName());
        updateProfileForm.setAffiliationStatus(customerData.getAffiliationStatus());

        model.addAttribute("updateProfileForm", updateProfileForm);

        model.addAttribute("updateProfileForm", updateProfileForm);
        return page;
    }

    @RequestMapping(value = "/update-profile-affiliate", method = RequestMethod.POST)
    @RequireHardLogIn
    public String updateProfileAffiliate(final UpdateProfileForm updateProfileForm, final BindingResult bindingResult, final Model model,
                                final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException
    {
        getProfileValidator().validate(updateProfileForm, bindingResult);

        String returnAction = REDIRECT_TO_UPDATE_PROFILE;
        final CustomerData currentCustomerData = customerFacade.getCurrentCustomer();
        final CustomerData customerData = new CustomerData();
        customerData.setTitleCode(updateProfileForm.getTitleCode());
        customerData.setFirstName(updateProfileForm.getFirstName());
        customerData.setLastName(updateProfileForm.getLastName());
        customerData.setUid(currentCustomerData.getUid());
        customerData.setDisplayUid(currentCustomerData.getDisplayUid());

        if (currentCustomerData.getAffiliationStatus() == null && updateProfileForm.isBecomeAffiliate())
        {
            customerData.setAffiliationStatus(AffiliationStatus.REQUESTED);
        }

        model.addAttribute(TITLE_DATA_ATTR, userFacade.getTitles());

        final ContentPageModel updateProfilePage = getContentPageForLabelOrId(UPDATE_PROFILE_CMS_PAGE);
        storeCmsPageInModel(model, updateProfilePage);
        setUpMetaDataForContentPage(model, updateProfilePage);

        if (bindingResult.hasErrors())
        {
            returnAction = setErrorMessagesAndCMSPage(model, UPDATE_PROFILE_CMS_PAGE);
        }
        else
        {
            try
            {
                customerFacade.updateProfile(customerData);
                GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.CONF_MESSAGES_HOLDER,
                        "text.account.profile.confirmationUpdated", null);

            }
            catch (final DuplicateUidException e)
            {
                bindingResult.rejectValue("email", "registration.error.account.exists.title");
                returnAction = setErrorMessagesAndCMSPage(model, UPDATE_PROFILE_CMS_PAGE);
            }
        }


        model.addAttribute(BREADCRUMBS_ATTR, accountBreadcrumbBuilder.getBreadcrumbs(TEXT_ACCOUNT_PROFILE));
        return returnAction;
    }

    @RequestMapping(value = "/affiliateIncome", method = RequestMethod.GET)
    @RequireHardLogIn
    public String affiliateIncome(final Model model) throws CMSItemNotFoundException
    {
        model.addAttribute("commissions",affiliateFacade.getAffiliateIncomeData());
        final ContentPageModel affliateIncomePage = getContentPageForLabelOrId(AFFILIATE_INCOME_CMS_PAGE);
        storeCmsPageInModel(model, affliateIncomePage);
        setUpMetaDataForContentPage(model, affliateIncomePage);
        model.addAttribute(BREADCRUMBS_ATTR, accountBreadcrumbBuilder.getBreadcrumbs("text.account.affilateIncome"));
        model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
        return getViewForPage(model);
    }
}
