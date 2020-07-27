package controllers;

import app.Path;
import app.View;
import dao.CategoryDao;
import dao.InventoryDao;
import model.Category;
import model.Item;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryController{
    private static InventoryDao inventoryDao = new InventoryDao();
    private static CategoryDao categoryDao = new CategoryDao();

    public static Route serveViewInventory = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        List<Item> items = inventoryDao.getAllItems();

        model.put("itemList", items);
        model.put("type", req.session().attribute("type"));
        model.put("activeMenu", 2);
        model.put("username", req.session().attribute("username"));
        return View.render(req, model, Path.Template.INVENTORY);
    };
    public static Route serveAddInventory = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        List<Category> categories = categoryDao.getAllCategory();

        model.put("categoryList", categories);
        model.put("item", new Item());
        model.put("type", req.session().attribute("type"));
        model.put("activeMenu", 2);
        model.put("username", req.session().attribute("username"));
        return View.render(req, model, Path.Template.INVENTORY_DETAIL);
    };
    public static Route handleAddInventory = (Request req, Response res) -> {
        String name = req.queryParams("name");
        Integer category = Integer.parseInt(req.queryParams("category"));
        Integer stock = Integer.parseInt(req.queryParams("stock"));
        Integer price = Integer.parseInt(req.queryParams("price"));
        inventoryDao.insertItem(name, category, stock, price);
        res.redirect(Path.Web.INVENTORY);
        return null;
    };

    public static Route serveEditInventory = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        List<Category> categories = categoryDao.getAllCategory();
        Integer id = Integer.parseInt(req.params("id"));

        model.put("categoryList", categories);
        model.put("item", inventoryDao.getItem(id));
        model.put("type", req.session().attribute("type"));
        model.put("username", req.session().attribute("username"));
        model.put("activeMenu", 2);

        return View.render(req, model, Path.Template.INVENTORY_DETAIL);
    };
    public static Route handleEditInventory = (Request req, Response res) -> {
        Integer id = Integer.parseInt(req.params("id"));
        String name = req.queryParams("name");
        Integer category = Integer.parseInt(req.queryParams("category"));
        Integer stock = Integer.parseInt(req.queryParams("stock"));
        Integer price = Integer.parseInt(req.queryParams("price"));

        inventoryDao.updateItem(id, name, category, stock, price);
        res.redirect(Path.Web.INVENTORY);
        return null;
    };


    public static Route serveDeleteInventory = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        return View.render(req, model, Path.Template.INVENTORY);
    };
    public static Route handleDeleteInventory = (Request req, Response res) -> {
        Integer id = Integer.parseInt(req.params("id"));
        inventoryDao.deleteItem(id);
        res.redirect(Path.Web.INVENTORY);
        return null;
    };
}