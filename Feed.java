
import java.util.*;
import java.io.*;

public class Feed {

    private User currentUser;
    private int length;
    private ArrayList<Post> posts;
    private Post feed[];
    // expecting that the user is prompted asking how many posts to display, with a default value of 20 being passed.
    // also expecting that user has been prompted with the options for sorting the post, passed to constructor as an int.

    public Feed(int option, int len, User crntUsr) {
        try (Scanner storage = new Scanner(new FileInputStream("posts.csv"))) {
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

    private void chooseSort(int option) {
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

    private void readPosts(Scanner storage) {
        while (storage.hasNextLine()) {
            String post[] = storage.nextLine().split(",");
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
            posts.add(new Post(id, date, privacy, user, contents, location, hashtags));
        }
    }

    private void update() {
        int j = 0;
        for (int i = 0; i < length; i++) {
            while (j < posts.size()) {
                Post check = posts.get(j);
                int privacy = check.getPrivacyLevel();
                if (privacy == 0) {
                    feed[i] = check;
                }
                if (privacy == 1) {
                    if (currentUser.isSubscribedTo(check.getPostAuthor())) {
                    }
                }
                if (privacy == 2) {
                    if (currentUser.getUsername() == check.getPostContents().split(" ")[0]) {
                    }
                }
                j++;
                break;
            }
        }
    }

    private void updateHash(String hash) {
        int j = 0;
        for (int i = 0; i < length; i++) {
            while (j < posts.size()) {
                if (posts.get(j).hasHashtag(hash)) {
                    feed[i] = posts.get(j);
                    break;
                }
                j++;
            }
        }
        try (Scanner readMons = new Scanner(new FileInputStream("pokemon.csv"))) {
            String pokemonList[] = readMons.nextLine().split(",");
            for (String pokemon : pokemonList) {
                if (hash == pokemon) {
                    pokemonSearch(pokemon);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("pokemon.csv not found, please add it to the working directory");
        }
    }

    private String pokemonSearch(String pokemon) {
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

    private void updateUser(String user) {
        int j = 0;
        for (int i = 0; i < length; i++) {
            while (j < posts.size()) {
                if (user == posts.get(j).getPostAuthor()) {
                    feed[i] = posts.get(j);
                    break;
                }
                j++;
            }
        }
    }

    private void updateLoca(String loca) {
        int j = 0;
        for (int i = 0; i < length; i++) {
            while (j < posts.size()) {
                if (loca.equals(posts.get(j).getLocationTag())) {
                    feed[i] = posts.get(j);
                    break;
                }
                j++;
            }
        }
    }

    private void displayFeed() {
        Post read;
        for (int i = 0; i < length; i++) {
            read = feed[i];
            System.out.printf("By user: %s\n%s:\n%s\nLocation: %s.\n", read.getPostAuthor(), read.getDate().toString(), read.getPostContents(), read.getLocationTag());
        }
    }
}
