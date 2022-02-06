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
	Label userSplit1CardHit1Label = new Label("");
	Label userSplit1CardHit2Label = new Label("");
	Label userSplit1CardHit3Label = new Label("");
	Label userSplit1CardHit4Label = new Label("");
	Label userSplit2CardHit1Label = new Label("");
	Label userSplit2CardHit2Label = new Label("");
	Label userSplit2CardHit3Label = new Label("");
	Label userSplit2CardHit4Label = new Label("");
	Label currentBetLabel = new Label("");
	Label gameOverLabel = new Label("");
	Button btHit = new Button("Hit");
	Button btRetry = new Button("Next Round");
	Button btStand = new Button("Stand");
	Button btnDouble = new Button("Double Down");
	Button btnDoubleSplit1 = new Button ("Double Down" + '\n'+
											"Split 1");
	Button btnDoubleSplit2 = new Button ("Double Down" + '\n'+
											"Split 2");
	Button btnSplit = new Button("Split");
	Button btnStandSplit1 = new Button ("Stand Split 1");
	Button btnStandSplit2 = new Button ("Stand Split 2");
	Button btnHitSplit1 = new Button("Hit Split 1");
	Button btnHitSplit2 = new Button("Hit Split 2");
	int resetCounter=0;
	int bank = 1000;
	Label bankLabel = new Label("");
	Label bankLabel2 =new Label("");
	Spinner<Integer> betSpinner = new Spinner<>(25, bank, 25, 25);
	Button btnBet = new Button("Bet");
	String bet;
	boolean splitBoolean = false;
	boolean split1Bust = false;
	boolean split2Bust = false;
	int split1Bet;
	int split2Bet;
	Label split1BetLabel = new Label("");
	Label split2BetLabel = new Label("");
	
	

	
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
		ArrayList<Card>userHandSplit1Array= new ArrayList<Card>();
		ArrayList<Card>userHandSplit2Array= new ArrayList<Card>();
		
		
		userHandArray.add(iterator.next());
		iterator.next();
		dealerHandArray.add(iterator.next());
		iterator.next();
		userHandArray.add(iterator.next());
		iterator.next();
		dealerHandArray.add(iterator.next());
		
		//Creats an object to hold the cards and points of the dealer and user
		Hand userHand = new Hand(0, null);
		Hand dealerHand = new Hand(0, null);
		Hand userHandSplit1 = new Hand(0,null);
		Hand userHandSplit2 = new Hand(0,null);
		
		//initaliaztes hand arrays
		userHand.handArray=userHandArray;
		dealerHand.handArray=dealerHandArray;
		
		
		//Clones the first card card of the userHand's array
		try {
			Card split1Card1 = (Card)userHand.handArray.get(0).clone();
			userHandSplit1Array.add(split1Card1);
		} catch (CloneNotSupportedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//Clones the second card card of the userHand's array
		try {
			Card split2Card1 = (Card)userHand.handArray.get(1).clone();
			userHandSplit2Array.add(split2Card1);
		} catch (CloneNotSupportedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		//adds the clones to their respective split hands (first clone goes to first split, second clone to second split)
		userHandSplit1.handArray=userHandSplit1Array;
		userHandSplit2.handArray=userHandSplit2Array;
		
		System.out.println("Dealer hand size " +dealerHand.handArray.size());
		
		
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
	    
	    btnSplit.setVisible(false);
	    btnSplit.setLayoutX(50);
	    btnSplit.setLayoutY(450);
	    
	    btnHitSplit1.setVisible(false);
	    btnHitSplit1.setLayoutX(10);
	    btnHitSplit1.setLayoutY(370);
	    
	    btnHitSplit2.setVisible(false);
	    btnHitSplit2.setLayoutX(85);
	    btnHitSplit2.setLayoutY(370);
	    
	    btnStandSplit1.setVisible(false);
	    btnStandSplit1.setLayoutX(10);
	    btnStandSplit1.setLayoutY(335);
	    
	    btnStandSplit2.setVisible(false);
	    btnStandSplit2.setLayoutX(100);
	    btnStandSplit2.setLayoutY(335);
	    
	    btnDoubleSplit1.setVisible(false);
	    btnDoubleSplit1.setLayoutX(10);
	    btnDoubleSplit1.setLayoutY(285);
	    
	    btnDoubleSplit2.setVisible(false);
	    btnDoubleSplit2.setLayoutX(110);
	    btnDoubleSplit2.setLayoutY(285);
	    
	    split2BetLabel.setVisible(true);
	    split2BetLabel.setLayoutX(10);
	    split2BetLabel.setLayoutY(265);
	    split2BetLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 16));
	    split2BetLabel.setTextFill(Color.BEIGE);
	    
	    split1BetLabel.setVisible(true);
	    split1BetLabel.setLayoutX(10);
	    split1BetLabel.setLayoutY(245);
	    split1BetLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 16));
	    split1BetLabel.setTextFill(Color.BEIGE);
	   
	    btRetry.setVisible(false);
	    btRetry.setLayoutX(100);
	    btRetry.setLayoutY(450);
	    
	    btStand.setVisible(false);
	    btStand.setLayoutX(190);
	    btStand.setLayoutY(450);
	    
	    btnDouble.setVisible(false);
	    btnDouble.setLayoutX(250);
	    btnDouble.setLayoutY(450);
	   
	    
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
	    		dealerCardHit3Label,dealerCardHit4Label,resultsLabel, currentBetLabel,bankLabel2,gameOverLabel,btnDouble,btnSplit,
	    		userSplit1CardHit1Label,userSplit1CardHit2Label,userSplit1CardHit3Label,userSplit1CardHit4Label,btnHitSplit1,btnHitSplit2,
	    		btnStandSplit1,btnStandSplit2,userSplit2CardHit1Label,userSplit2CardHit2Label,userSplit2CardHit3Label,
	    		userSplit2CardHit4Label,btnDoubleSplit1,btnDoubleSplit2,split2BetLabel,split1BetLabel);
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
			initialDealMethod(imageView,imageView2,imageView3,imageView4,userHand);
			
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
	    		resultsLabel.setText("Dealer and user" +'\n' + "Black Jack. Tie!");
				imageView4.setImage(dealerHand.handArray.get(1).image);
				btStand.setDisable(true);
				btHit.setDisable(true);
				btRetry.setVisible(true);
				btnDouble.setDisable(true);
	    	}else {
	    		resultsLabel.setText("User Black Jack!");
				imageView4.setImage(dealerHand.handArray.get(1).image);
				btStand.setDisable(true);
				btHit.setDisable(true);
				btRetry.setVisible(true);
				btnDouble.setDisable(true);
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
		btnDouble.setDisable(false);
		imageView4.setImage(dealerHand.handArray.get(1).image);
		userLose(userHand,dealerHand,primaryStage);
	
	    }
	    
	 
	   
	    //methdod and hit button action to hit hand
	    btHit.setOnAction(e -> userHit(iterator,userHand,dealerHand,imageView4)); 
	    //User ends turn by standing
	    btStand.setOnAction(e -> stand(iterator,userHand,dealerHand,userHandSplit1,userHandSplit2,imageView4,primaryStage));
	    //User doubles their bet if they want, but only as their first move
	    btnDouble.setOnAction(e ->{
	    	int betInt = Integer.parseInt(bet);
	    	bank=bank-betInt;
	    	bankLabel2.setText("Bank: $"+bank);
	    	betInt=betInt*2;
	    	bet = String.valueOf(betInt);
	    	currentBetLabel.setText("Current bet: $"+betInt);
	    	userHit(iterator,userHand,dealerHand,imageView4);
	    	if(userHand.points>21) {
	    		imageView4.setImage(dealerHand.handArray.get(1).image);
				resultsLabel.setText("Bust");
				btHit.setDisable(true);
				btRetry.setVisible(true);
				userHand.handArray.removeAll(userHand.handArray);
				btStand.setDisable(true);
				btHit.setDisable(true);
				btRetry.setVisible(true);
				btnDouble.setDisable(true);
				userLose(userHand,dealerHand,primaryStage);
	    		
	    	}else {
	    	imageView4.setImage(dealerHand.handArray.get(1).image);
	    	System.out.println("Dealer hand size after double down " +dealerHand.handArray.size());
	    	stand(iterator,userHand,dealerHand,userHandSplit1,userHandSplit2,imageView4,primaryStage);
	    	btnDouble.setDisable(true);
	    	}
	    	
	    });
	    
	    btnSplit.setOnAction(e -> {
	    	btnHitSplit1.setDisable(false);
	    	btnStandSplit1.setDisable(false);
			btStand.setDisable(true);
	    	splitBoolean=true;
	    	imageView.setY(240);
	    	imageView2.setX(200);
	    	imageView2.setY(340);
	    	split1Hit(userHandSplit1,iterator,userHand,dealerHand,primaryStage);
	    	split2Hit(userHandSplit2,userHandSplit1,iterator,userHand,dealerHand,primaryStage,imageView4);
	    	btnSplit.setDisable(true);
	    	btHit.setDisable(true);
	    	btnDouble.setDisable(true);
	    	btnDoubleSplit1.setVisible(true);
	    	btnDoubleSplit1.setDisable(false);
	    	bank -=Integer.parseInt(bet); 
	    	bankLabel2.setText("Bank: $"+bank);
	    	split1Bet = Integer.parseInt(bet); 
	    	split2Bet = Integer.parseInt(bet); 
	    	System.out.println("Split 1 bet " + split1Bet);
	    	System.out.println("Split 2 bet " + split2Bet);
	    	currentBetLabel.setText("Initial bet: $" + bet );
	    	split2BetLabel.setText("Split 2 bet: $"+split2Bet);
	    	split1BetLabel.setText("Split 1 bet: $"+split1Bet);
	    	
	    });
	    //button to double down on split 1
	    btnDoubleSplit1.setOnAction(e ->{
	    	btnStandSplit1.setDisable(true);
	    	btnDoubleSplit1.setDisable(true);
	    	btnHitSplit1.setDisable(true);
	    	btnHitSplit2.setDisable(false);
	    	btnStandSplit2.setDisable(false);
	    	btnDoubleSplit2.setDisable(false);
	    	split1Hit(userHandSplit1,iterator,userHand,dealerHand,primaryStage);
	    	bank -= split1Bet;
	    	split1Bet = split1Bet *2;
	    	bankLabel2.setText("Bank: $"+bank);
	    	split1BetLabel.setText("Split 1 bet: $"+split1Bet);
	    	
	    });
	    
	    btnDoubleSplit2.setOnAction(e ->{
	    	btnStandSplit2.setDisable(true);
	    	btnDoubleSplit2.setDisable(true);
	    	btnHitSplit2.setDisable(true);
	    	btnHitSplit2.setDisable(true);
	    	btnStandSplit2.setDisable(true);
	    	btnDoubleSplit2.setDisable(true);
	    	split2Hit(userHandSplit2,userHandSplit1,iterator,userHand,dealerHand,primaryStage, imageView4);
	    	bank -= split2Bet;
	    	split2Bet = split2Bet *2;
	    	bankLabel2.setText("Bank: $"+bank);
	    	split2BetLabel.setText("Split 1 bet: $"+split2Bet);
	    	
	    });
	    
		//hit split one	
	    btnHitSplit1.setOnAction(e ->{
	    	btnDoubleSplit1.setDisable(true);
	    	split1Hit(userHandSplit1,iterator,userHand,dealerHand,primaryStage);
	    });
	    
	    btnHitSplit2.setOnAction(e ->{
	    	split2Hit(userHandSplit2,userHandSplit1,iterator,userHand,dealerHand,primaryStage, imageView4);
	    });
	    
	    btnStandSplit1.setOnAction(e -> {
	    	btnStandSplit1.setDisable(true);
	    	btnHitSplit1.setDisable(true);
	    	btnStandSplit2.setDisable(false);
	    	btnHitSplit2.setDisable(false);
	    	btnDoubleSplit1.setDisable(true);
	    	btnDoubleSplit2.setDisable(false);
	    	
	    	
	    });
	    
	    btnStandSplit2.setOnAction(e -> {
	    	btnStandSplit1.setDisable(true);
	    	btnHitSplit1.setDisable(true);
	    	btnStandSplit2.setDisable(true);
	    	btnHitSplit2.setDisable(true);
	    	btnDoubleSplit2.setDisable(true);
	    	stand(iterator,userHand,dealerHand,userHandSplit1,userHandSplit2,imageView4,primaryStage);
	    	
	    });
	    
	    
	    
	    
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
				userSplit1CardHit1Label.setGraphic(null);
				userSplit1CardHit2Label.setGraphic(null);
				userSplit1CardHit3Label.setGraphic(null);
				userSplit1CardHit4Label.setGraphic(null);
				userSplit2CardHit1Label.setGraphic(null);
				userSplit2CardHit2Label.setGraphic(null);
				userSplit2CardHit3Label.setGraphic(null);
				userSplit2CardHit4Label.setGraphic(null);
				split2BetLabel.setText("");
		    	split1BetLabel.setText("");
				resultsLabel.setText("");
				btHit.setDisable(false);
				btStand.setDisable(false);
				btRetry.setVisible(false);
				btnDouble.setDisable(false);
				userHand.points=0;
				userHand.handArray.removeAll(userHand.handArray);
				dealerHand.points=0;
				dealerHand.handArray.removeAll(userHand.handArray);
				userHandSplit1.points=0;
				userHandSplit1.handArray.removeAll(userHand.handArray);
				userHandSplit2.points=0;
				userHandSplit2.handArray.removeAll(userHand.handArray);
				dealerCount =2;
				count =2;
				split1Count=1;
				split2Count =1;
				split1Bust = false;
				split2Bust = false;
				splitBoolean = false;
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
		}
	//Adds user win to bank
	public void userWin() {
		int betInt = Integer.parseInt(bet);
		bank = bank + (betInt*2);
			
		}
	//checks to see if the user runs out of money
	public void userLose(Hand userHand, Hand dealerHand, Stage primaryStage) {
		System.out.println("Bank value "+bank);
		if (bank<1) {
			//btRetry.setVisible(false);
			btHit.setVisible(false);
			btStand.setVisible(false);
			gameOverLabel.setVisible(true);
			btnDouble.setVisible(false);
			gameOverLabel.setText("You went broke! Try again?");
			//btnNewGame.setVisible(true);
			bank = 1000;
			btRetry.setText("New Game?");
				count = 0;
				
		}
	}



	//deals cards
	private void initialDealMethod(ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, Hand userHand) {
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
									btnDouble.setVisible(true);
									btnSplit.setVisible(true);
									btnSplit.setDisable(true);
									btnHitSplit1.setVisible(true);
									btnHitSplit2.setVisible(true);
									btnHitSplit1.setDisable(true);
									btnHitSplit2.setDisable(true);
									btnStandSplit1.setVisible(true);
									btnStandSplit1.setDisable(true);
									btnStandSplit2.setVisible(true);
									btnStandSplit2.setDisable(true);
									btnDoubleSplit1.setVisible(true);
									btnDoubleSplit1.setDisable(true);
									btnDoubleSplit2.setVisible(true);
									btnDoubleSplit2.setDisable(true);
									
									if (userHand.handArray.get(0).type==userHand.handArray.get(1).type &&
								    		userHand.handArray.get(0).value==userHand.handArray.get(1).value) {
										btnSplit.setDisable(false);
									}
									
									
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
			Hand userHandSplit1, Hand userHandSplit2, ImageView imageView4, Stage primaryStage) {
		imageView4.setImage(dealerHand.handArray.get(1).image);
		
		btnDoubleSplit2.setDisable(true);
		
		//In case the user's first move was to stand, is user's hand is calculated again
		userHand.points= 0;
		boolean ace = false;
		for(int i = 0; i < userHand.handArray.size(); i++) {
			
			
			
			if(userHand.handArray.get(i).type=="Ace") {
				ace = true;
			}
		
			userHand.points += userHand.handArray.get(i).value;;
			
			//if the hand breaks 21 and has an ace, the ace value is reassigned to one and the 
			//hand points value is recalculated
			if (userHand.points>21 && ace==true) {
				userHand.points = 0;
				for( i = 0; i < userHand.handArray.size(); i++) {
					if(userHand.handArray.get(i).type=="Ace") {
						userHand.handArray.get(i).value=userHand.handArray.get(i).otherValue;
						
						
					}userHand.points += userHand.handArray.get(i).value;
				}
			
			}
			
		
			if (userHand.points>21) {
				imageView4.setImage(dealerHand.handArray.get(1).image);
				resultsLabel.setText("Bust");
				btHit.setDisable(true);
				btRetry.setVisible(true);
				userHand.handArray.removeAll(userHand.handArray);
				btStand.setDisable(true);
				btHit.setDisable(true);
				btRetry.setVisible(true);
				btnDouble.setDisable(true);
				userLose(userHand,dealerHand,primaryStage);
			}
		
		}
		
		//calls the hit sound sound effect
		hitSound();
		//btHit.setDisable(true);
		System.out.println("Dealer hand array size after play :"+ dealerHand.handArray.size());
		//imageView4.setImage(dealerHand.handArray.get(1).image);
			
		dealerHand.points = 0;
		for(int i = 0; i < dealerHand.handArray.size(); i++) {
			//Tells the dealer what to do and it's resulting consequences ie hitting
			dealerHand.points+=dealerHand.handArray.get(i).value;
			} 
			//Determines if the dealer needs a hit or not	
		
		while (dealerHand.points<17) {
				
			
			dealerHit(dealerHand,iterator, userHand,imageView4);
			
				
			}
		//imageView4.setImage(dealerHand.handArray.get(1).image);
		
		if (splitBoolean == true ) {
			if (split1Bust==false && split2Bust == false) {
				if(userHandSplit1.points>dealerHand.points && userHandSplit2.points>dealerHand.points && dealerHand.points<22) {
					resultsLabel.setText("Split 1 and 2 win!");
					btHit.setDisable(true);
					btRetry.setVisible(true);
					btStand.setDisable(true);
					btnDouble.setDisable(true);
					btnDoubleSplit2.setDisable(true);
					split1Win();
					split2Win();
					
				}else if(userHandSplit1.points<dealerHand.points && userHandSplit2.points>dealerHand.points && dealerHand.points<22) {
					resultsLabel.setText("Split 1 loses!" + '\n'+
											"Split 2 wins!");
					btHit.setDisable(true);
					btRetry.setVisible(true);
					btStand.setDisable(true);
					btnDouble.setDisable(true);	
					btnDoubleSplit2.setDisable(true);
					split2Win();
					userLose(userHand,dealerHand,primaryStage);
				}else if(userHandSplit1.points>dealerHand.points && userHandSplit2.points<dealerHand.points && dealerHand.points<22) {
					resultsLabel.setText("Split 1 wins!" + '\n'+
							"Split 2 loses!");
					btHit.setDisable(true);
					btRetry.setVisible(true);
					btStand.setDisable(true);
					btnDouble.setDisable(true);	
					btnDoubleSplit2.setDisable(true);
					split1Win();
					userLose(userHand,dealerHand,primaryStage);
				}else if(userHandSplit1.points<dealerHand.points && userHandSplit2.points<dealerHand.points && dealerHand.points<22) {
					resultsLabel.setText("Split 1 and 2 lose!");
					btHit.setDisable(true);
					btRetry.setVisible(true);
					btStand.setDisable(true);
					btnDouble.setDisable(true);	
					btnDoubleSplit2.setDisable(true);
					userLose(userHand,dealerHand,primaryStage);
				}else if (dealerHand.points>21) {
					resultsLabel.setText("Dealer Bust");
					btHit.setDisable(true);
					btStand.setDisable(true);
					btRetry.setVisible(true);
					btnDouble.setDisable(true);
					btnDoubleSplit2.setDisable(true);
					//Adds the user win to bank
					split1Win();
					split2Win();
				}else if(userHandSplit1.points>dealerHand.points && userHandSplit2.points==dealerHand.points && dealerHand.points<22) {
					resultsLabel.setText("Split 1 wins!" + '\n'+
							"Split 2 ties!");
					btHit.setDisable(true);
					btRetry.setVisible(true);
					btStand.setDisable(true);
					btnDouble.setDisable(true);	
					btnDoubleSplit2.setDisable(true);
					split1Win();
					split2Tie();
				}else if(userHandSplit1.points==dealerHand.points && userHandSplit2.points>dealerHand.points && dealerHand.points<22) {
					resultsLabel.setText("Split 1 ties!" + '\n'+
							"Split 2 wins!");
					btHit.setDisable(true);
					btRetry.setVisible(true);
					btStand.setDisable(true);
					btnDouble.setDisable(true);	
					btnDoubleSplit2.setDisable(true);
					split1Tie();
					split2Win();
				}else if(userHandSplit1.points==dealerHand.points && userHandSplit2.points<dealerHand.points && dealerHand.points<22) {
					resultsLabel.setText("Split 1 ties!" + '\n'+
							"Split 2 loses!");
					btHit.setDisable(true);
					btRetry.setVisible(true);
					btStand.setDisable(true);
					btnDouble.setDisable(true);	
					btnDoubleSplit2.setDisable(true);
					split1Tie();
					userLose(userHand,dealerHand,primaryStage);
				}else if(userHandSplit1.points<dealerHand.points && userHandSplit2.points==dealerHand.points && dealerHand.points<22) {
					resultsLabel.setText("Split 1 loses!" + '\n'+
							"Split 2 ties!");
					btHit.setDisable(true);
					btRetry.setVisible(true);
					btStand.setDisable(true);
					btnDouble.setDisable(true);	
					btnDoubleSplit2.setDisable(true);
					split2Tie();
					userLose(userHand,dealerHand,primaryStage);
				}else if(userHandSplit1.points==dealerHand.points && userHandSplit2.points==dealerHand.points && dealerHand.points<22) {
					resultsLabel.setText("Split 1 ties!" + '\n'+
							"Split 2 ties!");
					btHit.setDisable(true);
					btRetry.setVisible(true);
					btStand.setDisable(true);
					btnDouble.setDisable(true);	
					btnDoubleSplit2.setDisable(true);
					split2Tie();
					split1Tie();
				}
				
			}else if (split1Bust == false && split2Bust == true) {
				if(userHandSplit1.points<dealerHand.points && dealerHand.points<22) {
					resultsLabel.setText("Dealer wins");
					btHit.setDisable(true);
					btRetry.setVisible(true);
					btStand.setDisable(true);
					btnDouble.setDisable(true);
					btnDoubleSplit2.setDisable(true);
					userLose(userHand,dealerHand,primaryStage); 
				}else if(userHandSplit1.points>dealerHand.points) {
					resultsLabel.setText("Split 1 wins!" + '\n'+
							"Split 2 bust!");
					btHit.setDisable(true);
					btRetry.setVisible(true);
					btStand.setDisable(true);
					btnDouble.setDisable(true);
					btnDoubleSplit2.setDisable(true);
					split1Win();
					
				}else if(dealerHand.points==userHandSplit1.points) {
					resultsLabel.setText("Split 1 ties!" + '\n'+
							"Split 2 bust!");
					btHit.setDisable(true);
					btRetry.setVisible(true);
					btStand.setDisable(true);
					btnDouble.setDisable(true);
					btnDoubleSplit2.setDisable(true);
					//adds user's bet back to hand
					split1Tie();
					
				}else if (dealerHand.points>21) {
					resultsLabel.setText("Split 1 wins!" + '\n'+
							"Dealer Bust");
					btHit.setDisable(true);
					btStand.setDisable(true);
					btRetry.setVisible(true);
					btnDouble.setDisable(true);
					btnDoubleSplit2.setDisable(true);
					//Adds the user win to bank
					split1Win();
				
				}
			}else if (split2Bust == false && split1Bust == true) {
				if(userHandSplit2.points<dealerHand.points && dealerHand.points<22) {
					resultsLabel.setText("Split 1 bust!" + '\n'+
							"Split 2 loses!");
					btHit.setDisable(true);
					btRetry.setVisible(true);
					btStand.setDisable(true);
					btnDouble.setDisable(true);
					btnDoubleSplit2.setDisable(true);
					userLose(userHand,dealerHand,primaryStage); 
				}else if(userHandSplit2.points>dealerHand.points) {
					resultsLabel.setText("Split 2 wins!" + '\n'+
							"Split 1 bust!");
					btHit.setDisable(true);
					btRetry.setVisible(true);
					btStand.setDisable(true);
					btnDouble.setDisable(true);
					btnDoubleSplit2.setDisable(true);
					split2Win();
					
				}else if(dealerHand.points==userHandSplit2.points) {
					resultsLabel.setText("Split 2 ties!" + '\n'+
							"Split 1 bust!");
					btHit.setDisable(true);
					btRetry.setVisible(true);
					btStand.setDisable(true);
					btnDouble.setDisable(true);
					btnDoubleSplit2.setDisable(true);
					//adds user's bet back to hand
					split2Tie();
					
				}else if (dealerHand.points>21) {
					resultsLabel.setText("Split 2 wins!" + '\n'+
							"Dealer Bust");
					btHit.setDisable(true);
					btStand.setDisable(true);
					btRetry.setVisible(true);
					btnDouble.setDisable(true);
					btnDoubleSplit2.setDisable(true);
					//Adds the user win to bank
					split2Win();
				
				}
				
			}
		//This line of codes determines if the dealer wins with just their two initial cards
		}else if(userHand.points<dealerHand.points && dealerHand.points<22) {
			resultsLabel.setText("Dealer wins");
			btHit.setDisable(true);
			btRetry.setVisible(true);
			btStand.setDisable(true);
			btnDouble.setDisable(true);
			btnDoubleSplit2.setDisable(true);
		}else if(userHand.points>dealerHand.points) {
			resultsLabel.setText("Player wins");
			btHit.setDisable(true);
			btRetry.setVisible(true);
			btStand.setDisable(true);
			btnDouble.setDisable(true);
			btnDoubleSplit2.setDisable(true);
			userWin();
			
		}else if(dealerHand.points==userHand.points) {
			imageView4.setImage(dealerHand.handArray.get(1).image);
			resultsLabel.setText("Tie");
			btHit.setDisable(true);
			btRetry.setVisible(true);
			btStand.setDisable(true);
			btnDouble.setDisable(true);
			btnDoubleSplit2.setDisable(true);
			//adds user's bet back to hand
			tie();
			
		}else if (dealerHand.points>21) {
			imageView4.setImage(dealerHand.handArray.get(1).image);
			resultsLabel.setText("Dealer Bust");
			btHit.setDisable(true);
			btStand.setDisable(true);
			btRetry.setVisible(true);
			btnDouble.setDisable(true);
			btnDoubleSplit2.setDisable(true);
			//Adds the user win to bank
			userWin();
			
			
		
		
			
		}
	}		


				

