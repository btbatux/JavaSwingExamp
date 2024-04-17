package Business;

import Dao.UserDao;
import Entity.Users;

import java.util.ArrayList;

public class UsersController {
    private UserDao userDao;

    public UsersController()
    {
        this.userDao = new UserDao();
    }


    public ArrayList<Users> findAll() {
        return userDao.findAll();
    }

    public Users getById(int id)
    {
        if(id == 0)
        {
            System.out.println("ID 0 Olamaz");
            return new Users();
        }
        return this.userDao.getById(id);
    }

    public boolean update(Users users)
    {
        Users checkUser = this.getById(users.getId());
        if(checkUser == null || checkUser.getId() == 0)
        {
            return false;
        }
        return this.userDao.update(users);
    }
}
