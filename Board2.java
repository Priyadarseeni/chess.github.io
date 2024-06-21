package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


    public class Board2 extends JPanel {
        public int tilesize = 85;
        int cols=8;
        int rows=8;

        ArrayList<Piece> PieceList=new ArrayList<>();

        public Piece selectedPiece;

        Input input = new Input(this);

        public int enPassantTile = -1;







        public Board2(){
            this.setPreferredSize(new Dimension(cols * tilesize,rows * tilesize));

            this.addMouseListener(input);
            this.addMouseMotionListener(input);
            addPieces();
        }


        public Piece getPiece(int col,int row){
            for(Piece piece : PieceList){
                if(piece.col== col && piece.row == row){
                    return piece;
                }
            }

            return null;
        }
public void makeMove(Move move) {

    if (move.piece.name.equals("pawn")) {
        movePawn(move);
    }
    else {


        move.piece.col = move.newCol;
        move.piece.row = move.newRow;
        move.piece.xPos = move.newCol * tilesize;
        move.piece.yPos = move.newRow * tilesize;

        move.piece.isFirstMove = false;

        capture(move.capture);
    }

}

        private void movePawn(Move move) {
        //en passant
            int colorIndex = move.piece.isWhite ? 1 : -1 ;

            if (getTileNum(move.newCol, move.newRow) == enPassantTile){
                move.capture= getPiece(move.newCol,move.newRow + colorIndex);
            }
            if (Math.abs(move.piece.row- move.newRow) == 2){
                enPassantTile=getTileNum(move.newCol, move.newRow + colorIndex);
            }
            else{
                enPassantTile = -1;
            }

            //promotions
            colorIndex = move.piece.isWhite ? 0 :7;
            if (move.newRow==colorIndex){
                promotepawn(move);
            }
            move.piece.col = move.newCol;
            move.piece.row = move.newRow;
            move.piece.xPos = move.newCol * tilesize;
            move.piece.yPos = move.newRow * tilesize;

            move.piece.isFirstMove = false;
            capture(move.capture);
        }

        private void promotepawn (Move move) {
            PieceList.add(new Queen(this,move.newCol, move.newRow, move.piece.isWhite));
            capture(move.piece);
        }


        public void capture(Piece piece) {

            PieceList.remove(piece);
        }


        public boolean isValidMove(Move move){

            if (sameTeam(move.piece,move.capture)){
                return false;
            }

            if (!move.piece.isValidMovement(move.newCol,move.newRow)){
                return false;
            }
            if (move.piece.moveCollidesWithPiece(move.newCol, move.newRow)){
                return false;
            }
            return true;
        }

        public boolean sameTeam(Piece p1, Piece p2){
            if (p1== null || p2== null){
                return false;
            }
            return p1.isWhite == p2.isWhite;
        }

        public int getTileNum(int col,int row){

            return row * rows + col ;
        }




        public void addPieces(){
            PieceList.add(new Rook(this,0,0,false));
            PieceList.add(new Knight(this,1,0,false));
            PieceList.add(new Bishop(this,2,0,false));
            PieceList.add(new Queen(this,3,0,false));
            PieceList.add(new King(this,4,0,false));
            PieceList.add(new Bishop(this,5,0,false));
            PieceList.add(new Knight(this,6,0,false));
            PieceList.add(new Rook(this,7,0,false));


            PieceList.add(new Pawn(this,0,1,false));
            PieceList.add(new Pawn(this,1,1,false));
            PieceList.add(new Pawn(this,2,1,false));
            PieceList.add(new Pawn(this,3,1,false));
            PieceList.add(new Pawn(this,4,1,false));
            PieceList.add(new Pawn(this,5,1,false));
            PieceList.add(new Pawn(this,6,1,false));
            PieceList.add(new Pawn(this,7,1,false));

            PieceList.add(new Rook(this,0,7,true));
            PieceList.add(new Knight(this,1,7,true));
            PieceList.add(new Bishop(this,2,7,true));
            PieceList.add(new Queen(this,3,7,true));
            PieceList.add(new King(this,4,7,true));
            PieceList.add(new Bishop(this,5,7,true));
            PieceList.add(new Knight(this,6,7,true));
            PieceList.add(new Rook(this,7,7,true));


            PieceList.add(new Pawn(this,0,6,true));
            PieceList.add(new Pawn(this,1,6,true));
            PieceList.add(new Pawn(this,2,6,true));
            PieceList.add(new Pawn(this,3,6,true));
            PieceList.add(new Pawn(this,4,6,true));
            PieceList.add(new Pawn(this,5,6,true));
            PieceList.add(new Pawn(this,6,6,true));
            PieceList.add(new Pawn(this,7,6,true));

        }

        public void paintComponent(Graphics g){
            Graphics2D g2d=(Graphics2D) g;
//paint board
            for(int r=0;r<rows;r++)
                for (int c=0;c<cols;c++){
                    g2d.setColor((c+r)%2==0 ? new Color(227,198,181):new Color(157,105,53));
                    g2d.fillRect(c*tilesize,r*tilesize,tilesize,tilesize);
                }


//paint highlights
            if (selectedPiece != null)
                for (int r=0;r<rows;r++)
                    for (int c=0;c<cols;c++) {

                if (isValidMove(new Move(this,selectedPiece,c ,r ))){
                    g2d.setColor(new Color(68,180,57,190));
                    g2d.fillRect(c * tilesize,r * tilesize,tilesize,tilesize);

                }

            }
//paint pieces
            for (Piece piece:PieceList){
                piece.Paint(g2d);
            }
        }
    }



