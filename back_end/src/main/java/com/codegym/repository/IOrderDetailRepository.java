package com.codegym.repository;

import com.codegym.dto.IShoeCartDto;
import com.codegym.model.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
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


    @Modifying
    @Query(value = " update order_detail set quantity = :quantityOrderDetail where id = :idOrderDetail", nativeQuery = true)
    void updateQuantityOrderDetail(@Param("quantityOrderDetail") Integer quantityOrderDetail,
                                   @Param("idOrderDetail") Integer idOrderDetail);


    @Modifying
    @Query(value = "update order_detail set quantity= (quantity-1) where is_pay =false and id= :id", nativeQuery = true)
    void descQuantity(@Param("id") Integer id);

    @Modifying
    @Query(value = "update order_detail set quantity= (quantity+1) where  is_pay= false  and id= :id", nativeQuery = true)
    void ascQuantity(@Param("id") Integer id);

    @Modifying
    @Query(value = "delete from order_detail where  id=:id", nativeQuery = true)
    void removeCart(@Param("id") Integer id);


    @Modifying
    @Query(value = "update order_detail set date_payment= now(), is_pay= true where customer_id =:id  and is_pay=false ", nativeQuery = true)
    void paymentShoe(@Param("id") Integer id);


    @Query(value = "select order_detail.id as id, shoe.name as name, size.name as `size`, shoe.price as price, shoe.discount as discount, shoe.image as image," +
            " order_detail.quantity as quantity, order_detail.date_payment as `date` from order_detail join shoe_size on order_detail.shoe_size_id= shoe_size.id" +
            " join shoe on shoe_size.shoe_id= shoe.id join `size` on shoe_size.size_id = `size`.id  where order_detail.is_pay =true and order_detail.is_delete=false" +
            " and order_detail.quantity>0 and order_detail.customer_id= :id order by order_detail.date_payment desc", nativeQuery = true)
    Page<IShoeCartDto> findAllHistoryCart(@Param("id") Integer id,
                                          Pageable pageable);
}
