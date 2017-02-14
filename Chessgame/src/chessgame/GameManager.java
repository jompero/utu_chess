package chessgame;

import java.util.ArrayList;

public class GameManager {
	
    Square s;						// Queued square (always contains a piece if not null)
    ArrayList<Square> validMoves;	// Valid moves for queued square
    GameState state;
	
    static int round = 0;

    static GameManager instance;
    
    private GameManager() {
    	defaultStart();
    }
    
    public static GameManager getInstance() {
    	if (instance == null) {
    		instance = new GameManager();
    	}
    	return instance;
    }

    // ------------------ TURN HANDLING ----------------- //
    private void nextRound() {
    	s = null;
    	validMoves.clear();
    	
    	round++;
    	RoundCounter.getInstance().refresh();
    }

    // Places a square with a piece in queue or moves the piece from previously selected square to newly selected if a valid move
    public void clickQueue(Square s) {
    	if (this.s != null) {										// If a square has already been selected
    		if(isValidMove(s)) {									// If the square is in within valid moves
    			movePiece(this.s, s);								// Move piece and proceed with game logic
    			nextRound();
    			return;
    		}
    	}
    	if (s.getPiece() != null) {									// Select the square if there is a piece		
    		if (s.getPiece().getPlayer() == getTurn()) {			// And the piece belongs to the player
        		if (this.s != null) {
        			this.s.select();
        			this.s.highlightMoves();
        		}
    			this.s = s;
            	this.s.highlightMoves();
            	s.select();
            	if (s.getPiece() != null) {
            		validMoves = s.getAvailableMoves();
            	} else {
            		validMoves.clear();
            	}
    		}
    	}
    }
    
    private boolean isValidMove(Square s) {
    	for (Square move : validMoves) {
    		if (move == s) {
    			if (s.getPiece() != null) {
    				if (s.getPiece().getPlayer() != getTurn()) {
    					return true;
    				}
    			} else {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    public void movePiece(Square from, Square to) {
    	System.out.println(Chess.printPGN(round + 1, from, to));
    	to.setPiece(from.getPiece());
    	state.logTurn(round, from, to);
    	from.select();
    	from.highlightMoves();
    	from.clear();
    }
    // -------------------------------------------------- //
    
    // ------------------- PIECE SETUP ------------------ //
    public void loadState(GameState state) {
    	this.state = state;
    	for (int i = 0; i < state.getMoveHistory().size(); i++) {
    		Square from = ChessBoard.getSquare(state.moveHistory.get(i));
    		Square to = ChessBoard.getSquare(state.moveHistory.get(++i));
    		movePiece(from, to);
    	}
    }
    
    public void defaultStart() {
    	round = 0;
    	RoundCounter.getInstance().refresh();
    	s = null;
    	state = new GameState();
    	
    	
        String backline= "RNBQKBNR"; 
        
        ChessBoard.clearBoard();
        ArrayList<Square> board = ChessBoard.getBoard();
        
        for (int i = 0; i < backline.length(); i++) {
            // Set player 2 backline
            board.get(i).setPiece(newPiece(backline.charAt(i), 1));
            // Set player 1 backline
            board.get(i + 7 * Chess.BOARDSIZE).setPiece(newPiece(backline.charAt(i), 0));
            
            //Set pawns
            board.get(i + Chess.BOARDSIZE).setPiece(newPiece('P', 1));
            board.get(i + 6 * Chess.BOARDSIZE).setPiece(newPiece('P', 0));
        }
    }
    
    private Piece newPiece(char piece, int player) {
        switch (piece) {
            case 'R':   return new Rook(player);
            case 'N':   return new Knight(player);           
            case 'B':   return new Bishop(player);
            case 'Q':   return new Queen(player);
            case 'K':   return new King(player);
            case 'P':   return new Pawn(player);
            default:    return null;
        }
    }
    // -------------------------------------------------- //
    
    // --------------- GET AND SET METHODS -------------- //
    public static int getTurn() {
    	return round % 2;
    }
    
    public static int getRound() {
    	return round + 1;
    }
    
    public GameState getState() {
    	return state;
    }
    // -------------------------------------------------- //
}
