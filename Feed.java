import java.util.*;

public class Feed {
	private ArrayList posts;
	private int opcode;
	public Feed(int option){
		update(option);
	}
	private void update(int option){
		Scanner in = new Scanner(System.in);
		switch(option){
			case 0: updateDate();
				break;
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
		}
	}
	private void updateDate(){}
	private void updateHash(String hash){}
	private void pokemonSearch(ArrayList DB, String pokemon){}
	private void updateUser(String user){}
	private void updateLoca(String loca){}
	private void download(String filename){}
	private void insertDM(DirectMessage dm){
		// TODO: figure out what the heck to do with DM's.
	}
}
