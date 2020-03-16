package domain.gamer;

import domain.card.Card;
import domain.card.Hands;
import domain.result.Score;

import java.util.List;
import java.util.Objects;

public abstract class Gamer {
	private static final int ZERO = 0;

	private final String name;
	private final Hands hands;

	public Gamer(String name) {
		validate(name);
		this.name = name;
		this.hands = Hands.createEmpty();
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

	public void hit(Card card) {
		hands.add(card);
	}

	public boolean canHit() {
		return calculateScore().isLowerThan(Score.from(getHitPoint()));
	}

	public Score calculateScore() {
		return Score.from(hands);
	}

	public List<Card> firstOpenedCards() {
		return hands.getCards()
				.subList(ZERO, firstOpenedCardsCount());
	}

	public List<Card> getCards() {
		return hands.getCards();
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
				Objects.equals(hands, gamer.hands);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, hands);
	}
}
