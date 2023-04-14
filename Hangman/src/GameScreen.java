import javax.swing.*;

public class GameScreen extends JFrame{

    private JPanel panel;
    private JLabel imageLabel;

    private JPanel hangmanPanel;
    private JPanel gamePanel;
    private JPanel helperPanel;
    private JLabel secretWord;
    private JButton guessButton;
    private JTextField guess;
    private JLabel suggestionTitle;
    private JLabel suggestions;
    private JButton menuButton;
    private JLabel guessedTitle;
    private JLabel guessedChars;

    public GameScreen(){
//        imageLabel.setIcon(image);
//        secretWord.setText(str);
//        suggestions.setText(sugStr);
        guessButton.setFocusable(false);
        menuButton.setFocusable(false);

        setTitle("Hangman");
        setSize(750,500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panel);
//        setVisible(true);
    }

    public JButton getGuessButton() {
        return guessButton;
    }

    public JTextField getGuess() {
        return guess;
    }

    public JButton getMenuButton() {
        return menuButton;
    }

    public void setSuggestions(String str){
        str = str.replace("", " ").trim();
        suggestions.setText(str);
    }
    public void setGuessedChars(char[] arr){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length;i++){
            if(arr[i] == '\u0000')
                continue;
            sb.append(arr[i]).append(" ");
        }
        guessedChars.setText(sb.toString());
    }

    public void setSecretWord(String str) {
        str =str.replace(""," ").trim();
        secretWord.setText(str);
    }
    public void setImageLabel(ImageIcon icon){
        imageLabel.setIcon(icon);
    }
}
