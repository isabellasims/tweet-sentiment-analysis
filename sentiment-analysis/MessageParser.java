import java.awt.image.AreaAveragingScaleFilter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Arrays;

public class MessageParser{
  /**
   * Take the name of a comma-separated value (.csv) file in the local directory and parse the contents for tweets. The tweets have an expected structure of "Topic", "Sentiment", "TweetId", "TweetDate", "TweetText"
   * @param  filename the name of a comma-separated value (.csv) file in the local directory
   * @return          An array of parsed Message objects
   */
  public static Message[] parseMessages(String filename) throws Exception {
    // initialize length
    Message[] messages = new Message[5113];
    ArrayList<Message> messages1 = new ArrayList<Message>();

    File file  = new File(filename);
    Scanner sc = new Scanner(file);
    sc.useDelimiter("\n");


    try {
        File f = new File(filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));

        String raw_line;
        while ((raw_line = br.readLine()) != null) {

            // INDEXING LINES FOR EACH CATEGORY
            String[] line = raw_line.split(",",5);
            String sentiment_string = line[1];
            String topic = line[0];
            String tweetId = line[2];
            String tweetDate = line[3];
            String tweetText = line[4];

            int sentiment;

            // read with parentheses
            if (sentiment_string.equals("\"negative\"")) {
                sentiment = 0;

            }
            else if (sentiment_string.equals("\"neutral\"")) {
                sentiment = 1;
            }
            else if (sentiment_string.equals("\"positive\"")) {
                sentiment = 2;
            }
            else{
                continue;
            }

            // Adding message objects
            Message message_obj = new Message(topic, sentiment, tweetId, tweetDate, tweetText);
            messages1.add(message_obj);

        }

    } catch (Exception e) {
        throw new Exception(e);
    }
   
    // Initialize size then create message array
    messages = new Message[messages1.size()];
    messages = messages1.toArray(messages);
    //System.out.println(messages);
    return messages;

    }
  
  }
