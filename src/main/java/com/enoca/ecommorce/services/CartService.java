package com.enoca.ecommorce.services;

import com.enoca.ecommorce.dto.request.UpdateCartRequest;
import com.enoca.ecommorce.dto.response.getCartResponse;
import com.enoca.ecommorce.entities.concretes.Cart;
import com.enoca.ecommorce.entities.concretes.Product;
import com.enoca.ecommorce.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final ProductService productService;

    public void addProduct(Long cartId, Long productId) {
        cartRepository.findById(cartId).ifPresent(cart -> {
            Product product = modelMapper
                    .map(productService.getProduct(productId), Product.class);
            cart.getProducts().add(product);
            updateCartTotalPrice(cart);
            cartRepository.save(cart);
        });
    }

    public getCartResponse getCart(Long cartId) {
        return cartRepository.findById(cartId)
                .map(cart -> modelMapper.map(cart, getCartResponse.class))
                .orElse(null);
    }

    public void updateCart(UpdateCartRequest request) {
        cartRepository.findById(request.getCartId()).ifPresent(cart -> {
            cart.getProducts().clear();
            request.getProducts().forEach(updateCartProductRequest -> {
                Product product = modelMapper
                        .map(productService.getProduct(updateCartProductRequest.getProductId()), Product.class);
                cart.getProducts().add(product);
            });
            updateCartTotalPrice(cart);
            cartRepository.save(cart);
        });
    }

    public void emptyCart(Long cartId) {
        cartRepository.findById(cartId).ifPresent(cart -> {
            cart.getProducts().clear();
            updateCartTotalPrice(cart);
            cartRepository.save(cart);
        });
    }

    public void removeProduct(Long cartId, Long productId) {
        cartRepository.findById(cartId).ifPresent(cart -> {
            cart.getProducts().removeIf(product -> product.getId().equals(productId));
            updateCartTotalPrice(cart);
            cartRepository.save(cart);
        });
    }

    protected Optional<Cart> getCartByUserId(Long userId){
        return cartRepository.findByCustomerId(userId);
    }

    private Cart updateCartTotalPrice(Cart cart){
        double totalPrice = cart.getProducts().stream().mapToDouble(Product::getPrice).sum();
        cart.setTotalPrice(totalPrice);
        return cart;
    }
}
