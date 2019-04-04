import java.util.Random;
import java.util.ArrayList;


/**
 * a bot that only tweets about neutral things. Child class of ChatterBot.
 * read and post responses to messages based on the valence of their mood
 */
public class NeutralBot extends ChatterBot{
  
  /**
   *  A constructor for a bot that initially only responds with neutral tweets about topics, but has a moderate positive or negative bias. As its bias increases, it begins to post positive or negative comments.
   * @param  messages Neutral messages
   * @param  room room notifies each ChatterBot that a message has been posted and allows bots to post new messages
   * respond to a message based on the results of a random number
   */
  public NeutralBot(Message[] messages, ChatRoom room){
    super(messages, room);
    Random rand = new Random();
    mood += 0.25*(rand.nextDouble() - 1);

  }

  /**
   * Decides whether or not the message will be passed on to read() based how close it is to mood
   * @param msg potential messages to be read
   */
  public void update(Message msg){
    boolean read = false;
    int sentiment = msg.getSentiment();

    if(mood > 1 && sentiment > 1)
      read = true;
    else if(mood < 1 && sentiment < 1)
      read = true;
    else if(sentiment == 1 && mood == 1)
      read = true;
 
    if(read)
      read(msg);

    
  }

  /**
   * reads messages and decides whether or not to pass them to post()
   * @param msg [description]
   */
  public void read(Message msg){
    int sentiment = msg.getSentiment();
    Random rand = new Random();
    double replyChance = rand.nextDouble()*2.0;
    boolean post = false;

    if(mood > 1 && replyChance < mood)
      post = true;
    else if(mood < 1 && replyChance > mood)
      post = true;

    if(sentiment > 1)
      mood += 0.01;
    else if (sentiment < 1)
      mood -= 0.01;
    else{
      if(mood > 1)
        mood -= 0.01;
      else
        mood += 0.01;
    }

    if(post)
      post();
  }

 /**
   * post, posts to the our chatrooom.
   * if the mood of NeutralBot goes below 0.5 or above 1.5 it
   * will post negative or positive articles, respectively.
   * Otherwise, it will post neutral articles.
   */
  public void post(){
    System.out.println("POST");
    Message reply;
    double room_mood = this.room.getMoodMean();
    Random random = new Random();
    ArrayList<Message>  reply_choices = new ArrayList<Message>();




    if (mood < .5) {
      reply_choices = mood_list(0, messages);
    } else if ((mood >= 0.5) && (room_mood <= 1.5)){
      reply_choices = mood_list(1, messages);
    } else {
      reply_choices = mood_list(2, messages);
    }

    // Choose a random message of the correct mood from reply_choices
    int randint = random.nextInt(reply_choices.size()-1); 
    reply = reply_choices.get(randint);
    this.room.postNewMessage(reply);
    System.out.println(reply);



  }
}