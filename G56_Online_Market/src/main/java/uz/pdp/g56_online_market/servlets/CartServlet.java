package uz.pdp.g56_online_market.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.g56_online_market.entities.Cart;
import uz.pdp.g56_online_market.entities.CartItems;
import uz.pdp.g56_online_market.entities.Products;
import uz.pdp.g56_online_market.entities.User;
import uz.pdp.g56_online_market.services.CartService;
import uz.pdp.g56_online_market.services.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private final CartService cartService = new CartService();
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Sessiondan userni olish
        User user = (User) req.getSession().getAttribute("currentUser");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        List<CartItems> cartItems = cartService.getCartItems(user);
        req.setAttribute("cartItems", cartItems);

        // umumiy narx
        req.setAttribute("totalPrice", cartService.getCartTotal(user));

        req.getRequestDispatcher("/views/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("currentUser");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String action = req.getParameter("action");
        int productId = Integer.parseInt(req.getParameter("productId"));

        Products product = productService.getProductById(productId);

        if (action != null) {
            switch (action) {
                case "add" -> cartService.addProductToCart(user, product);
                case "decrease" -> cartService.decreaseProductToCart(user, product);
                case "remove" -> cartService.removeProductFromCart(user, product);
                case "clear" -> cartService.clearCart(user);
            }
        }

        // qayta savat sahifasiga yuborish
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}