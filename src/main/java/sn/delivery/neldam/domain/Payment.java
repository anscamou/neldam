package sn.delivery.neldam.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import sn.delivery.neldam.domain.enumeration.PaymentType;

import sn.delivery.neldam.domain.enumeration.PaymentStatus;

/**
 * A Payment.
 */
@Entity
@Table(name = "payment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "place_date", nullable = false)
    private Instant placeDate;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "total_price", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalPrice;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment", nullable = false)
    private PaymentType payment;

    @Column(name = "payment_reference")
    private String paymentReference;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "message")
    private String message;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getPlaceDate() {
        return placeDate;
    }

    public Payment placeDate(Instant placeDate) {
        this.placeDate = placeDate;
        return this;
    }

    public void setPlaceDate(Instant placeDate) {
        this.placeDate = placeDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Payment totalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PaymentType getPayment() {
        return payment;
    }

    public Payment payment(PaymentType payment) {
        this.payment = payment;
        return this;
    }

    public void setPayment(PaymentType payment) {
        this.payment = payment;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public Payment paymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
        return this;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public Payment paymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
        return this;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getMessage() {
        return message;
    }

    public Payment message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Payment)) {
            return false;
        }
        return id != null && id.equals(((Payment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Payment{" +
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
