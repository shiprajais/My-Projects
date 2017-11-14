package com.crawler;

/**
 * 
 * Shipra Jais
 */
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class WikiCFPScraper {
	public static int DELAY = 7;
	public static void main(String[] args) {
	
		try {
			
			
			//String category = "data mining";
			//String category = "databases";
			//String category = "machine learning";
			String category = "artificial intelligence";
			
	        int numOfPages = 20;
	        
	        //create the output file
	        File file = new File("wikicfp_crawl.txt");
	        
	        file.createNewFile();
	        FileWriter writer = new FileWriter(file); 
	       
	        BufferedWriter bw = new BufferedWriter(writer);
		
	        
	        String content = null;
	        List<String> confNameList = new ArrayList<String>();
	        List<String> acronymList = new ArrayList<String>();
	        List<String> locationList = new ArrayList<String>();
	        for(int i = 1;i<=numOfPages;i++) {
	        	//Create the initial request to read the first page 
				//and get the number of total results
	        	String linkToScrape = "http://www.wikicfp.com/cfp/call?conference="+
	        				      URLEncoder.encode(category, "UTF-8") +"&page=" + i;
	        	content = getPageFromUrl(linkToScrape);	        	
	        	//parse or store the content of page 'i' here in 'content'
	        	//Using Jsoup for parsing
	        	Document doc = Jsoup.parse(content);
		        
		        // parsing conference names
		        Elements confNames = doc.select("td[colspan^=3]");
		        
		        for (Element element : confNames) {
		            
		            confNameList.add(element.text().toString());
		        }
		       
		       //Parsing Acronym
		        Elements trAcronyms = doc.select("a[href^=/cfp/servlet/event.showcfp?eventid=]");
		        
		       
		        for (Element accronym : trAcronyms) {
		           
		            acronymList.add(accronym.text().toString());
		            
		            
		        }
		        
		        
		        // parsing location
		        Element table = doc.select("table").get(5); 
		        Elements rows = table.select("tr");
		        for (int k = 1; k < rows.size(); k++) { 
		        	
		            Element row = rows.get(k);
		           if(row.text().contains("Expired")){
		            	k++;
		            }
		            Element row1 = rows.get(++k);
		            Elements cols = row1.select("td");

		           
		               String location = cols.get(1).text();
		               locationList.add(location);
		            
		        }
		             
		        
		        
	        	//IMPORTANT! Do not change the following:
	        	Thread.sleep(DELAY*1000); //rate-limit the queries
	        }
	        
	        //writing data to file
	        for (int j=0; j<confNameList.size();j++) {
	        	bw.write(String.format("%-10s	%-10s	%-10s", confNameList.get(j),  acronymList.get(j), locationList.get(j)));
	        	
	        	bw.write("\n");
	        	//System.out.println(confNameList.get(j)+"     "+ acronymList.get(j)+"	"+locationList.get(j));
			}
	       
	       
	    bw.close();
        writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Given a string URL returns a string with the page contents
	 * Adapted from example in 
	 * http://docs.oracle.com/javase/tutorial/networking/urls/readingWriting.html
	 * @param link
	 * @return
	 * @throws IOException
	 */
	public static String getPageFromUrl(String link) throws IOException {
		URL thePage = new URL(link);
        URLConnection yc = thePage.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                                    yc.getInputStream()));
        String inputLine;
        String output = "";
        while ((inputLine = in.readLine()) != null) {
        	output += inputLine + "\n";
        }
        in.close();
		return output;
	}
	
	
	
	}
