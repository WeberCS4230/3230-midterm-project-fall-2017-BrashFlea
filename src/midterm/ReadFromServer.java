package midterm;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadFromServer implements Runnable {
    private ObjectInputStream in = null;
	
	public void run() {
		try {
			while(true) {
				in.readObject();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

} //end class
