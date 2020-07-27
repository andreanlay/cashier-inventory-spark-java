package app;

import lombok.Getter;

public class Path{
    public static class Web{
        @Getter public static final String LOGIN_PAGE = "/login/";
        @Getter public static final String DASHBOARD = "/dashboard/";
        @Getter public static final String DASHBOARD_CART = "/dashboard/cart/";
        @Getter public static final String DASHBOARD_CART_ADD = "/dashboard/cart/add/:id/";
        @Getter public static final String DASHBOARD_CART_EDIT = "/dashboard/cart/edit/:id/";
        @Getter public static final String DASHBOARD_CART_DELETE = "dashboard/cart/delete/:id/";

        @Getter public static final String PAYMENT = "/dashboard/payment/";
        @Getter public static final String PAYMENT_SUCCESS = "/dashboard/payment/success/";

        @Getter public static final String INVENTORY = "/dashboard/inventory/";
        @Getter public static final String INVENTORY_ADD = "/dashboard/inventory/add/";
        @Getter public static final String INVENTORY_EDIT = "/dashboard/inventory/:id/edit/";
        @Getter public static final String INVENTORY_DELETE = "/dashboard/inventory/:id/delete/";

        @Getter public static final String CATEGORY = "/dashboard/category/";
        @Getter public static final String CATEGORY_ADD = "/dashboard/category/add/";
        @Getter public static final String CATEGORY_EDIT = "/dashboard/category/:id/edit/";
        @Getter public static final String CATEGORY_DELETE = "/dashboard/category/:id/delete/";

        @Getter public static final String EMPLOYEE = "/dashboard/employee/";
        @Getter public static final String EMPLOYEE_ADD = "/dashboard/employee/add/";
        @Getter public static final String EMPLOYEE_EDIT = "/dashboard/employee/:id/edit/";
        @Getter public static final String EMPLOYEE_DELETE = "/dashboard/employee/:id/delete/";
    }

    public static class Template{
        public static final String LOGIN_PAGE = "/templates/vm/login.vm";
        public static final String DASHBOARD = "/templates/vm/dashboard.vm";

        public static final String PAYMENT = "/templates/vm/payment/payment.vm";
        public static final String PAYMENT_SUCCESS = "/templates/vm/payment/payment_success.vm";

        public static final String INVENTORY = "/templates/vm/inventory/inventory.vm";
        public static final String INVENTORY_DETAIL = "/templates/vm/inventory/inventory_detail.vm";

        public static final String CATEGORY = "/templates/vm/category/category.vm";
        public static final String CATEGORY_DETAIL = "/templates/vm/category/category_detail.vm";

        public static final String EMPLOYEE = "/templates/vm/employee/employee.vm";
        public static final String EMPLOYEE_DETAIL = "/templates/vm/employee/employee_detail.vm";
    }
}