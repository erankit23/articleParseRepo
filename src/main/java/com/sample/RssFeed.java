package com.sample;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.net.URL;
import java.util.List;


public class RssFeed {
	public static void main(String[] args) {
        String printFeeds = getRSSfeeds("http://news.softpedia.com/newsRSS/Global-0.xml"); // http://news.yahoo.com/rss/sports
        System.out.println(printFeeds);
}

@SuppressWarnings({ "rawtypes", "finally", "unused" })
protected static String getRSSfeeds(String feedUrl) {
        StringBuffer sb = new StringBuffer();
        try {
                URL url = new URL(feedUrl);
                SyndFeedInput input = new SyndFeedInput();
                SyndFeed feed = input.build(new XmlReader(url));
                List list = feed.getEntries();
                if (list.size() > 0) {
                        for (int j = 0; j < list.size(); j++) {
                                String container = "<p class=\"RSSlist\">";
                                sb.append(container);
                                String title = ((SyndEntry) list.get(j)).getTitle();
                                sb.append(title + "\n");
                                String link = ((SyndEntry) list.get(j)).getLink();
                                String readmore = " Read more ";
                                sb.append(readmore);
                                String endContainer = "";
                                sb.append(endContainer);
                        }
                }
        }

        catch (Exception e) {
                System.err.print("Exception");
        }

        finally {
                return sb.toString();
        }

}
}