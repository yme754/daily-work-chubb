package com.app.fileOps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class fileOpsFunc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = "India.txt";
		long cnt = 0;
		try(Stream<String> lines = Files.lines(Paths.get(fileName))) {
			cnt = lines.flatMap(line-> Arrays.stream(line.split("\\W+")))
			.filter(word-> !word.isEmpty()).filter(word-> word.equalsIgnoreCase("india")).count();
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("Count: "+ cnt);
		
	}

}
