package com.example.demo.services;

import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImplementation implements CheckoutService{

    private CustomerRepository customerRepository;
    private CartRepository cartRepository;

    @Autowired
    public  CheckoutServiceImplementation(CustomerRepository customerRepository, CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse checkOut(Purchase purchase) {

        //retrieve order info from dto
        Cart cart = purchase.getCart();

        // populate cart with cartItems
        Set<CartItem> cartItems = purchase.getCartItems();
        cartItems.forEach(cartItem -> {
            cartItem.setCart(cart);
            cart.add(cartItem);
        });

        // populate customer with cart
        Customer customer = purchase.getCustomer();
        cart.setCustomer(customer);
        customer.add(cart);

        // VALIDATION FOR EMPTY CARTS. WILL MAKE SURE TRACKING NUMBER WON'T BE GENERATED FOR AN EMPTY CART
        if (cartItems.isEmpty()){
            System.out.println("ITS EMPTY");
            return new PurchaseResponse("PURCHASE FAILED: CART IS EMPTY");
        }

        // VALIDATION FOR PARTY SIZE IS AT LEAST 1
        if (cart.getParty_size() < 1){
            // System.out.println("PARTY SIZE IS LESS THAN 1");
            return new PurchaseResponse("PURCHASE FAILED: PARTY SIZE CANNOT BE LESS THAN 1");
        }

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);


        // save to database
        cart.setStatus(Cart.StatusType.ordered);
        cartRepository.save(cart);

        // return something
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber(){

        // generate a random UUID number (UUID version 4)
        return UUID.randomUUID().toString();
    }
}
