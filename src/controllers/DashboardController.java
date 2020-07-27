package controllers;

import app.Path;
import app.View;
import dao.CartDao;
import dao.CategoryDao;
import dao.InventoryDao;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class DashboardController{
    private static InventoryDao inventoryDao = new InventoryDao();
    private static CategoryDao categoryDao = new CategoryDao();
    private static CartDao cartDao = new CartDao();

    public static Route serveViewDashboard = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        if(req.session().attribute("sortItem").equals(-1)){
            model.put("itemList", inventoryDao.getAllItems());
        }else{
            model.put("itemList", inventoryDao.getItemsByCategory(req.session().attribute("sortItem")));
        }
        model.put("qtyExceeded", req.session().attribute("qtyExceeded"));
        model.put("stockExceeded", req.session().attribute("stockExceeded"));
        model.put("totalPrice", cartDao.getTotal());
        model.put("cartItems", cartDao.getAllCartItems());
        model.put("currentCategory", req.session().attribute("sortItem"));
        model.put("categoryList", categoryDao.getAllCategory());
        model.put("type", req.session().attribute("type"));
        model.put("activeMenu", 1);
        model.put("username", req.session().attribute("username"));
        return View.render(req, model, Path.Template.DASHBOARD);
    };

    public static Route handleSortItem = (Request req, Response res) -> {
        Integer option = Integer.parseInt(req.queryParams("category"));
        req.session().attribute("sortItem", option);
        res.redirect(Path.Web.DASHBOARD);
        return null;
    };

    public static Route serveViewCart = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        return View.render(req, model, Path.Template.DASHBOARD);
    };
    public static Route handleAddCart = (Request req, Response res) -> {
        Integer id = Integer.parseInt(req.params("id"));
        Integer maxQty = inventoryDao.getStock(id);
        Integer currentQty = cartDao.getQuantityFromProduct(id);

        if(currentQty != null && maxQty <= currentQty){
            req.session().attribute("stockExceeded", true);
            res.redirect(Path.Web.DASHBOARD);
            return null;
        }
        if(cartDao.itemExist(id)){
            cartDao.AddQuantity(id);
        }else{
            cartDao.AddItem(id, 1);
        }
        req.session().attribute("stockExceeded", false);
        res.redirect(Path.Web.DASHBOARD);
        return null;
    };

    public static Route serveEditCart = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        return View.render(req, model, Path.Template.DASHBOARD);
    };
    public static Route handleEditCart = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        Integer product_id = Integer.parseInt(req.queryParams("id"));
        Integer maxQty = inventoryDao.getStock(product_id);

        Integer id = Integer.parseInt(req.params("id"));
        Integer qty = Integer.parseInt(req.queryParams("quantity"));
        if(maxQty < qty){
            req.session().attribute("qtyExceeded", true);
            res.redirect(Path.Web.DASHBOARD);
            return null;
        }
        req.session().attribute("qtyExceeded", false);
        cartDao.updateItem(id, qty);
        res.redirect(Path.Web.DASHBOARD);
        return null;
    };

    public static Route serveDeleteCart = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        return View.render(req, model, Path.Template.DASHBOARD);
    };
    public static Route handleDeleteCart = (Request req, Response res) -> {
        Integer id = Integer.parseInt(req.params("id"));
        cartDao.deleteItem(id);
        res.redirect(Path.Web.DASHBOARD);
        return null;
    };
}
