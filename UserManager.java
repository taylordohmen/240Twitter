
import java.io.*;
import java.util.*;

public class UserManager {

    public static final int NUMFIELDS = 15;

    //checks database for new username
    //if it does not find it, it
    //appends information for new user at the end of the file
    public static void registerUser(String[] userInfo) {
        ArrayList<User> allUsers = getAllUsers();
        if (uniqueUsername(userInfo[0], allUsers)) {
            try (FileWriter fw = new FileWriter("UserInfo.csv", true)) {
                for (String s : userInfo) {
                    fw.write(s + ",");
                }
                fw.write("\n");
            } catch (IOException e) {
            }
        } else {
            System.out.println("That username is already taken");
        }
        createSubscribesToFile(userInfo[0]);
    }

    //helper function for registerUser()
    //returns false if username already exists on file
    //returns true otherwise
    private static boolean uniqueUsername(String username, ArrayList<User> allUsers) {
        boolean unique = true;
        for (User u : allUsers) {
            if (getUserInfo(u)[0].equals(username)) {
                unique = false;
                break;
            }
        }
        return unique;
    }

    //helper function for registerUser()
    //creates a new csv file that will hold the usernames of all the associated user's subscribees
    private static void createSubscribesToFile(String username) {
        try (PrintWriter pw = new PrintWriter(username + "SubscribesTo.csv")) {
        } catch (IOException e) {
        }
    }


    /*
     Things to think about:
     -how to prevent two people from logging on to the same profile at the same time?
     -Which of the following should this method return?
     1 - a boolean indicating whether the username/password combo is valid
     2 - a User object containing the data related to the username/password combo
     (If this is the case then the User() constructor needs to be written)
     */
    //right now this checks the database to make sure that the username/password combination is valid
    //and contains a boolean indicating such
    public static boolean loginUser(String username, String password) {
        ArrayList<User> allUsers = getAllUsers();
        boolean valid = false;
        for (User u : allUsers) {
            if (username.equals(u.getUsername())) {
                if (password.equals(u.getPassword())) {
                    valid = true;
                }
            }
        }
        return valid;
    }

    //what should this method do?
    //will there be a list of logged on users in the main method?
    public static void logoutUser(String username) {

    }

    //gets all user information for each user registered with the system
    //each user must have all info on a single line in the database
    //returns an ArrayList of User objects
    public static ArrayList<User> getAllUsers() {

        ArrayList<User> allUsers = new ArrayList();

        try (Scanner in = new Scanner(new FileInputStream("UserInfo.csv"))) {

            while (in.hasNextLine()) {

                String[] allFields = new String[NUMFIELDS];
                int placeCount = 0;
                StringBuilder currentField = new StringBuilder("");
                String line = in.nextLine();

                for (int i = 0; i < line.length(); i++) {

                    char currentChar = line.charAt(i);

                    if (currentChar == ',') {
                        allFields[placeCount] = currentField.toString();
                        placeCount++;
                        currentField = new StringBuilder("");
                    } else {
                        currentField.append(currentChar);
                    }
                }

                allUsers.add(new User(allFields));
            }
        } catch (IOException e) {
        }
        return allUsers;
    }

    //returns an array that contains all user info as strings in the same order in which they are stored in the .csv
    public static String[] getUserInfo(User u) {
        String[] userInfo = new String[NUMFIELDS];
        userInfo[0] = u.getUsername();
        userInfo[1] = u.getPassword();
        userInfo[2] = u.getRealName();
        userInfo[3] = u.getAge();
        userInfo[4] = u.getHometown();
        userInfo[5] = u.getBadges();
        userInfo[6] = u.getFavPoke();
        for (int i = 7; i < 13; i++) {
            userInfo[i] = u.getParty()[i - 7];
        }
        userInfo[13] = u.getBio();
        userInfo[14] = u.getFavoriteType();
        return userInfo;
    }

    //returns an ArrayList of strings which are the usernames of of all the users the designated user is subscribed to
    //assumes a SubscribesTo.csv file already exists for the designated user

    
    public static boolean isUser(String username) {
        ArrayList<User> allUsers = getAllUsers();
        boolean isUser = false;
        for (User u : allUsers) {
            if (username.equals(u.getUsername())) {
                isUser = true;
                break;
            }
        }
        return isUser;
    }
}
