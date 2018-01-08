import java.util.ArrayList;
import java.util.HashMap;

public class Player {
	private boolean turn = true;
	private String name;
	private int balance = 3200;
	private ArrayList<Box> assets = new ArrayList<Box>();
	private int positionX;
	private int positionY;
	private int locationIndex;

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public void locate(int newX, int newY) {
		positionX=newX;
		positionY= newY;

	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}




	public int getLocationIndex() {
		return locationIndex;
	}

	public void setLocationIndex(int locationIndex) {
		this.locationIndex = locationIndex;
	}

	public Player(String playerName) {

		name = playerName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public ArrayList<Box> getAssets() {
		return assets;
	}

	public void setAssets(ArrayList<Box> assets) {
		this.assets = assets;
	}

}
