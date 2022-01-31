package BlackJackGame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JTextField;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Game extends Application{
	Label userCardHit1Label = new Label("");
	Label userCardHit2Label = new Label("");
	Label userCardHit3Label = new Label("");
	Label userCardHit4Label = new Label("");
	Label resultsLabel = new Label("");
	Label dealerCardHit1Label = new Label("");
	Label dealerCardHit2Label = new Label("");
	Label dealerCardHit3Label = new Label("");
	Label dealerCardHit4Label = new Label("");
	Label currentBetLabel = new Label("");
	Label gameOverLabel = new Label("");
	Button btHit = new Button("Hit");
	Button btRetry = new Button("Next Round");
	Button btStand = new Button("Stand");
	int resetCounter=0;
	int bank = 1000;
	Label bankLabel = new Label("");
	Label bankLabel2 =new Label("");
	Spinner<Integer> betSpinner = new Spinner<>(25, bank, 25, 25);
	Button btnBet = new Button("Bet");
	String bet;
	
	

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		//Initializing the home/start screen
		Image background= new Image(getClass().getResourceAsStream("PokerTable.jpeg"));
		ImagePattern bgImage= new ImagePattern(background);
		Image openImage = new Image(getClass().getResourceAsStream("IntroPic.jpeg"));
		ImageView imgOpen =new ImageView(openImage);
		imgOpen.setFitWidth(300);
		imgOpen.setFitHeight(200);
		imgOpen.setLayoutX(110);
		imgOpen.setLayoutY(50);
		
		//Button to start the game
		Button btPlay = new Button("Play");
		btPlay.setLayoutX(230);
		btPlay.setLayoutY(350);
		
		  
		Group rootOpen= new Group(imgOpen,btPlay);
		Scene scene = new Scene(rootOpen, 535,500);
		scene.setFill(bgImage); 
		primaryStage.setScene(scene);
		primaryStage.show();
		
		  
		 //Listens for click on button play
		   btPlay.setOnAction(e -> {
			try {
				mainGame(primaryStage);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
	}
	
	
	
		//mainGame method essentially allows for restarting the game without going back to the home screen
		void mainGame(Stage primaryStage) throws FileNotFoundException{
			
			//dec shuffle sound effect
			File file = new File("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/Shuffle.wav");
			Clip shuffle = null;
			try {
				shuffle = AudioSystem.getClip();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			try {
				shuffle.open(AudioSystem.getAudioInputStream(file));
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			shuffle.start();
			
			//Sets up betting scene
			
			
		//Creates an image for the back of the card
		InputStream imageBackStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/BackSide.png");
		Image imageBack = new Image(imageBackStream);
		//ImageView imageView4 = new ImageView(imageBack);
		
		//Creating card objects (spades) in chronological order
		Card twoSpade = new Card(2, 0, "Spade", null, null, null);
		InputStream twoSpadeStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/2Spade.png");
		Image twoSpadeImage = new Image (twoSpadeStream);
		twoSpade.image=twoSpadeImage;
		twoSpade.imageBack=imageBack;
		
		Card threeSpade = new Card(3, 0, "Spade", null, null, null);
		InputStream threeSpadeStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/3Spade.png");
		Image threeSpadeImage = new Image (threeSpadeStream);
		threeSpade.image=threeSpadeImage;
		threeSpade.imageBack=imageBack;
		
		Card fourSpade = new Card(4, 0, "Spade", null, null,null);
		InputStream fourSpadeStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/4Spade.png");
		Image fourSpadeImage = new Image (fourSpadeStream);
		fourSpade.image=fourSpadeImage;
		fourSpade.imageBack=imageBack;
		
		Card fiveSpade = new Card(5, 0, "Spade", null, null,null);
		InputStream fiveSpadeStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/5Spade.png");
		Image fiveSpadeImage = new Image (fiveSpadeStream);
		fiveSpade.image=fiveSpadeImage;
		fiveSpade.imageBack=imageBack;
		
		Card sixSpade = new Card(6, 0, "Spade", null, null,null);
		InputStream sixSpadeStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/6Spade.png");
		Image sixSpadeImage = new Image (sixSpadeStream);
		sixSpade.image=sixSpadeImage;
		sixSpade.imageBack=imageBack;
		
		Card sevenSpade = new Card(7, 0, "Spade", null, null,null);
		InputStream sevenSpadeStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/7Spade.png");
		Image sevenSpadeImage = new Image (sevenSpadeStream);
		sevenSpade.image=sevenSpadeImage;
		sevenSpade.imageBack=imageBack;
		
		Card eightSpade = new Card(8, 0, "Spade", null, null,null);
		InputStream eightSpadeStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/8Spade.png");
		Image eightSpadeImage = new Image (eightSpadeStream);
		eightSpade.image=eightSpadeImage;
		eightSpade.imageBack=imageBack;
		
		Card nineSpade = new Card(9, 0, "Spade", null, null,null);
		InputStream nineSpadeStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/9Spade.png");
		Image nineSpadeImage = new Image (nineSpadeStream);
		nineSpade.image=nineSpadeImage;
		nineSpade.imageBack=imageBack;
		
		Card tenSpade = new Card(10, 0, "Spade", null, null,null);
		InputStream tenSpadeStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/10Spade.png");
		Image tenSpadeImage = new Image (tenSpadeStream);
		tenSpade.image=tenSpadeImage;
		tenSpade.imageBack=imageBack;
		
		Card jackSpade = new Card(10, 0, "Spade","Jack", null, null);
		InputStream jackSpadeStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/JackSpade.png");
		Image jackSpadeImage = new Image (jackSpadeStream);
		jackSpade.image=jackSpadeImage;
		jackSpade.imageBack=imageBack;
		
		Card queenSpade = new Card(10, 0, "Spade","Queen", null, null);
		InputStream queenSpadeStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/QueenSpade.png");
		Image queenSpadeImage = new Image (queenSpadeStream);
		queenSpade.image=queenSpadeImage;
		queenSpade.imageBack=imageBack;
		
		Card kingSpade = new Card(10, 0, "Spade", "King", null, null);
		InputStream kingSpadeStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/KingSpade.png");
		Image kingSpadeImage = new Image (kingSpadeStream);
		kingSpade.image=kingSpadeImage;
		kingSpade.imageBack=imageBack;
		
		Card aceSpade = new Card(11, 1, "Spade", "Ace", null, null);
		InputStream aceSpadeStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/AceSpade.png");
		Image aceSpadeImage = new Image (aceSpadeStream);
		aceSpade.image=aceSpadeImage;
		aceSpade.imageBack=imageBack;
		
		//Creating clubs in chronological order
		
		Card twoClub = new Card(2, 0, "Club", null, null, null);
		InputStream twoClubStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/2Club.png");
		Image twoClubImage = new Image (twoClubStream);
		twoClub.image=twoClubImage;
		twoClub.imageBack=imageBack;
		
		Card threeClub = new Card(3, 0, "Club", null, null, null);
		InputStream threeClubStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/3Club.png");
		Image threeClubImage = new Image (threeClubStream);
		threeClub.image=threeClubImage;
		threeClub.imageBack=imageBack;
		
		Card fourClub = new Card(4, 0, "Club", null, null,null);
		InputStream fourClubStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/4Club.png");
		Image fourClubImage = new Image (fourClubStream);
		fourClub.image=fourClubImage;
		fourClub.imageBack=imageBack;
		
		Card fiveClub = new Card(5, 0, "Club", null, null,null);
		InputStream fiveClubStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/5Club.png");
		Image fiveClubImage = new Image (fiveClubStream);
		fiveClub.image=fiveClubImage;
		fiveClub.imageBack=imageBack;
		
		Card sixClub = new Card(6, 0, "Club", null, null,null);
		InputStream sixClubStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/6Club.png");
		Image sixClubImage = new Image (sixClubStream);
		sixClub.image=sixClubImage;
		sixClub.imageBack=imageBack;
		
		Card sevenClub = new Card(7, 0, "Club", null, null,null);
		InputStream sevenClubStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/7Club.png");
		Image sevenClubImage = new Image (sevenClubStream);
		sevenClub.image=sevenClubImage;
		sevenClub.imageBack=imageBack;
		
		Card eightClub = new Card(8, 0, "Club", null, null,null);
		InputStream eightClubStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/8Club.png");
		Image eightClubImage = new Image (eightClubStream);
		eightClub.image=eightClubImage;
		eightClub.imageBack=imageBack;
		
		Card nineClub = new Card(9, 0, "Club", null, null,null);
		InputStream nineClubStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/9Club.png");
		Image nineClubImage = new Image (nineClubStream);
		nineClub.image=nineClubImage;
		nineClub.imageBack=imageBack;
		
		Card tenClub = new Card(10, 0, "Club", null, null,null);
		InputStream tenClubStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/10Club.png");
		Image tenClubImage = new Image (tenClubStream);
		tenClub.image=tenClubImage;
		tenClub.imageBack=imageBack;
		
		Card jackClub = new Card(10, 0, "Club","Jack", null, null);
		InputStream jackClubStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/JackClub.png");
		Image jackClubImage = new Image (jackClubStream);
		jackClub.image=jackClubImage;
		jackClub.imageBack=imageBack;
		
		Card queenClub = new Card(10, 0, "Club","Queen", null, null);
		InputStream queenClubStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/QueenClub.png");
		Image queenClubImage = new Image (queenClubStream);
		queenClub.image=queenClubImage;
		queenClub.imageBack=imageBack;
		
		Card kingClub = new Card(10, 0, "Club", "King", null, null);
		InputStream kingClubStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/KingClub.png");
		Image kingClubImage = new Image (kingClubStream);
		kingClub.image=kingClubImage;
		kingClub.imageBack=imageBack;
		
		Card aceClub = new Card(11, 1, "Club", "Ace", null, null);
		InputStream aceClubStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/AceClub.png");
		Image aceClubImage = new Image (aceClubStream);
		aceClub.image=aceClubImage;
		aceClub.imageBack=imageBack;
		
		
		//Creating cards in hearts
		
		Card twoHeart = new Card(2, 0, "Heart", null, null, null);
		InputStream twoHeartStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/2Heart.png");
		Image twoHeartImage = new Image (twoHeartStream);
		twoHeart.image=twoHeartImage;
		twoHeart.imageBack=imageBack;
		
		Card threeHeart = new Card(3, 0, "Heart", null, null, null);
		InputStream threeHeartStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/3Heart.png");
		Image threeHeartImage = new Image (threeHeartStream);
		threeHeart.image=threeHeartImage;
		threeHeart.imageBack=imageBack;
		
		Card fourHeart = new Card(4, 0, "Heart", null, null,null);
		InputStream fourHeartStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/4Heart.png");
		Image fourHeartImage = new Image (fourHeartStream);
		fourHeart.image=fourHeartImage;
		fourHeart.imageBack=imageBack;
		
		Card fiveHeart = new Card(5, 0, "Heart", null, null,null);
		InputStream fiveHeartStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/5Heart.png");
		Image fiveHeartImage = new Image (fiveHeartStream);
		fiveHeart.image=fiveHeartImage;
		fiveHeart.imageBack=imageBack;
		
		Card sixHeart = new Card(6, 0, "Heart", null, null,null);
		InputStream sixHeartStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/6Heart.png");
		Image sixHeartImage = new Image (sixHeartStream);
		sixHeart.image=sixHeartImage;
		sixHeart.imageBack=imageBack;
		
		Card sevenHeart = new Card(7, 0, "Heart", null, null,null);
		InputStream sevenHeartStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/7Heart.png");
		Image sevenHeartImage = new Image (sevenHeartStream);
		sevenHeart.image=sevenHeartImage;
		sevenHeart.imageBack=imageBack;
		
		Card eightHeart = new Card(8, 0, "Heart", null, null,null);
		InputStream eightHeartStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/8Heart.png");
		Image eightHeartImage = new Image (eightHeartStream);
		eightHeart.image=eightHeartImage;
		eightHeart.imageBack=imageBack;
		
		Card nineHeart = new Card(9, 0, "Heart", null, null,null);
		InputStream nineHeartStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/9Heart.png");
		Image nineHeartImage = new Image (nineHeartStream);
		nineHeart.image=nineHeartImage;
		nineHeart.imageBack=imageBack;
		
		Card tenHeart = new Card(10, 0, "Heart", null, null,null);
		InputStream tenHeartStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/10Heart.png");
		Image tenHeartImage = new Image (tenHeartStream);
		tenHeart.image=tenHeartImage;
		tenHeart.imageBack=imageBack;
		
		Card jackHeart = new Card(10, 0, "Heart","Jack", null, null);
		InputStream jackHeartStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/JackHeart.png");
		Image jackHeartImage = new Image (jackHeartStream);
		jackHeart.image=jackHeartImage;
		jackHeart.imageBack=imageBack;
		
		Card queenHeart = new Card(10, 0, "Heart","Queen", null, null);
		InputStream queenHeartStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/QueenHeart.png");
		Image queenHeartImage = new Image (queenHeartStream);
		queenHeart.image=queenHeartImage;
		queenHeart.imageBack=imageBack;
		
		Card kingHeart = new Card(10, 0, "Heart", "King", null, null);
		InputStream kingHeartStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/KingHeart.png");
		Image kingHeartImage = new Image (kingHeartStream);
		kingHeart.image=kingHeartImage;
		kingHeart.imageBack=imageBack;
		
		Card aceHeart = new Card(11, 1, "Heart", "Ace", null, null);
		InputStream aceHeartStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/AceHeart.png");
		Image aceHeartImage = new Image (aceHeartStream);
		aceHeart.image=aceHeartImage; 
		aceHeart.imageBack=imageBack;
		
		
		
		//Creating cards in diamonds
		Card twoDiamond = new Card(2, 0, "Diamond ", null, null, null);
		InputStream twoDiamondStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/2Diamond.png");
		Image twoDiamondImage = new Image (twoDiamondStream);
		twoDiamond.image=twoDiamondImage;
		twoDiamond.imageBack=imageBack;
		
		Card threeDiamond = new Card(3, 0, "Diamond", null, null, null);
		InputStream threeDiamondStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/3Diamond.png");
		Image threeDiamondImage = new Image (threeDiamondStream);
		threeDiamond.image=threeDiamondImage;
		threeDiamond.imageBack=imageBack;
		
		Card fourDiamond = new Card(4, 0, "Diamond", null, null,null);
		InputStream fourDiamondStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/4Diamond.png");
		Image fourDiamondImage = new Image (fourDiamondStream);
		fourDiamond.image=fourDiamondImage;
		fourDiamond.imageBack=imageBack;
		
		Card fiveDiamond = new Card(5, 0, "Diamond", null, null,null);
		InputStream fiveDiamondStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/5Diamond.png");
		Image fiveDiamondImage = new Image (fiveDiamondStream);
		fiveDiamond.image=fiveDiamondImage;
		fiveDiamond.imageBack=imageBack;
		
		Card sixDiamond = new Card(6, 0, "Diamond", null, null,null);
		InputStream sixDiamondStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/6Diamond.png");
		Image sixDiamondImage = new Image (sixDiamondStream);
		sixDiamond.image=sixDiamondImage;
		sixDiamond.imageBack=imageBack;
		
		Card sevenDiamond = new Card(7, 0, "Diamond", null, null,null);
		InputStream sevenDiamondStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/7Diamond.png");
		Image sevenDiamondImage = new Image (sevenDiamondStream);
		sevenDiamond.image=sevenDiamondImage;
		sevenDiamond.imageBack=imageBack;
		
		Card eightDiamond = new Card(8, 0, "Diamond", null, null,null);
		InputStream eightDiamondStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/8Diamond.png");
		Image eightDiamondImage = new Image (eightDiamondStream);
		eightDiamond.image=eightDiamondImage;
		eightDiamond.imageBack=imageBack;
		
		Card nineDiamond = new Card(9, 0, "Diamond", null, null,null);
		InputStream nineDiamondStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/9Diamond.png");
		Image nineDiamondImage = new Image (nineDiamondStream);
		nineDiamond.image=nineDiamondImage;
		nineDiamond.imageBack=imageBack;
		
		Card tenDiamond = new Card(10, 0, "Diamond", null, null,null);
		InputStream tenDiamondStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/10Diamond.png");
		Image tenDiamondImage = new Image (tenDiamondStream);
		tenDiamond.image=tenDiamondImage;
		tenDiamond.imageBack=imageBack;
		
		Card jackDiamond = new Card(10, 0, "Diamond","Jack", null, null);
		InputStream jackDiamondStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/JackDiamond.png");
		Image jackDiamondImage = new Image (jackDiamondStream);
		jackDiamond.image=jackDiamondImage;
		jackDiamond.imageBack=imageBack;
		
		Card queenDiamond = new Card(10, 0, "Diamond","Queen", null, null);
		InputStream queenDiamondStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/QueenDiamond.png");
		Image queenDiamondImage = new Image (queenDiamondStream);
		queenDiamond.image=queenDiamondImage;
		queenDiamond.imageBack=imageBack;
		
		Card kingDiamond = new Card(10, 0, "Diamond", "King", null, null);
		InputStream kingDiamondStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/KingDiamond.png");
		Image kingDiamondImage = new Image (kingDiamondStream);
		kingDiamond.image=kingDiamondImage;
		kingDiamond.imageBack=imageBack;
		
		Card aceDiamond = new Card(11, 1, "Diamond", "Ace", null, null);
		InputStream aceDiamondStream = new FileInputStream("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/AceDiamond.png");
		Image aceDiamondImage = new Image (aceDiamondStream);
		aceDiamond.image=aceDiamondImage; 
		aceDiamond.imageBack=imageBack;
		
		//Creating an array list for the cards
		ArrayList<Card> deckArray = new ArrayList<Card>();
		Collections.addAll(deckArray, twoSpade, threeSpade, fourSpade, fiveSpade, sixSpade, sevenSpade, eightSpade, nineSpade, tenSpade,
				jackSpade, queenSpade, kingSpade, aceSpade,
				twoClub, threeClub, fourClub, fiveClub, sixClub, sevenClub, eightClub, nineClub, tenClub,
				jackClub, queenClub, kingClub, aceClub,
				twoHeart, threeHeart, fourHeart, fiveHeart, sixHeart, sevenHeart, eightHeart, nineHeart, tenHeart,
				jackHeart, queenHeart, kingHeart, aceHeart,
				twoDiamond, threeDiamond, fourDiamond, fiveDiamond, sixDiamond, sevenDiamond, eightDiamond, nineDiamond, tenDiamond,
				jackDiamond, queenDiamond, kingDiamond, aceDiamond);
		
		Collections.shuffle(deckArray);
		
		//Creates an iterator to go through the deck
		ListIterator<Card> iterator= deckArray.listIterator();
		
		//Creates arraylists for the hand of the user and dealer
		ArrayList<Card>userHandArray= new ArrayList<Card>();
		ArrayList<Card>dealerHandArray= new ArrayList<Card>();
		
		
		userHandArray.add(iterator.next());
		iterator.next();
		dealerHandArray.add(iterator.next());
		userHandArray.add(iterator.next());
		iterator.next();
		dealerHandArray.add(iterator.next());
		
		//Creats an object to hold the cards and points of the dealer and user
		Hand userHand = new Hand(0, null);
		Hand dealerHand = new Hand(0, null);
		
		//initaliaztes array
		//userHand.points = 0;
		userHand.handArray=userHandArray;
		dealerHand.handArray=dealerHandArray;
		
		
		
		//Creates image view
		ImageView imageView = new ImageView(userHand.handArray.get(0).image);
		ImageView imageView2 = new ImageView(userHand.handArray.get(1).image);
		ImageView imageView3 = new ImageView(dealerHand.handArray.get(0).image);
		ImageView imageView4 = new ImageView(dealerHand.handArray.get(1).imageBack);
		//ImageView bgImage = new ImageView(backgroundImage);
		
		
		
		
		//Layouts for images and buttons
		imageView.setPreserveRatio(true);
	    imageView.setFitWidth(100);
	    imageView.setFitHeight(100);      
	    imageView.setX(200);
		imageView.setY(300);
		imageView.setVisible(false);
		
		imageView2.setX(225);
		imageView2.setY(300);
	    imageView2.setPreserveRatio(true);
	    imageView2.setFitWidth(100);
	    imageView2.setFitHeight(100);
		imageView2.setVisible(false);

	    
	    imageView3.setX(200);
		imageView3.setY(100);
	    imageView3.setPreserveRatio(true);
	    imageView3.setFitWidth(100);
	    imageView3.setFitHeight(100);
		imageView3.setVisible(false);


	    imageView4.setX(225);
		imageView4.setY(100);
	    imageView4.setPreserveRatio(true);
	    imageView4.setFitWidth(100);
	    imageView4.setFitHeight(100);
		imageView4.setVisible(false);

	    
	    //button layouts
		btHit.setVisible(false);
	    btHit.setLayoutX(10);
	    btHit.setLayoutY(450);
	    
	    
	    btRetry.setVisible(false);
	    btRetry.setLayoutX(100);
	    btRetry.setLayoutY(450);
	    
	    btStand.setVisible(false);
	    btStand.setLayoutX(190);
	    btStand.setLayoutY(450);
	    
	    
	    gameOverLabel.setVisible(false);
	    gameOverLabel.setLayoutX(177);
	    gameOverLabel.setLayoutY(250);
	    gameOverLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
	    gameOverLabel.setTextFill(Color.BEIGE);
	    
	    resultsLabel.setLayoutX(350);
	    resultsLabel.setLayoutY(450);
	    resultsLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
	    resultsLabel.setTextFill(Color.BEIGE);
	    
	    
	    //hit cards labels layout
	    userCardHit1Label.setLayoutX(147);
	    userCardHit1Label.setLayoutY(25);
	    userCardHit2Label.setLayoutX(162);
	    userCardHit2Label.setLayoutY(25);
	    userCardHit3Label.setLayoutX(177);
	    userCardHit3Label.setLayoutY(25);
	    userCardHit4Label.setLayoutX(192);
	    userCardHit4Label.setLayoutY(25);
	    dealerCardHit1Label.setLayoutX(147);
	    dealerCardHit1Label.setLayoutY(25);
	    dealerCardHit2Label.setLayoutX(162);
	    dealerCardHit2Label.setLayoutY(25);
	    dealerCardHit3Label.setLayoutX(177);
	    dealerCardHit3Label.setLayoutY(25);
	    dealerCardHit4Label.setLayoutX(192);
	    dealerCardHit4Label.setLayoutY(25);
	    betSpinner.setLayoutX(190);
	    betSpinner.setLayoutY(210);
	    btnBet.setLayoutX(230);
	    btnBet.setLayoutY(275);
	    currentBetLabel.setLayoutX(10);
	    currentBetLabel.setLayoutY(425);
	    currentBetLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
	    currentBetLabel.setTextFill(Color.BEIGE);
	    bankLabel.setLayoutX(10);
	    bankLabel.setLayoutY(400);
	    bankLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
	    bankLabel.setTextFill(Color.BEIGE);
	    bankLabel2.setLayoutX(10);
	    bankLabel2.setLayoutY(400);
	    bankLabel2.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
	    bankLabel2.setTextFill(Color.BEIGE);
	    
	    
	    
	   
	   
	   //Sets up background image
	   
	    Image background= new Image(getClass().getResourceAsStream("PokerTable.jpeg"));
		  ImagePattern bgImage= new ImagePattern(background);
	  	//Creating main part
	  	//adding elements to window 
		
	    Group root = new Group (imageView, imageView2,imageView3,imageView4,btHit,btRetry,userCardHit1Label,
	    		userCardHit2Label,userCardHit3Label,userCardHit4Label,btStand,dealerCardHit1Label,dealerCardHit2Label,
	    		dealerCardHit3Label,dealerCardHit4Label,resultsLabel, currentBetLabel,bankLabel2,gameOverLabel);
	     //root.getChildren().add(cardHit1Label);
	    Scene scene1 = new Scene(root, 535,500);
	    bankLabel.setText("Bank: $"+bank);
		Group preRoot = new Group(btnBet,betSpinner,bankLabel);
		Scene preScene = new Scene(preRoot,535,500);
	
		preScene.setFill(bgImage);
		scene1.setFill(bgImage);
		primaryStage.setScene(preScene);
		primaryStage.show();
		btnBet.setOnAction(e -> {
			bet = betSpinner.getValue().toString();
			int betInt = Integer.parseInt(bet);
			bank = bank-betInt;
			currentBetLabel.setText("Current bet: $"+bet);
			bankLabel2.setText("Bank: $"+bank);
			//calls the method to do the initial dealing of the game
			initialDealMethod(imageView,imageView2,imageView3,imageView4);
			
			//launches scene of main game
			
		primaryStage.setScene(scene1);
	});
		//resets the button
		btRetry.setText("Next Round");
		
	    //Checks for user and dealer Black Jack 
	    if ((userHand.handArray.get(0).type==("Ace")&&userHand.handArray.get(1).value==10)||
	    		(userHand.handArray.get(1).type==("Ace")&&userHand.handArray.get(0).value==10)) {
	    	if((dealerHand.handArray.get(0).type==("Ace")&&dealerHand.handArray.get(1).value==10)||
		    		(dealerHand.handArray.get(1).type==("Ace")&&dealerHand.handArray.get(0).value==10)) {
	    		String firstHalf = "Dealer and user";
	    		String secondHalf = "Black Jack! Tie!";
	    		resultsLabel.setText("Dealer and user" +'\n' + "Black Jack. Tie!");
				imageView4.setImage(dealerHand.handArray.get(1).image);
				btStand.setDisable(true);
				btHit.setDisable(true);
				btRetry.setVisible(true);
	    	}else {
	    		resultsLabel.setText("User Black Jack!");
				imageView4.setImage(dealerHand.handArray.get(1).image);
				btStand.setDisable(true);
				btHit.setDisable(true);
				btRetry.setVisible(true);
				//Adds user win to bank
				userWin();
	    	}
		
				
			//Checks for dealer Black Jack for win
	    }else if ((dealerHand.handArray.get(0).type==("Ace")&&dealerHand.handArray.get(1).value==10)||
	    		(dealerHand.handArray.get(1).type==("Ace")&&dealerHand.handArray.get(0).value==10)){
		resultsLabel.setText("Dealer Black Jack!" +'\n' + "You lose!");
		imageView4.setImage(dealerHand.handArray.get(1).image);
		btHit.setDisable(true);
		btRetry.setVisible(true);
		btStand.setDisable(true);
		imageView4.setImage(dealerHand.handArray.get(1).image);
		userLose(userHand,dealerHand,primaryStage);
	
	    }
	    
	 
	   
	    //methdod and hit button action to hit hand
	    btHit.setOnAction(e -> userHit(iterator,userHand,dealerHand,imageView4)); 
	    btStand.setOnAction(e -> stand(iterator,userHand,dealerHand,imageView4,primaryStage));
	    
	    //Resets game
	    btRetry.setOnAction(e -> {
			count = 0;
			
			try {
				
				userCardHit1Label.setGraphic(null);
				userCardHit2Label.setGraphic(null);
				userCardHit3Label.setGraphic(null);
				userCardHit4Label.setGraphic(null);
				dealerCardHit1Label.setGraphic(null);
				dealerCardHit2Label.setGraphic(null);
				dealerCardHit3Label.setGraphic(null);
				dealerCardHit4Label.setGraphic(null);
				resultsLabel.setText("");
				btHit.setDisable(false);
				btStand.setDisable(false);
				btRetry.setVisible(false);
				userHand.points=0;
				userHand.handArray.removeAll(userHand.handArray);
				dealerHand.points=0;
				dealerHand.handArray.removeAll(userHand.handArray);
				dealerCount =2;
				count =2;
				mainGame(primaryStage);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	
		}

		//adds user's bet bank to bank
	public void tie() {
			int betInt = Integer.parseInt(bet);
			bank= bank+betInt;
			System.out.println(bank);
		}
	//Adds user win to bank
	public void userWin() {
		int betInt = Integer.parseInt(bet);
		bank = bank + (betInt*2);
		System.out.println(bank);
			
		}
	//checks to see if the user runs out of money
	public void userLose(Hand userHand, Hand dealerHand, Stage primaryStage) {
		System.out.println("Lost");
		if (bank<1) {
			System.out.print("Bank is 0");
			//btRetry.setVisible(false);
			btHit.setVisible(false);
			btStand.setVisible(false);
			gameOverLabel.setVisible(true);
			gameOverLabel.setText("You went broke! Try again?");
			//btnNewGame.setVisible(true);
			bank = 1000;
			btRetry.setText("New Game?");
				count = 0;
				
				
			
			
		}
		System.out.println(bank);
	}



	//deals cards
	private void initialDealMethod(ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4) {
		shuffleSound();
		Timer timer = new Timer();
		   timer.schedule(new TimerTask() {
			   //first loop keeps any card from being dealt 
			@Override
			public void run() {
				hitSound();
				imageView.setVisible(true);
				timer.schedule(new TimerTask() {

				@Override
					public void run() {
						hitSound();
						imageView3.setVisible(true);
						timer.schedule(new TimerTask() {

				@Override
						public void run() {
							hitSound();
							imageView2.setVisible(true);
							timer.schedule(new TimerTask() {

				@Override
							public void run() {
								hitSound();
								imageView4.setVisible(true);
								timer.schedule(new TimerTask() {

					@Override
								public void run() {
									btStand.setVisible(true);
									btHit.setVisible(true);
									
								}
					   
								}, 500);
								
							}
				   
							}, 500);
							
						}
				    
						}, 500);
		  
					}
				   
					}, 500);
		   
			}
			   
		   }, 500);
			
		}



	private void shuffleSound() {
		//dec shuffle sound effect
		File file = new File("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/Shuffle.wav");
		Clip shuffle = null;
		try {
			shuffle = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		try {
			shuffle.open(AudioSystem.getAudioInputStream(file));
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shuffle.start();		
	}



	private void stand(ListIterator<Card> iterator, Hand userHand, Hand dealerHand,
			ImageView imageView4, Stage primaryStage) {
		
		//In case the user's first move was to stand, is user's hand is calculated again
		userHand.points= 0;
		boolean ace = false;
		for(int i = 0; i < userHand.handArray.size(); i++) {
			//System.out.println(userHand.handArray.get(0).type +userHand.handArray.get(1).type );
			
			
			
			if(userHand.handArray.get(i).type=="Ace") {
				ace = true;
				System.out.println("You have an ace");	
			}
		
			userHand.points += userHand.handArray.get(i).value;;
			
			//if the hand breaks 21 and has an ace, the ace value is reassigned to one and the 
			//hand points value is recalculated
			if (userHand.points>21 && ace==true) {
				userHand.points = 0;
				for( i = 0; i < userHand.handArray.size(); i++) {
					if(userHand.handArray.get(i).type=="Ace") {
						userHand.handArray.get(i).value=userHand.handArray.get(i).otherValue;
						System.out.println("Ace new value "+userHand.handArray.get(i).value);
						
						
					}userHand.points += userHand.handArray.get(i).value;
					System.out.println("New hand points value "+userHand.points);
				}
			
			}
			
			System.out.println(userHand.points);
		
			if (userHand.points>21) {
				imageView4.setImage(dealerHand.handArray.get(1).image);
				resultsLabel.setText("Bust");
				btHit.setDisable(true);
				btRetry.setVisible(true);
				userHand.handArray.removeAll(userHand.handArray);
				btStand.setDisable(true);
				btHit.setDisable(true);
				btRetry.setVisible(true);
				userLose(userHand,dealerHand,primaryStage);
			}
		
		}
		
		//calls the hit sound sound effect
		hitSound();
		//btHit.setDisable(true);
		System.out.println("You stand");
		System.out.println("User points after stand " +userHand.points);
		imageView4.setImage(dealerHand.handArray.get(1).image);
			
		dealerHand.points = 0;
		for(int i = 0; i < dealerHand.handArray.size(); i++) {
			//Tells the dealer what to do and it's resulting consequences ie hitting
			dealerHand.points+=dealerHand.handArray.get(i).value;
			System.out.println("Dealer pounts before hit"+dealerHand.points);
			} 
			//Determines if the dealer needs a hit or not	
		imageView4.setImage(dealerHand.handArray.get(1).image);
		while (dealerHand.points<17 ||dealerHand.points<userHand.points) {
				
			
			dealerHit(dealerHand,iterator, userHand,imageView4);
			
				
			}
		imageView4.setImage(dealerHand.handArray.get(1).image);
		//This line of codes determines if the dealer wins with just their two initial cards
		if(dealerHand.handArray.size()==2 && userHand.points<dealerHand.points) {
			System.out.println("Dealer wins with two cards " +dealerHand.points);
			resultsLabel.setText("Dealer wins");
			btHit.setDisable(true);
			btRetry.setVisible(true);
			btStand.setDisable(true);
		}else if(dealerHand.handArray.size()==2 && userHand.points>dealerHand.points) {
				System.out.println("Dealer loses with two cards " +dealerHand.points);
				resultsLabel.setText("Player wins");
				btHit.setDisable(true);
				btRetry.setVisible(true);
				btStand.setDisable(true);
				userWin();
			
		}else if(dealerHand.handArray.size()==2 && dealerHand.points==userHand.points) {
			imageView4.setImage(dealerHand.handArray.get(1).image);
			resultsLabel.setText("Tie");
			btHit.setDisable(true);
			btRetry.setVisible(true);
			btStand.setDisable(true);
			//adds user's bet back to hand
			tie();
			
		}
		imageView4.setImage(dealerHand.handArray.get(1).image);
		if (dealerHand.points>21) {
			imageView4.setImage(dealerHand.handArray.get(1).image);
			resultsLabel.setText("Dealer Bust");
			btHit.setDisable(true);
			btRetry.setVisible(true);
			//Adds the user win to bank
			userWin();
			
			
		}else if (dealerHand.points>userHand.points) {
			imageView4.setImage(dealerHand.handArray.get(1).image);
			resultsLabel.setText("Dealer wins" );
			btHit.setDisable(true);
			btRetry.setVisible(true);
			btStand.setDisable(true);
			//checks to see if the user still has money in the bank
			userLose(userHand,dealerHand,primaryStage);
			System.out.println("Lose 1");
			
		}else if(dealerHand.points<userHand.points) {
			imageView4.setImage(dealerHand.handArray.get(1).image);
			resultsLabel.setText("You win");
			btHit.setDisable(true);
			btRetry.setVisible(true);
			btStand.setDisable(true);
			//Adds the user win to bank
			userWin();
			System.out.println("Win 1");
			
				
		}else if(dealerHand.points==userHand.points) {
			imageView4.setImage(dealerHand.handArray.get(1).image);
			resultsLabel.setText("Tie");
			btHit.setDisable(true);
			btRetry.setVisible(true);
			btStand.setDisable(true);
			ImageView dealerCardHitFront = new ImageView(dealerHand.handArray.get(2).image);
			dealerCardHit1Label.setGraphic(dealerCardHitFront);
			//Adds the user's bet back to the bank 
			tie();
			System.out.println("Tie 1");
		
			
		}
	}		


				

int dealerCount=2;
	//Determines the consequences of a dealer hit
	private int dealerHit(Hand dealerHand, ListIterator<Card> iterator, Hand userHand, ImageView imageView4) {
		imageView4.setImage(dealerHand.handArray.get(1).image);
	
		System.out.println("Method works");
		System.out.println("User hand after dealer hit" + userHand.points);
		//adds card to userhand
		dealerHand.handArray.add(iterator.next());
		hitSound();
	
		imageView4.setImage(dealerHand.handArray.get(1).image);					
	//adds dealer hit cards
	if (dealerCount==2) {
		 
		   	
		ImageView dealerCardHit1 =new ImageView(dealerHand.handArray.get(dealerCount).image);
		  
		System.out.println("Should add card "+ (dealerCount-1));
		
		dealerCardHit1Label.setGraphic(dealerCardHit1);
		dealerCardHit1Label.setLayoutX(105);
		dealerCardHit1Label.setLayoutY(-114);
		dealerCardHit1Label.setScaleX(.2);
		dealerCardHit1Label.setScaleY(.19);
		dealerCount++;	
		

	}else if(dealerCount == 3) {
		
		ImageView dealerCardHit2 =new ImageView(dealerHand.handArray.get(dealerCount).image);

		dealerCardHit2Label.setGraphic(dealerCardHit2);
		dealerCardHit2Label.setTranslateX(-35);
		dealerCardHit2Label.setTranslateY(-140);
		dealerCardHit2Label.setScaleX(.2);
		dealerCardHit2Label.setScaleY(.19);
		dealerCount++;							
				
					
			
		
	}else if(dealerCount == 4) {
		
		hitSound();
		ImageView dealerCardHit3 =new ImageView(dealerHand.handArray.get(dealerCount).image);

		dealerCardHit3Label.setGraphic(dealerCardHit3);
		dealerCardHit3Label.setTranslateX(-25);
		dealerCardHit3Label.setTranslateY(-140);
		dealerCardHit3Label.setScaleX(.2);
		dealerCardHit3Label.setScaleY(.19);
		dealerCount++;	
					
		
		
	
	}else if (dealerCount ==5) {
		
		ImageView dealerCardHit4 =new ImageView(dealerHand.handArray.get(dealerCount).image);

		dealerCardHit4Label.setGraphic(dealerCardHit4);
		dealerCardHit4Label.setTranslateX(-35);
		dealerCardHit4Label.setTranslateY(-140);
		dealerCardHit4Label.setScaleX(.2);
		dealerCardHit4Label.setScaleY(.19);
		dealerCount++;	
					
			}
						
			
			//Adds up the userHand points
			dealerHand.points= 0;
			boolean ace = false;
			for(int i = 0; i < dealerHand.handArray.size(); i++) {
				
				
				//Checks dealer hand for an ace 
				if(dealerHand.handArray.get(i).type=="Ace") {
					ace = true;
					System.out.println("You have an ace");	
				}
			
				dealerHand.points += dealerHand.handArray.get(i).value;
				
				//if the hand breaks 21 and has an ace, the ace value is reassigned to one and the 
				//hand points value is recalculated
				if (dealerHand.points>21 && ace==true) {
					dealerHand.points = 0;
					for( i = 0; i < dealerHand.handArray.size(); i++) {
						if(dealerHand.handArray.get(i).type=="Ace") {
							dealerHand.handArray.get(i).value=dealerHand.handArray.get(i).otherValue;
							System.out.println("Dealer Ace new value "+dealerHand.handArray.get(i).value);
							
							
						}dealerHand.points += dealerHand.handArray.get(i).value;
						System.out.println("New dealer hand points value "+dealerHand.points);
					}
				
				}
				
				System.out.println(dealerHand.points);
			
			 
			}
				
			return dealerHand.points;	
			
				
				}
			
	

	//counter adds cards to userHand
	int count=2;
	@SuppressWarnings("unchecked")
	public int userHit( ListIterator<Card> iterator, Hand userHand,Hand dealerHand, ImageView imageView4) {
		
		//adds card to userhand
		userHand.handArray.add(iterator.next());
		//calls the hit sound effect method
		hitSound();
	
		
		if (count==2) {
			ImageView userCardHit1 =new ImageView(userHand.handArray.get(count).image);
		
			userCardHit1Label.setGraphic(userCardHit1);
			userCardHit1Label.setTranslateX(-35);
			userCardHit1Label.setTranslateY(61);
			userCardHit1Label.setScaleX(.2);
			userCardHit1Label.setScaleY(.19);
			count++;
		}else if(count == 3) {
			ImageView userCardHit2 =new ImageView(userHand.handArray.get(count).image);
			
			userCardHit2Label.setGraphic(userCardHit2);
			userCardHit2Label.setTranslateX(-25);
			userCardHit2Label.setTranslateY(61);
			userCardHit2Label.setScaleX(.2);
			userCardHit2Label.setScaleY(.19);
			count++;
		}else if(count == 4) {
			ImageView userCardHit3 =new ImageView(userHand.handArray.get(count).image);
			
			userCardHit3Label.setGraphic(userCardHit3);
			userCardHit3Label.setTranslateX(-15);
			userCardHit3Label.setTranslateY(61);
			userCardHit3Label.setScaleX(.2);
			userCardHit3Label.setScaleY(.19);
			count++;
		
		}else if (count ==5) {
			ImageView userCardHit4 =new ImageView(userHand.handArray.get(count).image);
			userCardHit4Label.setGraphic(userCardHit4);
			userCardHit4Label.setTranslateX(-5);
			userCardHit4Label.setTranslateY(61);
			userCardHit4Label.setScaleX(.2);
			userCardHit4Label.setScaleY(.19);
			count++;
		}
		//Adds up the userHand points
		userHand.points= 0;
		boolean ace = false;
		for(int i = 0; i < userHand.handArray.size(); i++) {
			//System.out.println(userHand.handArray.get(0).type +userHand.handArray.get(1).type );
			
			
			
			if(userHand.handArray.get(i).type=="Ace") {
				ace = true;
				System.out.println("You have an ace");	
			}
		
			userHand.points += userHand.handArray.get(i).value;;
			
			//if the hand breaks 21 and has an ace, the ace value is reassigned to one and the 
			//hand points value is recalculated
			if (userHand.points>21 && ace==true) {
				userHand.points = 0;
				for( i = 0; i < userHand.handArray.size(); i++) {
					if(userHand.handArray.get(i).type=="Ace") {
						userHand.handArray.get(i).value=userHand.handArray.get(i).otherValue;
						System.out.println("Ace new value "+userHand.handArray.get(i).value);
						
						
					}userHand.points += userHand.handArray.get(i).value;
					System.out.println("New hand points value "+userHand.points);
				}
			
			}
			
			System.out.println(userHand.points);
		
			if (userHand.points>21) {
				imageView4.setImage(dealerHand.handArray.get(1).image);
				resultsLabel.setText("Bust");
				btHit.setDisable(true);
				btRetry.setVisible(true);
				userHand.handArray.removeAll(userHand.handArray);
				btStand.setDisable(true);
				btHit.setDisable(true);
				btRetry.setVisible(true);
				userLose(dealerHand, dealerHand, null);
	
			}
		
		}
		return userHand.points;					
			
	}
	//hit sound sound effect	
	private void hitSound() {
		File file = new File("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/CardHit.wav");
		Clip hit = null;
		try {
			hit = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		try {
			hit.open(AudioSystem.getAudioInputStream(file));
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hit.start();
		
	}	
			

	public static void main(String[] args) {
		
//Background music
File file = new File("/Users/dwlakes/Desktop/Java Projects/Black Jack Cards/bensound-theelevatorbossanova.wav");
Clip song = null;
try {
	song = AudioSystem.getClip();
} catch (LineUnavailableException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}  
try {
	song.open(AudioSystem.getAudioInputStream(file));
} catch (LineUnavailableException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (UnsupportedAudioFileException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

song.loop(Clip.LOOP_CONTINUOUSLY);
Application.launch(args);
	
	}

	
}