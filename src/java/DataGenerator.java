import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;

public class DataGenerator {

    public static void main (String[] args) throws IOException {
    	// get parameters from user
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Enter full location of source file: ");
		String fileLocation = br.readLine();

		System.out.print("How many records per minute? ");
		int rate = Integer.parseInt(br.readLine());

		// spit it back out for testing purposes..
		System.out.println("Full location is: " + fileLocation + ". Rate is " + rate + " records per minute.");

		Enrollment e = new Enrollment();

		System.out.println("Enrollment id is.. " + e.enrollment_id);

		System.out.println("Now reading source file...");

		// start reading lines from file
		BufferedReader brl = new BufferedReader(new FileReader(fileLocation));
		String line;
		while ( (line = brl.readLine()) != null ) {
			// process the line
			System.out.println(line);
			System.out.println("line has been read...");
		} 
    }
}

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
  	public Enrollment() { enrollment_id = 0; };
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
}