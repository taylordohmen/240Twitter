import java.util.*;
public class User {
	private String username;
        private String realName;
        private String bio;
        private String favType;
        private String hometown;
        private int age;
        private int numOfBadges;
        private boolean admin = false;
        private String[] partyPokemon;
        private String[] subscribees;
        
        public User() {}
        
        public void updateProfile() {
            Scanner in = new Scanner(System.in);
            String choice;
            String choice2; 
            System.out.println("Would you like to set or get user profile data members? (set/get):");
            choice = in.nextLine();
            
            switch(choice) {
                case "set":
                    System.out.println("Enter an option to set: " +
                    "/nUsername"
                    + "/nReal Name"
                    + "/nAge"
                    + "/nBio"
                    + "/nHometown"
                    + "/nFavorite Pokemon type"
                    + "/nPartY Pokemon"
                    + "/nNumber of Badges");
                    choice2 = in.nextLine();
                    
                    switch(choice2) {
                        case "Username":
                            break;
                        case "Real Name":
                            break;
                        case "Age":
                            break;
                        case "Bio":
                            break;
                        case "Hometown":
                            break;
                        case "Favoite type of Pokemon":
                            break;
                        case "Party Pokemon":
                            break;
                        case "Number of Badges":
                            System.out.println("Enter a new number of badges for your profile: ");
                            numOfBadges = in.nextInt();
                            setBadges(numOfBadges);
                            break;
                        default:
                            break;
                    }
                    break;
                case "get":
                    System.out.println("Enter an option to get: " +
                    "/nUsername"
                    + "/nReal Name"
                    + "/nAge"
                    + "/nBio"
                    + "/nHometown"
                    + "/nFavorite Pokemon type"
                    + "/nPartY Pokemon"
                    + "/nNumber of Badges");
                    choice2 = in.nextLine();
                    
                    switch(choice2) {
                        case "Username":
                            getUsername();
                            break;
                        case "Real Name":
                            getRealName();
                            break;
                        case "Age":
                            getAge();
                            break;
                        case "Bio":
                            getBio();
                            break;
                        case "Hometown":
                            getHometown();
                            break;
                        case "Favoite type of Pokemon":
                            getFavoriteType();
                            break;
                        case "Party Pokemon":
                            getParty();
                            break;
                        case "Number of Badges":
                            getBadges();
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
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
        //getters
        public String getUsername() {
            return username;
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