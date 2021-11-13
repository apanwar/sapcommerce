package de.hybris.personalizationtrail.trigger.converters.populator;
import de.hybris.personalizationtrail.data.UserGroupTriggerData;
import de.hybris.personalizationtrail.model.UserGroupTriggerModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.springframework.util.Assert;
public class UserGroupTriggerPopulator implements Populator<UserGroupTriggerModel, UserGroupTriggerData>
{
    @Override
    public void populate(final UserGroupTriggerModel source, final UserGroupTriggerData target) throws ConversionException
    {
        Assert.notNull(source, "Parameter source cannot be null.");
        Assert.notNull(target, "Parameter target cannot be null.");
        target.setUserGroupUid(source.getUserGroup().getUid());
    }
}