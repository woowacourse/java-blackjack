package domain;

import java.util.List;

import domain.card.Card;
import domain.card.Cards;

public class User {

	private static final int NAME_MAX_LENGTH = 5;
	private static final String DEALER_NAME = "딜러";
	private static final int DEALER_LIMIT = 17;
	private static final int BLACK_JACK_SCORE = 21;


	protected String name;
	protected Cards cards;

	public User(final String name, Cards cards) {
		validate(name);
		this.name = name;
		this.cards = cards;
	}

	public void hit(Card card) {
		cards.addCard(card);
	}

	public boolean isHittable(){
		if(name.equals(DEALER_NAME) && cards.isHittable(DEALER_LIMIT))
			return true;
		return !name.equals(DEALER_NAME) && cards.isHittable(BLACK_JACK_SCORE);
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
