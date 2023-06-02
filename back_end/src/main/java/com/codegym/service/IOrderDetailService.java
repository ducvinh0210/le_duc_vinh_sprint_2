package com.codegym.service;

import com.codegym.dto.IShoeCartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderDetailService {

    List<IShoeCartDto> findAllCartByUser(Integer idCustomer);

    void addOrderDetailCart(Integer quantity, Integer customerId, Integer shoeSizeId);

    void descQuantity(Integer id);

    void ascQuantity(Integer id);

    void removeCart(Integer id);

    void paymentShoe(Integer id);

    Page<IShoeCartDto> findAllHistoryCart(Integer id, Pageable pageable);

}
