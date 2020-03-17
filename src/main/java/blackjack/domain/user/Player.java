package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.user.exceptions.PlayerException;

import java.util.List;

public final class Player extends AbstractPlayer {
	private Player(String name) {
		super(name);
		validateNameIsDifferentFromDealer(name);
	}

	private void validateNameIsDifferentFromDealer(String name) {
		if (name.equals(DEALER_NAME)) {
			throw new PlayerException("플레이어의 이름은 " + DEALER_NAME + "일 수 없습니다.");
		}
	}

	public static Player of(String name) {
		return new Player(name);
	}

	@Override
	public List<Card> getStartHand() {
		return getHand();
	}

	@Override
	public Boolean isWinner(Score dealerScore) {
		if (isBust()) {
			return false;
		}
		if (dealerScore.isOver(MAX_SCORE)) {
			return true;
		}
		return getScore().isOver(dealerScore);
	}

	@Override
	public boolean canReceiveCard() {
		return !isBust();
	}

}
