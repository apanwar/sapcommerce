package org.training.affiliateprogramaddon.affiliates.populators;

import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.training.affiliateprogramaddon.affiliates.data.AffiliateIncomeData;
import org.training.affiliateprogramaddon.model.AffiliateComissionModel;

import java.math.BigDecimal;

public class AffiliateIncomeDataPopulator implements Populator<AffiliateComissionModel, AffiliateIncomeData> {

    private PriceDataFactory priceDataFactory;

    @Override
    public void populate(AffiliateComissionModel source, AffiliateIncomeData target) throws ConversionException {

        target.setOrderNumber(source.getOrderNumber());
        target.setIncome(createPrice(source, source.getComissionAmount().doubleValue()));
    }

    protected PriceData createPrice(final AffiliateComissionModel source, final double val)
    {
        if (source == null)
        {
            throw new IllegalArgumentException("source affiliateCommission must not be null");
        }

        final CurrencyModel currency = source.getCurrency();
        if (currency == null)
        {
            throw new IllegalArgumentException("source affiliateCommission currency must not be null");
        }

        return priceDataFactory.create(PriceDataType.BUY, BigDecimal.valueOf(val), currency);
    }

    public void setPriceDataFactory(PriceDataFactory priceDataFactory) {
        this.priceDataFactory = priceDataFactory;
    }
}
