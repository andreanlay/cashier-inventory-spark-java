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

public class CategoryController {
    private static CategoryDao dao = new CategoryDao();

    public static Route serveViewCategory = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        List<Category> categories = dao.getAllCategory();
        model.put("categoryList", categories);
        model.put("type", req.session().attribute("type"));
        model.put("username", req.session().attribute("username"));
        model.put("activeMenu", 2);
        return View.render(req, model, Path.Template.CATEGORY);
    };

    public static Route serveAddCategory = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();

        model.put("category", new Category());
        model.put("type", req.session().attribute("type"));
        model.put("username", req.session().attribute("username"));
        model.put("activeMenu", 2);

        return View.render(req, model, Path.Template.CATEGORY_DETAIL);
    };
    public static Route handleAddCategory = (Request req, Response res) -> {
        String name = req.queryParams("name");
        dao.insertCategory(name);
        res.redirect(Path.Web.CATEGORY);
        return null;
    };

    public static Route serveEditCategory = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        Integer id = Integer.parseInt(req.params("id"));

        model.put("category", dao.getCategory(id));
        model.put("type", req.session().attribute("type"));
        model.put("username", req.session().attribute("username"));
        model.put("activeMenu", 2);

        return View.render(req, model, Path.Template.CATEGORY_DETAIL);
    };
    public static Route handleEditCategory = (Request req, Response res) -> {
        Integer id = Integer.parseInt(req.params("id"));
        String name = req.queryParams("name");
        dao.updateCategory(id, name);
        res.redirect(Path.Web.CATEGORY);
        return null;
    };

    public static Route serveDeleteCategory = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        return View.render(req, model, Path.Template.CATEGORY);
    };
    public static Route handleDeleteCategory = (Request req, Response res) -> {
        Integer id = Integer.parseInt(req.params("id"));
        dao.deleteCategory(id);
        res.redirect(Path.Web.CATEGORY);
        return null;
    };
}