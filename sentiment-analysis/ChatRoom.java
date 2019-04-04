import java.util.ArrayList;
import java.util.Iterator;

public class ChatRoom {

  private final ChatterBot[] bots;
  private ArrayList<Message> posts;
  private int sumposts;
  private int numposts;
  private ArrayList<Message> new_messages;


  public ChatRoom(ChatterBot[] bots){
    this.bots = bots;
    this.posts = new ArrayList<Message>();
    this.sumposts = posts.size();
    this.new_messages = new ArrayList<Message>();
    this.numposts = 0;
  }


  public int getAmountPosts(){
    return this.numposts;

  }

  /**
   * What is the mean (average) mood of all the bots in this room?
   * @return mean (average) mood of all the bots in this room
   */
  public double getMoodMean(){
    int i = 0;
    double total = 0;

    for (ChatterBot b: bots) {
      total += b.getMood();
      i++;
    }
    return total/(double) i;
  }

  /**
   * What is the variance of the moods of all the bots in this room?
   * @return the variance of the moods of all the bots in this room
   */
  public double getMoodVariance(){
    double mean = getMoodMean();
    int i = 0;
    double sum = 0;
    double variance_numerator = 0;

    for (ChatterBot b: bots) {
      sum += b.getMood();
      i++;
    }
 
    for (ChatterBot b: bots) {
      variance_numerator += Math.pow((b.getMood() - mean), 2);
    }


    return variance_numerator/i;
  }

  /**
   * Accept a new message for posting in the next round of updates
   * @param msg The message to be posted.
   */
  public void postNewMessage(Message msg){

     posts.add(msg); 
     new_messages.add(msg);


  }


  /**
   * Update all the bots with messages that have been posted since the last round of updates.
   * Doing this by index
   */ 
int index = 0;
public void updateBots(){
  
 
    for(int new_index = index; new_index < posts.size(); new_index++){
      for(int bot_index =0; bot_index <bots.length; bot_index ++){

        bots[bot_index].update(posts.get(new_index));  

      }
      index = new_index;    
    
    }
    index++;
   
  }



  /**
   * Were messages posted during the last round of updates?
   * @return If new messages were posted during the last round of updates, return true, otherwise, false.
   */
  public boolean hasNewMessages(){
    //System.out.println(this.new_messages.size() > 0);
    return this.new_messages.size() > 0;

  }


}