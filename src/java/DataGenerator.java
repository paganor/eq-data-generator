import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
		enrollment_id = enrollment_id;
	};
}