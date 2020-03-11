package blackjack.player;

import blackjack.player.card.CardBundle;

public class Dealer extends Player {
	public Dealer(CardBundle cardBundle) {
		super(cardBundle);
	}

	@Override
	public boolean isDealer() {
		return true;
	}

	@Override
	public boolean isGambler() {
		return false;
	}
}
