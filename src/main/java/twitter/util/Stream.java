package twitter.util;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import java.io.*;

public class Stream implements StatusListener{
    public static void main(String[] args) throws TwitterException, IOException{
        StatusListener listener = new StatusListener(){


            public void onStatus(Status status) {
                System.out.println(status.getUser().getName() + " : " + status.getText());
                try {
                    //change this stupid part
                    Util.writeStringToFile("/Users/laralu/Desktop/stream.txt", status.getUser().getName() + " : " + status.getText() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
            @Override
            public void onScrubGeo(long arg0, long arg1) { }
            @Override
            public void onStallWarning(StallWarning arg0) { }
        };

        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        FilterQuery fq = new FilterQuery();
        double[][] loc={{-116,33},{-95, 48},{-87,30},{-73, 41},{-74,40},{-73, 41}};
        fq.locations(loc);
        String[] lan={"en"};
        fq.language(lan);
        twitterStream.addListener(listener);
        twitterStream.filter(fq);
        // sample() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
//     twitterStream.sample();
    }
    @Override
    public void onException(Exception e) {
        e.printStackTrace();
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice arg0) { }

    @Override
    public void onScrubGeo(long arg0, long arg1) { }

    @Override
    public void onStallWarning(StallWarning arg0) { }

    @Override
    public void onStatus(Status arg0) { }

    @Override
    public void onTrackLimitationNotice(int arg0) { }

}