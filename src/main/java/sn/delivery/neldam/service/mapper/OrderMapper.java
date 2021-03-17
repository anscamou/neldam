package sn.delivery.neldam.service.mapper;


import sn.delivery.neldam.domain.*;
import sn.delivery.neldam.service.dto.OrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Order} and its DTO {@link OrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {PaymentMapper.class, CustomerMapper.class})
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {

    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "customer.id", target = "customerId")
    OrderDTO toDto(Order order);

    @Mapping(source = "orderId", target = "order")
    @Mapping(source = "customerId", target = "customer")
    Order toEntity(OrderDTO orderDTO);

    default Order fromId(Long id) {
        if (id == null) {
            return null;
        }
        Order order = new Order();
        order.setId(id);
        return order;
    }
}
