package blackjack.player.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import blackjack.card.domain.Card;
import blackjack.card.domain.CardBundle;

public abstract class Player {
	private static final int STARTING_CARDS = 2;
	protected final CardBundle cardBundle;

	public Player(CardBundle cardBundle) {
		Objects.requireNonNull(cardBundle, "카드번들이 존재하지 않습니다.");
		this.cardBundle = cardBundle;
	}

	public void addCard(Card card) {
		cardBundle.addCard(card);
	}

	public void addCard(Card card, Consumer consumer) {
		cardBundle.addCard(card);
		consumer.accept(this);
	}

	public void addCard(Card card, Runnable runnable) {
		cardBundle.addCard(card);
		runnable.run();
	}

	public boolean isNotBurst() {
		return cardBundle.isNotBurst();
	}

	public boolean isBlackjack() {
		return cardBundle.isBlackjack();
	}

	public boolean isDealer() {
		return this instanceof Dealer;
	}

	public boolean isGambler() {
		return this instanceof Gambler;
	}

	public boolean hasStatingCards() {
		return cardBundle.isSameCount(STARTING_CARDS);
	}

	public abstract boolean isHit();

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Player player = (Player)o;
		return Objects.equals(cardBundle, player.cardBundle);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardBundle);
	}

	public abstract String getName();

	public List<Card> getCardBundle() {
		return Collections.unmodifiableList(this.cardBundle.getCards());
	}

	public int getScore() {
		return cardBundle.calculateScore();
	}

}
