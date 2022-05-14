package application;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DeleteFiles implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent e) {
		File bookData = new File("booklist.dat");
		File pageData = new File("pagelist.dat");
		File rateData = new File("ratings.dat");
		bookData.delete();
		pageData.delete();
		rateData.delete();
		Alert deletedFiles = new Alert(AlertType.CONFIRMATION, "Book Data Deleted. Restart the program if changes do not take effect.");
		deletedFiles.show();
	}
}
