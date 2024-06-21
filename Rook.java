package src;

import java.awt.image.BufferedImage;


public class Rook extends Piece {
    public Rook(Board2 board, int col, int row, boolean isWhite){
        super(board);
        this.col=col;
        this.row=row;
        this.xPos=col * board.tilesize;
        this.yPos=row * board.tilesize;
        this.isWhite=isWhite;
        this.name="Rook";

        this.sprite=sheet.getSubimage(4 * sheetScale, isWhite ? 0 : sheetScale,sheetScale,sheetScale).getScaledInstance(board.tilesize,board.tilesize, BufferedImage.SCALE_SMOOTH);
    }

    @Override
    public boolean isValidMovement(int col, int row) {
        return this.col== col|| this.row==row;
    }
    public boolean moveCollidesWithPiece(int col, int row) {
        //left
        if (this.col > col)
            for (int c = this.col - 1;c> col;c--)
                if (board.getPiece(c,this.row)!=null)
                    return true;
        //right
        if (this.col < col)
            for (int c= this.col + 1;c< col;c++)
                if (board.getPiece(c,this.row)!=null)
                    return true;
        //up
        if (this.row > row)
            for (int r = this.row - 1; r > row; r--)
                if (board.getPiece(this.col,r)!=null)
                    return true;
        //down
        if (this.row < row)
            for (int r = this.row + 1;r< row;r++)
                if (board.getPiece(this.col,r)!=null)
                    return true;

        return false;


    }
}






