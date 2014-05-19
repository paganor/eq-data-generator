import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;

public class DataGenerator {

    public static void main (String[] args) throws IOException {
    	// get parameters from user
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String fileLocation = null;
		String fileType = null;
		Enrollment e = new Enrollment();
		Alert a = new Alert();
		while (fileLocation == null) {
			System.out.print("Please select a data source (enter an integer): " + "\n" + "\n" +
							 "----- 1: cm_enrollment sample data" + "\n" + 
							 "----- 2: cm_alert sample data" + "\n" +
							 "----- 3: cm_enrollment full data" + "\n" +
							 "----- 4: cm_alert full data" + "\n" +
							 "----- 5: sample text file" + "\n" + "\n");
			System.out.print("Choice: ");

			int choice = Integer.parseInt(br.readLine());
			// select choice
			switch (choice) {
				case 1:
					fileLocation = "/Users/berto/Documents/project-files/eq-data-generator/test/data/cm_enrollment_sample.csv";
					fileType = "enrollment";
					break;
				case 2:
					fileLocation = "/Users/berto/Documents/project-files/eq-data-generator/test/data/cm_alert_sample.csv";
					fileType = "alert";
					break;
				case 5:
					fileLocation = "/Users/berto/Documents/project-files/eq-data-generator/test/data/sampleText.txt";
					fileType = "text";
					break;
				default:
					System.out.println("\n" + "That was not a valid choice... Please try again." + "\n");
			}
		}

		System.out.print("\n" + "How many records per minute? ");
		int rate = Integer.parseInt(br.readLine());

		// spit it back out for testing purposes..
		System.out.println("\n" + "Full location is: " + fileLocation + ". Rate is " + rate + " records per minute." + "\n");

		// start reading lines from file
		BufferedReader brl = new BufferedReader(new FileReader(fileLocation));
		String line;
		String tokens[];
		// read the file until an empty line
		// while ( (line = brl.readLine()) != null )
		System.out.println("Now starting source file..." + "\n");
		outerloop:
		while (true) {
			for (int i = 0; i < rate; i++) {
				// if the line is not null deal with it
				if ((line = brl.readLine()) != null ) {
					// parse line
					tokens = line.split("[|]");

					// determine type and create object
					if (fileType == "alert") {
						// create alert object
						a.enrollment_id = Integer.parseInt(tokens[0]);
						a.bureau = tokens[1];
						a.tenant_name = tokens[2];
						a.partner_name = tokens[3];
						a.alert_dttm = tokens[4];
						a.alert_utc = tokens[5];
						a.alert_type = tokens[6];
						// print it out (for now)
						System.out.println(a.toString());
					} else if (fileType == "enrollment") {
						// create enrollment object
						e.enrollment_id = Integer.parseInt(tokens[0]);
						e.enrollment_dtmm = tokens[1];
						e.enrollment_utc = tokens[2];
						e.efx_enrollee_data_src_id = Integer.parseInt(tokens[3]);
						e.exp_enrollee_data_src_id = Integer.parseInt(tokens[4]);
						e.tu_enrollee_data_src_id = Integer.parseInt(tokens[5]);
						e.configuration_cd = tokens[6];
						e.configuration_desc = tokens[7];
						e.partner_name = tokens[8];
						e.tenant_name = tokens[9];
						e.efx_status = tokens[10];
						e.efx_modify_dttm = tokens[11];
						e.efx_modify_utc = tokens[12];
						e.exp_status = tokens[13];
						e.exp_modify_dtmm = tokens[14];
						e.efx_modify_utc = tokens[15];
						e.tu_status = tokens[16];
						e.tu_modify_dttm = tokens[17];
						e.tu_modify_utc = tokens[18];
						e.city = tokens[19];
						e.state = tokens[20];
						e.country = tokens[21];
						e.postal_cd = Integer.parseInt(tokens[22]);
						// print it out (for now)
						System.out.println(e.toString());
					} else {
						System.out.println("It's not an alert or an enrollment");
					}
					
				} else {
					break outerloop;
				}
			}
			System.out.println("\n");
			// wait for one minute..
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				System.out.println("Something goofy happened..");
			}
		}
		System.out.println("\n" + "Now finished with all records");
    }
}

// Alert Class
class Alert {
	// all fields
	int enrollment_id;
	String bureau;
	String tenant_name;
	String partner_name;
	String alert_dttm;
	String alert_utc;
	String alert_type;

	// empty constructor
	public Alert() { };
	// basic constructor
	public Alert(int enrollment_id,
				 String bureau,
				 String tenant_name,
				 String partner_name,
				 String alert_dttm,
				 String alert_utc,
				 String alert_type) {
		// basic assignment
		this.enrollment_id = enrollment_id;
		this.bureau = bureau;
		this.tenant_name = tenant_name;
		this.partner_name = partner_name;
		this.alert_dttm = alert_dttm;
		this.alert_utc = alert_utc;
		this.alert_type = alert_type;
	}

