package grit.skane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Station {
	private String name;
	private String id;
	private int distance;
	private List<String> lines = new ArrayList<String>();
	
	Station(String name, String id, int distance) throws IOException{
		this.name = name;
		this.id = id;
		this.distance = distance;
		
		fetchLines();
	}
	
	public String toString() {
		return distance + " " + name + " " + id; 
	}
	
	public List<String> getDeparts(){
		return lines;
	}
	
	private void fetchLines() throws IOException {
		
		// Build the API call
		String fetchStationStr = "http://www.labs.skanetrafiken.se/v2.2/stationresults.asp?selPointFrKey=" + id;

		System.out.println(fetchStationStr);

		// Set the URL that will be sent
		URL fetchStationURL = new URL(fetchStationStr);

		// Create a HTTP connection to sent the GET request over
		HttpURLConnection linec = (HttpURLConnection) fetchStationURL.openConnection();
		linec.setDoInput(true);
		linec.setDoOutput(true);
		linec.setRequestMethod("GET");

		// Make a Buffer to read the response from the API
		BufferedReader in = new BufferedReader(new InputStreamReader(linec.getInputStream()));

		// a String to temp save each line in the response
		String inputLine;

		// a String to save the full response to use later
		String ApiResponse = "";

		// loop through the whole response
		while ((inputLine = in.readLine()) != null) {
			
			//System.out.println(inputLine);
			// Save the temp line into the full response
			ApiResponse += inputLine;
		}
		in.close();
		System.out.println(ApiResponse);
		
		
		//Call a method to make a XMLdoc out of the full response
		Document doc = convertStringToXMLDocument(ApiResponse);

		
		//normalize the XML response
		doc.getDocumentElement().normalize();
		// check that the XML response is OK by getting the Root element 
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		// Create a Node list that gets everything in and under the "clouds" tag  
		NodeList nList = doc.getElementsByTagName("Line");
		
		System.out.println(nList.getLength());
		// loop through the content of the tag
		for (int temp = 0; temp < nList.getLength(); temp++) {
			// Save a node of the current list id 
			Node node = nList.item(temp);
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {

				// set the current node as an Element
				Element e = (Element) node;
				// get the content of an attribute in element
				// and print it out to the client 

				String time = get(e, "JourneyDateTime");
				time = time.substring(time.indexOf("T") + 1);
				lines.add(time + " - " + get(e, "LineTypeName") + " " + get(e, "LineTypeId") + 
						  ": " + get(e, "Towards"));
			}
		}
	}
	
	private static String get(Element e, String tag) {
		return e.getElementsByTagName(tag).item(0).getTextContent();
	}
	
	private static Document convertStringToXMLDocument(String xmlString) {
		// Parser that produces DOM object trees from XML content
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// API to obtain DOM Document instance
		DocumentBuilder builder = null;
		try {
			// Create DocumentBuilder with default configuration
			builder = factory.newDocumentBuilder();

			// Parse the content to Document object
			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
