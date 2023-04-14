import java.util.ArrayList;
import java.util.List;

public class Trie {

    private class TrieNode{
        private char data;
        private boolean isWord;
        private TrieNode[] children;

        private TrieNode(char data){
            this.data = data;
            isWord = false;
            children = new TrieNode[26];
        }
    }

    private TrieNode root;

    public Trie(){
        root = new TrieNode(' ');
    }
    private char toLower(char c){
        if(c >= 'A' && c <='Z')
            c = (char) (c + 32);
        return c;
    }
    public void insert(String word){
        TrieNode walk = root;
        for(int i = 0; i < word.length();i++){

            char c = toLower(word.charAt(i));

            if(walk.children[c - 'a'] == null){
                walk.children[c - 'a'] = new TrieNode(c);
            }
            walk = walk.children[c - 'a'];
        }
        walk.isWord = true;
    }
    public boolean search(String word){
        TrieNode walk = root;
        for(int i = 0; i < word.length();i++){
            char c = word.charAt(i);
            if(walk.children[c - 'a'] == null)
                return false;
            walk = walk.children[c - 'a'];
        }
        return walk.isWord;
    }
    public List<String> searchPattern(String pattern){
        List<String> result = new ArrayList<>();
        searchPattern(root,pattern,"",result);
        return result;
    }
    private void searchPattern(TrieNode root, String pattern, String currWord, List<String> result){
        if(pattern.length() == 0){
            if(root.isWord){
                result.add(currWord);
            }
            return;
        }
        char c = toLower(pattern.charAt(0));
        if(c >= 'a' && c <= 'z'){
            if(root.children[c - 'a'] != null){
                searchPattern(root.children[c - 'a'], pattern.substring(1),currWord+c,result);
            }
        }
        else{
            for(int i = 0; i < root.children.length;i++){
                if(root.children[i] != null){
                    searchPattern(root.children[i], pattern.substring(1),currWord + root.children[i].data,result);
                }
            }
        }

    }
}
