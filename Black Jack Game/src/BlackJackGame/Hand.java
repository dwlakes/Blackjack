package BlackJackGame;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Hand {
	int points;
	ArrayList <Card> handArray;
	
	public Hand(int points,
			ArrayList<Card> handArray) {
		this.points=points;
		this.handArray=handArray;
	}

}
