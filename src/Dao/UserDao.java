package Dao;

import Core.Database;
import Entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDao {
    private Connection connection;

    public UserDao() {
        this.connection = Database.connection();
        //Veritabanı gelen verieri, Users entity nesnelere aktaracak..
        //CRUD işlemler - Ekleme-silme-okuma-güncelleme işlemleri..
    }


    public ArrayList<Users> findAll() {
        ArrayList<Users> users = new ArrayList<>();
        String query = "SELECT * FROM public.users"; //Tüm user kayıtları

        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next())
            {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setMail(rs.getString("mail"));
                user.setPassword(rs.getString("password"));
                user.setType(Users.Type.valueOf(rs.getString("type")));
                user.setGender(Users.Gender.valueOf(rs.getString("gender")));

                users.add(user); // tüm user kayıtları listede tutulacak
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    //parametre ile gelen id'ye sahip user kayıtlarını çek
    public Users getById(int id)
    {
        Users user = new Users();
        String query = "SELECT * FROM public.users WHERE id = ?";
        try{
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if(rs.next())
            {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setMail(rs.getString("mail"));
                user.setPassword(rs.getString("password"));
                user.setType(Users.Type.valueOf(rs.getString("type")));
                user.setGender(Users.Gender.valueOf(rs.getString("gender")));

            }
        }catch (Exception e)
        {

        }
        return user;
    }


    public boolean update(Users users)
    {
        String query = ("UPDATE public.users SET name = ?, mail = ?, password = ?, type = ?, gender = ? WHERE id = ?");
        try
        {
            PreparedStatement prst = this.connection.prepareStatement(query);
            prst.setString(1,users.getName());
            prst.setString(2,users.getMail());
            prst.setString(3,users.getPassword());
            prst.setString(4,users.getType().toString());
            prst.setString(5,users.getGender().toString());
            prst.setInt(6,users.getId());
            return prst.executeUpdate() != -1;

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
