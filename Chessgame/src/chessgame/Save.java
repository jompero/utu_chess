package chessgame;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Save {
	
	public boolean SaveData(GameState sd) {

		FileOutputStream fout = null;
		ObjectOutputStream oos = null;

		try {

			fout = new FileOutputStream("/savedata.sav");
			oos = new ObjectOutputStream(fout);
			oos.writeObject(sd);

			System.out.println("Saved");
			return true;

		} catch (Exception ex) {

			ex.printStackTrace();
			return false;

		} finally {

			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
}
