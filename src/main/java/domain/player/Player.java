package domain.player;

import domain.card.CardDeck;
import domain.card.PlayerCards;

import java.util.Objects;

public class Player implements PlayerInterface {
	protected String name;
	protected PlayerCards playerCards;

	public void cardDraw(CardDeck cardDeck, int count) {
		playerCards.addAll(cardDeck.draw(count));
	}

	public void cardDraw(CardDeck cardDeck) {
		playerCards.addAll(cardDeck.draw());
	}

	public String getName() {
		return this.name;
	}

	public int calculateScore() {
		return playerCards.calculateScore();
	}

	public String toStringAllCard() {
		return name + "카드: " + playerCards.toStringAllCard();
	}

	public String toStringOneCard() {
		return name + "카드: " + playerCards.toStringOneCard();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User) o;
		return Objects.equals(name, user.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

}
