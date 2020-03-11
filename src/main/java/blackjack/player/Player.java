package blackjack.player;

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

	public void addCard(Card card) {
		cardBundle.addCard(card);
	}

	public abstract boolean isDealer();

	public abstract boolean isGambler();

	public abstract GameReport getReport(Player player);

	public boolean isBlackjack() {
		return cardBundle.isBlackjack();
	}

	public boolean isBurst() {
		return cardBundle.isBurst();
	}

	public abstract void playerDrawCard();
}
