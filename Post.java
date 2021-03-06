import java.io.*;
import java.util.*;

public class Post {

    private final int postID; //how will this be stored and incremented?
    private final int privacyLevel; //how is this determined and added to the post? Is 1 admin, 2 normal, 3 dm?
    private final String postContents;
    private final String postAuthor;
    private final String locationTag;
    private final String[] hashtags;
    private final Date date;

    public Post(int pID, Date date, int privacy, String user, String contents, //constructor shouldn't have IO
        String location, String hashes[]) {   //IO is handled in Main
        postID = pID;
        this.date = date;
        privacyLevel = privacy;
        postAuthor = user;
        postContents = contents;
        locationTag = location;
        hashtags = hashes;
    }
    
    void writePostToFile() {
        try (FileWriter fw = new FileWriter("/tmp/fetchd/posts.tsv", true)) {
            fw.write(this.postID + "\t");
            //date in milliseconds since whenever
            //to convert back to date object call new date() with this as the parameter
            fw.write(this.date.getTime() + "\t");
            fw.write(this.privacyLevel + "\t");
            fw.write(this.postAuthor + "\t");
            fw.write(this.postContents + "\t");
            fw.write(this.locationTag + "\t");
            fw.write(this.hashtags.length + "\t");
            for (String s : this.hashtags) {
                fw.write(s + "\t");
            }
            fw.write("\n");
        } catch (IOException e) {
            
        }
    }

    public boolean hasHashtag(String hashtag) {
        boolean has = false;
        for (String s : this.hashtags) {
            if (hashtag.equals(s)) {
                has = true;
                break;
            }
        }
        return has;
    }

    public ArrayList<User> postMentions() {
        ArrayList<User> mentions = new ArrayList();
        String[] words = this.postContents.split(" ");
        for (String s : words) {
            if (s.charAt(0) == '@' && UserManager.isUser(s.substring(1))) {
                for (User u : UserManager.getAllUsers()) {
                    if (u.getUsername().equals(s.substring(1))) {
                        mentions.add(u);
                        break;
                    }
                }
            }
        }
        return mentions;
    }

    public int getPostId() {
        return this.postID;
    }

    public int getPrivacyLevel() {
        return this.privacyLevel;
    }

    public String getPostContents() {
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
        return this.date;
    }
}
