package com.sample;

import java.util.List;
import java.util.Map;

public class ArticleDTO {

		private String title="";
		private String summary="";
		private String fullStory="";
		private List<String> listImages;
		private String author="";
		private List<Map<String,String>> metaData;
		private String publishDate="";
		private String storyURL="";
		
		public String getStoryURL() {
			return storyURL;
		}
		public void setStoryURL(String storyURL) {
			this.storyURL = storyURL;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getSummary() {
			return summary;
		}
		public void setSummary(String summary) {
			this.summary = summary;
		}
		public String getFullStory() {
			return fullStory;
		}
		public void setFullStory(String fullStory) {
			this.fullStory = fullStory;
		}
		public List<Map<String, String>> getMetaData() {
			return metaData;
		}
		public void setMetaData(List<Map<String, String>> metaData) {
			this.metaData = metaData;
		}
		public void setListImages(List<String> listImages) {
			this.listImages = listImages;
		}
		
		public List getListImages() {
			return listImages;
		}
		
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		
		public String getPublishDate() {
			return publishDate;
		}
		public void setPublishDate(String publishDate) {
			this.publishDate = publishDate;
		}
		
}
