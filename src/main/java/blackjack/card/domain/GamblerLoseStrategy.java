package blackjack.card.domain;

// todo: 테스트 코드 작성필요
public class GamblerLoseStrategy extends GameResultStrategy {
	@Override
	public boolean isResult(CardBundle dealerCardBundle, CardBundle gamblerCardBundle) {
		return gamblerCardBundle.isBurst()
			|| isGreatorThan(dealerCardBundle, gamblerCardBundle)
			|| isBlackjackWin(dealerCardBundle, gamblerCardBundle);
	}
}
