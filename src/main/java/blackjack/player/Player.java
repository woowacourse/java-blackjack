package blackjack.player;

import java.util.Collections;
import java.util.List;

import blackjack.GameReport;
import blackjack.player.card.Card;
import blackjack.player.card.CardBundle;

public abstract class Player {
	protected final String name;
	protected final CardBundle cardBundle;

	public Player(CardBundle cardBundle, String name) {
		this.cardBundle = cardBundle;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void addCard(Card card) {
		cardBundle.addCard(card);
	}

	public boolean isBurst() {
		return cardBundle.isBurst();
	}

	public boolean isNotBurst() {
		return !isBurst();
	}

	public boolean isBlackjack() {
		return cardBundle.isBlackjack();
	}

	public abstract boolean isDealer();

	public abstract boolean isGambler();

	public abstract boolean isDrawable();

	public abstract GameReport getReport(Player player);

	public List<Card> getCardBundle() {
		return Collections.unmodifiableList(this.cardBundle.getCards());
	}

	public int getScore() {
		return cardBundle.calculateScore();
	}
}
