import app.Path;
import controllers.*;

import static spark.Spark.*;

public class Application{
    public static void main(String[] args){
        staticFileLocation("/templates");

        notFound("<html><body><h1>URL Not found! Make sure you typed the url right!</h1></body></html>");

        get(Path.Web.LOGIN_PAGE, LoginController.serveViewLoginPage);
        post(Path.Web.LOGIN_PAGE, LoginController.handleLogin);

        get(Path.Web.DASHBOARD, DashboardController.serveViewDashboard);
        post(Path.Web.DASHBOARD, DashboardController.handleSortItem);

        get(Path.Web.DASHBOARD_CART_ADD, DashboardController.serveViewCart);
        post(Path.Web.DASHBOARD_CART_ADD, DashboardController.handleAddCart);

        get(Path.Web.DASHBOARD_CART_EDIT, DashboardController.serveEditCart);
        post(Path.Web.DASHBOARD_CART_EDIT, DashboardController.handleEditCart);

        get(Path.Web.DASHBOARD_CART_DELETE, DashboardController.serveDeleteCart);
        post(Path.Web.DASHBOARD_CART_DELETE, DashboardController.handleDeleteCart);

        //----------------------------------------------------------------------------------------

        get(Path.Web.PAYMENT, PaymentController.serveViewPayment);
        post(Path.Web.PAYMENT, PaymentController.handlePayment);

        get(Path.Web.PAYMENT_SUCCESS, PaymentController.serveViewPaymentSuccess);

        //----------------------------------------------------------------------------------------

        get(Path.Web.INVENTORY, InventoryController.serveViewInventory);

        get(Path.Web.INVENTORY_ADD, InventoryController.serveAddInventory);
        post(Path.Web.INVENTORY_ADD, InventoryController.handleAddInventory);

        get(Path.Web.INVENTORY_EDIT, InventoryController.serveEditInventory);
        post(Path.Web.INVENTORY_EDIT, InventoryController.handleEditInventory);

        get(Path.Web.INVENTORY_DELETE, InventoryController.serveDeleteInventory);
        post(Path.Web.INVENTORY_DELETE, InventoryController.handleDeleteInventory);

        //----------------------------------------------------------------------------------------

        get(Path.Web.CATEGORY, CategoryController.serveViewCategory);

        get(Path.Web.CATEGORY_ADD, CategoryController.serveAddCategory);
        post(Path.Web.CATEGORY_ADD, CategoryController.handleAddCategory);

        get(Path.Web.CATEGORY_EDIT, CategoryController.serveEditCategory);
        post(Path.Web.CATEGORY_EDIT, CategoryController.handleEditCategory);

        get(Path.Web.CATEGORY_DELETE, CategoryController.serveDeleteCategory);
        post(Path.Web.CATEGORY_DELETE, CategoryController.handleDeleteCategory);

        //----------------------------------------------------------------------------------------

        get(Path.Web.EMPLOYEE, EmployeeController.serveViewEmployee);

        get(Path.Web.EMPLOYEE_ADD, EmployeeController.serveAddEmployee);
        post(Path.Web.EMPLOYEE_ADD, EmployeeController.handleAddEmployee);

        get(Path.Web.EMPLOYEE_EDIT, EmployeeController.serveEditEmployee);
        post(Path.Web.EMPLOYEE_EDIT, EmployeeController.handleEditEmployee);

        get(Path.Web.EMPLOYEE_DELETE, EmployeeController.serveDeleteEmployee);
        post(Path.Web.EMPLOYEE_DELETE, EmployeeController.handleDeleteEmployee);
    }
}