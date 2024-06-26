
package src;

import java.awt.image.BufferedImage;


    public class King extends Piece {
        public King(Board2 board, int col, int row, boolean isWhite){
            super(board);
            this.col=col;
            this.row=row;
            this.xPos=col * board.tilesize;
            this.yPos=row * board.tilesize;
            this.isWhite=isWhite;
            this.name="King";

            this.sprite=sheet.getSubimage(0 , isWhite ? 0 : sheetScale,sheetScale,sheetScale).getScaledInstance(board.tilesize,board.tilesize, BufferedImage.SCALE_SMOOTH);
        }

        @Override
        public boolean isValidMovement(int col, int row) {
            return Math.abs((col-this.col)*(row-this.row)) == 1 || Math.abs(col-this.col) + Math.abs(row-this.row) == 1;
        }
    }



