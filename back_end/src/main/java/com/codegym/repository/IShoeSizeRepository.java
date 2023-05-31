package com.codegym.repository;

import com.codegym.dto.IShoeSizeDto;
import com.codegym.model.ShoeSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface IShoeSizeRepository extends JpaRepository<ShoeSize,Integer> {
    @Query(value = "select shoe_size.id as id, shoe_size.quantity as quantity, size.id as idSize, size.name as size from shoe_size join size on shoe_size.size_id= size.id\n" +
            " join shoe on shoe_size.shoe_id= shoe.id where shoe.id=:id and shoe.is_delete = false", nativeQuery = true)
    List<IShoeSizeDto> findAllSizeByShoe(@Param("id") Integer id);

}
