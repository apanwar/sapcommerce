/**
 *
 */
package org.training.affiliateprogramaddon.affiliates.jobs;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.training.affiliateprogramaddon.affiliates.service.AffiliatesService;
import org.training.affiliateprogramaddon.enums.AffiliationStatusType;


/**
 * @author panwa
 *
 */
public class AffiliatesApprovalJob extends AbstractJobPerformable<CronJobModel>
{

	private static final Logger LOGGER = Logger.getLogger(AffiliatesApprovalJob.class);

	private AffiliatesService affiliatesService;

	private ModelService modelService;

	@Override
	public PerformResult perform(final CronJobModel cronJob)
	{
		final List<CustomerModel> unapprovedAffiliates = affiliatesService.getUnapprovedAffiliates();
		if (null == unapprovedAffiliates)
		{
			LOGGER.error("Unexpected list of unapproved affiliates retrieved");
			return new PerformResult(CronJobResult.ERROR, CronJobStatus.FINISHED);
		}
		else
		{
			LOGGER.info("Strating approval for unapproved and registered affiliates.");
			// Filter the customers who placed orders in past. These customers are eligible to be affiliates.
			final List<CustomerModel> eligibleAffiliates = unapprovedAffiliates.stream()
					.filter(customer -> CollectionUtils.isNotEmpty(customer.getOrders())).collect(Collectors.toList());
			LOGGER.info(String.format("Only %s customers are eligible to be affiliates.", eligibleAffiliates.size()));
			// Mark eligible customers to be approved affiliates
			eligibleAffiliates.forEach(affiliate -> affiliate.setAffiliationStatus(AffiliationStatusType.APPROVED));
			modelService.saveAll(unapprovedAffiliates);
			LOGGER.info(String.format("Approved %s unapproved affiliates.", eligibleAffiliates.size()));
			return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
		}
	}

	/**
	 * @param affiliatesService
	 *           the affiliatesService to set
	 */
	public void setAffiliatesService(final AffiliatesService affiliatesService)
	{
		this.affiliatesService = affiliatesService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Override
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

}
