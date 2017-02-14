package chessgame;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Save {
	
	public boolean SaveData(GameState sd) {

		FileOutputStream fout = null;
		ObjectOutputStream oos = null;

		try {
			fout = new FileOutputStream("savedata.sav");
			oos = new ObjectOutputStream(fout);
			
			oos.writeObject(sd);
			return true;

		} catch (Exception e) {

			e.printStackTrace();
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
	
	public GameState LoadData() {

		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fis = new FileInputStream("savedata.sav");
			ois = new ObjectInputStream(fis);
			return (GameState) ois.readObject();

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {

			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
}
