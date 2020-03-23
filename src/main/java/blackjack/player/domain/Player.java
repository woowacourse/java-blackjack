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
	protected final String name;

	public Player(CardBundle cardBundle, String name) {
		Objects.requireNonNull(cardBundle, "카드번들이 존재하지 않습니다.");
		validateName(name);
		this.cardBundle = cardBundle;
		this.name = name;
	}

	private void validateName(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("이름의 입력이 없습니다.");
		}
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

	public int getScore() {
		return cardBundle.calculateScore();
	}

	public abstract boolean isHit();

	public String getName() {
		return name;
	}

	public List<Card> getCardBundle() {
		return Collections.unmodifiableList(this.cardBundle.getCards());
	}
}
