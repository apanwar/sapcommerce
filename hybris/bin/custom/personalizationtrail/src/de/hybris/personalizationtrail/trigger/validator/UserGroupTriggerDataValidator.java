package de.hybris.personalizationtrail.trigger.validator;
import de.hybris.personalizationtrail.data.UserGroupTriggerData;
import de.hybris.platform.personalizationwebservices.validator.TriggerValidator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
public class UserGroupTriggerDataValidator implements TriggerValidator
{
    @Override
    public boolean supports(final Class<?> arg0)
    {
        return UserGroupTriggerData.class.isAssignableFrom(arg0);
    }
    @Override
    public void validate(final Object target, final Errors errors)
    {
        ValidationUtils.rejectIfEmpty(errors, "userGroupUid", "field.required");
    }
}