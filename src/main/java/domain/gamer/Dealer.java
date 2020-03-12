package domain.gamer;

import domain.card.Hands;

/**
 *   딜러 클래스입니다.
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class Dealer extends Gamer {
	private String name;
	private Hands hands;

	public Dealer() {
		super("딜러");
	}
}
