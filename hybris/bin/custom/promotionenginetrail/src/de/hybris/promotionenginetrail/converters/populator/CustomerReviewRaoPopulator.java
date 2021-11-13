package de.hybris.promotionenginetrail.converters.populator;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.promotionenginetrail.rao.CustomerReviewRAO;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
 
public class CustomerReviewRaoPopulator implements Populator<CustomerReviewModel, CustomerReviewRAO>
{
    @Override
    public void populate(final CustomerReviewModel source, final CustomerReviewRAO target) throws ConversionException
    {
        target.setProductCode(source.getProduct().getCode());
    }
}