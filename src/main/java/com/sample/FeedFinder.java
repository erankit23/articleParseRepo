package com.sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class FeedFinder {
    private static Set<URL> search(Document doc) { // static Set<URL> search(Document doc) {
        Set<URL> feeds = new HashSet<URL>();
        Elements links = doc.select("head > link" +
                                    "[rel=alternate]" +
                                    "[type~=(application/(rss|(x(\\.|\\-))?atom|rdf)\\+|text/)xml]" +
                                    "[href~=.+]");

        for (Element link : links) {
            try {
                feeds.add(new URL(link.attr("abs:href")));
            }
            catch (MalformedURLException e) {
                // ignore
            }
        }

        return feeds;
    }

    public static Set<URL> search(String html) {
        return search(Jsoup.parse(html));
    }

    public static Set<URL> search(String html, String baseUri) {
        return search(Jsoup.parse(html, baseUri));
    }

    public static Set<URL> search(File in, String charsetName) throws IOException {
        return search(Jsoup.parse(in, charsetName));
    }

    public static Set<URL> search(File in, String charsetName, String baseUri) throws IOException {
        return search(Jsoup.parse(in, charsetName, baseUri));
    }
}
