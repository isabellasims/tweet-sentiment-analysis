public class Message {
    private final int sentiment;
    private final String tweetText;
    private final String topic;
    private final String tweetDate;
    private final String tweetId;

    /**
     * Construct a new empty message. Declared as private to override default
     * @return Am empty message.
     */
    private Message(){
      sentiment = -1;
      tweetText = null;
      topic = null;
      tweetDate = null;
      tweetId = null;
    }

    /**
     * Constructs a new message Object using the given parameters
     * @param  topic     A single word String for the topic of the tweet
     * @param  sentiment Is the tweet positive (2), negative (0), neutral (1), or irrelevant (-1) to the given topic
     * @param  tweetId   Unique identifier for the tweet from the Twitter API
     * @param  tweetDate Date the tweet was tweeted
     * @param  tweetText Text of the tweet
     * @return           A constructed Message object
     */
    public Message(String topic, int sentiment,  String tweetId, String tweetDate, String tweetText){
      this.sentiment = sentiment;
      this.tweetText = tweetText;
      this.topic = topic;
      this.tweetDate = tweetDate;
      this.tweetId = tweetId;
    }

    /**
     * Get the sentiment of a message
     * @return Is the tweet positive (2), negative (0), neutral (1), or irrelevant (-1) to the given topic
     */
    public int getSentiment(){
      return this.sentiment;
    }

    /**
     * Get the topic of a message
     * @return The topic of the message as a String
     */
    public String getTopic(){
      return this.topic;
    }

    /**
     * Get the content of this message as a String
     * @return The content of this message (sentiment, text, topic, date, id) as a String
     */
    public String toString(){
      return this.sentiment
      + "\t" + this.tweetText
      + "\t" + this.topic
      + "\t" + this.tweetDate
      + "\t" + this.tweetId;
    }
  }