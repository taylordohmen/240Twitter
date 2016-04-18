
import java.util.*;
import java.io.*;

public class User {

	final String username;
	private String password;
	private String realName;
	private String bio;
	private String favType;
	private String hometown;
	private String favPoke;
	private String age;
	private String numOfBadges;
	private boolean admin = false;
	private String[] partyPokemon;
	private String[] subscribees;

	//this takes a String array of length 15
	public User(String[] userInfo) {
		username = userInfo[0];
		password = userInfo[1];
		realName = userInfo[2];
		age = (userInfo[3]);
		hometown = userInfo[4];
		numOfBadges = (userInfo[5]);
		favPoke = userInfo[6];
		partyPokemon = Arrays.copyOfRange(userInfo, 7, 13);
		bio = userInfo[13];
		favType = userInfo[14];
	}

	public void updateProfile() {
		Scanner in = new Scanner(System.in);
		String choice;
		int choice2;
		String c;
		boolean cont = true;
		////System.out.println("Newly created profiles are completely blank by default.");  ADD THIS SOMEWHERE AS A DISCLAIMER (AFTER PROFILE CREATION?)

		do {
			try {
				System.out.println("\nEnter an option to set: "
						+ "\n1- Real Name"
						+ "\n2- Age"
						+ "\n3- Bio"
						+ "\n4- Hometown"
						+ "\n5- Favorite Pokemon type"
						+ "\n6- Party Pokemon"
						+ "\n7- Number of Badges"
						+ "\n0- quit");
				choice2 = in.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a number listed.");
				choice2 = -1;
			}
			switch (choice2) {
				case 0:
					cont = false;
					break;
				case 1:
					System.out.println("Enter a new real name: ");
					in.nextLine();
					realName = in.nextLine();
					break;                    
				case 2:
					System.out.println("Enter a new age: ");
					age = in.next();
					break;
				case 3:
					System.out.println("Enter a new bio: ");
					in.nextLine();
					bio = in.nextLine();
					break;
				case 4:
					System.out.println("Enter a new hometown: ");
					in.nextLine();
					hometown = in.nextLine();
					break;
				case 5:
					System.out.println("Enter a new favorite type of pokemon: ");
					favType = in.next();
					break;
				case 6:
					try (Scanner pokes = new Scanner(new FileInputStream("/tmp/fetchd/pokemon.csv"))) {
						String allPokemon[] = pokes.nextLine().split(",");
						for (int i = 0; i < 6; i++) {
							System.out.println("Add a new Pokemon to your party? (y/n): ");
							String validate = in.next();
							if (validate.toLowerCase().charAt(0) == 'y') {
								boolean exists = false;
								String pokemonToAdd;
								do {
									System.out.println("Enter the pokemon's name to add: ");
									pokemonToAdd = in.next();
									for (String pokemon : allPokemon) {
										if (pokemonToAdd.equals(pokemon)) {
											exists = true;
										}
									}
								} while (!exists);
								partyPokemon[i] = pokemonToAdd;
							} else {
								for (int j = i; j < 6; j++) {
									partyPokemon[j] = " ";
								}
								break;
							}
						}
					} catch (FileNotFoundException e) {
						System.out.println("You broke it... :(");
					}
					//setParty(partyPokemon);
					break;
				case 7:
					System.out.println("Enter a new number of badges for your profile: ");
					numOfBadges = in.next();
					//setBadges(numOfBadges);
					break;
				default:
					break;
			}
		} while (cont == true);
	}

	//returns boolean indicating whether this is subscribed to the given username
	public boolean isSubscribedTo(String username) {
		boolean isSubscribed = false;
		for (String s : getSubscribedTo()) {
			if (s.equals(username)) {
				isSubscribed = true;
				break;
			}
		}
		return isSubscribed;
	}

	//checks to see if the given username is a valid username and that the given username is not already subscribes to
	//if so it appends the username to this user's SubscribesTo.csv
	public void subscribeTo(String username) {
		if (UserManager.isUser(username) && !isSubscribedTo(username)) {
			try (FileWriter fw = new FileWriter("/tmp/fetchd/" + this.username + "SubscribesTo.csv", true)) {
				fw.write(username + ",");
			} catch (IOException e) {
			}
		}
	}

	//removes username from user's subscribedTo file
	public void unsubscribeTo(String username) {
		if (isSubscribedTo(username)) {
			ArrayList<String> subscribedToList = getSubscribedTo();
			subscribedToList.remove(username);

			try (FileWriter fw = new FileWriter("/tmp/fetchd/" + this.username + "SubscribesTo.csv", false)) {
				fw.write("");
			} catch (IOException e) {
			}

			try (FileWriter fw = new FileWriter("/tmp/fetchd/" + this.username + "SubscribesTo.csv", true)) {
				for (String s : subscribedToList) {
					fw.write(s + ",");
				}
			} catch (IOException e) {
			}
		}
	}

	//returns an ArrayList of strings which are the usernames of of all the users the designated user is subscribed to
	//assumes a SubscribesTo.csv file already exists for the designated user
	public ArrayList<String> getSubscribedTo() {

		ArrayList<String> subscribedTo = new ArrayList();

		try (Scanner in = new Scanner(new FileInputStream("/tmp/fetchd/" + this.username + "SubscribesTo.csv"))) {

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

	//setters
	public void setRealName(String realName) {
		this.realName = realName;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public void setFavoriteType(String favType) {
		this.favType = favType;
	}

	public void setParty(String[] partyPokemon) {
		this.partyPokemon = Arrays.copyOfRange(partyPokemon, 0, 6);
	}

	public void setBadges(String numOfBadges) {
		this.numOfBadges = numOfBadges;
	}

	public void setFavPoke(String fav) {
		this.favPoke = fav;
	}

	public void setPassword(String pass) {
		this.password = pass;
	}

	//getters
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getRealName() {
		return realName;
	}

	public String getAge() {
		return age;
	}

	public String getBio() {
		return bio;
	}

	public String getHometown() {
		return hometown;
	}

	public String getFavPoke() {
		return favPoke;
	}

	public String getFavoriteType() {
		return favType;
	}

	public String[] getParty() {
		return partyPokemon;
	}

	public String getBadges() {
		return numOfBadges;
	}
}
