package domain.gamer;

import domain.card.Hands;

/**
 *   딜러 클래스입니다.
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class Dealer extends Gamer {
	private static final int DRAW_CONDITION = 16;

	private String name;
	private Hands hands;

	public Dealer() {
		super("딜러");
	}

	public boolean canDraw() {
		return scoreHands() <= DRAW_CONDITION;
	}
}
