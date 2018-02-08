import DataAccessLayer.MovieDAO;
import DataAccessLayer.UserDAO;
import DataObjects.MainMenu;
import DataObjects.Movie;
import DataObjects.User;
import Facades.MovieFacade;
import Facades.UserFacade;

public class Main {

    public static void main(String[] args) {
//        UserDAO userDAO = new UserDAO();
//
//        User user = userDAO.findUser("Art", "Venere", "123456");
//
//        if (user == null) {
//            System.out.println("Incorrect info");
//            return;
//        }
//
//        System.out.println(user.getFullName());


//        MovieDAO movieDAO = new MovieDAO();
//        Movie movie = movieDAO.insertMovie2("Sesame", 1995, 95);

//        MovieDAO movieDAO1 = new MovieDAO();
//        Movie movie1 = movieDAO1.insertMovie("Sesame", 1995, 95);

//        MovieFacade movieFacade = new MovieFacade();
//        movieFacade.listMovies();

//        UserFacade userFacade = new UserFacade();
////        userFacade.insertUser("Pesho", "Peshev", "Home No 7", "pepito@abv.bg","123456");
//        userFacade.listUsers();

//        MovieFacade movieFacade = new MovieFacade();
//        movieFacade.checkFines("3");

            MainMenu mainMenu = new MainMenu();
            mainMenu.startMenu();
    }

}
