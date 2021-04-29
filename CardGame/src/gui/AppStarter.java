package gui;

public class AppStarter {

    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();   // creates the gameFrame object, which inherits from JFrame
        StarterWindow starterWindow  = new StarterWindow(); // StarterWindow is the GUI responsible class, here all the gui objects' interactions are defined
        gameFrame.setContentPane(starterWindow.getPanelCont()); // Set main panel to frame to show the game
        gameFrame.setSize(900, 600); // sets size of the frame
        starterWindow.start(); // sets action listener for startButton and thus starts the game
    }

}
