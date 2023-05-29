package com.codegym.controller;


import com.codegym.dto.IShoeDto;
import com.codegym.jwt.JwtTokenUtil;
import com.codegym.model.ShoeType;
import com.codegym.model.User;
import com.codegym.payload.request.LoginRequest;
import com.codegym.payload.request.LoginResponse;
import com.codegym.service.IShoeService;
import com.codegym.service.IShoeTypeService;
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

    @GetMapping("/list-newest/{nameShoe}&{typeShoe}&{priceStart}&{priceEnd}")
    public ResponseEntity<Page<IShoeDto>> showListShoeNewest(@PathVariable("nameShoe") String nameShoe,
                                                             @PathVariable("typeShoe") String typeShoe,
                                                             @PathVariable("priceStart") Integer priceStart,
                                                             @PathVariable("priceEnd") Integer priceEnd,
                                                             @PageableDefault(value = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<IShoeDto> listShoe = iShoeService.showListShoe(nameShoe, typeShoe, priceStart, priceEnd, pageable);
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

}
