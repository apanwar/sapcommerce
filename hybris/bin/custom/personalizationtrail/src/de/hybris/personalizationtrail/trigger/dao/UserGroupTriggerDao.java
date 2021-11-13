package de.hybris.personalizationtrail.trigger.dao;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.personalizationservices.model.CxVariationModel;
import de.hybris.platform.servicelayer.internal.dao.Dao;

import java.util.Collection;
import java.util.Set;
public interface UserGroupTriggerDao extends Dao
{
    /**
     * Selects variations which can be triggered for given user groups. Should respect given catalog version.
     *
     * @param userGroups
     *           groups for which variations should be selected
     * @param catalogVersion
     *           from which variation will be returned
     * @return collection of valid variations
     */
    Collection<CxVariationModel> findApplicableVariations(Set<UserGroupModel> userGroups, CatalogVersionModel catalogVersion);
}