import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;

public class Die {

	private int faceValue= 0;
	Random rand = new Random();
	public Die(){
	}

	public void Roll() {


		int f = rand.nextInt(6) + 1;
		this.setFaceValue(f);
	}




	public int getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(int faceValue) {
		this.faceValue = faceValue;
	}

}






