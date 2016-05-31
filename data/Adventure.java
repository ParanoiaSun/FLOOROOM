package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Adventure implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 剧情模式保存
	 */
	/*private int story;
	
	public Story(){
		load();
	}

	public int getStory() {
		load();
		return story;
	}

	public void setStory(int story) {
		this.story = story;
		save();
	}
	
	public void load(){
		if (!new File("data/story.dat").exists()){
			save();
			return;
		}
		Story story = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("data/story.dat"));
			story = (Story) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.story=story.story;
	}
	
	public void save(){
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("data/story.dat"));
			oos.writeObject(this);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/

}
