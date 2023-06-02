package com.codegym.repository;

import com.codegym.dto.IShoeSizeDto;
import com.codegym.dto.Quantity;
import com.codegym.model.ShoeSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Transactional
public interface IShoeSizeRepository extends JpaRepository<ShoeSize, Integer> {
    @Query(value = "select shoe_size.id as id, shoe_size.quantity as quantity, size.id as idSize, size.name as size from shoe_size join size on shoe_size.size_id= size.id\n" +
            " join shoe on shoe_size.shoe_id= shoe.id where shoe.id=:id and shoe.is_delete = false", nativeQuery = true)
    List<IShoeSizeDto> findAllSizeByShoe(@Param("id") Integer id);

    @Modifying
    @Query(value = "update shoe_size set quantity = :quantity where id =:id", nativeQuery = true)
    void updateQuantityShoeSize(@Param("quantity") Integer quantity,
                                @Param("id") Integer id);


    @Query(value = "select order_detail.quantity as quantity, shoe_size.quantity as quantityShoeSize, shoe_size.id as shoeSizeId from shoe_size join " +
            " order_detail on order_detail.shoe_size_id= shoe_size.id where order_detail.customer_id = :customerId and order_detail.is_pay=false ", nativeQuery = true)
    List<Quantity> findAllShoeSize(@Param("customerId") Integer customerId);

}
