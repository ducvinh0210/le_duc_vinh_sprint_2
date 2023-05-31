package com.codegym.repository;

import com.codegym.dto.IShoeCartDto;
import com.codegym.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query(value = "select order_detail.id as id, shoe_size.id as shoeSizeId, shoe.name as name, size.name as size, shoe.price as price,\n" +
            " shoe.discount as discount, shoe.image as image, order_detail.quantity as quantity , order_detail.date_payment as date , shoe_size.quantity as quantityShoe\n" +
            " from order_detail \n" +
            " join shoe_size on order_detail.shoe_size_id= shoe_size.id \n" +
            " join size on shoe_size.size_id= size.id\n" +
            " join shoe on shoe_size.shoe_id= shoe.id\n" +
            " join customer on order_detail.customer_id= customer.id \n" +
            " where order_detail.is_delete= false and order_detail.is_pay= false and order_detail.quantity>0 and customer.id =:idCustomer", nativeQuery = true)
    List<IShoeCartDto> findCartByUser(@Param("idCustomer") Integer idCustomer);


    @Modifying
    @Query(value = " insert into order_detail(date_payment,quantity, customer_id, shoe_size_id) value(now(), :quantity, :customerId, :shoeSizeId)", nativeQuery = true)
    void addOrderDetailCart(@Param("quantity") Integer quantity,
                            @Param("customerId") Integer customerId,
                            @Param("shoeSizeId") Integer shoeSizeId);
}
