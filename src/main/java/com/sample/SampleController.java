package com.sample;

import java.io.IOException;
import java.net.URL;
import java.util.Set;

import org.jsoup.Jsoup;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
@SpringBootApplication
public class SampleController {
	
/*	@Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("//ui/");
        bean.setSuffix(".jsp");
        return bean;
    }*/

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
    
    @RequestMapping("/welcome")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="Worldd") String name, Model model) {
        model.addAttribute("name", name);
        return "welcome";
    }
    
    @RequestMapping("/listFeeder")
    public ResponseEntity listFeeder(@RequestParam(value="html", required=false, defaultValue="") String html, Model model) {
    	Object response = "welcome";
    	try {
    		System.out.println("Inside list feeder");
			if(html ==null || html.equals(""))
				html=Jsoup.connect("http://www.feedforall.com/sample-feeds.htm").get().html();
			Set<URL> test=FeedFinder.search(html);
			response =  test;
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    	return new ResponseEntity(response, HttpStatus.OK);
    }
    
    @RequestMapping("/articleParser")
    public ResponseEntity articleParser(@RequestParam(value="url", required=false, defaultValue="") String url, Model model) {
    	Object response = "welcome";
    	ArticleDTO article=null;
    	try {
    		 article=ArticleParser.articleParser(url);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    	return new ResponseEntity(article, HttpStatus.OK);
    }
	    
    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/feederFinder", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity feederFinder(@RequestParam(value="test",required=false) Set<URL> test, Model model) {
    	Object response = "welcome";
    	try {
			response = ListLinks.listLinks("http://news.ycombinator.com/");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    	
     return new ResponseEntity(response, HttpStatus.OK);
    }
    /*public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }*/
}