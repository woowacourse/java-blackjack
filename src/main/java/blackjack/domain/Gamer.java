package blackjack.domain;

import java.util.List;

public abstract class Gamer {
	protected final Cards cards = new Cards();
	protected final Name name;

	public Gamer(Name name) {
		this.name = name;
	}

	public void addCards(List<Card> cards) {
		cards.forEach(this.cards::addCard);
	}

	public abstract boolean canHit();

	public boolean isWin(Gamer gamer) {
		return isWinByNormalCase(gamer) || isWinBySpecialCase(gamer);
	}

	public boolean isDraw(Gamer gamer) {
		return isDrawByNormalCase(gamer) || isDrawBySpecialCase(gamer);
	}

	public boolean isLose(Gamer gamer) {
		return !isWin(gamer) && !isDraw(gamer);
	}

	private boolean isGamersInNormalCase(Gamer gamer) {
		return !this.isBlackJack() && !this.isBust() && !gamer.isBlackJack() && !gamer.isBust();
	}

	private boolean isWinByNormalCase(Gamer gamer) {
		return isGamersInNormalCase(gamer) && this.getScore() > gamer.getScore();
	}

	protected abstract boolean isWinBySpecialCase(Gamer gamer);

	private boolean isDrawBySpecialCase(Gamer gamer) {
		return this.isBlackJack() && gamer.isBlackJack();
	}

	private boolean isDrawByNormalCase(Gamer gamer) {
		return isGamersInNormalCase(gamer)
			&& this.getScore() == gamer.getScore();
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
