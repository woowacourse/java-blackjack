package blackjack.card.domain;

// todo: 테스트 코드 작성필요
public class DrawStrategy extends GameResultStrategy {
	@Override
	public boolean isResult(CardBundle dealerCardBundle, CardBundle gamblerCardBundle) {
		if (dealerCardBundle.isBlackjack() && gamblerCardBundle.isBlackjack()) {
			return true;
		}
		if (dealerCardBundle.isSameScore(gamblerCardBundle) && checkNotBlackjack(dealerCardBundle, gamblerCardBundle)) {
			return true;
		}
		return false;
	}

	private boolean checkNotBlackjack(CardBundle dealerCardBundle, CardBundle gamblerCardBundle) {
		return !(dealerCardBundle.isBlackjack() || gamblerCardBundle.isBlackjack());
	}
}
