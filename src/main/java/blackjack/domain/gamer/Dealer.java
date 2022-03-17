package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Dealer extends Gamer {
	private static final String DEALER_NAME = "딜러";
	private static final int HIT_THRESHOLD = 17;

	public Dealer(Cards cards) {
		super(cards, new Name(DEALER_NAME));
	}

	public Dealer() {
		super(new Cards(), new Name("딜러"));
	}

	@Override
	public boolean isWin(Gamer gamer) {
		if (!this.isBlackJack() && !gamer.isBlackJack() && !this.isBust() && !gamer.isBust()){
			return this.getScore() > gamer.getScore();
		}
		return (this.isBlackJack() && !gamer.isBlackJack()) || gamer.isBust();
	}

	public Card getRandomOneCard() {
		return this.cards.getRandomCard();
	}

	@Override
	public boolean canHit() {
		return this.getScore() < HIT_THRESHOLD;
	}
}
