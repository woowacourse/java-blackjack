package blackjack.player.domain;

import blackjack.card.domain.Card;
import blackjack.card.domain.CardBundle;
import blackjack.player.domain.report.GameReport;

public class Dealer extends Player {

	public static final int HIT_VALUE = 16;

	public Dealer(CardBundle cardBundle) {
		super(cardBundle, "딜러");
	}

	@Override
	public boolean isHit() {
		return cardBundle.calculateScore() <= HIT_VALUE;
	}

	@Override
	public GameReport createReport(Player player) {
		return new GameReport(player.name, this.cardBundle.calculateWinOrLose(player.cardBundle));
	}

	public Card getFirstCard() {
		return cardBundle.getFirstCard();
	}
}
