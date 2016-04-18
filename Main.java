
import java.util.*;
import java.io.*;

public class Main {

	static String loggedInUser;
	static User currentUser;
	final static String[] fieldNames = {"Username", "Password", "Real Name", "Age", "Hometown", "Num. Badges", "Favorite Poke", "Poke1", "Poke2", "Poke3", "Poke4", "Poke5", "Poke6", "Bio", "Favorite Type"};

	public static void writePost(String username) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the privacy level you would like to apply to this post. \n(0 - Public; 1 - Subscribers; 2 - Direct Message; 3 - Private)");
		int privacy = in.nextInt();
		in.nextLine();
		String contents = "";
		if (privacy == 2) {
			String recipient = "";
			try{
				System.out.println("Please enter the username you would like to DM:");
				recipient = in.next();
				if (!UserManager.isUser(recipient)){
					throw new InputMismatchException();
				}
			} catch (InputMismatchException e){
				System.out.println("That user does not exist. Quitting post writer...");
				return;
			}
			contents = contents.concat("@".concat(recipient));
		}
		String location = "";
		String locations[] = getLocations();
		do {
			System.out.println("Please enter your valid location:");
			//debug:
			for (int i = 0; i < locations.length; i++) {
				System.out.println(locations[i]);
			}
			location = in.nextLine();
		} while (!contains(location, locations));

		System.out.println("Please enter the contents of your post:");
		contents = contents.concat(" ".concat(in.nextLine()));
		ArrayList<String> hashtags = new ArrayList();
		String hash = "";
		do {
			System.out.println("Please enter the hashtags you wish to include, or enter 'done' when you are finished.");
			hash = in.next();
			if (!hash.equals("done")) {
				contents = contents.concat(" #".concat(hash));
				hashtags.add(hash);
			}
		} while (!hash.equals("done"));
		Date date = new Date();
		String hashArray[] = new String[hashtags.size()];
		convertToArray(hashtags, hashArray);
		int id = getNextPostID();
		Post post = new Post(id, date, privacy, loggedInUser, contents, location, hashArray);
		setNextPostID(id + 1);
		post.writePostToFile();
	}

	public static void convertToArray(ArrayList<String> hashtags, String hashArray[]) {
		for (int i = 0; i < hashtags.size(); i++) {
			hashArray[i] = hashtags.get(i);
		}
	}

	public static boolean contains(String location, String locations[]) {
		for (int i = 0; i < locations.length; i++) {
			if (location.equals(locations[i])) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {

		final Scanner input = new Scanner(System.in);

		String userName = "";
		boolean loggedIn = false;
		while (!loggedIn) {
			System.out.println("Welcome to Fetch'd!");
			System.out.println("Select an action: \n"
					+ "1)Register a new user\n"
					+ "2)Log in\n"
					+ "3)Quit");
			try{
				int selection = input.nextInt();

				switch (selection) {
					case 1:
						String userPass;
						System.out.println("Thank you for choosing to register with Fetch'd!\nPlease enter a username: ");
						userName = input.next();
						System.out.println("Please enter a password");
						userPass = input.next();
						String[] userInfo = new String[15];
						userInfo[0] = userName;
						userInfo[1] = userPass;
						for (int i = 2; i < userInfo.length; i++) {
							userInfo[i] = "";
						}
						if (!UserManager.registerUser(userInfo)) {
							break;
						}
					case 2:
						System.out.println("Please log in");
						System.out.println("Username: ");
						String username = input.next();
						System.out.println("Password: ");
						String password = input.next();
						//if username and password matches then
						loggedIn = UserManager.loginUser(username, password);
						loggedInUser = username;
						currentUser = UserManager.getUser(loggedInUser);
						break;
					case 3:
						System.exit(0);
					case 0:
						break;
					default:
						System.out.println("Invalid Input");
						break;				
				}}catch(InputMismatchException e){
					System.out.println("Invalid Input");
					input.next();
				}

			while (loggedIn == true) {

				System.out.println("\nYou are logged in, please select an action: ");
				System.out.println("1) Update Feed\n"
						+ "2) Submit Post\n"
						+ "3) View User Profile\n"
						+ "4) Update User Profile\n"
						+ "5) Subscribe to a user\n"
						+ "6) Unsubscribe to a user\n"
						+ "7) Logout\n"
						);

				try{
					int selection2 = input.nextInt();

					switch (selection2) {
						case 1:
							//ToDo: Refresh and display feed
							int length = 20;
							System.out.println("How many posts do you want in your feed?");
							length = input.nextInt();
							input.nextLine();
							int option = 0;
							System.out.println("How would you like to organize your feed?\n0 - by date, 1 - hashtag; 2 - username; 3 - location");
							option = input.nextInt();
							input.nextLine();
							System.out.println("\nFeed updating: \n");
							Feed.generateFeed(option, length, UserManager.getUser(loggedInUser));
							break;
						case 2:
							input.nextLine();  // clear newline out of the buffer
							writePost(userName);
							break;
						case 3:
							System.out.println("Please enter the username of the user who's profile you wish to view: ");
							String viewUser = input.next();
							String[] profile = UserManager.getUserInfo(UserManager.getUser(viewUser));

							for (int i = 0; i < profile.length; i++) {

								if (i == 1) {
									System.out.println("Password: Password Obscured");
								} else if (i == 6) {
                                                                    continue;
                                                                } else {
									System.out.println(fieldNames[i] + ": " + profile[i]);
								}
							}
							break;
						case 4:
							currentUser.updateProfile();
							UserManager.writeUserUpdates(currentUser);
							break;
						case 5:
							System.out.println("Please enter the username of the user who you wish to subscribe to: ");
							String subscribeToUser = input.next();
							currentUser.subscribeTo(subscribeToUser);
							break;
						case 6:
							System.out.println("Please enter the username of the user who you wish to unsubscribe to: ");
							String unsubscribeToUser = input.next();
							currentUser.unsubscribeTo(unsubscribeToUser);
							break;
						case 7:
							System.out.println("Logging out...\n");
							loggedIn = false;
						case 0:
							break;
						default:
							System.out.println("Invalid Input");
							break;

					}}catch(InputMismatchException e){
						System.out.println("Invalid Input");
						input.next();
					}
			}
		}
	}

	//returns string array containing all possible location tags
	static String[] getLocations() {
		String location = "";
		try (Scanner in = new Scanner(new FileInputStream("/tmp/fetchd/locations.csv"))) {
			location = in.nextLine();
		} catch (IOException e) {
		}
		return location.split(",");
	}

	//returns string array containing all possible pokemon
	//We still need to create the pokemon file
	static String[] getPokemon() {
		String pokemon = "";
		try (Scanner in = new Scanner(new FileInputStream("/tmp/fetchd/pokemon.csv"))) {
			pokemon = in.nextLine();
		} catch (IOException e) {
		}
		return pokemon.split(",");
	}

	static int getNextPostID() {
		int next = -1;
		try (Scanner in = new Scanner(new FileInputStream("/tmp/fetchd/postID"))) {
			next = Integer.parseInt(in.next());
		} catch (IOException e) {
		}
		return next;
	}

	static void setNextPostID(int next) {
		try (FileWriter fw = new FileWriter("/tmp/fetchd/postID", false)) {
			fw.write(Integer.toString(next));
		} catch (IOException e) {
		}
	}
}