private void split1Tie() {
	bank = bank + split1Bet;		
	}



private void split2Tie() {
	bank = bank + split2Bet;	
	}



private void split2Win() {
	bank = bank + (split2Bet*2);
		
	}



private void split1Win() {
	bank = bank + (split1Bet*2);
		
	}




int dealerCount=2;
	//Determines the consequences of a dealer hit
	private int dealerHit(Hand dealerHand, ListIterator<Card> iterator, Hand userHand, ImageView imageView4) {
		//imageView4.setImage(dealerHand.handArray.get(1).image);
	
		//adds card to userhand
		dealerHand.handArray.add(iterator.next());
		hitSound();
	
		//imageView4.setImage(dealerHand.handArray.get(1).image);					
	//adds dealer hit cards
	if (dealerCount==2) {
		 
		   	
		ImageView dealerCardHit1 =new ImageView(dealerHand.handArray.get(dealerCount).image);
		  
		
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
				}
			
				dealerHand.points += dealerHand.handArray.get(i).value;
				
				//if the hand breaks 21 and has an ace, the ace value is reassigned to one and the 
				//hand points value is recalculated
				if (dealerHand.points>21 && ace==true) {
					dealerHand.points = 0;
					for( i = 0; i < dealerHand.handArray.size(); i++) {
						if(dealerHand.handArray.get(i).type=="Ace") {
							dealerHand.handArray.get(i).value=dealerHand.handArray.get(i).otherValue;
							
							
						}
						dealerHand.points += dealerHand.handArray.get(i).value;
					}
				
				}
			
			 
			}
				
			return dealerHand.points;	
			
				
				}
	int split2Count =1;
	private void split2Hit(Hand userHandSplit2, Hand userHandSplit1, ListIterator<Card> iterator, Hand userHand, Hand dealerHand,
			Stage primaryStage, ImageView imageView4) {
		hitSound();
		userHandSplit2.handArray.add(iterator.next());
		
		if (split2Count==1) {
			ImageView userSplit2CardHit1 =new ImageView(userHandSplit2.handArray.get(split2Count).image);
		
			userSplit2CardHit1Label.setGraphic(userSplit2CardHit1);
			userSplit2CardHit1Label.setTranslateX(85);
			userSplit2CardHit1Label.setTranslateY(127);
			userSplit2CardHit1Label.setScaleX(.2);
			userSplit2CardHit1Label.setScaleY(.19);
			split2Count++;
		}else if (split2Count==2) {
			ImageView userSplit2CardHit1 =new ImageView(userHandSplit2.handArray.get(split2Count).image);
		
			userSplit2CardHit2Label.setGraphic(userSplit2CardHit1);
			userSplit2CardHit2Label.setTranslateX(108);
			userSplit2CardHit2Label.setTranslateY(127);
			userSplit2CardHit2Label.setScaleX(.2);
			userSplit2CardHit2Label.setScaleY(.19);
			split2Count++;
		}else if (split2Count==3) {
			ImageView userSplit2CardHit1 =new ImageView(userHandSplit2.handArray.get(split2Count).image);
		
			userSplit2CardHit3Label.setGraphic(userSplit2CardHit1);
			userSplit2CardHit3Label.setTranslateX(131);
			userSplit2CardHit3Label.setTranslateY(127);
			userSplit2CardHit3Label.setScaleX(.2);
			userSplit2CardHit3Label.setScaleY(.19);
			split2Count++;
		}else if (split2Count==4) {
			ImageView userSplit2CardHit1 =new ImageView(userHandSplit2.handArray.get(split2Count).image);
		
			userSplit2CardHit4Label.setGraphic(userSplit2CardHit1);
			userSplit2CardHit4Label.setTranslateX(154);
			userSplit2CardHit4Label.setTranslateY(127);
			userSplit2CardHit4Label.setScaleX(.2);
			userSplit2CardHit4Label.setScaleY(.19);
			split1Count++;
			
		}
		userHandSplit2.points= 0;
			boolean ace = false;
			for(int i = 0; i < userHandSplit2.handArray.size(); i++) {
				
				
				
				if(userHandSplit2.handArray.get(i).type=="Ace") {	
				}
			
				userHandSplit2.points += userHandSplit2.handArray.get(i).value;;
				
				//if the hand breaks 21 and has an ace, the ace value is reassigned to one and the 
				//hand points value is recalculated
				if (userHandSplit2.points>21 && ace==true) {
					userHandSplit2.points = 0;
					for( i = 0; i < userHandSplit2.handArray.size(); i++) {
						if(userHandSplit2.handArray.get(i).type=="Ace") {
							userHandSplit2.handArray.get(i).value=userHandSplit2.handArray.get(i).otherValue;
							
							
						}userHandSplit2.points += userHandSplit2.handArray.get(i).value;
					}
				
				}
			
				if (userHandSplit2.points>21) {
					resultsLabel.setText("Split 2 Bust");
					split2Bust = true; 
					btnHitSplit1.setDisable(true);
					btnStandSplit1.setDisable(true);
					btnHitSplit2.setDisable(true);
					btnStandSplit2.setDisable(true);
					userLose(userHand,dealerHand,primaryStage);
					if(split1Bust == true) {
						resultsLabel.setText("Split 1 and 2 bust!");
						btHit.setDisable(true);
						btRetry.setVisible(true);
						btStand.setDisable(true);
						btnDouble.setDisable(true);
						btnDoubleSplit2.setDisable(true);
					}else {
						stand(iterator,userHand,dealerHand,userHandSplit1,userHandSplit2,imageView4,primaryStage);
					}
		
				}
			
			}
			//return userHandSplit1.points;					
				
		}
	
		
	
	//hits the split 1 hand
	int split1Count = 1;
	private void split1Hit(Hand userHandSplit1, ListIterator<Card> iterator, Hand userHand, Hand dealerHand, Stage primaryStage) {
		hitSound();
		userHandSplit1.handArray.add(iterator.next());
		
		if (split1Count==1) {
			ImageView userSplit1CardHit1 =new ImageView(userHandSplit1.handArray.get(split1Count).image);
			userSplit1CardHit1Label.setGraphic(userSplit1CardHit1);
			userSplit1CardHit1Label.setTranslateX(85);
			userSplit1CardHit1Label.setTranslateY(26);
			userSplit1CardHit1Label.setScaleX(.2);
			userSplit1CardHit1Label.setScaleY(.19);
			split1Count++;
		}else if (split1Count==2) {
			ImageView userSplit1CardHit2 =new ImageView(userHandSplit1.handArray.get(split1Count).image);
		
			userSplit1CardHit2Label.setGraphic(userSplit1CardHit2);
			userSplit1CardHit2Label.setTranslateX(108);
			userSplit1CardHit2Label.setTranslateY(26);
			userSplit1CardHit2Label.setScaleX(.2);
			userSplit1CardHit2Label.setScaleY(.19);
			split1Count++;
		}else if (split1Count==3) {
			ImageView userSplit1CardHit3 =new ImageView(userHandSplit1.handArray.get(split1Count).image);
		
			userSplit1CardHit3Label.setGraphic(userSplit1CardHit3);
			userSplit1CardHit3Label.setTranslateX(131);
			userSplit1CardHit3Label.setTranslateY(26);
			userSplit1CardHit3Label.setScaleX(.2);
			userSplit1CardHit3Label.setScaleY(.19);
			split1Count++;
		}else if (split1Count==4) {
			ImageView userSplit1CardHit4 =new ImageView(userHandSplit1.handArray.get(split1Count).image);
		
			userSplit1CardHit4Label.setGraphic(userSplit1CardHit4);
			userSplit1CardHit4Label.setTranslateX(154);
			userSplit1CardHit4Label.setTranslateY(26);
			userSplit1CardHit4Label.setScaleX(.2);
			userSplit1CardHit4Label.setScaleY(.19);
			split1Count++;
			
		}
			userHandSplit1.points= 0;
			boolean ace = false;
			for(int i = 0; i < userHandSplit1.handArray.size(); i++) {
				
				
				
				if(userHandSplit1.handArray.get(i).type=="Ace") {
					ace = true;
				}
			
				userHandSplit1.points += userHandSplit1.handArray.get(i).value;;
				
				//if the hand breaks 21 and has an ace, the ace value is reassigned to one and the 
				//hand points value is recalculated
				if (userHandSplit1.points>21 && ace==true) {
					userHandSplit1.points = 0;
					for( i = 0; i < userHandSplit1.handArray.size(); i++) {
						if(userHandSplit1.handArray.get(i).type=="Ace") {
							userHandSplit1.handArray.get(i).value=userHandSplit1.handArray.get(i).otherValue;
							
							
						}userHandSplit1.points += userHandSplit1.handArray.get(i).value;
					}
				
				}
			
				if (userHandSplit1.points>21) {
					resultsLabel.setText("Split 1 Bust");
					split1Bust = true;
					btnHitSplit1.setDisable(true);
					btnStandSplit1.setDisable(true);
					btnHitSplit2.setDisable(false);
					btnStandSplit2.setDisable(false);
					btnDoubleSplit1.setDisable(true);
					btnDoubleSplit2.setDisable(false);
					userLose(userHand,dealerHand,primaryStage);
		
				}
			
			}
			//return userHandSplit1.points;					
				
		}
		
	

	//counter adds cards to userHand
	int count=2;
	@SuppressWarnings("unchecked")
	public int userHit( ListIterator<Card> iterator, Hand userHand,Hand dealerHand, ImageView imageView4) {
		
		//Disables the double down feature after the user hits, per Blackjack rules
		btnDouble.setDisable(true);
		
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
			
			
			
			if(userHand.handArray.get(i).type=="Ace") {
				ace = true;
			}
		
			userHand.points += userHand.handArray.get(i).value;;
			
			//if the hand breaks 21 and has an ace, the ace value is reassigned to one and the 
			//hand points value is recalculated
			if (userHand.points>21 && ace==true) {
				userHand.points = 0;
				for( i = 0; i < userHand.handArray.size(); i++) {
					if(userHand.handArray.get(i).type=="Ace") {
						userHand.handArray.get(i).value=userHand.handArray.get(i).otherValue;
						
						
					}userHand.points += userHand.handArray.get(i).value;
				}
			
			}
		
			if (userHand.points>21) {
				imageView4.setImage(dealerHand.handArray.get(1).image);
				resultsLabel.setText("Bust");
				btHit.setDisable(true);
				btRetry.setVisible(true);
				userHand.handArray.removeAll(userHand.handArray);
				btStand.setDisable(true);
				btHit.setDisable(true);
				btRetry.setVisible(true);
				btnDouble.setDisable(true);
				btnDoubleSplit2.setDisable(false);
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
