package application;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BookList {
	//Method for getting the book's list.
	public String bookList() {
		String listData = "";
	
		try {
			DataInputStream listFile = new DataInputStream(new FileInputStream("booklist.dat"));
			while (listFile.available() > 0) {
				//add Title
				String bookTitle = "Book Title: " + listFile.readUTF();
				listData = listData.concat(bookTitle);
				
				//add Author
				String bookAuthor = "\nAuthor: " + listFile.readUTF();
				listData = listData.concat(bookAuthor);
				
				//add Genre
				String bookGenre = "\nGenre: " + listFile.readUTF();
				listData = listData.concat(bookGenre);
				
				//add Pages
				String bookPages = "\nPages: " + listFile.readInt();
				listData = listData.concat(bookPages);
				
				//add Rating
				String bookRating = "\nRating: " + listFile.readDouble() + " stars\n\n";
				listData = listData.concat(bookRating);
			}
			listFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			String newString = "You haven't read any books.";
			listData = listData.concat(newString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listData;
	}
}
