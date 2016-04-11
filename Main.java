
import java.util.*;
import java.io.*;

public class Main {

	public static void writePost(String username){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the privacy level you would like to apply to this post. \n(0 - Public; 1 - Subscribers; 2 - Direct Message; 3 - Private)");
		int privacy = in.nextInt();
		in.nextLine();
		String contents = "";
		if(privacy == 2){
			System.out.println("Please enter the username you would like to DM:");
			contents.concat(in.next());
		}
		String location = "";
		String locations[];
		do{
			System.out.println("Please enter your valid location:");
			locations = getLocations();
			location = in.nextLine();
		}while(!contains(location, locations));
		System.out.println("Please enter the contents of your post:");
		contents.concat(" ".concat(in.nextLine()));
		ArrayList<String> hashtags = new ArrayList();
		String hash = "";
		do{
			System.out.println("Please enter the hashtags you wish to include, or enter 'done' when you are finished.");
			hash = in.next();
			if(hash != "done"){
				contents.concat(" #".concat(hash));
				hashtags.add(hash);
			}
		}while(hash != "done");
		Date date = new Date();
		String hashArray[] = new String[hashtags.size()];
		convertToArray(hashtags, hashArray);
		Post post = new Post(1234, date, privacy, username, contents, location, hashArray);
	}

	public static void convertToArray(ArrayList<String> hashtags, String hashArray[]){
		for(int i = 0; i < hashtags.size(); i++){
			hashArray[i] = hashtags.get(i);
		}
	}

	public static boolean contains(String location, String locations[]){
		for(int i = 0; i < locations.length; i++){
			if(location == locations[i]){
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		final Scanner input = new Scanner(System.in);
		String userName;
		boolean loggedIn = false;
		while (!loggedIn) {
			System.out.println("Welcome to Fetch'd!");
			System.out.println("Select an action: \n"
					+ "1)Register a new user\n"
					+ "2)Log in\n"
					+ "3)Quit");

			int selection = input.nextInt();

			switch (selection) {
				case 1:
					String userPass;
					System.out.println("Thank you for choosing to register with Fetch'd!\nPlease enter a username: ");
					userName = input.next();
					System.out.println("Please enter a password");
					userPass = input.next();
					//Add above (plus more? to database/text file
					String[] userInfo = new String[15];
					userInfo[0] = userName;
					userInfo[1] = userPass;
					for (int i = 2; i < userInfo.length; i++) {
						userInfo[i] = "";
					}
					UserManager.registerUser(userInfo);
				case 2:
					System.out.println("Please log in");
					System.out.println("Username: ");
					String username = input.next();
					System.out.println("Password: ");
					String password = input.next();
					//if username and password matches then
					loggedIn = UserManager.loginUser(username, password);
					break;
				case 3:
					System.exit(0);
			}

			while (loggedIn == true) {

				System.out.println("You are logged in, please select an action: ");
				System.out.println("1) Update Feed\n"
						+ "2) Submit Post\n"
						+ "3) Respond to a post\n"
						+ "4) Direct Message\n"
						+ "5) View User Profile\n"
						+ "6) Update User Profile\n"
						+ "7) Logout\n"
				);
				int selection2 = input.nextInt();

				switch (selection2) {
					case 1:
						//ToDo: Refresh and display feed
						System.out.println("Feed updating: ");
						break;
					case 2:
						input.nextLine();  // clear newline out of the buffer
						System.out.println("Please enter your post contents:");
						String postMessage = input.nextLine();
						// System.out.printf("Debug: postMessage contains %s\n", postMessage);
						//Store postMessage in text file
						break;
					case 3:
						break;
					case 4:
						System.out.println("Please enter the username of the user you wish to DM: ");
						String DMuser = input.next();
						break;
					case 5:
						System.out.println("Please enter the username of the user who's profile you wish to view: ");
						String viewUser = input.next();
						break;
					case 6:
						break;
					case 7:
						System.out.println("Logging out...\n");
						loggedIn = false;

				}
			}
		}
	}
	
	
	//returns string array containing all possible location tags
	static String[] getLocations() {
		String locations = "";
		try (Scanner in = new Scanner(new FileInputStream("locations.csv"))) {
			locations = in.nextLine();
		} catch (IOException e) {
		}
		return locations.split(",");
	}
	
	//returns string array containing all possible pokemon
	//We still need to create the pokemon file
	static String[] getPokemon() {
		String pokemon = "";
		try (Scanner in = new Scanner(new FileInputStream("pokemon.csv"))) {
			pokemon = in.nextLine();
		} catch (IOException e) {
		}
		return pokemon.split(",");
	}

}
