package com.codegym.repository;

import com.codegym.dto.IShoeDto;
import com.codegym.model.Shoe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IShoeRepository extends JpaRepository<Shoe, Integer> {

    @Query(value = " select shoe.id as id , shoe.name as name , shoe.price as price, shoe.discount as discount , shoe.image as image, shoe.describes as describes , \n" +
            " shoe.manufacturer as manufacturer,  shoe_type.name as type from shoe \n" +
            " join shoe_type on shoe.shoe_type_id= shoe_type.id \n" +
            " join shoe_size on shoe_size.shoe_id= shoe.id \n" +
            " where shoe.name like %:nameShoe% and shoe_type.name like %:typeShoe% and shoe.manufacturer like %:manufacturerShoe% \n" +
            " and (shoe.price between :priceStart and :priceEnd) \n" +
            " group by shoe.id \n" +
            " having sum(shoe_size.quantity)>0",
            countQuery = "select count(*) from shoe \n" +
                    " join shoe_type on shoe.shoe_type_id= shoe_type.id \n" +
                    " join shoe_size on shoe_size.shoe_id= shoe.id \n" +
                    " where shoe.name like %:nameShoe%  and shoe_type.name like  %:typeShoe% and shoe.manufacturer like %:manufacturerShoe% \n" +
                    " and (shoe.price between :priceStart and :priceEnd) \n" +
                    " group by shoe.id \n" +
                    " having sum(shoe_size.quantity)>0", nativeQuery = true)
    Page<IShoeDto> showListShoe(@Param("nameShoe") String nameShoe,
                                @Param("typeShoe") String typeShoe,
                                @Param("manufacturerShoe") String manufacturerShoe,
                                @Param("priceStart") Integer priceStart,
                                @Param("priceEnd") Integer priceEnd,
                                Pageable pageable);


    @Query(value = "  select shoe.manufacturer  from  shoe  group by shoe.manufacturer ", nativeQuery = true)
    List<String> findAllManufacturerShoe();


    @Query(value = "select shoe.id as id , shoe.name as name, shoe.describes as describes , shoe.discount as discount ,\n" +
            " shoe.price as price, shoe.image as image , shoe.manufacturer as manufacturer, sum(shoe_size.quantity) as sumQuantity, shoe_type.name as type\n" +
            " from shoe join shoe_size on shoe_size.shoe_id = shoe.id join shoe_type on shoe.shoe_type_id= shoe_type.id \n" +
            " where shoe.is_delete = false and shoe.id =:id group by shoe.id having sum(shoe_size.quantity)>0", nativeQuery = true)
    IShoeDto findShoeById(@Param("id") Integer id);


    @Query(value = "select shoe_size.quantity from shoe join shoe_size on shoe_size.shoe_id = shoe.id join size on shoe_size.size.id = size.id" +
            " where size.id= :idSize and shoe.id = :idShoe", nativeQuery = true)
    Integer findByIdSize(@Param("idSize") Integer idSize,
                         @Param("idShoe") Integer idShoe);
}
