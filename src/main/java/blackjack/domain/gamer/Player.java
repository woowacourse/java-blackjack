package blackjack.domain.gamer;

import blackjack.domain.betting.BettingToken;
import blackjack.domain.card.Cards;

public class Player extends Gamer {
	private final BettingToken bettingToken;

	public Player(Cards cards, BettingToken bettingToken, Name name) {
		super(cards, name);
		this.bettingToken = bettingToken;
	}

	public Player(Cards cards, Name name) {
		this(cards, new BettingToken(), name);
	}

	public Player(BettingToken bettingToken, Name name) {
		this(new Cards(), bettingToken, name);
	}

	public Player(Name name) {
		this(new Cards(), name);
	}

	public BettingToken getBettingToken() {
		return bettingToken;
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
