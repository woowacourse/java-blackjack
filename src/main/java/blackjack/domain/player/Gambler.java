package blackjack.domain.player;

import blackjack.domain.card.CardBundle;
import blackjack.domain.generic.BettingMoney;

public class Gambler extends Player {
	public Gambler(CardBundle cardBundle, PlayerInfo playerInfo) {
		super(cardBundle, playerInfo);
	}

	public BettingMoney getResultMoney(Double rate) {
		return super.playerInfo.getBettingMoney().multipleRate(rate);
	}

	@Override
	public boolean isDrawable() {
		return isNotBurst() && isNotBlackjack();
	}

}
