package com.app.fileOps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class fileOps {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String fileName = "India.txt";
		int cnt = 0;
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while((line = br.readLine())!= null) {
				String[] words = line.split(" ");
				for(String word: words) {
					if(word.equalsIgnoreCase("india")) cnt++;
				}
			}
				System.out.println("Count: "+cnt);
		} catch(IOException e) {
			System.out.println("Error!");
		}
	}

}
