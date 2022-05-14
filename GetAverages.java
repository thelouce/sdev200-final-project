/*
 Could possibly be an interface, actually, or part of the CreateBook class.
 We'll see.
 */

package application;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GetAverages extends SubmitBook {
	
	//Some global variables for the class.
	double rateSum = 0;
	double rateCount = 0;
	int pageSum = 0;
	int pageCount = 0;
	
	//Finds the average rating.
	public double averageRating() {
		DataInputStream ratingsFile;
		double average = 0;
		
		try {
			ratingsFile = new DataInputStream(new FileInputStream("ratings.dat"));
			while (ratingsFile.available() > 0) {
				rateCount++;
				rateSum += ratingsFile.readDouble();
			}
			ratingsFile.close();
			double newAverage = rateSum / rateCount;
			average = roundNumber(newAverage, 2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			average = 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	return average;
	}
	
	public int averagePages() {
		DataInputStream pagesFile;
		int average = 0;
		
		try {
			pagesFile = new DataInputStream(new FileInputStream("pagelist.dat"));
			while (pagesFile.available() > 0) {
				pageCount++;
				pageSum += pagesFile.readInt();
			}
			pagesFile.close();
			average = pageSum / pageCount;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			average = 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	return average;
	}
	
	public static double roundNumber(double number, int places) {
		double scale = Math.pow(10, places);
		return Math.round(number * scale) / scale;
	}
}