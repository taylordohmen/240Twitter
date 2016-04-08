import java.util.*;
public class Post {
	private int postID; //how will this be stored and incremented?
        private int privacyLevel; //how is this determined and added to the post? Is 1 admin, 2 normal, 3 dm?
        private String postContents;
        private String postAuthor;
        private String locationTag;
        private String[] hashtags; //need to search through postContents for these and keep them separate
        private Date date;
/*        
        public Post() {
            Scanner in = new Scanner(System.in);
            System.out.println("Input a message to post: ");
            postContents = in.nextLine();
            System.out.println("Input location: ");
            locationTag = in.nextLine();
            
            //postID++;
        }
  */      
        public Post(int privacy, String user, String contents,  //constructor shouldn't have IO
			String location, int numHash, String[] hashtags){   //IO is handled in Main
			setPrivacy(privacy);
			setAuthor(user);
			setContents(contents);
			setLocation(location);
			setHashtags(hashtags);
		}
        
        public int getPostId() {
            return this.postID;
        }
        
        public int getPrivacyLevel() {
            return this.privacyLevel;
        }
        
        public String getPostConents() {
            return this.postContents;
        }
        
        public String getPostAuthor() {
            return this.postAuthor;
        }
        
        public String getLocationTag() {
            return this.locationTag;
        }
        
        public String[] getHashtags() {
            return this.hashtags;
        }
        
        public Date getDate() {
            return date;
        }
        
        void setPrivacy(int p) {
            this.privacyLevel = p;
        }
        
        void setAuthor(String u) {
            this.postAuthor = u;
        }
        
        void setContents(String c) {
            this.postContents = c;
        }
        
        void setLocation(String l) {
            this.locationTag = l;
        }
        
        void setHashtags(String[] h) {
            this.hashtags = h;
        }
}
