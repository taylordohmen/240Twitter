
import java.util.*;
import java.io.*;

public class Feed {

	private static User currentUser;
	private static int length;
	private static ArrayList<Post> posts;
	private static Post feed[];
	// expecting that the user is prompted asking how many posts to display, with a default value of 20 being passed.
	// also expecting that user has been prompted with the options for sorting the post, passed to constructor as an int.

	public static void generateFeed(int option, int len, User crntUsr) {
		try (Scanner storage = new Scanner(new FileInputStream("/tmp/fetchd/posts.tsv"))) {
			currentUser = crntUsr;
			length = len;
			feed = new Post[length];
			posts = new ArrayList();
			readPosts(storage);
			chooseSort(option);
			displayFeed();
		} catch (FileNotFoundException e) {
			System.out.println("Where are you running this program from, exactly, and where are the posts being kept?\n Should be in the same place.");
		}
	}

	private static void chooseSort(int option) {
		Scanner in = new Scanner(System.in);
		switch (option) {
			case 1:
				System.out.println("Please enter the hashtag you wish to search for");
				String hash = in.nextLine();
				updateHash(hash);
				break;
			case 2:
				System.out.println("Please enter the username whose posts you wish to view");
				String user = in.nextLine();
				updateUser(user);
				break;
			case 3:
				System.out.println("Please enter your location");
				String location = in.nextLine();
				updateLoca(location);
				break;
			default:
				update();
		}
	}

	static void readPosts(Scanner storage) {
		while (storage.hasNextLine()) {
			String post[] = storage.nextLine().split("\t");
			int id = Integer.parseInt(post[0]);
			Date date = new Date(Long.parseLong(post[1]));
			int privacy = Integer.parseInt(post[2]);
			String user = post[3];
			String contents = post[4];
			String location = post[5];
			int numHash = Integer.parseInt(post[6]);
			String hashtags[] = new String[numHash];
			if (numHash > 0) {
				for (int j = 7; j < (7 + numHash); j++) {
					hashtags[j - 7] = post[j];
				}
			}
			posts.add(0, new Post(id, date, privacy, user, contents, location, hashtags));
		}
	}

	private static boolean checkPrivacy(int i, Post check) {
		int privacy = check.getPrivacyLevel();
		if (privacy == 0) {
			return true;
		} else if (privacy == 1) {
			if (currentUser.isSubscribedTo(check.getPostAuthor()) || currentUser.getUsername().equals(check.getPostAuthor())) {
				return true;
			}
		} else if (privacy == 2) {
			System.out.printf("this user is %s, the sender is %s, the first element of the contents is %s, the substring is %s\n",
					currentUser.getUsername(), check.getPostAuthor(), check.getPostContents().split(" ")[0], check.getPostContents().split(" ")[0].substring(1));
			String user = currentUser.getUsername();
			String recipient = check.getPostContents().split(" ")[0].substring(1);
			if (user.equals(recipient) || user.equals(check.getPostAuthor())) {
				return true;
			}
		} else {
			return false;
		}
		return false; // java why u so dumb
	}

	private static void update() {
		int j = 0;
		for (int i = 0; i < length; i++) {
			while (j < posts.size()) {
				Post check = posts.get(j);
				if (checkPrivacy(i, check)) {
					feed[i] = check;
					j++;
					break;
				}
				j++;
			}
		}
		noNulls();
	}

	private static void updateHash(String hash) {
		int j = 0;
		for (int i = 0; i < length; i++) {
			while (j < posts.size()) {
				Post check = posts.get(j);
				if (posts.get(j).hasHashtag(hash) && checkPrivacy(i, check)) {
					feed[i] = check;
					j++;
					break;
				}
				j++;
			}
		}
		noNulls();
		try (Scanner readMons = new Scanner(new FileInputStream("/tmp/fetchd/pokemon.csv"))) {
			String pokemonList[] = readMons.nextLine().split(",");
			for (String pokemon : pokemonList) {
				if (hash.equals(pokemon)) {
					pokemonSearch(pokemon);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("pokemon.csv not found, please add it to the working directory");
		}
	}

	private static String pokemonSearch(String pokemon) {
		int count = 0;
		int maxCount = count;
		String chosen = "The pokemon you searched for has not been found yet!\nWill you be the first?";
		for (int i = 0; i < length; i++) {
			String check = feed[i].getLocationTag();
			for (int j = i + 1; j < length; j++) {
				if (check.equals(feed[j].getLocationTag())) {
					count++;
				}
			}
			if (count > maxCount) {
				maxCount = count;
				chosen = check;
			}
		}
		if (maxCount == 0) {
			return chosen;
		} else {
			return "The pokemon you're searching for is most common found here: ".concat(chosen);
		}
	}

	private static void updateUser(String user) {
		int j = 0;
		for (int i = 0; i < length; i++) {
			while (j < posts.size()) {
				Post check = posts.get(j);
				if (user.equals(posts.get(j).getPostAuthor()) && checkPrivacy(i, check)) {
					feed[i] = check;
					j++;
					break;
				}
				j++;
			}
		}
		noNulls();
	}

	private static void updateLoca(String loca) {
		int j = 0;
		for (int i = 0; i < length; i++) {
			while (j < posts.size()) {
				Post check = posts.get(j);
				if (loca.equals(posts.get(j).getLocationTag()) && checkPrivacy(i, check)) {
					feed[i] = check;
					j++;
					break;
				}
				j++;
			}
		}
		noNulls();
	}

	private static void displayFeed() {
		Post read;
		for (int i = length - 1; i >= 0; i--) {
			read = feed[i];
			if(!feed[i].getPostAuthor().equals("")){
				System.out.printf("By user: %s\n%s:\n%s\nLocation: %s.\n\n", read.getPostAuthor(), read.getDate().toString(), read.getPostContents(), read.getLocationTag());
			}
		}
	}

	private static void noNulls() {
		for (int i = 0; i < feed.length; i++) {
			if (feed[i] == null) {
				String empty[] = new String[]{""};
				feed[i] = new Post(0, new Date(), 0, "", "", "", empty);
			}
		}
	}
}
