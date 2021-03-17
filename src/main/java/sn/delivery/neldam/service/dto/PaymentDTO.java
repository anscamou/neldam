package sn.delivery.neldam.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import sn.delivery.neldam.domain.enumeration.PaymentType;
import sn.delivery.neldam.domain.enumeration.PaymentStatus;

/**
 * A DTO for the {@link sn.delivery.neldam.domain.Payment} entity.
 */
public class PaymentDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Instant placeDate;

    @NotNull
    @DecimalMin(value = "0")
    private BigDecimal totalPrice;

    @NotNull
    private PaymentType payment;

    private String paymentReference;

    private PaymentStatus paymentStatus;

    private String message;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getPlaceDate() {
        return placeDate;
    }

    public void setPlaceDate(Instant placeDate) {
        this.placeDate = placeDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PaymentType getPayment() {
        return payment;
    }

    public void setPayment(PaymentType payment) {
        this.payment = payment;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentDTO)) {
            return false;
        }

        return id != null && id.equals(((PaymentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentDTO{" +
            "id=" + getId() +
            ", placeDate='" + getPlaceDate() + "'" +
            ", totalPrice=" + getTotalPrice() +
            ", payment='" + getPayment() + "'" +
            ", paymentReference='" + getPaymentReference() + "'" +
            ", paymentStatus='" + getPaymentStatus() + "'" +
            ", message='" + getMessage() + "'" +
            "}";
    }
}
