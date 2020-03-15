package domain.gamer;

import domain.card.Hands;

/**
 *    게임 플레이어 나타내는 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class Player extends Gamer {
	public Player(String name) {
		super(name);
	}

	public boolean wins(int score) {
		return (score > Hands.BLACKJACK_SCORE) || ((this.scoreHands() <= Hands.BLACKJACK_SCORE) && (score
			< this.scoreHands()));
	}

	public boolean isPush(int score) {
		return score <= Hands.BLACKJACK_SCORE && (score == this.scoreHands());
	}
}
