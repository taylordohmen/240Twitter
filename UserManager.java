
import java.io.*;
import java.util.Scanner;

public class UserManager {

    public static final int NUMFIELDS = 15;

    public void registerUser(String[] userInfo) {

    }

    public void loginUser(String username, String password) {

    }

    public void logoutUser(String username, String password) {

    }

    //numUsers is the number of registered users on file
    //each user must have all info on a single line
    public static String[][] getAllUsers(int numUsers) {

        String[][] allUsers = new String[numUsers][NUMFIELDS];

        try (FileInputStream fStream = new FileInputStream("UserInfo.txt")) {
            
            Scanner in = new Scanner(fStream);

            for (int j = 0; j < numUsers; j++) {
                
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

                allUsers[j] = allFields;
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
