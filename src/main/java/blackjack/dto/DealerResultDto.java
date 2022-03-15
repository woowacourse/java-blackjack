package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.role.Hand;
import blackjack.domain.game.Outcome;
import blackjack.domain.role.Role;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
		this.bust = hand.isBust(totalScore);
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
