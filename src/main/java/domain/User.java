package domain;

import java.util.List;

public abstract class User {

	private static final int NAME_MAX_LENGTH = 5;

	protected Cards cards;
	protected String name;

	public User(final String name) {
		cards = new Cards();
		validate(name);
		this.name = name;
	}

	public void hit(Card card) {
		cards.addCard(card);
	}

	private void validate(final String name) {
		validateBlank(name);
		validateLength(name);
	}

	private void validateBlank(final String name) {
		if (name.isBlank()) {
			throw new IllegalArgumentException("플레이어의 이름은 공백으로만 이루어질 수 없습니다.");
		}
	}

	private void validateLength(final String name) {
		if (name.length() > NAME_MAX_LENGTH) {
			throw new IllegalArgumentException(String.format("플레이어의 이름은 %d보다 길 수 없습니다.", NAME_MAX_LENGTH));
		}

	}

	public abstract boolean isHittable();

	public String getName() {
		return name;
	}

	public List<Card> getCards() {
		return cards.getCards();
	}

	public int getScore() {
		return cards.calculateScore();
	}

	public List<String> getCardNames() {
		return cards.getCardNames();
	}
}
