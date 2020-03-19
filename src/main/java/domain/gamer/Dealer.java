package domain.gamer;

/**
 *   딜러 클래스입니다.
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class Dealer extends Gamer {
	private static final int DRAW_CONDITION = 16;

	public Dealer() {
		super(new Name("딜러"));
	}

	public boolean canDraw() {
		return scoreHands() <= DRAW_CONDITION;
	}

	@Override
	public boolean canHit() {
		return scoreHands() <= DRAW_CONDITION;
	}
}
