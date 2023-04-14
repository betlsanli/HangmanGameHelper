import javax.swing.*;
import java.util.Arrays;

public class HangmanGame {
    private String word;
    private boolean[] isGuessed;
    private int attempt;
    private char[] guessedChars;
    private ImageIcon[] icons;
    public HangmanGame(String word){
        this.word = word;
        isGuessed = new boolean[word.length()];
        guessedChars = new char[26];
        attempt = 0;
        icons = new ImageIcon[7];
        for(int i = 0 ;i < icons.length;i++){
            icons[i] = new ImageIcon("images\\" + i + ".png");
        }
    }
    public ImageIcon getIcon(){
        return icons[attempt];
    }
    public char[] getGuessedChars(){
        return  guessedChars;
    }
    public String getWord(){
        return word;
    }
    public String getSecretWord(){
        StringBuilder secretWord = new StringBuilder(word.length());
        for(int i = 0;i < word.length(); i++){
            if(isGuessed[i])
                secretWord.append(word.charAt(i));
            else
                secretWord.append("_");
//            secretWord.append(" ");
        }
        return secretWord.toString();
    }
    public boolean isWon(){
        for(boolean bool : isGuessed){
            if(!bool)
                return false;
        }
        return true;
    }
    public int getAttempt(){
        return attempt;
    }
    private char toLower(char c){
        if(c >= 'A' && c <='Z')
            c = (char) (c + 32);
        return c;
    }
    public void guess(String guess){
        if(guess.length() == 0){
            attempt++;
            return;
        }
        if(guess.length() > 1){
            if(guess.equalsIgnoreCase(word)){
                Arrays.fill(isGuessed, true);
            }
            else{
                attempt++;
            }
        }
        else{
            char c = toLower(guess.charAt(0));
            int index = word.indexOf(c);
            if(index == -1)
                attempt++;
            else{
                while(index >= 0){
                    isGuessed[index] = true;
                    index = word.indexOf(c,index+1);
                }
            }
            guessedChars[c - 'a'] = c;

        }

    }
}
