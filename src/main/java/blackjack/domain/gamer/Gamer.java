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

	protected Gamer() {
		this(new Cards(), new Name());
	}

	public void addCards(List<Card> cards) {
		cards.forEach(this.cards::addCard);
	}

	public abstract boolean canHit();

	public abstract boolean isWin(Gamer gamer);

	public boolean isDraw(Gamer gamer) {
		return (isGamersInNormalCase(gamer)
			&& this.getScore() == gamer.getScore()) || this.isBlackJack() && gamer.isBlackJack();
	}

	public boolean isLose(Gamer gamer) {
		return !isWin(gamer) && !isDraw(gamer);
	}

	private boolean isGamersInNormalCase(Gamer gamer) {
		return !this.isBlackJack() && !this.isBust() && !gamer.isBlackJack() && !gamer.isBust();
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
