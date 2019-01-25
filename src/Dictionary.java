import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.TreeMap;

/***********************************************************************************
Class:  	Dictionary
Purpose: 	This class will manage Dictionary of words from file or keyboard
Author:  	Nathan Festoso

Data members:	dictionary: TreeMap<String, Integer> - Holds all words entered manually or from a file
				file: File - Holds the file used to load words into the dictionary
				readFile: FileReader - Reads from specified file
 *************************************************************************************/

public class Dictionary{
	final int INVALID = -1;
	final int NOT_FOUND = 0;

	private TreeMap<String, Integer> dictionary;
	private File file;
	private FileReader read;


	/**
	 * Default constructor
	 */
	public Dictionary(){
		dictionary = new TreeMap<>();
	}

	/**
	 * Searches for a specified word in the dictionary, returns the result
	 * 
	 * @param word - Search query
	 * @return Occurrence of the word
	 */
	int searchDictionary(String word){
		if((word = validateWord(word)).equals(""))
			return INVALID;
		else
			if(dictionary.containsKey(word))
				return dictionary.get(word).intValue();
			else
				return NOT_FOUND;

	}


	/**
	 * Removes all non alphabetical characters in a string and converts it to lower case
	 * 
	 * @param word - to be validated
	 * @return trimmed and cleaned word
	 */
	String validateWord(String word){
		if((word = word.replaceAll("[^A-z]", "")).equals(""))
			return "";
		else
			word = word.toLowerCase();	
		return word;
	}


	/**
	 * Adds a new word to the dictionary, if the word exists, increment the amount that exists
	 * 
	 * @param input - Input stream from file or keyboard
	 * @return boolean - if word was usccessfully added or not
	 */
	boolean addWord(Scanner input){
		String word;
		int count;

		word = input.next();
		if(!(word = validateWord(word)).equals("")){
			if(dictionary.containsKey(word)){
				count = dictionary.get(word).intValue();
				count++;
				dictionary.put(word, count);
			}else{
				dictionary.put(word, 1);
			}
		}else{
			return false;
		}
		return true;
	}


	/**
	 * Counts the total number of unique words in the dictionary
	 * 
	 * @return int - number of words
	 */
	int wordCount(){
		if(dictionary.isEmpty())
			return 0;
		else
			return dictionary.size();
	}


	/**
	 * Completely wipes all existing data in the dictionary
	 * 
	 * @return boolean - if list was already empty return false
	 */
	boolean emptyDictionary(){
		if(dictionary.isEmpty())
			return false;
		else
			dictionary.clear();
		return true;
	}


	/**
	 * Returns whether or not the dictionary is empty or not
	 * 
	 * @return boolean
	 */
	boolean isEmpty(){
		if(dictionary.isEmpty())
			return true;
		return false;
	}


	/**
	 * Opens the specified file to be read for words
	 * 
	 * @param input - scanner
	 * @return boolean - successfully loaded or not
	 */
	boolean openFile(Scanner input){
		String fileName;
		fileName = input.next();

		try{
			file = new File(fileName);
			read = new FileReader(file);
			input = new Scanner(read);
			readFile(input);
		}catch(FileNotFoundException fnfe){
			return false;
		}
		return true;
	}


	/**
	 * Reads through an open file and adds all valid words to the dictionary
	 * 
	 * @param input - to file
	 */
	void readFile(Scanner input){
		while(input.hasNext())
			addWord(input);
	}

}
