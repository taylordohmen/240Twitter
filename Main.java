import java.util.*;
/*
 *
 *
 *
 */
public class Main {

	public static void main(String[] args) {
		final Scanner input = new Scanner(System.in);

		boolean loggedIn = false;
		while(!loggedIn) {
			System.out.println("Welcome to Fetch'd!");
			System.out.println("Select an action: \n" +
					"1)Register a new user\n" +
					"2)Log in\n" +
					"3)Quit" );


			int selection = input.nextInt();
			//input.next();
			switch (selection){
				case 1:
					String userName;
					String userPass;
					System.out.println("Thank you for choosing to register with fetched.\nPlease enter a username: ");
					userName = input.next();
					System.out.println("Please enter a password");
					userPass = input.next();
					//Add above (plus more? to database/text file
				case 2:
					System.out.println("Username: ");
					String username = input.next(); 
					System.out.println("Password: ");
					String password = input.next();
					//if username and password matches then
					loggedIn = true;
					break;
				case 3:
					System.exit(0);
			}

			while(loggedIn == true){

				System.out.println("You are logged in, please select an action: ");
				System.out.println("1) Update Feed\n" +
						"2) Submit Post\n" +
						"3) Respond to a post\n" +
						"4) Direct Message\n" +
						"5) View User Profile\n" +
						"6) Update User Profile\n" +
						"7) Logout\n"
						);
				int selection2 = input.nextInt();
				//input.next();
				switch (selection2){
					case 1:
						break;
					case 2:
						break;
					case 3:
						break;
					case 4: 
						break;
					case 5:
						break;
					case 6:
						break;
					case 7:
						loggedIn = false;

				}                 
			//break;
			}
		}
	}
}
