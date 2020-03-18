package blackjack.card.domain;

public class GamblerBlackJackWinStrategy extends GameResultStrategy {
	@Override
	public boolean isResult(CardBundle dealerCardBundle, CardBundle gamblerCardBundle) {
		return isBlackjackWin(gamblerCardBundle, dealerCardBundle);
	}
}
