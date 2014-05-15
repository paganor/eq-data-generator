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
    }
}