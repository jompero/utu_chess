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

	public ArrayList<Point> getMoves(Point point) {
		ArrayList<Point> moves = new ArrayList<>();
		int x = (int) point.getX();
		int y = (int) point.getY();

		for (int[] move : moveSet) {
			for (int i = 0; i < range; i++) {
				// New point = x + (move[0] * (i + 1)), y + (move[1] * (i + 1)
				/* Run point through cb.squareState(Point) and if:
				 * !0 {
				 * check if same player as gm.getTurn(); {
				 * Break if true, moves.add(Point) if false
				 * }}
				 * else moves.add(Point)
				*/ 
				
				
				moves.add(new Point(x + (move[0] * (i + 1)), y + (move[1] * (i + 1))));
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
