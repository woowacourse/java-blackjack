package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public abstract class Gamer {
	protected final Cards cards;
	private final Name name;

	protected Gamer(Cards cards, Name name) {
		this.cards = cards;
		this.name = name;
	}

	public void addCards(List<Card> cards) {
		cards.forEach(this.cards::addCard);
	}

	public abstract boolean canHit();

	public abstract boolean isWin(Gamer gamer);

	public boolean isDraw(Gamer gamer) {
		return !isWin(gamer) || gamer.isWin(this);
	}

	public boolean isLose(Gamer gamer) {
		return !isWin(gamer) && !isDraw(gamer);
	}

	protected boolean isBlackJackWin(Gamer gamer) {
		return this.isBlackJack() && !gamer.isBlackJack();
	}

	protected boolean isHigherScore(Gamer gamer) {
		return this.getScore() > gamer.getScore();
	}

	protected boolean isNotBustBoth(Gamer gamer) {
		return !this.isBust() && !gamer.isBust();
	}

	public List<Card> getCards() {
		return this.cards.getCards();
	}

	public int getScore() {
		return this.cards.getScore();
	}

	public String getName() {
		return this.name.getName();
	}

	public boolean isBust() {
		return this.cards.isBust();
	}

	public boolean isBlackJack() {
		return this.cards.isBlackJack();
	}
}
