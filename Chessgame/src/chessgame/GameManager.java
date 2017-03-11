package chessgame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;


public class GameManager {
	
	// Cached data from selected square
	private Square s;						// Square selection
	private ArrayList<Point> validMoves;	// Valid moves for piece in the selected square
    
    // Game data
    private GameState cachedState;			// Keeps the longest state
    private GameState currentState;			// Currently displayed state

    private int round = 0;
    private static int game = 0;
    private ChessBoard cb = ChessBoard.getInstance();
    private ArrayList<Square> board = cb.getBoard();
    private boolean winConditionMet = false;
	
	// Singleton instance of GameManager
    private static GameManager instance;
    
    public static GameManager getInstance() {
    	if (instance == null) {
    		instance = new GameManager();
    	}
    	return instance;
    }

    // ------------------ ROUND LOGIC ----------------- //
    private void nextRound() {
    	cachedState = new GameState(currentState);
    	
    	s = null;
    	validMoves.clear();
    	
    	setRound(++round);

    	if (checkmate(getTurn())) {
    		UtilityBar.updateConsole("Checkmate! " + currentState.getPlayer(1 - getTurn()) + " wins.");
    		winConditionMet = true;
    	}
    }

    // Check and mate
    private boolean check(int player) {
    	Square king = cb.getKing(player);
    	if (king != null) {
        	Point p = king.getPoint();
        	ArrayList<Point> moves = playerMoves(1 - player);
        	
        	for (Point move : moves) {
        		if (move.equals(p)) {
        			return true;
        		}
        	}
        	
        	return false;
    	}
    	return true;
    }
    
    private boolean checkmate(int player) {
    	ArrayList<Square> pieces = cb.getPlayerPieces(player);
    	
    	ArrayList<Point> moves;
    	
    	for (Square s : pieces) {
    		moves = s.getPiece().getMoves(s.getPoint());
    		for (Point move : moves) {
    			movePiece(s, cb.getSquare(move));
    			if (!check(player)) {
    				undo(getRound());
    				return false;
    			}
    			undo(getRound());
    		}
    	}
    	return true;
    }
    
	/**
	 * 
	 * @param player 0 || 1
	 * @return ArrayList of all player 0 || 1 moves
	 */
    private ArrayList<Point> playerMoves(int player) {
    	ArrayList<Point> moves = new ArrayList<>();

    	Set<Point> hs = new HashSet<>();
    	for (Square s : board) {
    		Piece p = s.getPiece();
    		if (p != null) {
    			if (p.getPlayer() == player) {
    				hs.addAll(p.getMoves(s.getPoint()));
    			}
    		}
    	}
    	moves.addAll(hs);
    	
    	return moves;
    }
    
    // -------------------------------------------------- //
    
    // ------------------ PIECE MOVEMENT ---------------- //
    
    /** Places a square with a piece in queue or moves the piece from previously selected square to newly selected if a valid move
     * 
     * @param s New square selection
     */
    public void clickQueue(Square s) {
    	if (!winConditionMet) {
        	if (this.s != null) {										// If a square has already been selected
        		if(isValidMove(s)) {									// If the square is in within valid moves, move it and proceed with game logic
        			nextRound();
        			return;
        		}
        	}
        	if (s.getPiece() != null) {									// Select the square if there is a piece		
        		if (s.getPiece().getPlayer() == getTurn()) {			// And the piece belongs to the player
            		if (this.s != null) {
            			highlightMoves(false);
            			this.s.select(false);							// Unselect previous selection
            		}
        			this.s = s;											// Select new selection
        			calculateMoves(s);
                	s.select(true);
                	highlightMoves(true);
        		}
        	}
    	}
    }
    
    // Get moves from piece and remove illegal moves
    private void calculateMoves(Square s) {
    	// Get moves
    	validMoves = s.getPiece().getMoves(s.getPoint());
    	ArrayList<Point> illegalMoves = new ArrayList<>();
    	
    	// Remove moves that will result in current player's check
		for (int i = 0; i < validMoves.size(); i++) {
			movePiece(s, cb.getSquare(validMoves.get(i)));
			if (!check(getTurn())) {
				undo(getRound());
				this.s = s;
			} else {
				illegalMoves.add(validMoves.get(i));
				undo(getRound());
				this.s = s;
			}
		}
		
		for (Point illegalMove : illegalMoves) {
			if (validMoves.remove(illegalMove)) {
			}
		}
    }
    
    // Enable javaFX stroke on validMoves
    private void highlightMoves(boolean isOn) {
    	if (validMoves != null) {
        	for (Point move : validMoves) {
        		Square s = cb.getSquare(move);
        		if (s != null) {
        			s.highlight(isOn);
        		}
        	}
    	}
    }
    
