import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Box extends JPanel {

	private String name;
	private int cost;
	private int xCoordinate;
	private int yCoordinate;
	private int width = 100;
	private int height = width;
	private Color colorOfBox;
	private int locationIndex;
	private int rentVal;
	private boolean buyable=true;
	private boolean isFree=true;
	private Player owner = null;





	public Box(int index, String name, int cost, int xCoordinate, int yCoordinate, Color colorOfBox, int rent) {
		this.locationIndex = index;
		this.name = name;
		this.cost = cost;
		this.xCoordinate = xCoordinate; 
		this.yCoordinate = yCoordinate;
		this.colorOfBox = colorOfBox;
		this.rentVal = rent;




	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public boolean isBuyable() {
		return buyable;
	}
	public void setBuyable(boolean buyable) {
		this.buyable = buyable;
	}
	public boolean isFree() {
		return isFree;
	}
	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}
	public int getRentVal() {
		return rentVal;
	}
	public void setRentVal(int rentVal) {
		this.rentVal = rentVal;
	}
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getLocationIndex() {
		return locationIndex;
	}

	public void setLocationIndex(int locationIndex) {
		this.locationIndex = locationIndex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public Color getColorOfBox() {
		return colorOfBox;
	}

	public void setColorOfBox(Color colorOfBox) {
		this.colorOfBox = colorOfBox;
	}	



}
