import java.io.*;

public class UserManager {
	
	public void registerUser(String[] userInfo) {

	}

	public void loginUser(String username, String password) {

	}

	public void logoutUser(String username, String password) {

	}

	//This method doesn't do anything yet
	public String[][] getAllUsers() {
		FileInputStream file = new FileInputStream("UserInfo.txt");
		Scanner in = new Scanner(file);
		while (in.hasNext()) {

		}
	}

	public String[] getUserInfo(String username) {

	} 

	public String[] getSubscribers(String username) {

	}
}