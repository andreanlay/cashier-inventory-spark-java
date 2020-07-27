package dao;

import app.BaseDao;
import model.Item;
import org.sql2o.Connection;

import java.util.List;

public class InventoryDao extends BaseDao{
    public List<Item> getAllItems(){
        String sql = "SELECT inv.id, inv.name, cat.name category, stock, price " +
                     "FROM inventory inv JOIN category cat " +
                     "ON inv.category = cat.id";
        try(Connection con = db.open()){
            return con.createQuery(sql).executeAndFetch(Item.class);
        }
    }
    public List<Item> getItemsByCategory(Integer categoryID){
        String sql = "SELECT inv.id, inv.name, cat.name category, stock, price " +
                "FROM inventory inv JOIN category cat " +
                "ON inv.category = cat.id WHERE inv.category = :id";
        try(Connection con = db.open()){
            return con.createQuery(sql)
                    .addParameter("id", categoryID)
                    .executeAndFetch(Item.class);
        }
    }
    public Item getItem(Integer id){
        String sql = "SELECT inv.id, inv.name, cat.name category, stock, price " +
                "FROM inventory inv JOIN category cat " +
                "ON inv.category = cat.id WHERE inv.id = :id";
        try(Connection con = db.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Item.class);
        }
    }
    public void insertItem(String name, Integer category, Integer stock, Integer price){
        String sql = "INSERT INTO inventory(name, category, stock, price) VALUES(:name, :category, :stock, :price)";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("category", category)
                    .addParameter("stock", stock)
                    .addParameter("price", price)
                    .executeUpdate();
        }
    }
    public void updateItem(Integer id, String name, Integer category, Integer stock, Integer price){
        String sql = "UPDATE inventory SET name = :name, category = :cat, stock = :stock, price = :price WHERE id = :id";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("cat", category)
                    .addParameter("stock", stock)
                    .addParameter("price", price)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
    public void deleteItem(Integer id){
        String sql = "DELETE FROM inventory WHERE id = :id";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public Integer getStock(Integer id){
        String sql = "SELECT stock FROM inventory WHERE id = :id";
        try(Connection con = db.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeScalar(Integer.class);
        }
    }
}
