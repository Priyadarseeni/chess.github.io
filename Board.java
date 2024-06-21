

import javax.swing.*;
import java.awt.*;


    public class Board {
        public static void main(String[] args) {
            JFrame frame=new JFrame();
            frame.getContentPane().setBackground(Color.black);
            frame.setLayout(new GridBagLayout());
            frame.setMinimumSize(new Dimension(1000,1000));
            frame.setLocationRelativeTo(null);
            Board2 board=new Board2();
            frame.add(board);
            frame.setVisible(true);

    }

}
