import java.util.ArrayList;
import java.util.Random;

public class ChatterBot{

  protected Message[] messages;
  protected final ChatRoom room;
  protected double mood = 1;

  public ChatterBot(Message[] messages, ChatRoom room){
     this.messages = messages;
     this.room = room;
  }


  /**
   * Creates a list of tweets that are all of a specific mood
   * @param sentiment int - Requests the sentiment that we want
   * @param messages Message[] - gets list of all messages
   * @return ret Message[] - list of messages all with specific sentiments
   */
  ArrayList<Message> mood_list(int sentiment, Message[] messages) {

    // Function to create list of responses based on csv which are all of the same mood

    ArrayList<Message> ret = new ArrayList<>();
    for (Message m : messages) {
      if (m.getSentiment() == sentiment) {
        ret.add(m);
      }
  
    }
    return ret;

  }


  public void update(Message msg){

  }

  public void read(Message msg){

  }

  public void post(){
  }

  public double getMood(){
    return mood;
  }
}