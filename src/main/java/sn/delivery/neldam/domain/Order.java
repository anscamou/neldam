package sn.delivery.neldam.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import sn.delivery.neldam.domain.enumeration.OrderStatus;

import sn.delivery.neldam.domain.enumeration.Payment;

/**
 * A Order.
 */
@Entity
@Table(name = "jhi_order")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "lat_from", nullable = false)
    private Double latFrom;

    @NotNull
    @Column(name = "long_from", nullable = false)
    private Double longFrom;

    @Column(name = "addr_from")
    private String addrFrom;

    @NotNull
    @Column(name = "phone_to", nullable = false)
    private String phoneTo;

    @NotNull
    @Column(name = "lat_to", nullable = false)
    private Double latTo;

    @NotNull
    @Column(name = "long_to", nullable = false)
    private Double longTo;

    @Column(name = "addr_to")
    private String addrTo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment", nullable = false)
    private Payment payment;

    @OneToOne(mappedBy = "customer")
    @JsonIgnore
    private Customer order;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Order description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLatFrom() {
        return latFrom;
    }

    public Order latFrom(Double latFrom) {
        this.latFrom = latFrom;
        return this;
    }

    public void setLatFrom(Double latFrom) {
        this.latFrom = latFrom;
    }

    public Double getLongFrom() {
        return longFrom;
    }

    public Order longFrom(Double longFrom) {
        this.longFrom = longFrom;
        return this;
    }

    public void setLongFrom(Double longFrom) {
        this.longFrom = longFrom;
    }

    public String getAddrFrom() {
        return addrFrom;
    }

    public Order addrFrom(String addrFrom) {
        this.addrFrom = addrFrom;
        return this;
    }

    public void setAddrFrom(String addrFrom) {
        this.addrFrom = addrFrom;
    }

    public String getPhoneTo() {
        return phoneTo;
    }

    public Order phoneTo(String phoneTo) {
        this.phoneTo = phoneTo;
        return this;
    }

    public void setPhoneTo(String phoneTo) {
        this.phoneTo = phoneTo;
    }

    public Double getLatTo() {
        return latTo;
    }

    public Order latTo(Double latTo) {
        this.latTo = latTo;
        return this;
    }

    public void setLatTo(Double latTo) {
        this.latTo = latTo;
    }

    public Double getLongTo() {
        return longTo;
    }

    public Order longTo(Double longTo) {
        this.longTo = longTo;
        return this;
    }

    public void setLongTo(Double longTo) {
        this.longTo = longTo;
    }

    public String getAddrTo() {
        return addrTo;
    }

    public Order addrTo(String addrTo) {
        this.addrTo = addrTo;
        return this;
    }

    public void setAddrTo(String addrTo) {
        this.addrTo = addrTo;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Order orderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Payment getPayment() {
        return payment;
    }

    public Order payment(Payment payment) {
        this.payment = payment;
        return this;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Customer getOrder() {
        return order;
    }

    public Order order(Customer customer) {
        this.order = customer;
        return this;
    }

    public void setOrder(Customer customer) {
        this.order = customer;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        return id != null && id.equals(((Order) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Order{" +
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
            "}";
    }
}
