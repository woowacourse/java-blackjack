package blackjack.dto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.Card;
import blackjack.domain.Hand;
import blackjack.domain.Outcome;
import blackjack.domain.Role;

public class DealerResultDto {

	private final String name;
	private final List<String> cards;
	private final String totalScore;
	private final Map<Outcome, Integer> competeResult;

	private DealerResultDto(final String name, final Hand hand, final Map<Outcome, Integer> competeResult) {
		this.name = name;
		this.cards = hand.getCards().stream()
			.map(Card::getInformation)
			.collect(Collectors.toList());
		this.totalScore = hand.getFinalScore();
		this.competeResult = competeResult;
	}

	public static DealerResultDto from(final Role dealer) {
		return new DealerResultDto(dealer.getName(), dealer.getHand(), dealer.getCompeteResult());
	}

	public String getName() {
		return name;
	}

	public List<String> getCards() {
		return cards;
	}

	public String getTotalScore() {
		return totalScore;
	}

	public Map<Outcome, Integer> getCompeteResult() {
		return competeResult;
	}
}
