package chess;

import java.util.Collection;
import java.util.Objects;
import java.util.HashSet;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private ChessGame.TeamColor pieceColor;
    private PieceType type;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new HashSet<ChessMove>();
        if (getPieceType() == PieceType.KING) {
            moves = kingMoves(board, myPosition);
        } else if (getPieceType() == PieceType.QUEEN) {
            moves = bishopMoves(board, myPosition);
            HashSet<ChessMove> rookMoves = rookMoves(board, myPosition);
            moves.addAll(rookMoves);
        } else if (getPieceType() ==  PieceType.BISHOP) {
            moves = bishopMoves(board, myPosition);
        } else if (getPieceType() == PieceType.ROOK) {
            moves = rookMoves(board, myPosition);
        } else if (getPieceType() == PieceType.KNIGHT) {
            moves =  knightMoves(board, myPosition);
        } else if (getPieceType() == PieceType.PAWN) {
            moves = pawnMoves(board, myPosition);
        }
        return moves;
    }

    public boolean checkMove(ChessBoard board, ChessPosition myPosition, ChessPosition endPosition) {
        int row = endPosition.getRow();
        int col = endPosition.getColumn();

        if (1<row && row<8 && 1<col && col<8 ) {
            ChessPiece piece = board.getPiece(endPosition);
            if (piece == null) {
                return true;
            } else if (piece.getTeamColor() != pieceColor) {
                return true;
            }
        }
        return false;
    }

    public HashSet<ChessMove> kingMoves(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> moves = new HashSet<>();
        HashSet<ChessMove> possibleMoves = new HashSet<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        possibleMoves.add(new ChessMove(myPosition, new ChessPosition(row+1, col-1), null));
        possibleMoves.add(new ChessMove(myPosition, new ChessPosition(row+1, col), null));
        possibleMoves.add(new ChessMove(myPosition, new ChessPosition(row+1, col+1), null));
        possibleMoves.add(new ChessMove(myPosition, new ChessPosition(row, col-1), null));
        possibleMoves.add(new ChessMove(myPosition, new ChessPosition(row, col+1), null));
        possibleMoves.add(new ChessMove(myPosition, new ChessPosition(row-1, col-1), null));
        possibleMoves.add(new ChessMove(myPosition, new ChessPosition(row-1, col), null));
        possibleMoves.add(new ChessMove(myPosition, new ChessPosition(row-1, col+1), null));

        for (ChessMove m : possibleMoves) {
            if (checkMove(board, myPosition, m.getEndPosition())) {
                moves.add(m);
            }
        }

        return moves;
    }

    public HashSet<ChessMove> knightMoves(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> moves = new HashSet<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        return moves;
    }

    public HashSet<ChessMove> rookMoves(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> moves = new HashSet<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        return moves;
    }

    public HashSet<ChessMove> bishopMoves(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> moves = new HashSet<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        return moves;
    }

    public HashSet<ChessMove> pawnMoves(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> moves = new HashSet<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        return moves;
    }

}
