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

        public void updateProfile() {
            Scanner in = new Scanner(System.in);
            String choice;
            
            System.out.println("Enter an option: " +
            "/nUsername"
            + "/nReal Name"
            + "/nAge"
            + "/nBio"
            + "/nHometown"
            + "/nFavorite Pokemon type"
            + "/nPartY Pokemon"
            + "/nNumber of Badges");
   
            choice = in.nextLine();
            switch(choice) {
                case "Username":
                    break;
                default:
                    break;
            }
        }
        
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
            this.partyPokemon = partyPokemon;
        }
        public void setBadges(int numOfBadges) {
            this.numOfBadges = numOfBadges;
        }

}