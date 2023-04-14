import java.util.ArrayList;
import java.util.List;

public class HangmanHelper {

    private Trie trie;
    private static final int suggestionNum = 5;

    public HangmanHelper(List<String> wordList){
        trie = new Trie();
        for(String word : wordList){
            trie.insert(word);
        }
    }

    public char[] getSuggestion(String pattern, char[] guessedChars){ //pattern and guessedChars are all lower case
        ArrayList<String> allPossibleWords = (ArrayList<String>) trie.searchPattern(pattern);
        int[] freq = getFreqArr(allPossibleWords,guessedChars);

        char[] mostCommon = new char[suggestionNum];
        int[] maxArr = new int[suggestionNum];
        for(int i = 0;i < freq.length;i++){
            int minIdx = 0;
            for(int j = 1; j < maxArr.length;j++){
                if(maxArr[minIdx] > maxArr[j]){
                    minIdx = j;
                }
            }
            if(freq[i] > maxArr[minIdx]) {
                mostCommon[minIdx] = (char) (i + 'a');
                maxArr[minIdx] = freq[i];
            }
        }
        return mostCommon;

    }
    private int[] getFreqArr(List<String> possibleWords, char[] guessedChars){
        int[] freq = new int[26];
        for(String word : possibleWords){
            for(int i = 0; i<word.length(); i++){
                if(guessedChars[word.charAt(i) - 'a'] == '\u0000'){
                    freq[word.charAt(i) - 'a']++;
                }
            }
        }
        return freq;
    }
}
