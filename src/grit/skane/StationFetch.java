package grit.skane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Servlet implementation class StationFetch
 */
@WebServlet("/StationFetch")
public class StationFetch extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public StationFetch() {
    	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
    	
    	PrintWriter out = response.getWriter();
		out.append("Served at: ").append(request.getContextPath());

		// Build the API call by adding x and y into a URL
		String x = "6167930", y = "1323215";
		String fetchStationStr = "http://www.labs.skanetrafiken.se/v2.2/neareststation.asp?x="
				+ x + "&y=" + y + "&Radius=500";

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
		NodeList nList = doc.getElementsByTagName("NearestStopArea");
		
		System.out.println(nList.getLength());
		// loop through the content of the tag
		for (int temp = 0; temp < nList.getLength(); temp++) {
			// Save a node of the current list id 
			Node node = nList.item(temp);
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {

				// set the current node as an Element
				Element eElement = (Element) node;
				// get the content of an attribute in element
				// and print it out to the client 
				out.print("Nearest stop " + eElement.getElementsByTagName("Name")
											.item(0).getTextContent());
				out.print(" Id: " + eElement.getElementsByTagName("Id")
				  							.item(0).getTextContent());
				out.println(" Distance: " + eElement.getElementsByTagName("Distance")
											  .item(0).getTextContent());
				  							
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

// Method the makes a XML doc out of a string, if it is in a XML format. 	
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
