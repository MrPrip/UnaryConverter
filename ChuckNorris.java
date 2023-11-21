package ChuckNorrisEncryption;

import java.util.Scanner;

public class ChuckNorris {
    public static void main(String[] args)  {
        Scanner scan = new Scanner(System.in);
        boolean continueLoop = true;
        while(continueLoop) {
            System.out.println("Please input operation (encode/decode/exit):");
            String command = scan.nextLine().trim();
            switch(command) {
                case "encode":
                    System.out.println("Input string:");
                    String stringToBeEncoded = scan.nextLine();
                    String chuckNorrisEncryptedString = "";
                    
                    for(char c : stringToBeEncoded.toCharArray()) {
                        chuckNorrisEncryptedString = chuckNorrisEncryptedString + "" + ChuckNorris.getBinaryValue(c);
                    }
                    
                    String encodedString = ChuckNorris.getChuckNorris(chuckNorrisEncryptedString);
                    System.out.println("Encoded string:");
                    System.out.println(encodedString);
                break;
                case "decode":
                    System.out.println("Input encoded string:");
                    String stringToBeDecoded = scan.nextLine();
                    try {
                        String binary = ChuckNorris.fromChuckToBinary(stringToBeDecoded);
                        String alpha = ChuckNorris.fromBinaryToAlpha(binary);
                        System.out.println("Decoded string:");
                        System.out.println(alpha);
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    continueLoop = false;
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("There is no '" + command + "' operation");
                    break;
            }            
            System.out.println();
        }
        scan.close();
    }

    private static String fromChuckToBinary(String userInput) throws Exception {
        String tempBinary = "";
        for(char c : userInput.toCharArray()) {
            if(Character.isWhitespace(c)) {
                
            } else if(c != '0') {
                throw new Exception("Encoded string is not valid.");
            }
        }
       try {
        for(int i = 0; i < userInput.length(); i++) {
            if(userInput.charAt(i) == '1') { throw new Exception("Encoded string is not valid."); }
                if(userInput.charAt(i) == '0' && userInput.charAt(i+1) == ' ') {
                i++;
                if(userInput.charAt(i) == '1') { throw new Exception("Encoded string is not valid."); }
                while(userInput.charAt(++i) == '0') {
                    tempBinary = tempBinary + "1";
                    if(userInput.charAt(i) == '1') { throw new Exception("Encoded string is not valid."); }
                    if(i == userInput.length()-1) break;
                }
            } else if (userInput.charAt(i) == '0' && userInput.charAt(i+1) == '0') {
                if(userInput.charAt(i) == '1') { throw new Exception("Encoded string is not valid."); }
                i += 2;
                while(userInput.charAt(++i) == '0') {
                    if(userInput.charAt(i) == '1') { throw new Exception("Encoded string is not valid."); }
                    tempBinary = tempBinary + "0";
                    if(i == userInput.length()-1) break;
                }
            }
            if(i > userInput.length()) break;
        }
        return tempBinary;
       }catch (Exception e) {
        throw new Exception("Encoded string is not valid.");
    }
    }

    private static String fromBinaryToAlpha(String binaryString) throws Exception {
        String alphabetString = "";
        int currentLetterPosition = 0;
        if(binaryString.length() % 7 != 0) {
            throw new Exception("Encoded string is not valid.");
        }
        try {
            for(int i = 0; i < binaryString.length()/7; i++) {
                int currentLetter = 0;
                int currentBinaryValue = 64;
                for(int j = currentLetterPosition; j < currentLetterPosition+7; j++) {
                    if(binaryString.charAt(j) == '1') {
                      currentLetter += currentBinaryValue;
                    }
                    currentBinaryValue /= 2;
                }
                alphabetString += ((char)currentLetter);
                currentLetterPosition += 7;
            }
            return alphabetString;
        } catch (Exception e) {
            throw new Exception("Encoded string is not valid.");
        }
    }

    private static String getBinaryValue(char c) {
        String tempBinaryValue = Integer.toBinaryString(c);
        while(tempBinaryValue.length() != 7) {
            tempBinaryValue = "0" + tempBinaryValue;
        }
        return (tempBinaryValue);
    }

    private static String getChuckNorris(String binaryString) {
        String tempString = "";
        for(int i = 0; i < binaryString.length();) {
            
            if(binaryString.charAt(i) == '1') {
                tempString = tempString + "0 ";
                for(int j = i; j < binaryString.length(); j++) {
                    if(binaryString.charAt(j) == '1') {
                        tempString = tempString + "0";
                        i++;
                    } else {
                        break;
                    }
                }
                
            } else {
                tempString = tempString + "00 ";
                for(int j = i; j < binaryString.length(); j++) {
                    if(binaryString.charAt(j) == '0') {
                        tempString = tempString + "0";
                        i++;
                    } else {
                        break;
                    }
                }
                
            }
            tempString = tempString + " ";
        }
        return tempString;
    }
}