	public String toString() {
		return "\n" + "enrollment_id: " + enrollment_id + "\n" +
		       "bureau: " + bureau + "\n" + 
		       "tenant_name: " + tenant_name + "\n" + 
		       "partner_name: " + partner_name + "\n" + 
		       "alert_dttm: " + alert_dttm + "\n" +
		       "alert_utc: " + alert_utc + "\n" +
		       "alert_type: " + alert_type; 
	}
}

// Enrollment Class
class Enrollment {
	// all fields
	int enrollment_id; 
	String enrollment_dtmm; 
	String enrollment_utc;
  	int efx_enrollee_data_src_id; 
  	int exp_enrollee_data_src_id; 
  	int tu_enrollee_data_src_id;
  	String configuration_cd; 
  	String configuration_desc; 
  	String partner_name;
  	String tenant_name; 
  	String efx_status; 
  	String efx_modify_dttm; 
  	String efx_modify_utc;
  	String exp_status; 
  	String exp_modify_dtmm; 
  	String exp_modify_utc;
  	String tu_status; 
  	String tu_modify_dttm; 
  	String tu_modify_utc;
  	String city; 
  	String state; 
  	String country; 
  	int postal_cd;

  	// empty constructor
  	public Enrollment() { };
	// basic constructor
	public Enrollment(int enrollment_id, String enrollment_dtmm, String enrollment_utc,
					  int efx_enrollee_data_src_id, int exp_enrollee_data_src_id, int tu_enrollee_data_src_id,
					  String configuration_cd, String configuration_desc, String partner_name,
					  String tenant_name, String efx_status, String efx_modify_dttm, String efx_modify_utc,
					  String exp_status, String exp_modify_dtmm, String exp_modify_utc,
					  String tu_status, String tu_modify_dttm, String tu_modify_utc,
					  String city, String state, String country, int postal_cd) {

		// basic setter methods
		this.enrollment_id = enrollment_id;
		this.enrollment_dtmm = enrollment_dtmm;
		this.enrollment_utc = enrollment_utc;
		this.efx_enrollee_data_src_id = efx_enrollee_data_src_id;
		this.exp_enrollee_data_src_id = exp_enrollee_data_src_id;
		this.tu_enrollee_data_src_id = tu_enrollee_data_src_id;
		this.configuration_cd = configuration_cd;
		this.configuration_desc = configuration_desc;
		this.partner_name = partner_name;
		this.tenant_name = tenant_name;
		this.efx_status = efx_status;
		this.efx_modify_dttm = efx_modify_dttm;
		this.efx_modify_utc = efx_modify_utc;
		this.exp_status = exp_status;
		this.exp_modify_dtmm = exp_modify_dtmm;
		this.exp_modify_utc = exp_modify_utc;
		this.tu_status = tu_status;
		this.tu_modify_dttm = tu_modify_dttm;
		this.tu_modify_utc = tu_modify_utc;
		this.city = city;
		this.state = state;
		this.country = country;
		this.postal_cd = postal_cd;
	};

	public String toString() {
		return "\n" + "enrollment_id: " + enrollment_id + "\n" + 
			   "enrollment_dtmm: " + enrollment_dtmm + "\n" + 
			   "enrollment_utc: " + enrollment_utc + "\n" + 
			   "efx_enrollee_data_src_id: " + efx_enrollee_data_src_id + "\n" + 
			   "exp_enrollee_data_src_id: " + exp_enrollee_data_src_id + "\n" + 
			   "tu_enrollee_data_src_id: " + tu_enrollee_data_src_id + "\n" + 
			   "configuration_cd: " + configuration_cd + "\n" + 
			   "configuration_desc: " + configuration_desc + "\n" + 
			   "partner_name: " + partner_name + "\n" + 
			   "tenant_name: " + tenant_name + "\n" + 
			   "efx_status: " + efx_status + "\n" + 
			   "efx_modify_dttm: " + efx_modify_dttm + "\n" + 
			   "efx_modify_utc: " + efx_modify_utc + "\n" + 
			   "exp_status: " + exp_status + "\n" + 
			   "exp_modify_dtmm: " + exp_modify_dtmm + "\n" + 
			   "exp_modify_utc: " + exp_modify_utc + "\n" + 
			   "tu_status: " + tu_status + "\n" + 
			   "tu_modify_dttm: " + tu_modify_dttm + "\n" + 
			   "tu_modify_utc: " + tu_modify_utc + "\n" + 
			   "city: " + city + "\n" + 
			   "state: " + state + "\n" + 
			   "country: " + country + "\n" + 
			   "postal_cd: " + postal_cd + "\n";
	}
}