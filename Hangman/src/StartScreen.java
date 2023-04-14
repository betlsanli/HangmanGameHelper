import javax.swing.*;

public class StartScreen extends JFrame {

    private JPanel startPanel;
    private JLabel startScreenLabel;
    private JButton startButton;
    private JButton exitButton;

    public StartScreen(){
        setSize(500,250);
        setResizable(false);
        startButton.setFocusable(false);
        exitButton.setFocusable(false);
        setTitle("Hangman");
        add(startPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setVisible(true);
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
}
