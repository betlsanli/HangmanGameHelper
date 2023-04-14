import javax.swing.*;

public class EndScreen extends JFrame{
    private JPanel endPanel;
    private JLabel endScreenLabel;
    private JLabel resLabel;
    private JButton newGameButton;
    private JButton exitButton;

    public EndScreen(){

        newGameButton.setFocusable(false);
        exitButton.setFocusable(false);
        setSize(500,250);
        setResizable(false);
        setTitle("Hangman");
        add(endPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setVisible(true);
    }

    public JButton getNewGameButton() {
        return newGameButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JLabel getEndScreenLabel() {
        return endScreenLabel;
    }

    public JLabel getResLabel() {
        return resLabel;
    }
}
