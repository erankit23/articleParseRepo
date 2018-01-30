package com.sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ListLinks {
	
	public static Object listLinks(String args) throws IOException {
		List<String> tempList=new ArrayList<String>();
		Map<String,List<String>> listLinksSet=new HashMap<String,List<String>>();
	
       // Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        String url = args;
        print("Fetching %s...", url);
        Document doc =null;
        Elements links =null;
        Elements media=null;
        Elements imports =null;
      try {
         doc = Jsoup.connect(url).get();
         links = doc.select("a[href]");
         media = doc.select("[src]");
        imports = doc.select("link[href]");
      }
      
      catch (Exception e) {
          System.err.println("An exception was thrown");
      }
        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.tagName().equals("img")){
               /* print(" * %s: <%s> %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        trim(src.attr("alt"), 20));*/
                tempList.add(print(" * %s: <%s> %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        trim(src.attr("alt"), 20)));
            }
            else
            {
                /*print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));*/
                tempList.add(print(" * %s: <%s>", src.tagName(), src.attr("abs:src")));
            }
            }
        listLinksSet.put("Media", tempList);
        tempList= new ArrayList<String>();
        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            /*print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));*/
            tempList.add(print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel")));
        }
        listLinksSet.put("Imports", tempList);
        tempList= new ArrayList<String>();
        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            /*print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));*/
            tempList.add(print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35)));
        }
        listLinksSet.put("Links", tempList);
        return listLinksSet;
    }

    /*private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }*/
	
	private static String print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
        return String.format(msg, args);
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
    
    /*public static String toPrettyFormat(Object json) 
    {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(jsonString).getAsJsonObject();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(json);

        return prettyJson;
    }*/
}
