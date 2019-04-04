import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Class with main method to run program
 */
public class Test {

    /**
     * prints out final output
     * @param  args      
     * @throws Exception will display exception to the console for (if any) file/file proccessing error
     ** throw Exception in both main method of Test class and parseMessages method of MessageParser class as try and catch
     */
    public static void main(String[] args)throws Exception{

    Message[] messages = MessageParser.parseMessages("full-corpus.csv");
    ChatterBot[] bots = new ChatterBot[100];
    ChatRoom room = new ChatRoom(bots);
    Random randint = new Random();
    Message message1 = messages[randint.nextInt(messages.length-1)];
    room.postNewMessage(message1);
    //System.out.println(message1);

    // CREATE 90 NEUTRAL BOTS
    for (int i=0; i<90; i++) {     
        bots[i] = new NeutralBot(messages, room);
    }

    // CREATE 10 PUSHER BOTS
    for (int i=90; i<100; i++) {      
        bots[i] = new PusherBot(messages, room);
    }

    int i = 0;
   

    while ((i < 1000) && (room.hasNewMessages())){     
               
        if ((room.getMoodMean() > 0.1) && (room.getMoodMean() < 1.9)){
            if (room.hasNewMessages()) {
                room.updateBots();
     
            }

            i++;
        }
              
    }

    // Output final results of ChatRoom test
    System.out.println("Number of iterations: " + i);
    System.out.println("Number of posts: " + room.getAmountPosts());
    System.out.println("Final mood mean:  " + room.getMoodMean());
    System.out.println("Final mood variance:  " + room.getMoodVariance());

    }
}


