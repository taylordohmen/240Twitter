// import Main;
// import Post;
// import UserManager;
import java.util.*;
import java.io.*;

public class Feed {
	private int length;
	private FileInputStream database;
	private Scanner storage;
	private ArrayList posts;
	public Feed(int option, int len){
		try{
			database = new FileInputStream("posts.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Where are we running this program from, exactly, and where are the posts being kept?\n Should be in the same place.");
		}
		storage = new Scanner(database);
		length = len;
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
}
