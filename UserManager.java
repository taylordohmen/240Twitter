
import java.io.*;
import java.util.*;

public class UserManager {

    public static final int NUMFIELDS = 15;

    //checks database for new username
    //if it does not find it, it
    //appends information for new user at the end of the file
    public static boolean registerUser(String[] userInfo) {
        boolean success = false;
        ArrayList<User> allUsers = getAllUsers();
        if (uniqueUsername(userInfo[0], allUsers)) {
            try (FileWriter fw = new FileWriter("/tmp/fetchd/UserInfo.tsv", true)) {
                for (String s : userInfo) {
                    fw.write(s + "\t");
                }
                fw.write("\n");
            } catch (IOException e) {
            }
            createSubscribesToFile(userInfo[0]);
            success = true;
        } else {
            System.out.println("That username is already taken");
        }
        return success;
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
        try (PrintWriter pw = new PrintWriter("/tmp/fetchd/" + username + "SubscribesTo.csv")) {
        } catch (IOException e) {
        }
    }

    //returns true if the username password combo is valid
    public static boolean loginUser(String username, String password) {
        ArrayList<User> allUsers = getAllUsers();
        boolean valid = false;
        for (User u : allUsers) {
            if (username.equals(u.getUsername())) {
                if (password.equals(u.getPassword())) {
                    valid = true;
                    break;
                }
            }
        }
        return valid;
    }

    //gets all user information for each user registered with the system
    //each user must have all info on a single line in the database
    //returns an ArrayList of User objects
    public static ArrayList<User> getAllUsers() {

        ArrayList<User> allUsers = new ArrayList();

        try (Scanner in = new Scanner(new FileInputStream("/tmp/fetchd/UserInfo.tsv"))) {

            while (in.hasNextLine()) {

                String[] allFields = new String[NUMFIELDS];
                int placeCount = 0;
                StringBuilder currentField = new StringBuilder("");
                String line = in.nextLine();

                for (int i = 0; i < line.length(); i++) {

                    char currentChar = line.charAt(i);

                    if (currentChar == '\t') {
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

    //returns true if the user is on file
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

    public static User getUser(String username) {
        User user = null;
        ArrayList<User> allUsers = getAllUsers();
        for (User u : allUsers) {
            if (username.equals(u.getUsername())) {
                user = u;
                break;
            }
        }
        return user;
    }

    public static void writeUserUpdates(User user) {
        ArrayList<User> allUsers = getAllUsers();
        try (FileWriter fw = new FileWriter("/tmp/fetchd/UserInfo.tsv", false)) {
            fw.write("");
        } catch (IOException e) {
        }
        try (FileWriter fw = new FileWriter("/tmp/fetchd/UserInfo.tsv", true)) {
            for (User u : allUsers) {
                if (u.getUsername().equals(user.getUsername())) {
                    u.setAge(user.getAge());
                    u.setBadges(user.getBadges());
                    u.setBio(user.getBio());
                    u.setFavPoke(user.getFavPoke());
                    u.setFavoriteType(user.getFavoriteType());
                    u.setHometown(user.getHometown());
                    u.setParty(user.getParty());
                    u.setRealName(user.getRealName());
                    u.setPassword(user.getPassword());
                }
                for (String s : getUserInfo(u)) {
                    fw.write(s + "\t");
                }
                fw.write("\n");
            }
        } catch (IOException e) {
        }
    }
}
