package gui;

import javax.swing.*;

public class GameFrame extends JFrame {

    GameFrame() {  //making the window
        this.setTitle("Card Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
     //   this.setLocationRelativeTo(null);
        this.setVisible(true);
        ImageIcon image = new ImageIcon("C:\\Users\\edgar\\Desktop\\CardGame\\CardGame\\src\\resources\\game_logo.jpg");
         this.setIconImage(image.getImage());
    }

}
