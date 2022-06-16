import telas.*;

class Main {
	public static void main(String[] args) {
		try {
			new TelaEscolha();
			// new TelaLoginNew();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
