/*
 * Base View of the Application
 * I can think of ways to make this laid out better but I'm not sure if I have time for that.
 * I need to really adjust a lot of things. Probably gonna start as soon as I submit this.
 */
package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.geometry.Pos;


public class Main extends Application {
	
	//These are global variables so that subclass SubmitBook can access them.
	public static TextField bookTitleText = new TextField();
	public static TextField bookAuthorText = new TextField();
	public static TextField bookPagesText = new TextField();
	public static TextField bookGenres = new TextField();
	public static Slider bookRatingSlide = new Slider(1, 5, 0.5);

	
	@Override
	public void start(Stage primaryStage) {
		try {
			//Book Add Page
			BorderPane addRoot = new BorderPane();
			ScrollPane addBookScroll = new ScrollPane();
			addBookScroll.setPrefSize(400, 500);
			
			GridPane bookAdditions = new GridPane();
			bookAdditions.setAlignment(Pos.CENTER);
			bookAdditions.setPrefSize(400, 100);
			bookAdditions.setPadding(new Insets(15, 15, 15, 15));
			bookAdditions.setHgap(15);
			bookAdditions.setVgap(15);
			//BookAdd grid
			bookAdditions.getStyleClass().add("bookadd");
			bookAdditions.add(new Label("Book Title:"), 0, 0);
			bookAdditions.add(bookTitleText, 1, 0);
			bookAdditions.add(new Label("Book Author:"), 0, 1);
			bookAdditions.add(bookAuthorText, 1, 1);
			bookAdditions.add(new Label("Book Pages:"), 0, 2);
			bookAdditions.add(bookPagesText, 1, 2);
			bookAdditions.add(new Label("Book Genre:"), 0, 3);
			bookAdditions.add(bookGenres, 1, 3);
			bookAdditions.add(new Label("Book Rating:"), 0, 4);
			bookAdditions.add(bookRatingSlide, 1, 4);
			bookRatingSlide.setMajorTickUnit(1);
			bookRatingSlide.setMinorTickCount(1);
			bookRatingSlide.setShowTickMarks(true);
			bookRatingSlide.setShowTickLabels(true);
			bookRatingSlide.setSnapToTicks(true);
			Button submitBook = new Button("Submit");
			Button goBack = new Button("Go Back");
			bookAdditions.add(submitBook, 0, 5);
			bookAdditions.add(goBack, 1, 5);
			addBookScroll.setContent(bookAdditions);

			//add pages to book page
			addRoot.setCenter(addBookScroll);
			
			//Home Page Strings
			GetAverages ratings = new GetAverages();
			String averageRatings = "\nThe average rating of all books you've read is " + ratings.averageRating() + "\n\nThe average page count of all books you've read is " + ratings.averagePages();
			
			//Home Page
			BorderPane homeRoot = new BorderPane();
			ScrollPane scrollBox = new ScrollPane();
			scrollBox.setPrefSize(400, 500);
			scrollBox.setStyle("-fx-base: rgb(235, 246, 255); -fx-background-color: rgb(130, 100, 228);");
			VBox welcomePage = new VBox();
			Text welcomeMessage = new Text("Welcome to The Java Book Tracker.");
			welcomeMessage.setWrappingWidth(390);
			welcomeMessage.getStyleClass().add("header");
			Text welcomeInfo = new Text(averageRatings);
			welcomeInfo.getStyleClass().add("basetext");
			welcomeInfo.setWrappingWidth(375);
			welcomePage.getChildren().addAll(welcomeMessage, welcomeInfo);
			scrollBox.setContent(welcomePage);
			
			
			//Home Button Pane
			GridPane buttonPane = new GridPane();
			buttonPane.setAlignment(Pos.CENTER);
			buttonPane.getStyleClass().add("grid");
			buttonPane.setPrefSize(400, 100);
			buttonPane.setPadding(new Insets(15, 15, 15, 15));
			buttonPane.setHgap(15);
			buttonPane.setVgap(15);
			
			Button homeButton = new Button("Home Page");
			Button addBookPage = new Button("Add Books");
			Button bookList = new Button("Book List");
			Button darkMode = new Button("Settings");
			buttonPane.add(homeButton, 0, 0);
			buttonPane.add(addBookPage, 1, 0);
			buttonPane.add(bookList, 0, 1);
			buttonPane.add(darkMode, 1, 1);
			
			//Add to home root
			homeRoot.setCenter(scrollBox);
			homeRoot.setBottom(buttonPane);
			
			//The Book List
			BorderPane bookRoot = new BorderPane();
			
			ScrollPane listScroll = new ScrollPane();
			listScroll.setPrefSize(400, 500);
			VBox listContents = new VBox();
			BookList listString = new BookList();
			Text bookBasics = new Text(listString.bookList());
			bookBasics.getStyleClass().add("basetext");
			bookBasics.setWrappingWidth(390);
			listScroll.setContent(listContents);
			listContents.getChildren().add(bookBasics);
			
			VBox goHome = new VBox();
			goHome.setPrefSize(400, 100);
			goHome.setAlignment(Pos.BASELINE_CENTER);
			Button goHomeButton = new Button("Home");
			goHome.getChildren().add(goHomeButton);
			
			bookRoot.setCenter(listScroll);
			bookRoot.setBottom(goHome);
			
			//Settings
			BorderPane settRoot = new BorderPane();
			
			ScrollPane settScroll = new ScrollPane();
			settScroll.setPrefSize(400, 500);
			VBox settContents = new VBox();
			Button deleteButton = new Button("Delete Local Files");
			deleteButton.setAlignment(Pos.CENTER);
			Label settBasics = new Label("Warning: This button will delete all local book files.");
			settBasics.getStyleClass().add("basetext");
			settBasics.setAlignment(Pos.CENTER);
			settScroll.setContent(settContents);
			settContents.getChildren().addAll(deleteButton, settBasics);
			VBox settGoHome = new VBox();
			settGoHome.setPrefSize(400, 100);
			settGoHome.setAlignment(Pos.CENTER);
			Button settGoHomeButton = new Button("Home");
			settGoHome.getChildren().add(settGoHomeButton);
			
			settRoot.setCenter(settScroll);
			settRoot.setBottom(settGoHome);
			
			//Primary Scene
			Scene homepage = new Scene(homeRoot,400,600);
			homepage.getStylesheets().add(getClass().getResource("application.css").toString());
			primaryStage.setScene(homepage);
			primaryStage.setTitle("Book Journal");
			primaryStage.show();
			
			//Secondary Scenes
			Scene bookScene = new Scene(addRoot, 400, 600);
			bookScene.getStylesheets().add(getClass().getResource("application.css").toString());
			Scene listScene = new Scene(bookRoot, 400, 600);
			bookRoot.getStylesheets().add(getClass().getResource("application.css").toString());
			Scene settScene = new Scene(settRoot, 400, 600);
			settScene.getStylesheets().add(getClass().getResource("application.css").toString());
			
			//Button Actions
			SubmitBook bookSub = new SubmitBook();
			DeleteFiles delete = new DeleteFiles();
			
			homeButton.setOnAction(e -> primaryStage.setScene(homepage));
			addBookPage.setOnAction(e -> primaryStage.setScene(bookScene));
			bookList.setOnAction(e -> primaryStage.setScene(listScene));
			darkMode.setOnAction(e -> primaryStage.setScene(settScene));
			submitBook.setOnAction(bookSub);
			goBack.setOnAction(e -> primaryStage.setScene(homepage));
			goHomeButton.setOnAction(e -> primaryStage.setScene(homepage));
			settGoHomeButton.setOnAction(e -> primaryStage.setScene(homepage));
			deleteButton.setOnAction(delete);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
