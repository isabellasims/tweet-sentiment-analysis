import java.util.Random;
import java.util.ArrayList;

/**
 * Gets mean mood of Chatroom and pushes that value to its extreme
 */
public class PusherBot extends ChatterBot {

    /**
     * @param  messages Allow access to messages from other classes
     * @param  room     Allow access to room from other classes
     */

    public PusherBot(Message[] messages, ChatRoom room){
        super(messages, room);
    }

    /**
     * Posts negative or neutral if mean is negative and positive if mean is positive
     */
    public void post(){
    
        Message reply;
        double room_mood = this.room.getMoodMean();
        Random random = new Random();
        ArrayList<Message>  reply_choices = new ArrayList<>();

        if (room_mood > 1) {
            reply_choices = mood_list(2, messages);
        } else if (room_mood < 1) {
            reply_choices = mood_list(0, messages);
        } else {
            reply_choices = mood_list(1, messages);
        }


      int randint = random.nextInt(reply_choices.size()-1); 
      reply = reply_choices.get(randint);
      this.room.postNewMessage(reply);



  }
}
