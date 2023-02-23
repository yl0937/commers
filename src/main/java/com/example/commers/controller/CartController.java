package com.example.commers.controller;

import com.example.commers.config.secret.BaseResponse;
import com.example.commers.config.security.JwtAuthenticationFilter;
import com.example.commers.config.security.TokenProvider;
import com.example.commers.domain.CartProduct;
import com.example.commers.domain.Member;
import com.example.commers.repository.MemberRepository;
import com.example.commers.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.example.commers.config.secret.BaseResponseStatus.*;

@RestController
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final MemberRepository memberRepository;

    private final MemberController memberController;

    private final TokenProvider tokenProvider;


    @PostMapping("/add")
    public BaseResponse<?> addCart(HttpServletRequest request){
        String jwt = memberController.getmemberId(request);
        if(jwt==null){
            return new BaseResponse<>(EMPTY_JWT);
        }
        else{

            String userEmail = tokenProvider.getUsername(jwt);

            Optional<Member> member = memberRepository.findByEmail(userEmail);
            Integer userId = member.get().getId();

            cartService.addCart(userId);
            return new BaseResponse<>(SUCCESS);
        }
    }
    @DeleteMapping("/delete")
    public BaseResponse<?> delete(HttpServletRequest request, @RequestParam Integer id){
        String jwt = memberController.getmemberId(request);
        if(jwt==null){
            return new BaseResponse<>(EMPTY_JWT);
        }
        else{
            String userEmail = tokenProvider.getUsername(jwt);
            Optional<Member> member = memberRepository.findByEmail(userEmail);
            Integer userId = member.get().getId();

            if(userId.equals(cartService.searchCartbyId(id).get().getUserId())){
                cartService.deleteCart(id);
                return new BaseResponse<>(SUCCESS);
            }else {
                return new BaseResponse<>(NO_AUTH_TO_CART);
            }


        }

    }

    @GetMapping("/search")
    public BaseResponse<?> searchCart(HttpServletRequest request){
        String jwt = memberController.getmemberId(request);
        if(jwt==null){
            return new BaseResponse<>(EMPTY_JWT);
        }else {
            String userEmail = tokenProvider.getUsername(jwt);
            Optional<Member> member = memberRepository.findByEmail(userEmail);
            Integer userId = member.get().getId();

            if (cartService.searchCart(userId).size()==0){
                return new BaseResponse<>(NO_CART);
            }
            return new BaseResponse<>(cartService.searchCart(userId));
        }
    }

    @PostMapping("/product")
    public BaseResponse<?> addProductToCart(HttpServletRequest request, @RequestBody CartProduct cartProduct){
        String jwt = memberController.getmemberId(request);
        if(jwt==null){
            return new BaseResponse<>(EMPTY_JWT);
        }
        else{
            String userEmail = tokenProvider.getUsername(jwt);
            Optional<Member> member = memberRepository.findByEmail(userEmail);
            Integer userId = member.get().getId();

            if(userId.equals(cartService.searchCartbyId(cartProduct.getCartId()).get().getUserId())){
                cartService.addProductToCart(cartProduct);
            }else {
                return new BaseResponse<>(NO_AUTH_TO_CART);
            }
            return new BaseResponse<>(SUCCESS);

        }
    }

    @DeleteMapping("/product/delete")
    public BaseResponse<?> getProductList(HttpServletRequest request, @RequestParam Integer cartId ,Integer productId ){
        String jwt = memberController.getmemberId(request);
        if(jwt==null){
            return new BaseResponse<>(EMPTY_JWT);
        }
        else {
            String userEmail = tokenProvider.getUsername(jwt);
            Optional<Member> member = memberRepository.findByEmail(userEmail);
            Integer userId = member.get().getId();

            if (!userId.equals(cartService.searchCartbyId(cartId).get().getUserId())) {
                return new BaseResponse<>(NO_AUTH_TO_CART);

            } else {

                cartService.deleteProductToCart(cartId, productId);
                return new BaseResponse<>(SUCCESS);
            }
        }
    }

    @GetMapping("/productList")
    public BaseResponse<?> getProductList(HttpServletRequest request, @RequestParam Integer cartId){
        String jwt = memberController.getmemberId(request);
        if(jwt==null){
            return new BaseResponse<>(EMPTY_JWT);
        }
        else{
            String userEmail = tokenProvider.getUsername(jwt);
            Optional<Member> member = memberRepository.findByEmail(userEmail);
            Integer userId = member.get().getId();

            if(!userId.equals(cartService.searchCartbyId(cartId).get().getUserId())){
                return new BaseResponse<>(NO_AUTH_TO_CART);

            }else {
                return new BaseResponse<>(cartService.getProductListToCart(cartId));
            }
        }
    }

}
