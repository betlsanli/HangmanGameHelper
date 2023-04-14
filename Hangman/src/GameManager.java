import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager {

    private GameScreen gameScreen;
    private StartScreen startScreen;
    private EndScreen endScreen;
    private HangmanGame game;
    private HangmanHelper helper;
    private static final File file = new File("wordList.txt");
    List<String> wordList;


    public GameManager() throws IOException {
        startScreen = new StartScreen();
        endScreen = new EndScreen();
        gameScreen = new GameScreen();
        wordList = readFile();
        helper = new HangmanHelper(wordList);
    }

    public void setupGame(){
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == startScreen.getStartButton()){
                    startScreen.dispose();
                    initGame();
                    update();
                }
                else if(e.getSource() == endScreen.getNewGameButton()){
                    endScreen.dispose();
                    gameScreen.dispose();
                    initGame();
                    update();
                }
                else if(e.getSource() == gameScreen.getMenuButton()){
                    gameScreen.dispose();
                    startScreen.setVisible(true);
                }
                else if(e.getSource() == gameScreen.getGuessButton()){
                    game.guess(gameScreen.getGuess().getText());
                    update();
                    gameScreen.getGuess().setText("");
                }
                else if (e.getSource() == endScreen.getExitButton() || e.getSource() == startScreen.getExitButton()) {
                    System.exit(0);
                }
            }
        };
        startScreen.getStartButton().addActionListener(actionListener);
        startScreen.getExitButton().addActionListener(actionListener);
        endScreen.getExitButton().addActionListener(actionListener);
        endScreen.getNewGameButton().addActionListener(actionListener);
        gameScreen.getMenuButton().addActionListener(actionListener);
        gameScreen.getGuessButton().addActionListener(actionListener);

        startScreen.setVisible(true);
    }
    private void initGame(){
        gameScreen.getGuessButton().setEnabled(true);
        gameScreen.getMenuButton().setEnabled(true);
        gameScreen.getGuess().setText("");

        Random rand = new Random();
        int index = rand.nextInt(0,wordList.size());
        game = new HangmanGame(wordList.get(index));
        gameScreen.setVisible(true);

    }

    private void update() {
        String pattern = game.getSecretWord();
        gameScreen.setSecretWord(pattern);
        char[] guessedChar = game.getGuessedChars();
        String suggestions = new String(helper.getSuggestion(pattern,guessedChar));
        gameScreen.setSuggestions(suggestions);
        gameScreen.setGuessedChars(guessedChar);
        gameScreen.setImageLabel(game.getIcon());
        if(game.getAttempt() >= 6 || game.isWon()){
            initEnd(game.isWon());
        }

    }
    private void initEnd(boolean isWon){
        gameScreen.getGuessButton().setEnabled(false);
        gameScreen.getMenuButton().setEnabled(false);
        endScreen.getEndScreenLabel().setText("The word was " + game.getWord());

        if(isWon){
            endScreen.getResLabel().setText("YOU WON!");
        }
        else{
            endScreen.getResLabel().setText("YOU LOST!");
        }

        endScreen.setVisible(true);
    }

    private List<String> readFile() throws IOException {
        List<String> wordList = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new FileReader(file));
        String str;
        while((str = bf.readLine()) != null){
            String[] words = str.split(" ");
            for(String word : words){
                wordList.add(word);
            }
        }
        return wordList;
    }
}
