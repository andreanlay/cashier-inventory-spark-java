package controllers;

import app.Path;
import app.View;
import dao.AccountDao;
import dao.CategoryDao;
import dao.InventoryDao;
import model.Account;
import model.Category;
import model.Item;
import model.Privilege;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeController{
    private static AccountDao dao = new AccountDao();

    public static Route serveViewEmployee = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        List<Account> accounts = dao.getAllAccount();
        model.put("employeeList", accounts);
        model.put("type", req.session().attribute("type"));
        model.put("username", req.session().attribute("username"));
        model.put("activeMenu", 2);
        return View.render(req, model, Path.Template.EMPLOYEE);
    };

    public static Route serveAddEmployee = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        List<Privilege> privileges = dao.getAllAccountsType();

        model.put("privilegeList", privileges);
        model.put("employee", new Account());
        model.put("type", req.session().attribute("type"));
        model.put("username", req.session().attribute("username"));
        model.put("activeMenu", 2);

        return View.render(req, model, Path.Template.EMPLOYEE_DETAIL);
    };
    public static Route handleAddEmployee = (Request req, Response res) -> {
        String username = req.queryParams("username");
        String password = req.queryParams("password");
        Integer type = Integer.parseInt(req.queryParams("type"));

        dao.insertAccount(username, password, type);
        res.redirect(Path.Web.EMPLOYEE);
        return null;
    };

    public static Route serveEditEmployee = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        List<Privilege> privileges = dao.getAllAccountsType();
        Integer id = Integer.parseInt(req.params("id"));

        model.put("privilegeList", privileges);
        model.put("employee", dao.getAccount(id));
        model.put("type", req.session().attribute("type"));
        model.put("username", req.session().attribute("username"));
        model.put("activeMenu", 2);

        return View.render(req, model, Path.Template.EMPLOYEE_DETAIL);
    };
    public static Route handleEditEmployee = (Request req, Response res) -> {
        Integer id = Integer.parseInt(req.params("id"));
        String username = req.queryParams("username");
        String password = req.queryParams("password");
        Integer type = Integer.parseInt(req.queryParams("type"));

        dao.updateAccount(id, username, password, type);
        res.redirect(Path.Web.EMPLOYEE);
        return null;
    };

    public static Route serveDeleteEmployee = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        return View.render(req, model, Path.Template.EMPLOYEE);
    };
    public static Route handleDeleteEmployee = (Request req, Response res) -> {
        Integer id = Integer.parseInt(req.params("id"));
        dao.deleteAccount(id);
        res.redirect(Path.Web.EMPLOYEE);
        return null;
    };
}