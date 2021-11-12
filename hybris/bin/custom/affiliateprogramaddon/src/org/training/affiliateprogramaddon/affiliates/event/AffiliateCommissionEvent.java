/**
 *
 */
package org.training.affiliateprogramaddon.affiliates.event;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.event.ClusterAwareEvent;
import de.hybris.platform.servicelayer.event.PublishEventContext;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;

/**
 * @author panwa
 *
 */
public class AffiliateCommissionEvent extends AbstractEvent implements ClusterAwareEvent {
    private OrderModel order;

    @Override
    public boolean canPublish(PublishEventContext publishEventContext) {
        return publishEventContext.getSourceNodeId() == publishEventContext.getTargetNodeId();
    }

    /**
     * @param order
     *           the order to set
     */
    public void setOrder(final OrderModel order) {
        this.order = order;
    }

    /**
     * @return the order
     */
    public OrderModel getOrder() {
        return order;
    }

}
