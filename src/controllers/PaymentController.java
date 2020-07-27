package controllers;

import app.Path;
import app.View;
import dao.CartDao;
import dao.CategoryDao;
import dao.InventoryDao;
import model.CartItem;
import spark.Request;
import spark.Response;
import spark.Route;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentController{
    private static InventoryDao inventoryDao = new InventoryDao();
    private static CategoryDao categoryDao = new CategoryDao();
    private static CartDao cartDao = new CartDao();

    public static Route serveViewPayment = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();

        model.put("moneyInsufficient", req.session().attribute("noMoney"));
        model.put("totalPay", cartDao.getTotal());
        model.put("cartItems", cartDao.getAllCartItems());
        model.put("type", req.session().attribute("type"));
        model.put("activeMenu", 1);
        model.put("username", req.session().attribute("username"));
        return View.render(req, model, Path.Template.PAYMENT);
    };
    public static Route handlePayment = (Request req, Response res) -> {
        Integer mustPay = cartDao.getTotal();
        Integer givenMoney = Integer.parseInt(req.queryParams("payMoney"));
        if(mustPay > givenMoney){
            req.session().attribute("noMoney", true);
            res.redirect(Path.Web.PAYMENT);
            return null;
        }
        cartDao.flushItems();
        req.session().attribute("noMoney", false);
        req.session().attribute("changeMoney", givenMoney - mustPay);
        res.redirect(Path.Web.PAYMENT_SUCCESS);
        return null;
    };

    public static Route serveViewPaymentSuccess = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();

        model.put("transactionTime", LocalDateTime.now());
        model.put("changeMoney", req.session().attribute("changeMoney"));
        model.put("type", req.session().attribute("type"));
        model.put("activeMenu", 1);
        model.put("username", req.session().attribute("username"));
        return View.render(req, model, Path.Template.PAYMENT_SUCCESS);
    };
}
