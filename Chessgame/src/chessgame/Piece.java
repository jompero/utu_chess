package chessgame;

import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract class Piece extends ImageView {
	protected Image sprite;
	protected int player;

	protected int range = 8;
	protected int[][] moveSet = { { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 0 },
			{ -1, 1 } };
	
	ChessBoard cb = ChessBoard.getInstance();
	GameManager gm = GameManager.getInstance();

	public Piece(int player) {
		this.player = player;
	}

	public void drawSprite() {
		super.setFitHeight(Chess.SQUARESIZE);
		super.setFitWidth(Chess.SQUARESIZE);
		this.setImage(sprite);
	}

	/**
	 * Returns all possible moves including moves that causes check.
	 * @param point Point where the piece is.
	 * @return All possible moves.
	 */
	public ArrayList<Point> getMoves(Point point) {
		ArrayList<Point> moves = new ArrayList<>();
		int x = (int) point.getX();
		int y = (int) point.getY();
		
		// For each direction add a square until blocking cause.
		for (int[] move : moveSet) {
			for (int i = 0; i < range; i++) {
				Point  p = new Point(x + (move[0] * (i + 1)), y + (move[1] * (i + 1)));
				int squareState = cb.squareState(p);
				// If square contains a piece
				if (squareState > -1) {
					// If the piece is not allied, add and then break direction.
					if (player != squareState) {
						moves.add(p);
						break;
					} else {
					// Else just break.
						break;
					}
				}
				// If the square is out of bounds, break direction.
				else if (squareState < -1) {
					break;
				}
				moves.add(p);
			}
		}
		return moves;
	}

	public int getPlayer() {
		return player;
	}

	/**
	 * @param range
	 *            >= 0
	 */
	protected void setRange(int range) {
		this.range = range;
	}

	protected void setMoveSet(int[][] moveSet) {
		this.moveSet = moveSet;
	}
}
