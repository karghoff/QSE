package de.ComputerElite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import de.ComputerElite.zip.ZipIt;

public class Input {

	public static void main(String[] args) throws IOException {
		ArrayList<String> list = new ArrayList<>();
		int count = 0;
		int exported = 0;
		String Name = "";
		Scanner Scanner = new Scanner(System.in);
		System.out.print("Directory where the folders are located: ");
		String Source = Scanner.nextLine();
		Scanner.close();
		
		File file = new File(Source);
		String[] directories = file.list();
		
		
		for(int i = 0; i<directories.length; i++) {
			System.out.println("");
			if(directories[i].substring(directories[i].length()-4, directories[i].length()).equalsIgnoreCase(".zip")) {
				System.out.println("File "+directories[i]+" is already zipped");
				continue;
			}
			
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(
						Source+File.separator+directories[i]+File.separator+"Info.dat" ));
				String line = reader.readLine();
				while (line != null) {
					//System.out.println(line);
					// read next line
					if(line.contains("songName")) {
						if(line.contains("version") && line.contains("songName")) {
							//BeatSage
							count = 0;
							Name = "";
							for(int n = 0; n<line.length(); n++) {
								if(count == 7) {
										Name = Name + line.substring(n, n+1);
								}
								if(line.substring(n, n+1).equalsIgnoreCase("\"")) {
									count++;
								}
							}
							Name = Name.substring(0, Name.length()-1);
							//Name = Name.replaceAll("[\\]", "");
							Name = Name.replaceAll("[/]", "");
							Name = Name.replaceAll("[:]", "");
							Name = Name.replaceAll("[*]", "");
							Name = Name.replaceAll("[?]", "");
							Name = Name.replaceAll("[\"]", "");
							Name = Name.replaceAll("[<]", "");
							Name = Name.replaceAll("[>]", "");
							Name = Name.replaceAll("[|]", "");
							for(int f = 0; f<Name.length(); f++) {
								if(Name.substring(f,f+1).equalsIgnoreCase("\\")) {
									Name = Name.substring(0, f-1) + Name.substring(f+1, Name.length());
								}
							}
							int Time = 0;
							while(list.contains(Name)) {
								Time++;
								if(Time > 1) {
									Name = Name.substring(0, Name.length()-1);
									Name = Name + Time;
								} else {
									Name = Name + " " + Time;
								}
								
							}
							list.add(Name);
							System.out.println(Name);
							System.out.println(directories[i]);
							File sour = new File(Source);
							File src = new File(sour+File.separator+directories[i]+File.separator);
							ZipIt.zipDirectory(src, Source+File.separator+Name+".zip");
							exported++;
							Name = "";
							src = new File("");
							
						} else {
							//normal Map
							count = 0;
							Name = "";
							for(int n = 0; n<line.length(); n++) {
								if(count == 3) {
										Name = Name + line.substring(n, n+1);
								}
								if(line.substring(n, n+1).equalsIgnoreCase("\"")) {
									count++;
								}
							}
							Name = Name.substring(0, Name.length()-1);
							//Name = Name.replaceAll("[\\]", "");
							Name = Name.replaceAll("[/]", "");
							Name = Name.replaceAll("[:]", "");
							Name = Name.replaceAll("[*]", "");
							Name = Name.replaceAll("[?]", "");
							Name = Name.replaceAll("[\"]", "");
							Name = Name.replaceAll("[<]", "");
							Name = Name.replaceAll("[>]", "");
							Name = Name.replaceAll("[|]", "");
							for(int f = 0; f<Name.length(); f++) {
								if(Name.substring(f,f+1).equalsIgnoreCase("\\")) {
									Name = Name.substring(0, f-1) + Name.substring(f+1, Name.length());
								}
							}
							int Time = 0;
							while(list.contains(Name)) {
								Time++;
								if(Time > 1) {
									Name = Name.substring(0, Name.length()-1);
									Name = Name + Time;
								} else {
									Name = Name + " " + Time;
								}
								
							}
							list.add(Name);
							System.out.println(Name);
							System.out.println(directories[i]);
							File sour = new File(Source);
							File src = new File(sour+File.separator+directories[i]+File.separator);
							ZipIt.zipDirectory(src, Source+File.separator+Name+".zip");
							exported++;
							Name = "";
							src = new File("");
							
						}
					}
					line = reader.readLine();
					
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		
		
		//ZipIt.zipDirectory(src, Output);;
		System.out.println("");
		System.out.println("");
		System.out.println("Finished! Exported "+exported+" Songs");
	}

}
