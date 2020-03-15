package domain.gamer;

/**
 *   딜러 클래스입니다.
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class Dealer extends Gamer {
	private static final int DRAW_CONDITION = 16;

	public Dealer() {
		super("딜러");
	}

	@Override
	public boolean canHit() {
		return super.scoreHands() <= DRAW_CONDITION;
	}
}
