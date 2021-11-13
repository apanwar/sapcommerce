package de.hybris.personalizationtrail.trigger.converters.populator;
import de.hybris.personalizationtrail.data.UserGroupTriggerData;
import de.hybris.personalizationtrail.model.UserGroupTriggerModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.user.UserService;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;
public class UserGroupTriggerReversePopulator implements Populator<UserGroupTriggerData, UserGroupTriggerModel>
{
    private UserService userService;
    @Override
    public void populate(final UserGroupTriggerData source, final UserGroupTriggerModel target) throws ConversionException
    {
        Assert.notNull(source, "Parameter source cannot be null.");
        Assert.notNull(target, "Parameter target cannot be null.");
        final UserGroupModel userGroup = userService.getUserGroupForUID(source.getUserGroupUid());
        target.setUserGroup(userGroup);
    }
    public UserService getUserService()
    {
        return userService;
    }
    @Required
    public void setUserService(final UserService userService)
    {
        this.userService = userService;
    }
}