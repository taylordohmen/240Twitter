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