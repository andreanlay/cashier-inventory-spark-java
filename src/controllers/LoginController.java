package controllers;

import app.Path;
import app.View;
import dao.AccountDao;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class LoginController{
    private static AccountDao dao = new AccountDao();

    public static Route serveViewLoginPage = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        return View.render(req, model, Path.Template.LOGIN_PAGE);
    };
    public static Route handleLogin = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>();
        String username = req.queryParams("username");
        String password = req.queryParams("password");
        if(!dao.verifyLogin(username, password)){
            model.put("authenticationFailed", true);
            return View.render(req, model, Path.Template.LOGIN_PAGE);
        }
        req.session().attribute("username", username);
        req.session().attribute("type", dao.getAccountType(username, password));
        req.session().attribute("sortItem", -1);
        res.redirect(Path.Web.DASHBOARD);
        return null;
    };
}
