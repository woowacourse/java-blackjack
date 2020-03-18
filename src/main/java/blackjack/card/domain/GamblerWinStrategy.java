package blackjack.card.domain;

// todo: 테스트 코드 작성필요
public class GamblerWinStrategy extends GameResultStrategy {
	@Override
	public boolean isResult(CardBundle dealerCardBundle, CardBundle gamblerCardBundle) {
		if (gamblerCardBundle.isNotBurst()) {
			return isGreatorThan(gamblerCardBundle, dealerCardBundle) ||
				isBlackjackWin(gamblerCardBundle, dealerCardBundle);
		}
		return false;
	}
}
