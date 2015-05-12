import java.util.ArrayList;

/**
 *
 * @author ecoli1996
 */


public class AlphaNumSort {
    private ArrayList<String> listOfStrings;
    private boolean ascending = true;
    
    public AlphaNumSort(ArrayList<String> listOfStrings) {
        this.listOfStrings = listOfStrings;
    }
    
    public AlphaNumSort(String[] stringList) {
        for (int i=0; i < stringList.length; i++) {
            this.listOfStrings.add(stringList[i]);
        }
    }
    
    //Checks if a character is a digit 0-9
    private boolean isDigit(char ch) {
        int asciiVal = (int) ch;
        
        return asciiVal >= 48 && asciiVal <= 57;
    }
    
    //Returns a string of sequential numbers in a string
    public String getNumberString(String s, int marker) {
        String numberString = "";
        char ch = s.charAt(marker);
        boolean numberStringFound = false;
        
        
        //Search for a digit throughout the string until reaching the end 
        //  of the string
        while (!isDigit(ch) && marker < s.length()) {
            ch = s.charAt(marker);
            if (!isDigit(ch)) {
                marker += 1;
            }   
        }
        
        //While we haven't reached a non-numerical character yet and the
        //  entire string hasn't been searched through, build the number string
        //  by concatenating each number character together
        if (isDigit(ch)) {
            while (marker < s.length() && !numberStringFound) {
                ch = s.charAt(marker);
                if (!isDigit(ch)) {
                    numberStringFound = true;
                }
                else {
                    numberString += ch;
                    marker += 1;
                }
            }
        }
        
        return numberString;
    }
    
    //Compares two strings and returns an integer value based on the
    //  lexiographic order of the strings (-1 if the first is before the second,
    //  1 if the second is before the first, and 0 if they're equal)
    public int compare(String string1, String string2){
        int rtnval = 0;
        boolean rtnvalFound = false;
        string1 = string1.toLowerCase().trim();
        string2 = string2.toLowerCase().trim();
        
        if (!string1.equals(string2)) {
            String numberString1;
            String numberString2;
            int index = 0;
         
            while (index < string1.length() && index < string2.length() && !rtnvalFound) {
                if(!isDigit(string1.charAt(index)) && !isDigit(string2.charAt(index))) {
                    if ((int)string1.charAt(index) < (int)string2.charAt(index)) {
                        rtnval = -1;
                        rtnvalFound = true;
                    }
                    
                    else if ((int)string1.charAt(index) > (int)string2.charAt(index)) {
                        rtnval = 1;
                        rtnvalFound = true;
                    }
                    
                    else {
                        index += 1;
                    }
                }
                
                else if(isDigit(string1.charAt(index)) && !isDigit(string2.charAt(index))) {
                    rtnval = -1;
                    rtnvalFound = true;
                }
                
                else if(!isDigit(string1.charAt(index)) && isDigit(string2.charAt(index))) {
                    rtnval = 1;
                    rtnvalFound = true;
                }
                
                else if(isDigit(string1.charAt(index)) && isDigit(string2.charAt(index))) {
                    
                    numberString1 = getNumberString(string1, index);
                    numberString2 = getNumberString(string2, index);
                    
                    if (Integer.parseInt(numberString1) < Integer.parseInt(numberString2)) {
                        rtnval = -1;
                        rtnvalFound = true;
                    } 
                    
                    else if(Integer.parseInt(numberString1) > Integer.parseInt(numberString2)) {
                        rtnval = 1;
                        rtnvalFound = true;
                    }
                    
                    else {
                        if (numberString1.length() > numberString2.length()) {
                            index += numberString1.length();
                        }
                        
                        else {
                            index += numberString2.length();
                        }
                        
                    }
                    
                }
            }
    
        }
        
        return rtnval;
    }
    
    //sets whether or not the strings will be sorted in ascending or descending order
    public void isAscending(boolean trueOrFalse) {
        ascending = trueOrFalse;
    }
    
    //Sorts the list of strings using an insertion sort
    public void sort() {
        String temp;
        
        if (ascending) {
        
            for(int i=1; i < listOfStrings.size(); i++) {
                temp = listOfStrings.get(i);
                int j;
                
                for(j= i-1; j >= 0 && compare(temp, listOfStrings.get(j)) < 0; j--) {
                    listOfStrings.set(j+1, listOfStrings.get(j));
                }
                
                listOfStrings.set(j+1, temp);
            }
    
        }
        
        else {
            int j;
            for(int i=1; i < listOfStrings.size(); i++) {
                temp = listOfStrings.get(i);
                
                for(j = i-1; j >= 0 && compare(temp, listOfStrings.get(j)) > 0; j--) {
                    listOfStrings.set(j+1, listOfStrings.get(j));
                }
                
                listOfStrings.set(j + 1, temp);
            }
        }
        

    }
    
    //Returns a string of the contents of listOfStrings with each string on a new line
    @Override
    public String toString() {
        String string = "";
        
        string = listOfStrings.stream().map((listOfString) -> listOfString + "\n").reduce(string, String::concat);
        
        return string;
    }
}
