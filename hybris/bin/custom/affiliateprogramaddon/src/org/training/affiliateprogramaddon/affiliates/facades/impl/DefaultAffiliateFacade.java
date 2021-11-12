package org.training.affiliateprogramaddon.affiliates.facades.impl;

import de.hybris.platform.converters.Converters;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.training.affiliateprogramaddon.affiliates.data.AffiliateIncomeData;
import org.training.affiliateprogramaddon.affiliates.facades.AffiliateFacade;
import org.training.affiliateprogramaddon.affiliates.service.AffiliatesService;
import org.training.affiliateprogramaddon.model.AffiliateComissionModel;

import java.util.List;

public class DefaultAffiliateFacade implements AffiliateFacade {

    private AffiliatesService affiliatesService;

    private Converter<AffiliateComissionModel, AffiliateIncomeData> affiliateIncomeConverter;

    @Override
    public List<AffiliateIncomeData> getAffiliateIncomeData() {
        return Converters.convertAll(affiliatesService.getAffiliateIncome(),affiliateIncomeConverter);
    }

    public void setAffiliatesService(AffiliatesService affiliatesService) {
        this.affiliatesService = affiliatesService;
    }

    public void setAffiliateIncomeConverter(Converter<AffiliateComissionModel, AffiliateIncomeData> affiliateIncomeConverter) {
        this.affiliateIncomeConverter = affiliateIncomeConverter;
    }
}
