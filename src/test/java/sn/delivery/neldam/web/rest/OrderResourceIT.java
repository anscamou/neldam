package sn.delivery.neldam.web.rest;

import sn.delivery.neldam.NeldamApp;
import sn.delivery.neldam.domain.Order;
import sn.delivery.neldam.repository.OrderRepository;
import sn.delivery.neldam.service.OrderService;
import sn.delivery.neldam.service.dto.OrderDTO;
import sn.delivery.neldam.service.mapper.OrderMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import sn.delivery.neldam.domain.enumeration.OrderStatus;
/**
 * Integration tests for the {@link OrderResource} REST controller.
 */
@SpringBootTest(classes = NeldamApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OrderResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Double DEFAULT_LAT_FROM = 1D;
    private static final Double UPDATED_LAT_FROM = 2D;

    private static final Double DEFAULT_LONG_FROM = 1D;
    private static final Double UPDATED_LONG_FROM = 2D;

    private static final String DEFAULT_ADDR_FROM = "AAAAAAAAAA";
    private static final String UPDATED_ADDR_FROM = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_TO = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_TO = "BBBBBBBBBB";

    private static final Double DEFAULT_LAT_TO = 1D;
    private static final Double UPDATED_LAT_TO = 2D;

    private static final Double DEFAULT_LONG_TO = 1D;
    private static final Double UPDATED_LONG_TO = 2D;

    private static final String DEFAULT_ADDR_TO = "AAAAAAAAAA";
    private static final String UPDATED_ADDR_TO = "BBBBBBBBBB";

    private static final OrderStatus DEFAULT_ORDER_STATUS = OrderStatus.VALIDATED;
    private static final OrderStatus UPDATED_ORDER_STATUS = OrderStatus.COMPLETED;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrderMockMvc;

    private Order order;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Order createEntity(EntityManager em) {
        Order order = new Order()
            .description(DEFAULT_DESCRIPTION)
            .latFrom(DEFAULT_LAT_FROM)
            .longFrom(DEFAULT_LONG_FROM)
            .addrFrom(DEFAULT_ADDR_FROM)
            .phoneTo(DEFAULT_PHONE_TO)
            .latTo(DEFAULT_LAT_TO)
            .longTo(DEFAULT_LONG_TO)
            .addrTo(DEFAULT_ADDR_TO)
            .orderStatus(DEFAULT_ORDER_STATUS);
        return order;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Order createUpdatedEntity(EntityManager em) {
        Order order = new Order()
            .description(UPDATED_DESCRIPTION)
            .latFrom(UPDATED_LAT_FROM)
            .longFrom(UPDATED_LONG_FROM)
            .addrFrom(UPDATED_ADDR_FROM)
            .phoneTo(UPDATED_PHONE_TO)
            .latTo(UPDATED_LAT_TO)
            .longTo(UPDATED_LONG_TO)
            .addrTo(UPDATED_ADDR_TO)
            .orderStatus(UPDATED_ORDER_STATUS);
        return order;
    }

    @BeforeEach
    public void initTest() {
        order = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrder() throws Exception {
        int databaseSizeBeforeCreate = orderRepository.findAll().size();
        // Create the Order
        OrderDTO orderDTO = orderMapper.toDto(order);
        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isCreated());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeCreate + 1);
        Order testOrder = orderList.get(orderList.size() - 1);
        assertThat(testOrder.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testOrder.getLatFrom()).isEqualTo(DEFAULT_LAT_FROM);
        assertThat(testOrder.getLongFrom()).isEqualTo(DEFAULT_LONG_FROM);
        assertThat(testOrder.getAddrFrom()).isEqualTo(DEFAULT_ADDR_FROM);
        assertThat(testOrder.getPhoneTo()).isEqualTo(DEFAULT_PHONE_TO);
        assertThat(testOrder.getLatTo()).isEqualTo(DEFAULT_LAT_TO);
        assertThat(testOrder.getLongTo()).isEqualTo(DEFAULT_LONG_TO);
        assertThat(testOrder.getAddrTo()).isEqualTo(DEFAULT_ADDR_TO);
        assertThat(testOrder.getOrderStatus()).isEqualTo(DEFAULT_ORDER_STATUS);
    }

    @Test
    @Transactional
    public void createOrderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderRepository.findAll().size();

        // Create the Order with an existing ID
        order.setId(1L);
        OrderDTO orderDTO = orderMapper.toDto(order);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLatFromIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setLatFrom(null);

        // Create the Order, which fails.
        OrderDTO orderDTO = orderMapper.toDto(order);


        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLongFromIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setLongFrom(null);

        // Create the Order, which fails.
        OrderDTO orderDTO = orderMapper.toDto(order);


        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPhoneToIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setPhoneTo(null);

        // Create the Order, which fails.
        OrderDTO orderDTO = orderMapper.toDto(order);


        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLatToIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setLatTo(null);

        // Create the Order, which fails.
        OrderDTO orderDTO = orderMapper.toDto(order);


        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLongToIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setLongTo(null);

        // Create the Order, which fails.
        OrderDTO orderDTO = orderMapper.toDto(order);


        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrderStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = orderRepository.findAll().size();
        // set the field null
        order.setOrderStatus(null);

        // Create the Order, which fails.
        OrderDTO orderDTO = orderMapper.toDto(order);


        restOrderMockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrders() throws Exception {
        // Initialize the database
        orderRepository.saveAndFlush(order);

        // Get all the orderList
        restOrderMockMvc.perform(get("/api/orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(order.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].latFrom").value(hasItem(DEFAULT_LAT_FROM.doubleValue())))
            .andExpect(jsonPath("$.[*].longFrom").value(hasItem(DEFAULT_LONG_FROM.doubleValue())))
            .andExpect(jsonPath("$.[*].addrFrom").value(hasItem(DEFAULT_ADDR_FROM)))
            .andExpect(jsonPath("$.[*].phoneTo").value(hasItem(DEFAULT_PHONE_TO)))
            .andExpect(jsonPath("$.[*].latTo").value(hasItem(DEFAULT_LAT_TO.doubleValue())))
            .andExpect(jsonPath("$.[*].longTo").value(hasItem(DEFAULT_LONG_TO.doubleValue())))
            .andExpect(jsonPath("$.[*].addrTo").value(hasItem(DEFAULT_ADDR_TO)))
            .andExpect(jsonPath("$.[*].orderStatus").value(hasItem(DEFAULT_ORDER_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getOrder() throws Exception {
        // Initialize the database
        orderRepository.saveAndFlush(order);

        // Get the order
        restOrderMockMvc.perform(get("/api/orders/{id}", order.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(order.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.latFrom").value(DEFAULT_LAT_FROM.doubleValue()))
            .andExpect(jsonPath("$.longFrom").value(DEFAULT_LONG_FROM.doubleValue()))
            .andExpect(jsonPath("$.addrFrom").value(DEFAULT_ADDR_FROM))
            .andExpect(jsonPath("$.phoneTo").value(DEFAULT_PHONE_TO))
            .andExpect(jsonPath("$.latTo").value(DEFAULT_LAT_TO.doubleValue()))
            .andExpect(jsonPath("$.longTo").value(DEFAULT_LONG_TO.doubleValue()))
            .andExpect(jsonPath("$.addrTo").value(DEFAULT_ADDR_TO))
            .andExpect(jsonPath("$.orderStatus").value(DEFAULT_ORDER_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingOrder() throws Exception {
        // Get the order
        restOrderMockMvc.perform(get("/api/orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrder() throws Exception {
        // Initialize the database
        orderRepository.saveAndFlush(order);

        int databaseSizeBeforeUpdate = orderRepository.findAll().size();

        // Update the order
        Order updatedOrder = orderRepository.findById(order.getId()).get();
        // Disconnect from session so that the updates on updatedOrder are not directly saved in db
        em.detach(updatedOrder);
        updatedOrder
            .description(UPDATED_DESCRIPTION)
            .latFrom(UPDATED_LAT_FROM)
            .longFrom(UPDATED_LONG_FROM)
            .addrFrom(UPDATED_ADDR_FROM)
            .phoneTo(UPDATED_PHONE_TO)
            .latTo(UPDATED_LAT_TO)
            .longTo(UPDATED_LONG_TO)
            .addrTo(UPDATED_ADDR_TO)
            .orderStatus(UPDATED_ORDER_STATUS);
        OrderDTO orderDTO = orderMapper.toDto(updatedOrder);

        restOrderMockMvc.perform(put("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isOk());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeUpdate);
        Order testOrder = orderList.get(orderList.size() - 1);
        assertThat(testOrder.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testOrder.getLatFrom()).isEqualTo(UPDATED_LAT_FROM);
        assertThat(testOrder.getLongFrom()).isEqualTo(UPDATED_LONG_FROM);
        assertThat(testOrder.getAddrFrom()).isEqualTo(UPDATED_ADDR_FROM);
        assertThat(testOrder.getPhoneTo()).isEqualTo(UPDATED_PHONE_TO);
        assertThat(testOrder.getLatTo()).isEqualTo(UPDATED_LAT_TO);
        assertThat(testOrder.getLongTo()).isEqualTo(UPDATED_LONG_TO);
        assertThat(testOrder.getAddrTo()).isEqualTo(UPDATED_ADDR_TO);
        assertThat(testOrder.getOrderStatus()).isEqualTo(UPDATED_ORDER_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingOrder() throws Exception {
        int databaseSizeBeforeUpdate = orderRepository.findAll().size();

        // Create the Order
        OrderDTO orderDTO = orderMapper.toDto(order);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderMockMvc.perform(put("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Order in the database
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrder() throws Exception {
        // Initialize the database
        orderRepository.saveAndFlush(order);

        int databaseSizeBeforeDelete = orderRepository.findAll().size();

        // Delete the order
        restOrderMockMvc.perform(delete("/api/orders/{id}", order.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
