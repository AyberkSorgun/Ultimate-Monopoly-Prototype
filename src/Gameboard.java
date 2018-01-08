import java.util.*;
import java.awt.*;
//import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.*;
import javafx.event.ActionEvent;

public class Gameboard extends JPanel {

	private static  Box[] gameBoard;
	private static JFrame fr = new JFrame("Game");
	private static JPanel jp = new JPanel();
	private static Die die1 = new Die();
	private static Die die2 = new Die();
	private static JButton jb = new JButton("Roll");
	private static JButton buy = new JButton("Buy");
	private static JButton pay = new JButton("Pay");
	private static JButton rollOnce = new JButton("Roll once");
	private static JButton squeeze = new JButton("Squeeze");
	private static JButton nothing = new JButton("Nothing");
	private static Player p1;
	private static Player p2;
	private static boolean doubles = die1.getFaceValue() == die2.getFaceValue();
	private static boolean stop = true;
	private static boolean paid = false;


	public Gameboard(){

		gameBoard = new Box[20];

		gameBoard[0]= new Box(0,"Go", 0, 20,20, Color.DARK_GRAY,0);
		gameBoard[1]= new Box(1,"Oriental Ave", 100, 120,20, Color.BLUE,6);
		gameBoard[2]= new Box(2,"Community Chest", 0, 220,20, Color.BLACK,0);
		gameBoard[3]= new Box(3,"Vermont", 100, 320,20, Color.BLUE,6);
		gameBoard[4]= new Box(4,"Connecticut Ave", 120, 420,20, Color.BLUE,8);
		gameBoard[5]= new Box(5,"Roll once", 0, 520,20, Color.BLACK,0);
		gameBoard[6]= new Box(6,"St. Charles Palace", 140, 520,120, Color.PINK,10);
		gameBoard[7]= new Box(7,"Chance", 0, 520,220, Color.BLACK,0);
		gameBoard[8]= new Box(8,"States Ave", 140, 520,320, Color.PINK,10);
		gameBoard[9]= new Box(9,"Virginia Ave", 160, 520,420, Color.PINK,12);
		gameBoard[10]= new Box(10,"Free Parking", 0, 520,520, Color.BLACK,0);
		gameBoard[11]= new Box(11,"St. James Palace", 180, 420,520, Color.ORANGE,14);
		gameBoard[12]= new Box(12,"Community Chest", 0, 320,520, Color.BLACK,0);
		gameBoard[13]= new Box(13,"Tenessee Ave", 180, 220,520, Color.ORANGE,14);
		gameBoard[14]= new Box(14,"New York Ave", 200, 120,520, Color.ORANGE,16);
		gameBoard[15]= new Box(15,"Squeeze Play", 0, 20,520, Color.BLACK,0);
		gameBoard[16]= new Box(16,"Pacific Ave", 300, 20,420, Color.GREEN,26);
		gameBoard[17]= new Box(17,"North Carolina Ave", 300, 20,320, Color.GREEN,26);
		gameBoard[18]= new Box(18,"Chance", 0, 20,220, Color.BLACK,0);
		gameBoard[19]= new Box(19,"Pennysylvania Ave", 320, 20,120, Color.GREEN,28);

		gameBoard[0].setBuyable(false);
		gameBoard[2].setBuyable(false);
		gameBoard[5].setBuyable(false);
		gameBoard[7].setBuyable(false);
		gameBoard[10].setBuyable(false);
		gameBoard[12].setBuyable(false);
		gameBoard[15].setBuyable(false);
		gameBoard[18].setBuyable(false);

		p1 = new Player("Player1");
		p1.locate(70, 70);
		p2 = new Player("Player2");
		p2.locate(80, 70);
		p1.setTurn(true);
		p2.setTurn(false);




	}





	public static boolean p1sTurn() {

		if(p1.isTurn())
			return true;
		else
			return false;
	}

	public static void buy(Player p, Box box) {

		if(box.isFree() && box.isBuyable() && (p.getBalance() > box.getCost())){
			box.setOwner(p);
			p.setBalance(p.getBalance()-box.getCost());
			box.setFree(false);
			ArrayList<Box> assets = p.getAssets();
			assets.add(box);
			p.setAssets(assets);
		}
		buy.setEnabled(false);
		switchTurns(p1,p2);
		jb.setEnabled(true);
//		System.out.println(p.getName() +" bought " + box.getName() );
	}

