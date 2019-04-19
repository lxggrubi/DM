package driver;

import view.DietView;

public class DietManager {

	public static void main(String[] args) {

		try {
			DietView mainView = new DietView();
		} catch (Throwable ex) {
			System.err.println("Uncaught exception - " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}