package domain.gamer;

import domain.card.Hands;

/**
 *    게임 플레이어 나타내는 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class Player extends Gamer {
	private String name;
	private Hands hands;

	public Player(String name) {
		super(name);
	}
}
