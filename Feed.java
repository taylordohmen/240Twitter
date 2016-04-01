import java.util.*;
import java.io.*;

public class Feed {
	private int length;
	private FileInputStream database;
	private Scanner storage;
	private Post posts[];
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
				String loca = in.nextLine();
				updateLoca(loca);
				break;
			default: updateDate();
		}
	}
	private void updateDate(){
		for(int i = 0; i< length; i++){
			String post[] = storage.nextLine().split(",");
			posts[i] = new Post();//post); // TODO: What goes in the post constructor?
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