	public static void payRent(Player p, Box box) {

		if(!box.isFree() && box.isBuyable() && box.getOwner() != p) {

			p.setBalance(p.getBalance()-box.getRentVal());
			box.getOwner().setBalance(box.getOwner().getBalance()+box.getRentVal());


		}
		pay.setEnabled(false);
		switchTurns(p1, p2);
		jb.setEnabled(true);
		paid = true;
//		System.out.println(p.getName()+ " paid rent for "+box.getName());

	}




	public static void printPlayerAssets(ArrayList<Box> properties, Graphics g,int x) {

		for(int i = 0;i< properties.size(); i++) {
			g.drawString(properties.get(i).getName(), 650+x, 80+(15*i));
		}


	}

	public static void squeezePlay(Die d1, Die d2) {

		Player taker;
		Player giver;

		if(p1.isTurn()) {
			taker = p1;
			giver = p2;
		}
		else {
			taker = p2;
			giver = p1;
		}

		rollEmAll(d1, d2);
		int total = d1.getFaceValue() + d2.getFaceValue();
		if(total == 2 || total == 12) {
			taker.setBalance(taker.getBalance()+200);
			giver.setBalance(giver.getBalance()-200);

		}
		else if(total == 5|| total ==  6|| total == 7 || total == 8 || total == 9) {
			taker.setBalance(taker.getBalance()+50);
			giver.setBalance(giver.getBalance()-50);
		}
		else {
			taker.setBalance(taker.getBalance()+100);
			giver.setBalance(giver.getBalance()-100);
		}



	}


	public static void switchTurns(Player p1, Player p2) {

		if(p1.isTurn()) {
			p1.setTurn(false);
			p2.setTurn(true);
		}
		else {

			p1.setTurn(true);
			p2.setTurn(false);
		}



	}


	public void movingPlayer(Player p, Die dice1, Die dice2, Graphics g) {

		int var = 0;
		g.setColor(Color.RED);
		if(p == p2) {
			var = 20;
			g.setColor(Color.BLUE);
		}
		int totalFaceValues = dice1.getFaceValue()+dice2.getFaceValue();
		int newLocationIndex = (p.getLocationIndex() + totalFaceValues) %20;

		if(newLocationIndex< p.getLocationIndex()) {

			p.setBalance(p.getBalance()+200);
		}


		p.setLocationIndex(newLocationIndex);
		p.locate(gameBoard[newLocationIndex].getxCoordinate()+40+var, gameBoard[newLocationIndex].getyCoordinate()+50);
		if(dice1.getFaceValue()+dice2.getFaceValue()!=0) {
			if(gameBoard[newLocationIndex].isBuyable() && !gameBoard[newLocationIndex].isFree() && gameBoard[newLocationIndex].getOwner() != p ) {
				nothing.setEnabled(false);
				pay.setEnabled(true);
				paid = false;

			}
			if(paid) {
				pay.setEnabled(false);
				nothing.setEnabled(true);
			}
			if(gameBoard[newLocationIndex].isFree() && gameBoard[newLocationIndex].isBuyable()) {
				buy.setEnabled(true);
				nothing.setEnabled(true);
			}
			if(!gameBoard[newLocationIndex].isBuyable()) {
				nothing.setEnabled(true);
			}

			if(newLocationIndex == 5){
				rollOnce.setEnabled(true);
				nothing.setEnabled(false);
				jb.setEnabled(false);
			}

			if(newLocationIndex == 15){
				squeeze.setEnabled(true);
				nothing.setEnabled(false);
				jb.setEnabled(false);
			}




		}
		g.fillOval(gameBoard[newLocationIndex].getxCoordinate()+40+var,gameBoard[newLocationIndex].getyCoordinate()+50, 10, 10);

	}

