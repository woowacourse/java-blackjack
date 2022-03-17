package blackjack.domain.gamer;

import blackjack.domain.card.Cards;

public class Player extends Gamer {

	public Player(Cards cards, Name name) {
		super(cards, name);
	}

	public Player(Name name) {
		this(new Cards(), name);
	}

	@Override
	public boolean isWin(Gamer gamer) {
		if (!this.isBlackJack() && !gamer.isBlackJack() && !this.isBust() && !gamer.isBust()){
			return this.getScore() > gamer.getScore();
		}
		return (this.isBlackJack() && !gamer.isBlackJack()) || (!this.isBust() && gamer.isBust());
	}

	@Override
	public boolean canHit() {
		return !this.isBlackJack() && !this.isBust();
	}
}