    // Verify if move is within validMoves
    private boolean isValidMove(Square s) {
    	for (Point move : validMoves) {
    		if (move.equals(s.getPoint())) {
    			movePiece(this.s, s);
    			return true;
    		}
    	}
    	return false;
    }
    
    private void movePiece(Square from, Square to) {
    	System.out.println(Chess.printPGN(round + 1, from, to));
    	to.setPiece(from.getPiece());
    	currentState.logTurn(round, from, to);
    	from.select(false);
    	highlightMoves(false);
    	from.clear();
    }
    
    private void undo(int round) {
    	currentState = new GameState(currentState, round);
    	loadGame(currentState);
    }
    
    public void redo(int round) {
    	currentState = new GameState(cachedState, round + 1);
    	loadGame(currentState);
    }
    // -------------------------------------------------- //
    
    // ------------------- GAME SETUP ------------------- //
    public void newGame() {
    	winConditionMet = false;
    	defaultStart();
    	renamePlayers();
    	setRound(0);
    }
    
    public void loadGame(GameState state) {
    	defaultStart();
    	System.out.println("[" + state.getPlayer(0) + "]");
    	System.out.println("[" + state.getPlayer(1) + "]");
    	
    	// Try to load given state
    	try {
        	for (int i = 0; i < state.getMoveHistory().size(); i += 2) {
        		Square from = cb.getSquare(state.moveHistory.get(i));
        		Square to = cb.getSquare(state.moveHistory.get(i + 1));
        		movePiece(from, to);
        		round++;
        	}
        	this.currentState = state;
        	setRound(round);
    	}
    	catch (NullPointerException npe) {
    		UtilityBar.updateConsole("Save corrupted!");
    		defaultStart();
    		npe.printStackTrace();
    		return;
    	}
    	
    	// Check if game is already finished
    	if (checkmate(getTurn())) {
    		UtilityBar.updateConsole("Checkmate! " + currentState.getPlayer(1 - getTurn()) + " won this game.");
    	}
    }
    
    private void renamePlayers() {
    	// Setup player names
    	for (int i = 0; i < currentState.getPlayers().length; i++) {
    		// Create new dialog box to change player names with appropriate text
    		String defaultName = currentState.getPlayer(i);
    		TextInputDialog renameDialog = new TextInputDialog(defaultName);
    		renameDialog.setTitle("Rename player");
    		renameDialog.setContentText("Rename " + defaultName + ".\n\nName cannot be longer than 8 characters\nand may only contain word characters.");
    		renameDialog.setHeaderText(null);
    		
    		// Get text field from the text input dialog
    		TextField textField = renameDialog.getEditor();
    		// And limit it's content to alphabetic characters and numbers and to maximum of 8 characters
    	    textField.textProperty().addListener(new ChangeListener<String>() {
    	        @Override
    	        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    	            if (!newValue.matches("\\d*")) {
    	            	newValue = newValue.replaceAll("[^\\w]", "");
    	            }
	                if (newValue.length() > 7) {
	                	newValue = newValue.substring(0, 8);
	                }
	                textField.setText(newValue);
    	        }
    	    });
    		
    		Optional<String> input = renameDialog.showAndWait();
    		int player = i;
    		input.ifPresent(name -> currentState.setPlayer(name, player));
    		if (currentState.getPlayer(i).equals("")) {
    			currentState.setPlayer(defaultName, i);
    		}
    		System.out.println("[" + currentState.getPlayer(i) + "]");
    	}
    }
    // -------------------------------------------------- //
    
    // ------------------- PIECE SETUP ------------------ //
    private void defaultStart() {
    	// Add 1 to game counter, used by UtilityBar to determine which alert is to be displayed
    	game++;
    	// Reset rounds and game state
    	round = 0;
    	RoundCounter.getInstance().refresh(getRound());
    	s = null;
    	currentState = new GameState();
    	
    	// Place pieces
        String backline= "RNBQKBNR"; 
        
        cb.clearBoard();
        ArrayList<Square> board = cb.getBoard();
        
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
    public int getTurn() {
    	return round % 2;
    }
    
    public int getRound() {
    	return round + 1;
    }
    
    public int getGame() {
    	return game;
    }
    
    public void setRound(int round) {
    	this.round = round;
    	RoundCounter.getInstance().refresh(getRound());
    	UtilityBar.updateConsole("Turn: " + currentState.getPlayer(getTurn()));
    }
    
    public GameState getState() {
    	return currentState;
    }
    // -------------------------------------------------- //
}
