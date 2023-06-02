package com.codegym.service.impl;


import com.codegym.dto.IShoeCartDto;
import com.codegym.repository.IOrderDetailRepository;
import com.codegym.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService implements IOrderDetailService {

    @Autowired
    private IOrderDetailRepository iOrderDetailRepository;

    @Override
    public List<IShoeCartDto> findAllCartByUser(Integer idCustomer) {
        return iOrderDetailRepository.findCartByUser(idCustomer);
    }

    @Override
    public void addOrderDetailCart(Integer quantity, Integer customerId, Integer shoeSizeId) {

        List<IShoeCartDto> listShoeCart = findAllCartByUser(customerId);
        for (IShoeCartDto iShoeCartDto : listShoeCart) {
            if (iShoeCartDto.getShoeSizeId().equals(shoeSizeId)) {
                if (quantity + iShoeCartDto.getQuantity() > iShoeCartDto.getQuantityShoe()) {
                    return;
                } else {
                    iOrderDetailRepository.updateQuantityOrderDetail(quantity + iShoeCartDto.getQuantity(), iShoeCartDto.getId());
                    return;
                }
            }
        }
        iOrderDetailRepository.addOrderDetailCart(quantity, customerId, shoeSizeId);
    }

    @Override
    public void descQuantity(Integer id) {
        iOrderDetailRepository.descQuantity(id);
    }

    @Override
    public void ascQuantity(Integer id) {
        iOrderDetailRepository.ascQuantity(id);
    }

    @Override
    public void removeCart(Integer id) {
        iOrderDetailRepository.removeCart(id);
    }

    @Override
    public void paymentShoe(Integer id) {
        iOrderDetailRepository.paymentShoe(id);
    }

    @Override
    public Page<IShoeCartDto> findAllHistoryCart(Integer id, Pageable pageable) {
        return iOrderDetailRepository.findAllHistoryCart(id, pageable);
    }


}
