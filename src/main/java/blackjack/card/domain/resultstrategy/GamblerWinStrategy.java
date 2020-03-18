package blackjack.card.domain;

// todo: 테스트 코드 작성필요
public class GamblerWinStrategy extends GameResultStrategy {
	@Override
	public boolean isResult(CardBundle dealerCardBundle, CardBundle gamblerCardBundle) {
		if ((gamblerCardBundle.isNotBurst() && dealerCardBundle.isBurst())
			|| isGreatorThan(gamblerCardBundle, dealerCardBundle)) {
			return true;
		}
		return false;
	}
}
