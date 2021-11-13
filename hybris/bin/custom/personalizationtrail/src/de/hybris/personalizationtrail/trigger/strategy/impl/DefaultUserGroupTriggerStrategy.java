package de.hybris.personalizationtrail.trigger.strategy.impl;
import de.hybris.personalizationtrail.trigger.dao.UserGroupTriggerDao;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.personalizationservices.model.CxVariationModel;
import de.hybris.platform.personalizationservices.trigger.strategy.CxTriggerStrategy;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;
public class DefaultUserGroupTriggerStrategy implements CxTriggerStrategy
{
    private UserService userService;
    private UserGroupTriggerDao userGroupTriggerDao;
    @Override
    public Collection<CxVariationModel> getVariations(final UserModel user, final CatalogVersionModel catalogVersion)
    {
        final Set<UserGroupModel> usersGroup = userService.getAllUserGroupsForUser(user);
        return userGroupTriggerDao.findApplicableVariations(usersGroup, catalogVersion);
    }
    @Required
    public void setUserService(final UserService userService)
    {
        this.userService = userService;
    }
    @Required
    public void setUserGroupTriggerDao(final UserGroupTriggerDao userGroupTriggerDao)
    {
        this.userGroupTriggerDao = userGroupTriggerDao;
    }
}