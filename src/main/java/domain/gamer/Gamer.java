package domain.gamer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import domain.card.Card;
import domain.result.Score;

public abstract class Gamer {
	private final String name;
	private final List<Card> cards;

	public Gamer(String name) {
		validate(name);
		this.name = name;
		this.cards = new ArrayList<>();
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

	protected abstract Score getHitPoint();

	public void hit(Card card) {
		cards.add(card);
	}

	public boolean canHit() {
		return Score.calculate(cards).isLowerThan(getHitPoint());
	}

	public Score calculateScore() {
		return Score.calculate(cards);
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null || getClass() != object.getClass())
			return false;
		Gamer that = (Gamer)object;
		return Objects.equals(name, that.name) &&
			Objects.equals(cards, that.cards);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, cards);
	}
}
