
import java.io.*;
import java.util.*;

public class UserManager {

    public static final int NUMFIELDS = 15;

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

    public static void loginUser(String username, String password) {

    }

    public static void logoutUser(String username, String password) {

    }

    //numUsers is the number of registered users on file
    //each user must have all info on a single line
    public static ArrayList<String[]> getAllUsers() {

        ArrayList<String[]> allUsers = new ArrayList();

        try (FileInputStream fStream = new FileInputStream("UserInfo.csv")) {

            Scanner in = new Scanner(fStream);

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
//
//	public String[] getSubscribedTo(String username) {
//          Read array of usernames to which the user is subscribed
//          from *username*SubscribedTo.txt
//          return that array
//	}
}