	//	dice rolling
	public static void rollEmAll(Die die1, Die die2) {
		die1.Roll();
		die2.Roll();
		if(die1.getFaceValue() == die2.getFaceValue()) {
			doubles = true;

		}
		else {
			doubles = false;
		}

	}
	//drawing part
	public void paint(Graphics g) {

		for(int i = 0;i<6;i++) {
			if(i == 0) g.setColor(Color.DARK_GRAY);
			else if(i==1||i==3||i==4) g.setColor(Color.BLUE);
			else g.setColor(Color.BLACK);
			g.fillRect(20+(100*i),20,100,20);
		}

		for(int i = 0;i<5;i++) {
			if(i==0||i==3||i==2) g.setColor(Color.PINK);
			else g.setColor(Color.BLACK);
			g.fillRect(520,120+(100*i),100,20);
		}

		for(int i = 0;i<5;i++) {
			if(i==0||i==3||i==2) g.setColor(Color.ORANGE);
			else g.setColor(Color.BLACK);
			g.fillRect(420-(100*i),520,100,20);
		}

		for(int i = 0;i<4;i++) {
			if(i==0||i==3||i==1) g.setColor(Color.GREEN);
			else g.setColor(Color.BLACK);
			g.fillRect(20,420-(100*i),100,20);
		}

		for(int i = 0;i<6;i++) {
			g.setColor(Color.BLACK);
			g.drawRect(20+(100*i),20,100,100);
			g.drawRect(20,20+(100*i),100,100);
			g.drawRect(520,20+(100*i),100,100);
			g.drawRect(20+(100*i),520,100,100);
		}

		Font f = new Font ("asd", 4 ,7);
		Font bf = new Font("fgh",4,12);
		g.setFont(f);
		g.drawString("0 Go", 50, 60);
		g.drawString("1 Oriental Ave", 150, 60);
		g.drawString("2 Community Chest", 235, 60);
		g.drawString("3 Vermont Ave", 350, 60);
		g.drawString("4 Connecticut Ave\n" + "", 450, 60);
		g.drawString("5 Roll once",550, 60);
		g.drawString("6 St. Charles Place", 550, 160);
		g.drawString("7 Chance", 550, 260);
		g.drawString("8 States Ave\n" + "", 550, 360);
		g.drawString("9 Virginia Ave\n" + "", 550, 460);
		g.drawString("10 Free Parking\n" + "", 550, 560);
		g.drawString("11 St. James Place",450, 560);
		g.drawString("12 Community Chest\n" + "",330, 560);
		g.drawString("13 Tennessee Ave\n" + "",250, 560);
		g.drawString("14 New York Ave\n" + "",150, 560);
		g.drawString("15 Squeeze Play\n" + "", 50, 560);
		g.drawString("16 Pacific Ave\n" + "", 50, 460);
		g.drawString("17 North Carolina Ave", 35, 360);
		g.drawString("18 Chance", 50, 260);
		g.drawString("19 Pennsylvania Ave\n" + "", 35, 160);
		g.drawString("$100", 165, 90);
		g.drawString("", 250, 90);
		g.drawString("$100", 350, 90);
		g.drawString("$120" + "", 450, 90);
		g.drawString("$140", 550, 190);
		g.drawString("", 550, 290);
		g.drawString("$160" + "", 550, 390);
		g.drawString("$160" + "", 550, 490);
		g.drawString("" + "", 550, 590);
		g.drawString("$180",450, 590);
		g.drawString("$180",250, 590);
		g.drawString("$200" + "",150, 590);
		g.drawString("" + "", 50, 590);
		g.drawString("$300", 50, 490);
		g.drawString("$300", 35, 390);
		g.drawString("", 50, 290);
		g.drawString("$320", 35, 190);
		g.drawRect(190, 200,120,80);
		g.drawRect(360, 400,120,80);
		g.drawString("Chance", 240, 240);
		g.drawString("Community Chest",390, 440);
		g.setFont(bf);
		g.drawString(""+die1.getFaceValue(), 700, 250);
		g.drawString(""+die2.getFaceValue(), 720, 250);
		if(stop) {
			die1 = new Die();
			die2 = new Die();
		}
		if(p1.isTurn()) {
			g.fillRect(637,50, 10, 10);
			movingPlayer(p1, die1, die2, g);
			movingPlayer(p2,new Die(),new Die(),g);

		}
		else {
			g.fillRect(757,50, 10, 10);
			movingPlayer(p2, die1, die2, g);
			movingPlayer(p1,new Die(),new Die(),g);
		}

		stop = true;
		g.setColor(Color.RED);
		g.drawString("Player1:\n"+p1.getBalance(), 650,60 );
		printPlayerAssets(p1.getAssets(), g,0);
		g.setColor(Color.BLUE);
		g.drawString("Player2:\n"+p2.getBalance(), 770,60 );
		printPlayerAssets(p2.getAssets(), g,120);





	}
	public static void rollOnce() {
		rollEmAll(die1, die2);   //bu zarın display edilmesi gerekiyor
		if(doubles) {
			if(p1.isTurn()) {
				p1.setBalance(p1.getBalance()+100);
			}else {
				p2.setBalance(p2.getBalance()+100);
			}
		}
		rollOnce.setEnabled(false);
		switchTurns(p1,p2);
		jb.setEnabled(true);
	}
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {

				fr.setVisible(true);
				fr.setSize(1024, 768);
				fr.add(jp);
				jp.setVisible(true);
				jp.setLocation(670,260);
				jp.setSize(80,200);
				jp.add(jb);
				jp.add(buy);
				jp.add(pay);
				jp.add(nothing);
				jp.add(rollOnce);
				jp.add(squeeze);
				buy.setVisible(true);
				pay.setVisible(true);
				nothing.setVisible(true);
				jb.setVisible(true);
				fr.add(new Gameboard());
				buy.setEnabled(false);
				pay.setEnabled(false);
				nothing.setEnabled(false);
				rollOnce.setEnabled(false);
				squeeze.setEnabled(false);


				jb.addActionListener(new ActionListener() {
					// public void actionPerformed(ActionEvent e) {
					//}

					@Override
					public void actionPerformed(java.awt.event.ActionEvent e) {
						// TODO Auto-generated method stub
						rollEmAll(die1,die2);
						stop = false;
						fr.repaint();
						jb.setEnabled(false);

					}


				});
				buy.addActionListener(new ActionListener() {
					// public void actionPerformed(ActionEvent e) {
					//}

					@Override
					public void actionPerformed(java.awt.event.ActionEvent e) {
						// TODO Auto-generated method stub
						if(p1sTurn()) {
							buy(p1,gameBoard[p1.getLocationIndex()]);
						}	
						else{
							buy(p2,gameBoard[p2.getLocationIndex()]);

						}

						fr.repaint();
					}


				});


				nothing.addActionListener(new ActionListener() {
					// public void actionPerformed(ActionEvent e) {
					//}

					@Override
					public void actionPerformed(java.awt.event.ActionEvent e) {
						// TODO Auto-generated method stub
						if(!doubles)
							switchTurns(p1,p2);
						jb.setEnabled(true);
						buy.setEnabled(false);
						fr.repaint();
					}


				});

				pay.addActionListener(new ActionListener() {
					// public void actionPerformed(ActionEvent e) {
					//}

					@Override
					public void actionPerformed(java.awt.event.ActionEvent e) {
						// TODO Auto-generated method stub
						if(p1sTurn() && gameBoard[p1.getLocationIndex()].getOwner()!=p1) {
							payRent(p1,gameBoard[p1.getLocationIndex()]);
							pay.setEnabled(false);

						}	
						else{
							if(gameBoard[p1.getLocationIndex()].getOwner()!=p2) {
								payRent(p2,gameBoard[p2.getLocationIndex()]);
							}


						}
						pay.setEnabled(false);

						fr.repaint();
					}



				});

				rollOnce.addActionListener(new ActionListener() {
					// public void actionPerformed(ActionEvent e) {
					//}

					@Override
					public void actionPerformed(java.awt.event.ActionEvent e) {
						// TODO Auto-generated method stub
						rollOnce();
						fr.repaint();
						rollOnce.setEnabled(false);

					}


				});

				squeeze.addActionListener(new ActionListener() {
					// public void actionPerformed(ActionEvent e) {
					//}

					@Override
					public void actionPerformed(java.awt.event.ActionEvent e) {
						// TODO Auto-generated method stub
						squeezePlay(die1, die2);
						squeeze.setEnabled(false);
						switchTurns(p1,p2);
						jb.setEnabled(true);
						fr.repaint();

					}


				});




			}
		});

	}


}


