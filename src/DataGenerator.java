import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;

public class DataGenerator {
	public static void main(String args[]) throws IOException {
		// present user with data file options
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String fileLocation = null;

		// get response
		while(fileLocation == null) {
			System.out.print("Please select a data source (enter an integer): " + "\n" + "\n" +
							 "----- 1: scenario 1 data file" + "\n" + 
							 "----- 2: scenario 2 data file" + "\n" +
							 "----- 3: scenario 3 data file" + "\n" +
							 "----- 4: sample data file" + "\n" +
							 "----- 5: sample text file" + "\n" + "\n");
			System.out.print("Choice: ");

			int choice = Integer.parseInt(br.readLine());
			
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
		System.out.print("\n" + "Enter number of rows per interval: ");
		int rows = Integer.parseInt(br.readLine());
		System.out.print("Enter number of seconds between intervals: ");
		int interval = Integer.parseInt(br.readLine());

		// read and parse file
		BufferedReader brl = new BufferedReader(new FileReader(fileLocation));
		String line;
		String tokens[];
		Event e = new Event();

		outerloop:
		while(true) {
			for(int i = 0; i < rows; i++) {
				if ((line = brl.readLine()) != null ) {
					// split csv
					tokens = line.split("[|]");
					// asign to event object
					e.enrollment_id = Integer.parseInt(tokens[0]);
					e.bureau = tokens[2];
					e.status = tokens[1];
					// send event via REST
					e.send();
				} else {
					break outerloop;
				}
			}
			System.out.println("\n" + "now waiting..." + "\n");
			// sleep for wait time
			try {
				Thread.sleep(interval * 1000);
			} catch (InterruptedException ie) {
				System.out.println("Something goofy happened during sleep interval.");
			}
		} // end of while loop
		System.out.println("Data generator complete.");
	}
}

// event class
class Event {
	int enrollment_id;
	String bureau, status;

	// constructors
	public Event() { };
	public Event(int enrollment_id, String bureau, String status) {
		this.enrollment_id = enrollment_id;
		this.bureau = bureau;
		this.status = status;
	}

	public String toString() {
		return "enrollment_id: " + enrollment_id + " | " + "bureau: " + bureau + " | " + "status: " + status;
	}

	public void send() {
		// put logic to publish to REST service here
		System.out.println(this.toString());
	}
}