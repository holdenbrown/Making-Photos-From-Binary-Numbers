package edu.iastate.cs228.hw4;

 
    
import java.util.ArrayList;  
import java.io.File;  
import java.util.Scanner;   
import java.text.DecimalFormat;
import java.io.FileNotFoundException; 
  

/**
 * @author Holden Brown      
 *   
 *	A user specified file contains a key to decode a message stored in binary inside said file   
 */   
  
public class MsgTree {
	  
	// the character contained within the tree  
	public char payloadChar;  
	
	// the left subtree of the MsgTree  
	public MsgTree left;  
	
	// the right subtree of the MsgTree  
	public MsgTree right;
   
	// tracks the character index when defining the tree
	private static int staticCharIdx = 0;  
	
	// holds binary data from the file   
	private static ArrayList<String> dataList = new ArrayList<String>();
	   
	// counts characters when the message is decoded
	private static int countChar = 0;
	
	// counts the characters in the message tree   
	private static int charTreeCount = 0;
	
	/**
	 * Recursively makes a tree using endocingString. if the next char is a carrot         
	 * and left is null make a node to the left. else make a node to the right. else if its not a carrot   
	 * set the payloadChar to the character  
	 *   
	 * @param encodingString - string used to define tree  
	 */  
	public MsgTree (String encodingString) {   
		if (staticCharIdx >= encodingString.length()) {  
			return;  
		}
		else {
   
			if (encodingString.charAt(staticCharIdx) != '^') {
				payloadChar = encodingString.charAt(staticCharIdx);   
				staticCharIdx++;
				charTreeCount++;  
				return;
			}
			
			staticCharIdx++;   
			if (left == null){
				left = new MsgTree(encodingString);  
			}   
			if (right == null){   
				right = new MsgTree(encodingString);  
			} 
		}
	}
	    
	/**
	 * makes a tree with no children nodes.   
	 *     
	 * @param payloadChar - char that is used in payloadChar    
	 */
	public MsgTree (char payloadChar) {      
		this.payloadChar = payloadChar;
		left = null;    	
		right = null;  
	}  		
	   
	
	/**      	
	 * uses recursion to print binary keys and the char that is represented by it.
	 * @param root   	
	 * @param code   
	 */    
	public static void printCodes (MsgTree root, String code) {
 		if (root.left == null && root.right == null) {
 			dataList.add(code);		
 			
 			if (root.payloadChar == '\n') {	
 				System.out.println(String.format("%s   %s	   %s", "", "\\n", code));  
 				return;   
 			}	
 			else {   	
 				System.out.println(String.format("%s   %s	   %s", "", root.payloadChar, code));   
				return;   	
 			}    	
 		}        
 		else {     
 			printCodes(root.left, code + "0");   
 				
 			printCodes(root.right, code + "1");    
 		}     
 	}    
	
	 public static void statistics (ArrayList<String> bits, int countChar, int charTreeCount) {          
  		 double compressed = 0;           
  		 double uncompressed = charTreeCount * 16;          	
		 DecimalFormat df = new DecimalFormat ("#.#");         	
 		           
  		for (int i = 0; i < bits.size(); i++){                
  			
	 		compressed += bits.get(i).length();           
			          
  		}        	
	                 
  		System.out.println("\nSTATISTICS:");
		System.out.println(String.format("%s          %s", "Avg bits/char:", df.format(compressed/charTreeCount)));           
		System.out.println(String.format("%s	%s", "Total characters:", countChar));         	
		System.out.println(String.format("%s          %s", "Space savings:", df.format(((1 - compressed/uncompressed)*100)) + "%"));          	
	        
  	}
  	      
	/**    		
 	 * creates the message when given the encoder data     
 	 * @param codes - MsgTree input chars in tree are used to decode msg   	
 	 * @param msg - binary used to decode codes     	
 	 */    
  	public void decode(MsgTree codes, String msg) {   
  		MsgTree cur = codes;         	
  		           
		String result = "";          	
 		          
 		for (int i = 0; i < msg.length(); i++) {                  
			if (msg.charAt(i) == '0') {         
				cur = cur.left;       	
			}        
  			       
			else {        
  				cur = cur.right;      
				         
  			}        
   			        
			if (cur.left == null && cur.right == null) {        	
	  			result += cur.payloadChar;        
				cur = codes;         
  				countChar++;    	
  				
			}          
  			
 		}             	
		          
		System.out.println(result);    
		
	}                  	
  
		
		
		
	
		
  
	 public static void main(String args[])   throws FileNotFoundException {  
		  
  		  
		 Scanner s = new Scanner (System.in);  
		 System.out.print("Please enter filename to decode: ");  
  		 String filename = s.nextLine();  
		 File file = new File(filename);  
	 	  
	 	  
  		 Scanner fScan = new Scanner(file);  
		 String msg = "";  
  		 String binaryMsg = "";  
		 String nextLine = fScan.nextLine();  
		
   		 boolean helper = false;  
		  
  		while (!Character.isDigit(nextLine.charAt(0))) {
			if (helper) {
  				   
				msg += "\n";
 				  
  			}
			msg += nextLine;
  			nextLine = fScan.nextLine();
			helper = true;
  		}
		binaryMsg += nextLine;
		
  		s.close();
  		
		fScan.close();
 		
		MsgTree m = new MsgTree(msg);
		
 		System.out.println("\ncharacter  code");
		System.out.println("-------------------------");
		
 		m.printCodes(m, "");
  		
 		System.out.println("\nMESSAGE:");
 		m.decode(m, binaryMsg);
 		
 		statistics(dataList, countChar, charTreeCount);
 	}
 	
} 








