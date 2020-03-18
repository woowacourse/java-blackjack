package blackjack.player.domain;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import blackjack.card.domain.Card;
import blackjack.card.domain.CardBundle;
import blackjack.player.domain.report.GameReport;

public abstract class Player {
	protected final String name;
	protected final CardBundle cardBundle;

	public Player(CardBundle cardBundle, String name) {
		validate(cardBundle, name);
		this.cardBundle = cardBundle;
		this.name = name;
	}

	private void validate(CardBundle cardBundle, String name) {
		checkCardBundle(cardBundle);

	}

	private void checkCardBundle(CardBundle cardBundle) {
		if (cardBundle == null) {
			throw new IllegalArgumentException("카드번들이 존재하지 않습니다.");
		}
	}

	public String getName() {
		return this.name;
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

	public boolean isNotBlackjack() {
		return !isBlackjack();
	}

	public boolean isDealer() {
		return this instanceof Dealer;
	}

	public boolean isGambler() {
		return this instanceof Gambler;
	}

	public abstract boolean isHit();

	public abstract GameReport createReport(Player player);

	public List<Card> getCardBundle() {
		return Collections.unmodifiableList(this.cardBundle.getCards());
	}

	public int getScore() {
		return cardBundle.calculateScore();
	}
}
