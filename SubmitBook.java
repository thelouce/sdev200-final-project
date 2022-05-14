package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SubmitBook extends Main implements EventHandler<ActionEvent> {

	private Alert emptyField = new Alert(AlertType.ERROR, "All Fields Must Be Filled");

	@Override
	public void handle(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			//open the files
			RandomAccessFile bookListData = new RandomAccessFile("booklist.dat", "rw");
			RandomAccessFile pageLists = new RandomAccessFile("pagelist.dat", "rw");
			RandomAccessFile ratingsFile = new RandomAccessFile("ratings.dat", "rw");
			
			//Getting the Data
			String bookTitle = bookTitleText.getText();
			String bookAuthor = bookAuthorText.getText();
			String genre = bookGenres.getText();
			int bookPages = Integer.valueOf(bookPagesText.getText());
			double bookRating = bookRatingSlide.getValue();
			
			//Checking to make sure fields are filled
			if (genre.length() == 0 || bookAuthor.length() == 0 || bookTitle.length() == 0 || bookPagesText.getText().length() == 0) {
				emptyField.show();
			} else {
				bookListData.seek(bookListData.length());
				pageLists.seek(pageLists.length());
				ratingsFile.seek(ratingsFile.length());
				bookListData.writeUTF(bookTitle);
				bookListData.writeUTF(bookAuthor);
				bookListData.writeUTF(genre);
				pageLists.writeInt(bookPages);
				bookListData.writeInt(bookPages);
				bookListData.writeDouble(bookRating);
				ratingsFile.writeDouble(bookRating);
				Alert bookAdded = new Alert(AlertType.CONFIRMATION, "Book Added! Feel free to return to the home page. Close and relaunch the program if data does not update.");
				bookTitleText.setText("");
				bookAuthorText.setText("");
				bookGenres.setText("");
				bookPagesText.setText("");
				bookAdded.show();
			}
			bookListData.close();
			pageLists.close();
			ratingsFile.close();
		} catch (FileNotFoundException ex) {
			Alert fof = new Alert(AlertType.ERROR, "File Not Found");
			fof.show();
		} catch (IOException ex) {
			Alert wrongInput = new Alert(AlertType.ERROR, "Wrong inputs detected");
			wrongInput.show();
		} catch (NumberFormatException ex) {
			Alert intField = new Alert(AlertType.ERROR, "Please enter a proper integer into the page field.");
			intField.show();
		}
	}
}
