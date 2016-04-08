import java.util.*;
import java.io.*;

public class Feed {
	private int length;
	private FileInputStream database;
	private Scanner storage;
	private Post posts[];
	// expecting that the user is prompted asking how many posts to display, with a default value of 20 being passed.
	// also expecting that user has been prompted with the options for sorting the post, passed to constructor as an int.
	public Feed(int option, int len){
		try{
			database = new FileInputStream("posts.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Where are you running this program from, exactly, and where are the posts being kept?\n Should be in the same place.");
		}
		storage = new Scanner(database);
		length = len;
		posts = new Post[length];
		update(option);
	}
	private void update(int option){
		Scanner in = new Scanner(System.in);
		switch(option){
			case 1: System.out.println("please enter your search term");
				String hash = in.nextLine();
				updateHash(hash);
				break;
			case 2: System.out.println("please enter the username whose posts you wish to view");
				String user = in.nextLine();
				updateUser(user);
				break;
			case 3: System.out.println("please enter your location");
				String location = in.nextLine();
				updateLoca(location);
				break;
			default: updateDate();
		}
	}
	private void updateDate(){
		for(int i = 0; i< length; i++){
			String post[] = storage.nextLine().split(",");
			int id = post[0];
			Date date = new Date(post[1]);
			int privacy = post[2];
			String user = post[3];
			String contents = post[4];
			String location = post[5];
			int numHash = integer.parseInt(post[6]);
			String hashtags[] = new String[numHash];
			if(numHash > 0){
				for(int j = 7, j < (7+numHash); j++){
					hashtags[j] = post[j];
				}
			}
			posts[i] = new Post(int postID, Date date, int privacy, String user, String contents, String location, int numHash, String hashtags[]);
		}
	}
	private void updateHash(String hash){}
	private void pokemonSearch(ArrayList DB, String pokemon){}
	private void updateUser(String user){}
	private void updateLoca(String loca){}
	private void download(String filename){}
	private void insertDM(DirectMessage dm){
		// TODO: figure out what the heck to do with DM's.
	}
	public static void main(String args[]){
		// TODO: remove this because it's just for testing
	}
}
