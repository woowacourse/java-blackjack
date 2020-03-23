package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.result.ResultType;
import blackjack.domain.user.exceptions.PlayerException;

import java.util.List;

public final class Player extends AbstractPlayer {
	private final Money money;

	public Player(Name name, Hand hand, Money money) {
		super(name, hand);
		this.money = money;

		validateNameIsDifferentFromDealer(name);
	}

	private void validateNameIsDifferentFromDealer(Name name) {
		if (new Name(DEALER_NAME).equals(name)) {
			throw new PlayerException("플레이어의 이름은 " + DEALER_NAME + "일 수 없습니다.");
		}
	}

	public static Player of(Name name, Money money) {
		return new Player(name, new Hand(), money);
	}

	public Money getMoney() {
		return money;
	}

	@Override
	public boolean canReceiveCard() {
		return !getHand().isBust();
	}

	@Override
	public List<Card> getStartHand() {
		return getHand().getHand();
	}
}
