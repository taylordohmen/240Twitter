
import java.util.*;
import java.io.*;

public class User {

    private String username;
    private String password;
    private String realName;
    private String bio;
    private String favType;
    private String hometown;
    private String favPoke;
    private int age;
    private int numOfBadges;
    private boolean admin = false;
    private String[] partyPokemon;
    private String[] subscribees;

    //this takes a String array of length 15
    public User(String[] userInfo) {
        username = userInfo[0];
        password = userInfo[1];
        realName = userInfo[2];
        age = Integer.parseInt(userInfo[3]);
        hometown = userInfo[4];
        numOfBadges = Integer.parseInt(userInfo[5]);
        favPoke = userInfo[6];
        partyPokemon = Arrays.copyOfRange(userInfo, 7, 13);
        bio = userInfo[13];
        favType = userInfo[14];
    }

    public void updateProfile() {
        Scanner in = new Scanner(System.in);
        String choice;
        String choice2;
        System.out.println("Would you like to set or get user profile data members? (set/get):");
        choice = in.nextLine();

        switch (choice) {
            case "set":
                System.out.println("Enter an option to set: "
                        + "\nUsername"
                        + "\nReal Name"
                        + "\nAge"
                        + "\nBio"
                        + "\nHometown"
                        + "\nFavorite Pokemon type"
                        + "\nPartY Pokemon"
                        + "\nNumber of Badges");
                choice2 = in.nextLine().toLowerCase();

                switch (choice2) {
                    case "username":
                        System.out.println("Enter a new username: ");
                        username = in.nextLine();
                        setUsername(username);
                        break;
                    case "real name":
                        System.out.println("Enter a new real name: ");
                        realName = in.nextLine();
                        setRealName(realName);
                        break;
                    case "age":
                        System.out.println("Enter a new age: ");
                        age = in.nextInt();
                        setAge(age);
                        break;
                    case "bio":
                        System.out.println("Enter a new bio: ");
                        bio = in.nextLine();
                        setBio(bio);
                        break;
                    case "hometown":
                        System.out.println("Enter a new hometown: ");
                        hometown = in.nextLine();
                        setHometown(hometown);
                        break;
                    case "favorite type of pokemon":
                        System.out.println("Enter a new favorite type of pokemon: ");
                        favType = in.nextLine();
                        setFavoriteType(favType);
                        break;
                    case "party pokemon":
                        for(int i=0; i<6; i++) {
                            System.out.println("Add a new Pokemon to your party? (y/n): ");
                            String poke = " ";
                            if(poke.equals("y"))
                                partyPokemon[i] = in.nextLine();
                            else {
                                for(int j=0; j<i; j++)
                                    partyPokemon[j] = " ";
                            }    
                        }
                        setParty(partyPokemon);
                        break;
                    case "number of badges":
                        System.out.println("Enter a new number of badges for your profile: ");
                        numOfBadges = in.nextInt();
                        setBadges(numOfBadges);
                        break;
                    default:
                        break;
                }
                break;
            case "get":
                System.out.println("Enter an option to get: "
                        + "\nUsername"
                        + "\nReal Name"
                        + "\nAge"
                        + "\nBio"
                        + "\nHometown"
                        + "\nFavorite Pokemon type"
                        + "\nPartY Pokemon"
                        + "\nNumber of Badges");
                choice2 = in.nextLine().toLowerCase();

                switch (choice2) {
                    case "username":
                        System.out.println(getUsername());
                        break;
                    case "real name":
                        System.out.println(getRealName());
                        break;
                    case "age":
                        System.out.println(getAge());
                        break;
                    case "bio":
                        System.out.println(getBio());
                        break;
                    case "hometown":
                        System.out.println(getHometown());
                        break;
                    case "favoite type of pokemon":
                        System.out.println(getFavoriteType());
                        break;
                    case "party pokemon":
                        System.out.println(getParty());
                        break;
                    case "number of badges":
                        System.out.println(getBadges());
                        break;
                    default:
                        break;
                }
        }
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
    return isSubscribed ;
}

        //checks to see if the given username is a valid username and that the given username is not already subscribes to
        //if so it appends the username to this user's SubscribesTo.csv
    public void subscribeTo(String username) {
        if (UserManager.isUser(username) && !isSubscribedTo(username)) {
            try (FileWriter fw = new FileWriter(this.username + "SubscribesTo.csv", true)) {
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

            try (FileWriter fw = new FileWriter(this.username + "SubscribesTo.csv", false)) {
                fw.write("");
            } catch (IOException e) {
            }

            try (FileWriter fw = new FileWriter(this.username + "SubscribesTo.csv", true)) {
                for (String s : subscribedToList) {
                    fw.write(s + ",");
                }
            } catch (IOException e) {
            }
        }
    }
    
    public ArrayList<String> getSubscribedTo() {

        ArrayList<String> subscribedTo = new ArrayList();

        try (Scanner in = new Scanner(new FileInputStream(this.username + "SubscribesTo.csv"))) {

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
        }catch (IOException e) {
        }
            return subscribedTo;
    }

    //setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setAge(int age) {
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
        this.partyPokemon = partyPokemon.clone(); //different method to copy array?
    }

    public void setBadges(int numOfBadges) {
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

    public int getAge() {
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

    public int getBadges() {
        return numOfBadges;
    }
}
