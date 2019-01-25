import java.util.InputMismatchException;
import java.util.Scanner;

/***********************************************************************************
Class:  	Main
Purpose: 	This class will run the Dictionary program
Author:  	Nathan Festoso

Methods: 	main(String[]) - main method - Runs Email List demo program
 *************************************************************************************/

public class Main {
	
	
	public static void main(String[] args) {
		final int SEARCH = 1;
		final int LOAD_FILE = 2;
		final int ADD_MANUAL = 3;
		final int WORD_COUNT = 4;
		final int CLEAR = 5;
		final int EXIT = 0;
		
		Scanner input = new Scanner(System.in);
		Dictionary dictionary = new Dictionary();
		int count;
		String word;
		int menuSelection = 1;
		
		do{
			try{
				// main menu
				System.out.println("1 - Search word occurrence");
				System.out.println("2 - Load words from file");
				System.out.println("3 - Load words manually");
				System.out.println("4 - Display number of unique words");
				System.out.println("5 - Clear dictionary");
				System.out.println("0 - EXIT");
				System.out.print("> ");
				menuSelection = input.nextInt();
				System.out.println();
				
				switch(menuSelection){
				// search dictionary with word
				case SEARCH:
					if(dictionary.isEmpty()){
						System.out.println("Dictionary is empty");
					}else{
						System.out.println("Enter word to search for");
						System.out.print("> ");
						word = input.next();
						count = dictionary.searchDictionary(word);
						word.replaceAll("[^A-z]", "");
						if(count == -1)
							System.out.println("Invalid word - a-z only");
						else if(count == 0)
							System.out.println(word+" not found");
						else	
							System.out.println(word+" occurs "+count+" times");

					}
					break;
					
					// enter file to be laoded
				case LOAD_FILE:
					System.out.println("Enter file name");
					System.out.print("> ");
					if(dictionary.openFile(input))
						System.out.println("File loaded successfully");
					else
						System.out.println("File not found");
					break;
					
					// add word from keyboard
				case ADD_MANUAL:
					System.out.println("Enter word to add");
					System.out.print("> ");
					if(!dictionary.addWord(input))
						System.out.println("Invalid word - a-z only");
					break;
					
					// count total number of words in dictionary
				case WORD_COUNT:
					if((count = dictionary.wordCount()) == 0)
						System.out.println("Dictionary is empty");
					else
						System.out.println("There are "+count+" words in the dictionary");
					break;
					
					// empty dictionary
				case CLEAR:
					if(dictionary.emptyDictionary())
						System.out.println("Dictionary successfully emptied");
					else
						System.out.println("Dictionary already empty");
					break;
					
					// terminate program
				case EXIT:
					System.out.println("Goodbye...");
					break;
					
				default:
					System.out.println("Invalid input");
					break;
				}
			}catch(InputMismatchException ime){
				System.out.println();
				System.out.println("Invalid input");
				input.nextLine();
			}
			System.out.println();
		}while(menuSelection != EXIT);
		input.close();
	}

}
