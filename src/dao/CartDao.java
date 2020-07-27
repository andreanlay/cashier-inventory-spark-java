package dao;

import app.BaseDao;
import model.CartItem;
import model.Category;
import model.Item;
import org.sql2o.Connection;

import java.util.List;

public class CartDao extends BaseDao{
    public List<CartItem> getAllCartItems(){
        String sql = "SELECT c.id, product_id p_id, name, quantity, price, quantity*price total " +
                "FROM cart c JOIN inventory inv ON c.product_id = inv.id";
        try(Connection con = db.open()){
            return con.createQuery(sql).executeAndFetch(CartItem.class);
        }
    }
    public void AddItem(Integer p_id, Integer quantity){
        String sql = "INSERT INTO cart(product_id, quantity) VALUES(:id, :qty)";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("id", p_id)
                    .addParameter("qty", quantity)
                    .executeUpdate();
        }
    }
    public void updateItem(Integer id, Integer quantity){
        String sql = "UPDATE cart SET quantity = :qty WHERE id = :id";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("qty", quantity)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
    public void deleteItem(Integer id){
        String sql = "DELETE FROM cart WHERE id = :id";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public Integer getQuantity(Integer id){
        String sql = "SELECT quantity FROM cart WHERE id = :id";
        try(Connection con = db.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeScalar(Integer.class);
        }
    }
    public Integer getQuantityFromProduct(Integer id){
        String sql = "SELECT COALESCE(quantity, 0) FROM cart WHERE product_id = :id";
        try(Connection con = db.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeScalar(Integer.class);
        }
    }
    public Boolean itemExist(Integer id){
        String sql = "SELECT COUNT(*) FROM cart WHERE product_id = :id";
        try(Connection con = db.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeScalar(Boolean.class);
        }
    }
    public void AddQuantity(Integer id){
        String sql = "UPDATE cart SET quantity = quantity + 1 WHERE product_id = :id";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
    public Integer getTotal(){
        String sql = "SELECT COALESCE(SUM(inv.price*c.quantity), 0) " +
                "FROM cart c JOIN inventory inv ON c.product_id = inv.id";
        try(Connection con = db.open()){
            return con.createQuery(sql).executeScalar(Integer.class);
        }
    }

    public void flushItems(){
        String updateStock = "UPDATE inventory inv SET stock = stock - " +
                             "(SELECT quantity FROM cart c WHERE inv.id = c.product_id) " +
                             "WHERE EXISTS (SELECT 1 FROM cart c WHERE inv.id = c.product_id)";
        String clearCart = "TRUNCATE TABLE cart";
        String checkStock = "DELETE FROM inventory WHERE stock = 0";
        try(Connection con = db.open()){
            con.createQuery(updateStock).executeUpdate();
            con.createQuery(clearCart).executeUpdate();
            con.createQuery(checkStock).executeUpdate();
        }
    }
}
