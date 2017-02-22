package chessgame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;


public class GameManager {
	
	// Cached data from selected square
    Square s;						// Square selection
    ArrayList<Point> validMoves;	// Valid moves for piece in the selected square
    
    // Game data
    GameState state;
	int round = 0;
	static int game = 0;
	ChessBoard cb = ChessBoard.getInstance();
	
	// Singleton instance of GameManager
    static GameManager instance;
    
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
    	
    	setRound(++round);
    }

    /** Places a square with a piece in queue or moves the piece from previously selected square to newly selected if a valid move
     * 
     * @param s New square selection
     */
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
        			highlightMoves(false);
        			this.s.select(false);							// Unselect previous selection
        			
        		}
    			this.s = s;											// Select new selection
    			validMoves = s.getPiece().getMoves(s.getPoint());
            	s.select(true);
            	highlightMoves(true);
            	if (s.getPiece() != null) {
            		validMoves = s.getPiece().getMoves(s.getPoint());
            	} else {
            		validMoves.clear();
            	}
    		}
    	}
    }
    
    private void highlightMoves(boolean isOn) {
    	for (Point move : validMoves) {
    		Square s = cb.getSquare(move);
    		if (s != null) {
    			s.highlight(isOn);
    		}
    	}
    }
    
    // Verify if move is within validMoves
    private boolean isValidMove(Square s) {
    	
    	//////DELETE WHEN GETMOVES LOGIC IS DONE//////
    	if (s.getPiece() != null) {
    		if (s.getPiece().getPlayer() == getTurn()) {
    			return false;
    		}
    	}
    	//////////////////////////////////////////////
    	
    	for (Point move : validMoves) {
    		if (move.equals(s.getPoint())) {
    			return true;
    		}
    	}
    	return false;
    }
    
    private void movePiece(Square from, Square to) {
    	System.out.println(Chess.printPGN(round + 1, from, to));
    	to.setPiece(from.getPiece());
    	state.logTurn(round, from, to);
    	from.select(false);
    	highlightMoves(false);
    	from.clear();
    }
    // -------------------------------------------------- //
    
    // ------------------- GAME SETUP ------------------- //
    public void newGame() {
    	defaultStart();
    	renamePlayers();
    	setRound(0);
    }
    
    public void loadGame(GameState state) {
    	defaultStart();
    	System.out.println("[" + state.getPlayer(0) + "]");
    	System.out.println("[" + state.getPlayer(1) + "]");
    	try {
        	for (int i = 0; i < state.getMoveHistory().size(); i += 2) {
        		Square from = cb.getSquare(state.moveHistory.get(i));
        		Square to = cb.getSquare(state.moveHistory.get(i + 1));
        		movePiece(from, to);
        		round++;
        	}
        	this.state = state;
        	setRound(round);
    	}
    	catch (NullPointerException npe) {
    		UtilityBar.updateConsole("Save file corrupted!");
    		defaultStart();
    		npe.printStackTrace();
    	}
    }
    
    private void renamePlayers() {
    	// Setup player names
    	for (int i = 0; i < state.getPlayers().length; i++) {
    		// Create new dialog box to change player names with appropriate text
    		String defaultName = state.getPlayer(i);
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
    		input.ifPresent(name -> state.setPlayer(name, player));
    		if (state.getPlayer(i).equals("")) {
    			state.setPlayer(defaultName, i);
    		}
    		System.out.println("[" + state.getPlayer(i) + "]");
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
    	state = new GameState();
    	
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
    	UtilityBar.updateConsole("Turn: " + state.getPlayer(getTurn()));
    }
    
    public GameState getState() {
    	return state;
    }
    // -------------------------------------------------- //
}
