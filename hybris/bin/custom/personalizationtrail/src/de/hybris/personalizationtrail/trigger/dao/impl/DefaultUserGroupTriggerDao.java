package de.hybris.personalizationtrail.trigger.dao.impl;
import de.hybris.personalizationtrail.model.UserGroupTriggerModel;
import de.hybris.personalizationtrail.trigger.dao.UserGroupTriggerDao;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.personalizationservices.dao.impl.AbstractCxDao;
import de.hybris.platform.personalizationservices.model.CxVariationModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public class DefaultUserGroupTriggerDao extends AbstractCxDao<UserGroupTriggerModel> implements UserGroupTriggerDao
{
    public static final String FIND_VARIATIONS_QUERY = " SELECT {v." + CxVariationModel.PK + "} " + " FROM { "
            + CxVariationModel._TYPECODE + " as v " + " JOIN " + UserGroupTriggerModel._TYPECODE + " as t on {t."
            + UserGroupTriggerModel.VARIATION + "} = {v." + CxVariationModel.PK + "} " + " } " + " WHERE {v."
            + CxVariationModel.CATALOGVERSION + "} = ?catalogVersion " + " AND {t." + UserGroupTriggerModel.CATALOGVERSION
            + "} = ?catalogVersion " + " AND {t." + UserGroupTriggerModel.USERGROUP + "} IN (?usergroups) ";
    public DefaultUserGroupTriggerDao()
    {
        super(UserGroupTriggerModel._TYPECODE);
    }
    @Override
    public Collection<CxVariationModel> findApplicableVariations(final Set<UserGroupModel> userGroups,
            final CatalogVersionModel catalogVersion)
    {
        ServicesUtil.validateParameterNotNull(userGroups, "userGroups must not be null");
        ServicesUtil.validateParameterNotNull(catalogVersion, "catalogVersion must not be null");
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("usergroups", userGroups);
        params.put("catalogVersion", catalogVersion);
        return queryList(FIND_VARIATIONS_QUERY, params);
    }
}