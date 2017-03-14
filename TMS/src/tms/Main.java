package tms;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.paint.*;

public class Main extends Application {

	private Stage stage;
	private Scene scene;
	private Scene contentScene;
	private getContent get;
	private ButtonStyles buttonStyles;
	int widthOfScreen;
	int heightOfScreen;
	// int widthOfScreenTot;
	// int heightOfScreenTot;
	double width;
	double height;
	Pane root;
	Pane mainbg;
	Label loginFailed;
	boolean loggedIn = false;
	TextField nameInput;
	TextField passInput;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {
		this.stage = stage;

		// SIZE CONSTRAINTS
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		widthOfScreen = (int) primaryScreenBounds.getWidth();
		heightOfScreen = (int) primaryScreenBounds.getHeight();
		stage.setMinHeight(heightOfScreen - 100);
		stage.setMinWidth(widthOfScreen - 100);

		width = stage.getWidth();
		height = stage.getHeight();

		scene = new Scene(contentController());
		stage.setTitle("Konsulent 1 - Transport Management System");
		stage.setScene(scene);
		stage.setMaximized(true);
		// stage.setResizable(true);
		stage.show();
	}

	public Parent contentController() {
		buttonStyles = new ButtonStyles();
		root = new Pane();

		// ERROR MESSAGE WHEN LOGIN FAILS
		loginFailed = new Label("Wrong USERNAME or PASSWORD");
		loginFailed.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);" + "-fx-padding: 7 16 7 16;"
				+ "-fx-background-radius: 30;" + "-fx-background-insets: 0;" + "-fx-text-fill: white;");
		// loginFailed.setStyle("-fx-text-fill: red");
		loginFailed.setVisible(false);

		stage.widthProperty().addListener(e -> {
			if (loggedIn == false) {
				if (width != stage.getWidth()) {
					//System.out.println("Width: " + stage.getWidth() + " , " + stage.getHeight());
					width = stage.getWidth();
					height = stage.getHeight();
					root.getChildren().clear();
					responsiveLogin((int) (width), (int) (height));
				}
			}
			if (loggedIn == true) {
				if (width != stage.getWidth()) {
					//System.out.println("Width: " + stage.getWidth() + " , " + stage.getHeight());
					width = stage.getWidth();
					height = stage.getHeight();
					root.getChildren().clear();
					responsiveContent2((int) (width), (int) (height));
				}
			}
		});
		stage.heightProperty().addListener(e -> {
			if (loggedIn == false) {
				if (height != stage.getHeight()) {
					//System.out.println("Height" + stage.getWidth() + " , " + stage.getHeight());
					width = stage.getWidth();
					height = stage.getHeight();
					root.getChildren().clear();
					responsiveLogin((int) (width), (int) (height));
				}
			}
			if (loggedIn == true) {
				if (height != stage.getHeight()) {
					//System.out.println("Height" + stage.getWidth() + " , " + stage.getHeight());
					width = stage.getWidth();
					height = stage.getHeight();
					root.getChildren().clear();
					responsiveContent2((int) (width), (int) (height));
				}
			}
		});

		return responsiveLogin((int) (width), (int) height);
	}

	public Parent responsiveLogin(int width, int height) {

		widthOfScreen = width;
		heightOfScreen = height;

		Pane background = new Pane();
		Canvas canvasBg = new Canvas(widthOfScreen, heightOfScreen);
		
                ImageView imageView = new ImageView(new Image(getClass()
                .getResource("Logomakr2.png").toExternalForm()));
                imageView.setFitWidth(220);
                imageView.setFitHeight(75);
                imageView.setTranslateX((int) ((widthOfScreen/2) - (220/2)));
                imageView.setTranslateY((int) ((heightOfScreen/2) - (170)));
                
                background.getChildren().addAll(canvasBg, imageView);
		background.setStyle("-fx-background-color: radial-gradient(radius 100%, red, darkgray, black);");

		Label name = new Label("Username:");
		name.setTextFill(Color.LIGHTGRAY);
		nameInput = new TextField();
		nameInput.setPromptText("username");
                nameInput.setStyle("-fx-background-color: rgba(255,255,255,0.1);");
                
		Label pass = new Label("Password:");
		pass.setTextFill(Color.LIGHTGRAY);
		passInput = new TextField();
		passInput.setPromptText("password");
                passInput.setStyle("-fx-background-color: rgba(255,255,255,0.1);");

		Button login = new Button("Login");
		login.setStyle(buttonStyles.getButtonStyle("iPadDarkSmall"));

		loginFailed.setTranslateX(47);
		loginFailed.setTranslateY(22);
		name.setTranslateX(40);
		name.setTranslateY(67);
		nameInput.setTranslateX(110);
		nameInput.setTranslateY(65);
		pass.setTranslateX(40);
		pass.setTranslateY(97);
		passInput.setTranslateX(110);
		passInput.setTranslateY(95);
		login.setTranslateX(110);
		login.setTranslateY(125);

		LoginBox loginBox = new LoginBox(300, 200);
		loginBox.setTranslateX((widthOfScreen / 2) - (300 / 2));
		loginBox.setTranslateY((heightOfScreen / 2) - ((210) / 2));
		loginBox.setFill(Color.TRANSPARENT);
		loginBox.getChildren().addAll(loginFailed, name, nameInput, pass, passInput, login);

		// ADDING LISTENERS TO ENTER KEY PRESSED AND LOGIN BUTTON PRESSED
		nameInput.setOnKeyPressed((event) -> {
			if (event.getCode() == KeyCode.ENTER) {
				if (nameInput.getText().equals("") && passInput.getText().equals("")) {
					loggedIn = true;
					root.getChildren().clear();
					root = (Pane) responsiveContent2(width, height);
				} else {
					passInput.clear();
					loginFailed.setVisible(true);
					stage.setScene(scene);
				}
			}
		});
		passInput.setOnKeyPressed((event) -> {
			if (event.getCode() == KeyCode.ENTER) {
				if (nameInput.getText().equals("") && passInput.getText().equals("")) {
					loggedIn = true;
					root.getChildren().clear();
					root = (Pane) responsiveContent2(width, height);
				} else {
					passInput.clear();
					loginFailed.setVisible(true);
					stage.setScene(scene);
				}
			}
		});
		login.setOnAction((event) -> {
			if (nameInput.getText().equals("") && passInput.getText().equals("")) {
				loggedIn = true;
				root.getChildren().clear();
				root = (Pane) responsiveContent2(width, height);

			} else {
				passInput.clear();
				loginFailed.setVisible(true);
				stage.setScene(scene);
			}
		});

		root.getChildren().addAll(background, loginBox);
		return root;
	}

	public Parent responsiveContent(int width, int height) {
		widthOfScreen = width;
		heightOfScreen = height;
		// CREATING THE MAIN PANE (WILL BE DISPLAYED WHEN A USER HAVE LOGGED IN
		// TO THE SYSTEM)
		// CONTAINS MENU AND CONTENT PANE
		Pane main = new Pane();

		// VARIABLES NEEDED IN THE NEXT SECTION
		int widthOfMenu = (int) (widthOfScreen * 0.20);
		int heightOfMenu = heightOfScreen;
		int widthOfContent = (int) (widthOfScreen * 0.80);
		int heightOfContent = heightOfScreen;
		//Buttons
		int prefWidth = (int) (widthOfMenu * 0.65);
		int prefHeight =(int) (heightOfMenu * 0.1);

		get = new getContent(widthOfContent, heightOfScreen);

		// CREATING THE MENU PANE. THIS IS WHERE ALL THE MENU ITEMS WILL BE
		// LOCATED
		MenuBox menu = new MenuBox(widthOfMenu, heightOfScreen);
		menu.setFill(Color.BLACK);


		// CREATING THE CONTENT PANE. THIS IS WHERE THE CONTENT WILL BE
		// DISPLAYED
		MenuBox content = new MenuBox(widthOfContent, heightOfScreen);
		content.setFill(Color.DARKGOLDENROD);
		content.setTranslateX(widthOfScreen * 0.20);
		content.getChildren().clear();
		content.getChildren().addAll(get.getHome());

		// HOME BUTTON
		Button home = new Button("Home");
		home.setStyle(buttonStyles.getButtonStyle("iPadDark"));
		home.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				home.setStyle("-fx-background-color:#dae7f3;");
			}
		});
		home.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				home.setStyle(buttonStyles.getButtonStyle("iPadDark"));
			}
		});
		home.setPrefSize(prefWidth, prefHeight);
		home.setTranslateX((widthOfMenu / 2) - (prefWidth / 2));
		home.setTranslateY(heightOfScreen * 0.1);
		home.setOnAction(e -> {
			// System.out.println("HOME CLICKED");
			content.getChildren().clear();
			content.getChildren().add(get.getHome());
		});

		// SCHEDULE BUTTON
		Button schedule = new Button("Schedule");
		schedule.setStyle(buttonStyles.getButtonStyle("iPadDark"));
		schedule.setPrefSize(prefWidth, prefHeight);
		schedule.setTranslateX((widthOfMenu / 2) - (prefWidth / 2));
		schedule.setTranslateY((heightOfScreen * 0.2) + 10);
		schedule.setOnAction(e -> {
			// System.out.println("SCHEDULE CLICKED");
			content.getChildren().clear();
			content.getChildren().add(get.getSchedule());
		});

		// LOAD BUTTON
		Button load = new Button("Load");
		load.setStyle(buttonStyles.getButtonStyle("iPadDark"));
		load.setPrefSize(prefWidth, prefHeight);
		load.setTranslateX((widthOfMenu / 2) - (prefWidth / 2));
		load.setTranslateY((heightOfScreen * 0.3) + 20);
		load.setOnAction(e -> {
			// System.out.println("LOAD CLICKED");
			content.getChildren().clear();
			content.getChildren().add(get.getLoad());
		});

		// UNLOAD BUTTON
		Button unload = new Button("Unload");
		unload.setStyle(buttonStyles.getButtonStyle("iPadDark"));
		unload.setPrefSize(prefWidth, prefHeight);
		unload.setTranslateX((widthOfMenu / 2) - (prefWidth / 2));
		unload.setTranslateY((heightOfScreen * 0.4) + 30);
		unload.setOnAction(e -> {
			// System.out.println("UNLOAD CLICKED");
			content.getChildren().clear();
			content.getChildren().add(get.getUnload());
		});

		// EXPORT DOCUMENT BUTTON
		Button exportDocument = new Button("Export document");
		exportDocument.setStyle(buttonStyles.getButtonStyle("iPadDark"));
		exportDocument.setPrefSize(prefWidth, prefHeight);
		exportDocument.setTranslateX((widthOfMenu / 2) - (prefWidth / 2));
		exportDocument.setTranslateY((heightOfScreen * 0.5) + 40);
		exportDocument.setOnAction(e -> {
			// System.out.println("EXPORT DOCUMENT CLICKED");
			content.getChildren().clear();
			content.getChildren().add(get.getExportDocument());
		});

		// LOG OUT BUTTON
		Button logout = new Button("Log out");
		logout.setStyle(buttonStyles.getButtonStyle("roundRed"));
		logout.setPrefSize(prefWidth, prefHeight);
		logout.setTranslateX((widthOfMenu / 2) - (prefWidth / 2));
		logout.setTranslateY(heightOfScreen - prefHeight - (heightOfScreen * 0.1));
		logout.setOnAction(e -> {
			nameInput.clear();
			passInput.clear();
			loginFailed.setVisible(false);
			loggedIn = false;
			root.getChildren().clear();
			root = (Pane) responsiveLogin(width, height);
		});

		menu.getChildren().addAll(home, schedule, load, unload, exportDocument, logout);

		root.getChildren().addAll(menu, content);

		return root;

	}
        
        public Parent responsiveContent2(int width, int height) {
		widthOfScreen = width;
		heightOfScreen = height;
		// CREATING THE MAIN PANE (WILL BE DISPLAYED WHEN A USER HAVE LOGGED IN
		// TO THE SYSTEM)
		// CONTAINS MENU AND CONTENT PANE
		Pane main = new Pane();     
                
                Pane background = new Pane();
		Canvas canvasBg = new Canvas(widthOfScreen, heightOfScreen);
		background.getChildren().addAll(canvasBg);
		background.setStyle("-fx-background-color: radial-gradient(radius 100%, red, darkgray, black);");

		// VARIABLES NEEDED IN THE NEXT SECTION
		int widthOfMenu = (int) (widthOfScreen * 0.20);
		int heightOfMenu = heightOfScreen;
                //Buttons
		int prefWidth = (int) (widthOfMenu * 0.65);
		int prefHeight =(int) (heightOfMenu * 0.1);
		int widthOfContent = (int) (widthOfScreen * 0.74); //- ((widthOfMenu / 2) - (prefWidth / 2)));
		int heightOfContent = (int) (heightOfScreen * 0.8);	

		get = new getContent(widthOfContent, heightOfContent);

		// CREATING THE MENU PANE. THIS IS WHERE ALL THE MENU ITEMS WILL BE
		// LOCATED
		MenuBox menu = new MenuBox(widthOfMenu, heightOfScreen);
		menu.setFill(Color.TRANSPARENT);


		// CREATING THE CONTENT PANE. THIS IS WHERE THE CONTENT WILL BE
		// DISPLAYED
		MenuBox content = new MenuBox(widthOfContent, heightOfContent);
		content.setFill(Color.DARKGOLDENROD);
		content.setTranslateX(widthOfScreen * 0.20);
                content.setTranslateY(heightOfScreen * 0.10);
		content.getChildren().clear();
		content.getChildren().addAll(get.getHome());

		// HOME BUTTON
		Button home = new Button("Home");
		home.setStyle(buttonStyles.getButtonStyle("iPadDark"));
		home.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				home.setStyle("-fx-background-color:#dae7f3;");
			}
		});
		home.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				home.setStyle(buttonStyles.getButtonStyle("iPadDark"));
			}
		});
		home.setPrefSize(prefWidth, prefHeight);
		home.setTranslateX((widthOfMenu / 2) - (prefWidth / 2));
		home.setTranslateY(heightOfScreen * 0.1);
		home.setOnAction(e -> {
			// System.out.println("HOME CLICKED");
			content.getChildren().clear();
			content.getChildren().add(get.getHome());
		});

		// SCHEDULE BUTTON
		Button schedule = new Button("Schedule");
		schedule.setStyle(buttonStyles.getButtonStyle("iPadDark"));
		schedule.setPrefSize(prefWidth, prefHeight);
		schedule.setTranslateX((widthOfMenu / 2) - (prefWidth / 2));
		schedule.setTranslateY((heightOfScreen * 0.2) + 10);
		schedule.setOnAction(e -> {
			// System.out.println("SCHEDULE CLICKED");
			content.getChildren().clear();
			content.getChildren().add(get.getSchedule());
		});

		// LOAD BUTTON
		Button load = new Button("Load");
		load.setStyle(buttonStyles.getButtonStyle("iPadDark"));
		load.setPrefSize(prefWidth, prefHeight);
		load.setTranslateX((widthOfMenu / 2) - (prefWidth / 2));
		load.setTranslateY((heightOfScreen * 0.3) + 20);
		load.setOnAction(e -> {
			// System.out.println("LOAD CLICKED");
			content.getChildren().clear();
			content.getChildren().add(get.getLoad());
		});

		// UNLOAD BUTTON
		Button unload = new Button("Unload");
		unload.setStyle(buttonStyles.getButtonStyle("iPadDark"));
		unload.setPrefSize(prefWidth, prefHeight);
		unload.setTranslateX((widthOfMenu / 2) - (prefWidth / 2));
		unload.setTranslateY((heightOfScreen * 0.4) + 30);
		unload.setOnAction(e -> {
			// System.out.println("UNLOAD CLICKED");
			content.getChildren().clear();
			content.getChildren().add(get.getUnload());
		});

		// EXPORT DOCUMENT BUTTON
		Button exportDocument = new Button("Export document");
		exportDocument.setStyle(buttonStyles.getButtonStyle("iPadDark"));
		exportDocument.setPrefSize(prefWidth, prefHeight);
		exportDocument.setTranslateX((widthOfMenu / 2) - (prefWidth / 2));
		exportDocument.setTranslateY((heightOfScreen * 0.5) + 40);
		exportDocument.setOnAction(e -> {
			// System.out.println("EXPORT DOCUMENT CLICKED");
			content.getChildren().clear();
			content.getChildren().add(get.getExportDocument());
		});

		// LOG OUT BUTTON
		Button logout = new Button("Log out");
		logout.setStyle(buttonStyles.getButtonStyle("roundRed"));
		logout.setPrefSize(prefWidth, prefHeight);
		logout.setTranslateX((widthOfMenu / 2) - (prefWidth / 2));
		logout.setTranslateY(heightOfScreen - prefHeight - (heightOfScreen * 0.1));
		logout.setOnAction(e -> {
			nameInput.clear();
			passInput.clear();
			loginFailed.setVisible(false);
			loggedIn = false;
			root.getChildren().clear();
			root = (Pane) responsiveLogin(width, height);
		});

		menu.getChildren().addAll(home, schedule, load, unload, exportDocument, logout);
		root.getChildren().addAll(background, menu, content);
		return root;
	}
}