public class DataGenerator {
	public static void main(String args[]) {
		System.out.println("Hello, world!");
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

		public void publish() {
			System.out.println("Publishing event via REST");
		}
	}
}