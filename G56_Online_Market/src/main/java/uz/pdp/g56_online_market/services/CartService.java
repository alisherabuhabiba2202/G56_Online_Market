package uz.pdp.g56_online_market.services;

import uz.pdp.g56_online_market.daos.CartDAO;
import uz.pdp.g56_online_market.daos.CartItemsDAO;
import uz.pdp.g56_online_market.entities.Cart;
import uz.pdp.g56_online_market.entities.CartItems;
import uz.pdp.g56_online_market.entities.Products;
import uz.pdp.g56_online_market.entities.User;

import java.math.BigDecimal;
import java.util.List;


public class CartService {

    private final CartDAO cartDAO = new CartDAO();
    private final CartItemsDAO cartItemsDAO = new CartItemsDAO();

    public Cart getOrCreateCart(User user) {
        Cart cart = cartDAO.findByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cartDAO.save(cart);
        }
        return cart;

    }

    public void addProductToCart(User user, Products product) {
        Cart cart = getOrCreateCart(user);

        CartItems cartItems = cartItemsDAO.findByCartAndProduct(cart, product);

        if ( cartItems != null) {
            cartItems.setQuantity(cartItems.getQuantity()+1);
            cartItemsDAO.update(cartItems);
        } else {

            cartItems = new CartItems();
            cartItems.setCart(cart);
            cartItems.setProducts(product);
            cartItems.setQuantity(1);
            cartItemsDAO.save(cartItems);
        }

    }

    public void decreaseProductToCart(User user, Products products) {

        Cart cart = getOrCreateCart(user);

        CartItems cartItems = cartItemsDAO.findByCartAndProduct(cart,products);

        if (cartItems != null){
            if (cartItems.getQuantity()>1){
                cartItems.setQuantity(cartItems.getQuantity()-1);
                cartItemsDAO.update(cartItems);
            }
            else {
                cartItemsDAO.delete(cartItems);
            }
        }
    }

    public void clearCart(User user) {
        Cart cart = getOrCreateCart(user);
        for (CartItems cartItems : cartItemsDAO.findByCart(cart)) {
            cartItemsDAO.delete(cartItems);
        }
    }

    public void removeProductFromCart(User user, Products product) {
        Cart cart = getOrCreateCart(user);
        CartItems cartItem = cartItemsDAO.findByCartAndProduct(cart, product);

        if (cartItem != null) {
            cartItemsDAO.delete(cartItem);
        }
    }

    public List<CartItems> getCartItems(User user) {

        Cart cart = getOrCreateCart(user);
        return cartItemsDAO.findByCart(cart);
    }

    public BigDecimal getCartTotal(User user) {
        Cart cart = getOrCreateCart(user);
        List<CartItems> items = cartItemsDAO.findByCart(cart);

        BigDecimal total = BigDecimal.ZERO;

        for (CartItems item : items) {
            BigDecimal price = BigDecimal.valueOf(item.getProducts().getPrice()); // mahsulot narxi
            BigDecimal quantity = BigDecimal.valueOf(item.getQuantity());
            total = total.add(price.multiply(quantity));
        }
        return total;
    }

}
