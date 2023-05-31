package com.codegym.controller;


import com.codegym.dto.IShoeCartDto;
import com.codegym.dto.IShoeDto;
import com.codegym.dto.IShoeSizeDto;
import com.codegym.jwt.JwtTokenUtil;
import com.codegym.model.Customer;
import com.codegym.model.OrderDetail;
import com.codegym.model.ShoeType;
import com.codegym.model.User;
import com.codegym.payload.request.LoginRequest;
import com.codegym.payload.request.LoginResponse;
import com.codegym.repository.IShoeRepository;
import com.codegym.service.*;
import com.codegym.service.security.impl.MyUserDetails;
import com.codegym.service.security.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shoes")
@CrossOrigin("*")
public class ShoeController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private IShoeService iShoeService;

    @Autowired
    private IShoeTypeService iShoeTypeService;

    @Autowired
    private IOrderDetailService iOrderDetailService;

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private IShoeSizeService iShoeSizeService;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(loginRequest.getUsername());
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> roles = myUserDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new LoginResponse(jwt, myUserDetails.getUsername(), roles));
    }


    @GetMapping("/findUsername")
    public ResponseEntity<?> showUsername(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        String username = jwtTokenUtil.getUsernameFromJwtToken(headerAuth.substring(7));
        Optional<User> user = userService.showUsername(username);
        if (user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list-newest/{nameShoe}&{typeShoe}&{manufacturerShoe}&{priceStart}&{priceEnd}")
    public ResponseEntity<Page<IShoeDto>> showListShoeNewest(@PathVariable("nameShoe") String nameShoe,
                                                             @PathVariable("typeShoe") String typeShoe,
                                                             @PathVariable("manufacturerShoe") String manufacturerShoe,
                                                             @PathVariable("priceStart") Integer priceStart,
                                                             @PathVariable("priceEnd") Integer priceEnd,
                                                             @PageableDefault(value = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<IShoeDto> listShoe = iShoeService.showListShoe(nameShoe, typeShoe, manufacturerShoe, priceStart, priceEnd, pageable);
        return new ResponseEntity<>(listShoe, HttpStatus.OK);
    }

    @GetMapping("/list-discount/{nameShoe}&{typeShoe}&{manufacturerShoe}&{priceStart}&{priceEnd}")
    public ResponseEntity<Page<IShoeDto>> showListShoeDiscount(@PathVariable("nameShoe") String nameShoe,
                                                               @PathVariable("typeShoe") String typeShoe,
                                                               @PathVariable("manufacturerShoe") String manufacturerShoe,
                                                               @PathVariable("priceStart") Integer priceStart,
                                                               @PathVariable("priceEnd") Integer priceEnd,
                                                               @PageableDefault(value = 6, sort = "discount", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<IShoeDto> listShoe = iShoeService.showListShoe(nameShoe, typeShoe, manufacturerShoe, priceStart, priceEnd, pageable);
        return new ResponseEntity<>(listShoe, HttpStatus.OK);
    }

    @GetMapping("/list-price-desc/{nameShoe}&{typeShoe}&{manufacturerShoe}&{priceStart}&{priceEnd}")
    public ResponseEntity<Page<IShoeDto>> showListShoePriceDesc(@PathVariable("nameShoe") String nameShoe,
                                                                @PathVariable("typeShoe") String typeShoe,
                                                                @PathVariable("manufacturerShoe") String manufacturerShoe,
                                                                @PathVariable("priceStart") Integer priceStart,
                                                                @PathVariable("priceEnd") Integer priceEnd,
                                                                @PageableDefault(value = 6, sort = "price", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<IShoeDto> listShoe = iShoeService.showListShoe(nameShoe, typeShoe, manufacturerShoe, priceStart, priceEnd, pageable);
        return new ResponseEntity<>(listShoe, HttpStatus.OK);
    }

    @GetMapping("/list-price-asc/{nameShoe}&{typeShoe}&{manufacturerShoe}&{priceStart}&{priceEnd}")
    public ResponseEntity<Page<IShoeDto>> showListShoePriceAsc(@PathVariable("nameShoe") String nameShoe,
                                                               @PathVariable("typeShoe") String typeShoe,
                                                               @PathVariable("manufacturerShoe") String manufacturerShoe,
                                                               @PathVariable("priceStart") Integer priceStart,
                                                               @PathVariable("priceEnd") Integer priceEnd,
                                                               @PageableDefault(value = 6, sort = "price", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<IShoeDto> listShoe = iShoeService.showListShoe(nameShoe, typeShoe, manufacturerShoe, priceStart, priceEnd, pageable);
        return new ResponseEntity<>(listShoe, HttpStatus.OK);
    }

    @GetMapping("/list-shoe-type")
    public ResponseEntity<List<ShoeType>> showListShoeType() {
        List<ShoeType> shoeTypeList = iShoeTypeService.findAllShoeType();
        if (shoeTypeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(shoeTypeList, HttpStatus.OK);
    }

    @GetMapping("/list-manufacturer")
    public ResponseEntity<List<String>> showListManufacturer() {
        List<String> listManufacturer = iShoeService.findAllManufacturerShoe();
        if (listManufacturer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(listManufacturer, HttpStatus.OK);
    }


    @GetMapping("/cart/{id}")
    public ResponseEntity<List<IShoeCartDto>> showListCartByUser(@PathVariable("id") Integer id) {
        List<IShoeCartDto> iShoeCartDtoList = iOrderDetailService.findAllCartByUser(id);
        if (iShoeCartDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(iShoeCartDtoList, HttpStatus.OK);
    }


    @GetMapping("/get-customer/{username}")
    public ResponseEntity<Customer> findCustomerByUser(@PathVariable("username") String username) {
        Optional<Customer> customer = iCustomerService.findCustomerByUser(username);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping("/detail-shoe/{id}")
    public ResponseEntity<IShoeDto> findShoeById(@PathVariable("id") Integer id) {
        IShoeDto iShoeDto = iShoeService.findShoeById(id);
        if (iShoeDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(iShoeDto, HttpStatus.OK);
    }

    @GetMapping("/shoe-size/{id}")
    public ResponseEntity<List<IShoeSizeDto>> findAllSizeByShoe(@PathVariable("id") Integer id) {
        List<IShoeSizeDto> shoeSizeDtoList = iShoeSizeService.findSizeByShoe(id);
        if (shoeSizeDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(shoeSizeDtoList, HttpStatus.OK);
    }

    @GetMapping("add-cart/{quantity}/{customerId}/{shoeSizeId}")
    public ResponseEntity<OrderDetail> addToCart(@PathVariable("quantity") Integer quantity,
                                                 @PathVariable("customerId") Integer customerId,
                                                 @PathVariable("shoeSizeId") Integer shoeSizeId) {
        iOrderDetailService.addOrderDetailCart(quantity, customerId, shoeSizeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("quantity-size/{idSize}/{idShoe}")
    public ResponseEntity<Integer> quantityShoeSizeBySize(@PathVariable("idSize") Integer idSize,
                                                          @PathVariable("idShie") Integer idShoe) {
        Integer quantityShoeBySize = iShoeService.findByIdSize(idSize, idShoe);
        return new ResponseEntity<>(quantityShoeBySize, HttpStatus.OK);
    }

}
