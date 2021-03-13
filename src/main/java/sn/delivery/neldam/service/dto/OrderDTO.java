package sn.delivery.neldam.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import sn.delivery.neldam.domain.enumeration.OrderStatus;
import sn.delivery.neldam.domain.enumeration.Payment;

/**
 * A DTO for the {@link sn.delivery.neldam.domain.Order} entity.
 */
public class OrderDTO implements Serializable {
    
    private Long id;

    private String description;

    @NotNull
    private Double latFrom;

    @NotNull
    private Double longFrom;

    private String addrFrom;

    @NotNull
    private String phoneTo;

    @NotNull
    private Double latTo;

    @NotNull
    private Double longTo;

    private String addrTo;

    @NotNull
    private OrderStatus orderStatus;

    @NotNull
    private Payment payment;


    private Long orderId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLatFrom() {
        return latFrom;
    }

    public void setLatFrom(Double latFrom) {
        this.latFrom = latFrom;
    }

    public Double getLongFrom() {
        return longFrom;
    }

    public void setLongFrom(Double longFrom) {
        this.longFrom = longFrom;
    }

    public String getAddrFrom() {
        return addrFrom;
    }

    public void setAddrFrom(String addrFrom) {
        this.addrFrom = addrFrom;
    }

    public String getPhoneTo() {
        return phoneTo;
    }

    public void setPhoneTo(String phoneTo) {
        this.phoneTo = phoneTo;
    }

    public Double getLatTo() {
        return latTo;
    }

    public void setLatTo(Double latTo) {
        this.latTo = latTo;
    }

    public Double getLongTo() {
        return longTo;
    }

    public void setLongTo(Double longTo) {
        this.longTo = longTo;
    }

    public String getAddrTo() {
        return addrTo;
    }

    public void setAddrTo(String addrTo) {
        this.addrTo = addrTo;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long customerId) {
        this.orderId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderDTO)) {
            return false;
        }

        return id != null && id.equals(((OrderDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", latFrom=" + getLatFrom() +
            ", longFrom=" + getLongFrom() +
            ", addrFrom='" + getAddrFrom() + "'" +
            ", phoneTo='" + getPhoneTo() + "'" +
            ", latTo=" + getLatTo() +
            ", longTo=" + getLongTo() +
            ", addrTo='" + getAddrTo() + "'" +
            ", orderStatus='" + getOrderStatus() + "'" +
            ", payment='" + getPayment() + "'" +
            ", orderId=" + getOrderId() +
            "}";
    }
}
