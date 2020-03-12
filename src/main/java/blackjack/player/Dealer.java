package blackjack.player;

import blackjack.card.CardBundle;
import blackjack.card.GameReport;
import blackjack.card.GameResult;

public class Dealer extends Player {

	private static final int HIT_VALUE = 16;

	public Dealer(CardBundle cardBundle) {
		super(cardBundle, "딜러");
	}

	@Override
	public boolean isDealer() {
		return true;
	}

	@Override
	public boolean isGambler() {
		return false;
	}

	@Override
	public boolean isDrawable() {
		return cardBundle.calculateScore() <= HIT_VALUE;
	}

	@Override
	public GameReport getReport(Player player) {
		GameResult gameResult = this.cardBundle.compare(player.cardBundle);
		return new GameReport(player.name, gameResult);
	}
}
