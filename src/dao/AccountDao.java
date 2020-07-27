package dao;

import app.BaseDao;
import model.Account;
import model.Item;
import model.Privilege;
import org.sql2o.Connection;

import java.util.List;

public class AccountDao extends BaseDao{
    public List<Account> getAllAccount(){
        String sql = "SELECT acc.id, username, password, pvl.type " +
                     "FROM account acc JOIN privilege pvl ON acc.type = pvl.id";
        try(Connection con = db.open()){
            return con.createQuery(sql).executeAndFetch(Account.class);
        }
    }
    public Account getAccount(Integer id){
        String sql = "SELECT acc.id, username, password, pvl.type " +
                     "FROM account acc JOIN privilege pvl ON acc.type = pvl.id " +
                     "WHERE acc.id = :id";
        try(Connection con = db.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Account.class);
        }
    }
    public void insertAccount(String username, String password, Integer type){
        String sql = "INSERT INTO account(username, password, type) VALUES(:username, :pass, :type)";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("username", username)
                    .addParameter("pass", password)
                    .addParameter("type", type)
                    .executeUpdate();
        }
    }
    public void updateAccount(Integer id, String username, String password, Integer type){
        String sql = "UPDATE account SET username = :username, password = :pass, type = :type WHERE id = :id";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("username", username)
                    .addParameter("pass", password)
                    .addParameter("type", type)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
    public void deleteAccount(Integer id){
        String sql = "DELETE FROM account WHERE id = :id";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }


    public List<Privilege> getAllAccountsType(){
        String sql = "SELECT*FROM privilege";
        try(Connection con = db.open()){
            return con.createQuery(sql).executeAndFetch(Privilege.class);
        }
    };

    public Boolean verifyLogin(String username, String pass){
        String sql = "SELECT COUNT(*) FROM account WHERE username = :id AND password = :pass";
        try(Connection con = db.open()){
            return con.createQuery(sql)
                    .addParameter("id", username)
                    .addParameter("pass", pass)
                    .executeScalar(Boolean.class);
        }
    }
    public Integer getAccountType(String username, String pass){
        String sql = "SELECT type FROM account WHERE username = :id AND password = :pass";
        try(Connection con = db.open()){
            return con.createQuery(sql)
                    .addParameter("id", username)
                    .addParameter("pass", pass)
                    .executeScalar(Integer.class);
        }
    }
}
