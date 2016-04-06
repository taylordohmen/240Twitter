import java.util.*;
public class Post {
	private int postID; //how will this be stored and incremented?
        private int privacyLevel; //how is this determined and added to the post? Is 1 admin, 2 normal, 3 dm?
        private String postContents;
        //private String postAuthor = User.getUsername();
        private String locationTag;
        private String hashtag; //need to search through postContents for these and keep them separate
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
        public Post(int postID, int privacy, String user, String contents,  //constructor shouldn't have IO
			String location, int numHash, String hashtags[]){   //IO is handled in Main
			setPostID(postID);
			setPrivacy(privacy);
			setUser(user);
			setContents(contents);
			setLocation(location);
			setHashtags(numHash, hashtags[]);
		}
}
