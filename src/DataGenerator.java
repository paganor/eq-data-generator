import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
// random
import java.util.Random;
// Jersey libraries
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
// JSON libraries
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.JSONValue;

public class DataGenerator {
	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String fileLocation = null;

		// present user with data file options
		while(fileLocation == null) {
			System.out.print("Please select a data source (enter an integer): " + "\n" + "\n" +
							 "----- 1: scenario 1 data file" + "\n" + 
							 "----- 2: scenario 2 data file" + "\n" +
							 "----- 3: scenario 3 data file" + "\n" +
							 "----- 4: sample data file" + "\n" +
							 "----- 5: single entry data file" + "\n" + "\n");
			System.out.print("Choice: ");

			// get response
			int choice = Integer.parseInt(br.readLine());
			
			// if moved to a different machine, these fileLocations will have to be changed to new absolute paths
			switch (choice) {
				case 1:
					fileLocation = "/Users/berto/Documents/project-files/eq-data-generator/test/ScenarioFile1.csv";
					break;
				case 2:
					fileLocation = "/Users/berto/Documents/project-files/eq-data-generator/test/ScenarioFile2.csv";
					break;
				case 3:
					fileLocation = "/Users/berto/Documents/project-files/eq-data-generator/test/ScenarioFile3.csv";
					break;
				case 4:
					fileLocation = "/Users/berto/Documents/project-files/eq-data-generator/test/SampleData.csv";
					break;
				case 5:
					fileLocation = "/Users/berto/Documents/project-files/eq-data-generator/test/SampleText.txt";
					break;
				default:
					System.out.println("\n" + "That was not a valid choice... Please try again." + "\n");
			}
		}

		// get rows per fetch
		System.out.print("\n" + "Enter maximum number of rows per interval: ");
		int rows = Integer.parseInt(br.readLine());
		System.out.print("Enter maximum number of seconds between intervals: ");
		int interval = Integer.parseInt(br.readLine());

		// read and parse file
		BufferedReader brl = new BufferedReader(new FileReader(fileLocation));
		String line;
		String tokens[];
		Event e = new Event();

		outerloop:
		while(true) {
			// calculate random size for each batch
			int size = randomSize(rows);
			System.out.println("Now printing " + size + " rows. \n");
			for(int i = 0; i < size; i++) {
				if ((line = brl.readLine()) != null ) {
					// split csv
					tokens = line.split("[|]");
					// asign to event object
					e.enrollment_id = tokens[0];
					e.bureau = tokens[1];
					e.status = tokens[2];
					// send event via REST
					e.send();
				} else {
					break outerloop;
				}
			}
			// calculate wait time
			int wait = randomWait(interval);
			System.out.println("\n" + "now waiting for " + wait/1000 + " seconds... \n");
			// sleep for wait time
			try {
				Thread.sleep(wait);
			} catch (InterruptedException ie) {
				System.out.println("Something goofy happened during sleep interval.");
			}
		} // end of while loop
		System.out.println("Out of rows... data generator complete.");
	}
	
	static int randomSize(int n) {
		// if 1, return that
		if(n == 1) {return 1;}
		// lowest number
		int low = 1;
		Random r = new Random();

		return r.nextInt(n - low) + low;
	}

	static int randomWait(int n) {
		// if 1, return that
		if(n == 1) {return 1000;}
		// lowest wait
		int low = 1;
		Random r = new Random();

		return (r.nextInt(n - low) + low) * 1000;
	}
}

// event class
class Event {
	String enrollment_id, bureau, status;

	// constructors
	public Event() { };
	public Event(String enrollment_id, String bureau, String status) {
		this.enrollment_id = enrollment_id;
		this.bureau = bureau;
		this.status = status;
	}

	public String toJsonString() {
		/*
		format should be {"EnrollmentID":"12341234", "Bureau":"Equifax", "Status":"Active"}

		Basic documentation found at https://code.google.com/p/json-simple/wiki/EncodingExamples
		*/
		Map<String, String> obj = new LinkedHashMap<String, String>();

  		obj.put("enrollment", enrollment_id);
  		obj.put("bureau", bureau);
  		obj.put("status", status);

  		return JSONValue.toJSONString(obj);
	}

	public void send() {
		/*
		Basic documentation found at http://www.mkyong.com/webservices/jax-rs/restful-java-client-with-jersey-client/
		*/
		try {
			// create client
	 		Client client = Client.create();
	 		WebResource webResource = client.resource("http://eqdev.bigcompass.com:8080/EQDemo/rest/json/event");
	 		
	 		// generate json string
			String input = this.toJsonString();

			// post this thing
	 		System.out.print("Now attempting to post " + input + " ... ");
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);
	 
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			}
	 
			System.out.print("Output from Server ... ");
			String output = response.getEntity(String.class);
			System.out.println(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}