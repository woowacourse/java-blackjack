package domain;

import java.util.List;

public abstract class User {
	private final Name name;
	private Cards cards;

	public User(String name) {
		this.name = new Name(name);
		this.cards = new Cards();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public String getName() {
		return name.getValue();
	}

	public int getScore() {
		return cards.getScore()
				.getValue();
	}

	public int getCardSize() {
		return cards.getSize();
	}

	public List<Card> getCards() {
		return cards.getValue();
	}

	public boolean isBlackJack() {
		return cards.getScore()
				.isBlackjack(cards.getSize());
	}

	public boolean isNotBlackJack() {
		return !isBlackJack();
	}

	public boolean isBust() {
		return cards.getScore()
				.isBust();
	}

	public boolean isNotBust() {
		return !isBust();
	}

	public boolean isScoreGreaterThan(int score) {
		return cards.getScore()
				.isGreaterThan(score);
	}
	
	public boolean isLose(User that) {
		return !isWin(that) && !isDraw(that);
	}

	public boolean isDraw(User that) {
		return !isWin(that) && !that.isWin(this);
	}

	public abstract boolean isWin(User that);
}
