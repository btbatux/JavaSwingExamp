import Core.Database;
import View.UserView;

public class App {
    public static void main(String[] args) {

        Database.connection();
        UserView userView = new UserView();


    }
}