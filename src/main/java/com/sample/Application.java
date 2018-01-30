package com.sample;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;

import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.X509Certificate;

import org.jsoup.Jsoup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	static {
	    TrustManager[] trustAllCertificates = new TrustManager[] {new X509TrustManager() {
	    	
	    	 public void checkClientTrusted(X509Certificate[] certs, String authType) {
	                // Do nothing. Just allow them all.
	            }
	            public void checkServerTrusted(X509Certificate[] certs, String authType) {
	                // Do nothing. Just allow them all.
	            }
	            @Override
				public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}
				@Override
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					// TODO Auto-generated method stub
					return null;
				}
		}};

	    HostnameVerifier trustAllHostnames = new HostnameVerifier() {
	        @Override
	        public boolean verify(String hostname, SSLSession session) {
	            return true; // Just allow them all.
	        }

			
	    };

	    try {
	        System.setProperty("jsse.enableSNIExtension", "false");
	        SSLContext sc = SSLContext.getInstance("SSL");
	        sc.init(null, trustAllCertificates, new SecureRandom());
	        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	        HttpsURLConnection.setDefaultHostnameVerifier(trustAllHostnames);
	    }
	    catch (GeneralSecurityException e) {
	        throw new ExceptionInInitializerError(e);
	    }
	}
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
     }

}