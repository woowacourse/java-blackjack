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
	private final int totalScore;
	private final boolean bust;
	private final Map<Outcome, Long> competeResult;

	private DealerResultDto(final String name, final Hand hand, final Map<Outcome, Long> competeResult) {
		this.name = name;
		this.cards = hand.getCards().stream()
				.map(Card::getInformation)
				.collect(Collectors.toList());
		this.totalScore = hand.calculateOptimalScore();
		this.bust = hand.isBustScore(totalScore);
		this.competeResult = competeResult;
	}

	public static DealerResultDto from(final Role dealer, final Map<Outcome, Long> competeResult) {
		return new DealerResultDto(dealer.getName(), dealer.getHand(), competeResult);
	}

	public String getName() {
		return name;
	}

	public List<String> getCards() {
		return cards;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public boolean isBust() {
		return bust;
	}

	public Map<Outcome, Long> getCompeteResult() {
		return competeResult;
	}
}
