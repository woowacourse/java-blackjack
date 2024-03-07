package blackjack.domain.gamer;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.dto.DealerInitialHandDto;
import blackjack.dto.DealerResultDto;

public class Dealer extends BlackjackGamer {

	private static final String DEFAULT_DEALER_NAME = "딜러";
	private static final int DEALER_DRAW_THRESHOLD = 16;

	public Dealer() {
		super(new Name(DEFAULT_DEALER_NAME));
	}

	@Override
	public boolean canReceiveCard() {
		return getScore() <= DEALER_DRAW_THRESHOLD;
	}

	public DealerResultDto convertDealerToResultDto(List<GameResult> playerResults) {
		Name name = getName();
		int winCount = (int)playerResults.stream()
			.filter(GameResult::isLose)
			.count();
		int loseCount = playerResults.size() - winCount;

		return new DealerResultDto(name.value(), winCount, loseCount);
	}

	public DealerInitialHandDto convertDealerToInitialHandDto() {
		String dealerName = getName().value();
		Card first = getFirstCard();

		return new DealerInitialHandDto(dealerName, first.convertCardToDto());
	}
}
