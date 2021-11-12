package org.training.affiliateprogramaddon.forms;

import org.training.affiliateprogramaddon.enums.AffiliationStatus;

public class UpdateProfileForm extends de.hybris.platform.acceleratorstorefrontcommons.forms.UpdateProfileForm {
    private boolean becomeAffiliate;
    private AffiliationStatus affiliationStatus;

    public boolean isBecomeAffiliate() {
        return becomeAffiliate;
    }

    public void setBecomeAffiliate(boolean becomeAffiliate) {
        this.becomeAffiliate = becomeAffiliate;
    }

    public AffiliationStatus getAffiliationStatus() {
        return affiliationStatus;
    }

    public void setAffiliationStatus(AffiliationStatus affiliationStatus) {
        this.affiliationStatus = affiliationStatus;
    }
}
