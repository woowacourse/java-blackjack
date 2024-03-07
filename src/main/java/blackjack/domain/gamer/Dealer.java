package blackjack.domain.gamer;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.dto.DealerInitialHandDto;
import blackjack.dto.DealerResultDto;

public class Dealer extends BlackjackGamer {

	public Dealer() {
		super(new Name("딜러"));
	}

	@Override
	public boolean canReceiveCard() {
		return getScore() <= 16;
	}

	public DealerResultDto getResult(List<GameResult> playerResults) {
		Name name = getName();
		int winCount = (int)playerResults.stream()
			.filter(result -> result == GameResult.WIN)
			.count();
		int loseCount = playerResults.size() - winCount;

		return new DealerResultDto(name.value(), winCount, loseCount);
	}

	public DealerInitialHandDto convertDealerToDto() {
		String dealerName = getName().value();
		Card first = getFirstCard();

		return new DealerInitialHandDto(dealerName, first.convertCardToDto());
	}
}
