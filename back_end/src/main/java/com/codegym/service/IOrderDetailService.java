package com.codegym.service;

import com.codegym.dto.IShoeCartDto;

import java.util.List;

public interface IOrderDetailService {

    List<IShoeCartDto> findAllCartByUser(Integer idCustomer);

    void addOrderDetailCart(Integer quantity, Integer customerId, Integer shoeSizeId);

}
