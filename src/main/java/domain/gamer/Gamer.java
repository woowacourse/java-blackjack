package domain.gamer;

import domain.card.Card;
import domain.card.Hand;
import domain.result.Score;

import java.util.List;
import java.util.Objects;

public abstract class Gamer {
	private static final int ZERO = 0;

	private final String name;
	private final Hand hand;

	public Gamer(String name) {
		validate(name);
		this.name = name;
		this.hand = Hand.createEmpty();
	}

	private void validate(String name) {
		validateNull(name);
		validateSpace(name);
	}

	private void validateNull(String name) {
		if (Objects.isNull(name)) {
			throw new NullPointerException("이름은 null이 될 수 없습니다.");
		}
	}

	private void validateSpace(String name) {
		if (name.trim().isEmpty()) {
			throw new IllegalArgumentException("이름은 공백이 될 수 없습니다.");
		}
	}

	protected abstract int getHitPoint();

	protected abstract int firstOpenedCardsCount();

	public Score getScore() {
		return Score.from(hand);
	}

	public void hit(Card card) {
		hand.add(card);
	}

	public boolean canHit() {
		return getScore().isLowerThan(Score.from(getHitPoint()));
	}

	public boolean hasTwoCards() {
		return hand.hasTwoCards();
	}

	public boolean isBust() {
		return getScore().isBiggerThan(Score.BLACKJACK);
	}

	public boolean isBiggerThan(Gamer gamer) {
		return getScore().isBiggerThan(gamer.getScore());
	}

	public boolean isEqualTo(Gamer gamer) {
		return getScore().isEqualTo(gamer.getScore());
	}

	public boolean isLowerThan(Gamer gamer) {
		return getScore().isLowerThan(gamer.getScore());
	}

	public List<Card> firstOpenedCards() {
		return hand.getCards()
				.subList(ZERO, firstOpenedCardsCount());
	}

	public List<Card> getCards() {
		return hand.getCards();
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Gamer gamer = (Gamer) o;
		return Objects.equals(name, gamer.name) &&
				Objects.equals(hand, gamer.hand);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, hand);
	}
}
