package BlackJackGame;

import java.io.InputStream;

import javafx.scene.image.Image;

public class Card {
	int value;
	int otherValue;
	String suit;
	String type;
	//InputStream stream;
    Image image;
    //InputStream streamBack;
    Image imageBack;
	public Card(int value,
			int otherValue,
			String suit,
			String type,
			//InputStream stream,
			Image image,
			//InputStream streamBack,
    		Image imageBack) {
		this.value=value;
		this.otherValue=otherValue;
		this.suit=suit;
		this.type=type;
		//this.stream=stream;
		this.image=image;
		//this.streamBack=streamBack;
		this.imageBack=imageBack;
	}
	

}
