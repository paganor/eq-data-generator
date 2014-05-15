public class Enrollment {
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

	// basic constructor
	public Enrollment(int enrollment_id, String enrollment_dtmm, String enrollment_utc,
					  int efx_enrollee_data_src_id, int exp_enrollee_data_src_id, int tu_enrollee_data_src_id,
					  String configuration_cd, String configuration_desc, String partner_name,
					  String tenant_name, String efx_status, String efx_modify_dttm, String efx_modify_utc,
					  String exp_status, String exp_modify_dtmm, String exp_modify_utc,
					  String tu_status, String tu_modify_dttm, String tu_modify_utc,
					  String city, String state, String country, int postal_cd);
}