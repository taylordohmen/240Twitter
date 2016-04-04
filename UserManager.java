
import java.io.*;
import java.util.*;

public class UserManager {

    public static final int NUMFIELDS = 15;
    public static Scanner in;

    //checks database for new username
    //if it does not find it, it
    //appends information for new user at the end of the file
    public static void registerUser(String[] userInfo, ArrayList<String[]> allUsers) {
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
    private static boolean uniqueUsername(String username, ArrayList<String[]> allUsers) {
        boolean unique = true;
        for (String[] s : allUsers) {
            if (s[0].equals(username)) {
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
    public static void loginUser(String username, String password, ArrayList<String[]> allUsers) {
        boolean valid = false;
        for (String[] s : allUsers) {
            if (username.equals(s[0])) {
                if (password.equals(s[1])) {
                    valid = true;
                }
            }
        }
    }

    //what should this method do?
    //will there be a list of logged on users in the main method?
    public static void logoutUser(String username, String password) {

    }

    //gets all user information for each user registered with the system
    //each user must have all info on a single line in the database
    public static ArrayList<String[]> getAllUsers() {

        ArrayList<String[]> allUsers = new ArrayList();

        try (FileInputStream fStream = new FileInputStream("UserInfo.csv")) {

            in = new Scanner(fStream);
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

                allUsers.add(allFields);
            }
        } catch (IOException e) {
        }

        return allUsers;
    }

//
//	public String[] getUserInfo(String username, String[][] allUsers) {
//          sort allUsers by alphabetical order
//          binary search to find correct user
//          return info array for that user
//	}
    
    //returns an ArrayList of strings which are the usernames of of all the users the designated user is subscribed to
    //assumes a SubscribesTo.csv file already exists for the designated user
    public static ArrayList<String> getSubscribedTo(String username) {
        ArrayList<String> subscribedTo = new ArrayList();
        try (FileInputStream fStream = new FileInputStream(username + "SubscribesTo.csv")) {

            in = new Scanner(fStream);
            while (in.hasNextLine()) {

                String line = in.nextLine();
                StringBuilder currentName = new StringBuilder("");

                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ',') {
                        subscribedTo.add(currentName.toString());
                        currentName = new StringBuilder("");
                    } else {
                        currentName.append(line.charAt(i));
                    }
                }

            }
        } catch (IOException e) {
        }
        return subscribedTo;
    }
}
