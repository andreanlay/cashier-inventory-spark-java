package dao;

import app.BaseDao;
import model.Category;
import model.Item;
import org.sql2o.Connection;

import java.util.List;

public class CategoryDao extends BaseDao{
    public List<Category> getAllCategory(){
        String sql = "SELECT*FROM category";
        try(Connection con = db.open()){
            return con.createQuery(sql).executeAndFetch(Category.class);
        }
    }
    public Category getCategory(Integer id){
        String sql = "SELECT*FROM category WHERE id = :id";
        try(Connection con = db.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Category.class);
        }
    }
    public void insertCategory(String name){
        String sql = "INSERT INTO category(name) VALUES(:name)";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("name", name)
                    .executeUpdate();
        }
    }
    public void updateCategory(Integer id, String name){
        String sql = "UPDATE category SET name = :name WHERE id = :id";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
    public void deleteCategory(Integer id){
        String sql = "DELETE FROM category WHERE id = :id";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}
