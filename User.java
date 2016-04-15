
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
        String choice2;
        String c;
        boolean cont = true;
        ////System.out.println("Newly created profiles are completely blank by default.");  ADD THIS SOMEWHERE AS A DISCLAIMER (AFTER PROFILE CREATION?)

        do {

            System.out.println("\nEnter an option to set: "
                    + "\nReal Name"
                    + "\nAge"
                    + "\nBio"
                    + "\nHometown"
                    + "\nFavorite Pokemon type"
                    + "\nParty Pokemon"
                    + "\nNumber of Badges");
            choice2 = in.nextLine().toLowerCase();

            switch (choice2) {
                case "real name":
                    System.out.println("Enter a new real name: ");
                    realName = in.nextLine();
                    break;
                case "age":
                    System.out.println("Enter a new age: ");
                    age = in.nextLine();
                    break;
                case "bio":
                    System.out.println("Enter a new bio: ");
                    bio = in.nextLine();
                    break;
                case "hometown":
                    System.out.println("Enter a new hometown: ");
                    hometown = in.nextLine();
                    break;
                case "favorite type of pokemon":
                    System.out.println("Enter a new favorite type of pokemon: ");
                    favType = in.nextLine();
                    break;
                case "party pokemon":
                    for (int i = 0; i < 6; i++) {
                        System.out.println("Add a new Pokemon to your party? (y/n): ");
                        String pokemon = in.nextLine();
                        if (pokemon.toLowerCase().equals("y")) {
                            System.out.println("Enter the pokemon's name to add: ");
                            partyPokemon[i] = in.nextLine();
                        } else {
                            for (int j = i; j < 6; j++) {
                                partyPokemon[j] = " ";
                            }
                            break;
                        }
                    }
                    //setParty(partyPokemon);
                    break;
                case "number of badges":
                    System.out.println("Enter a new number of badges for your profile: ");
                    numOfBadges = in.nextLine();
                    //setBadges(numOfBadges);
                    break;
                default:
                    break;
            }
            System.out.println("Do you want to continue editing your profile? (yes/no)");
            c = in.nextLine();
            cont = c.toLowerCase().charAt(0) == ('y'); //break;
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
