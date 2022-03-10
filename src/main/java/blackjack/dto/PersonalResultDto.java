package blackjack.dto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.Card;
import blackjack.domain.Hand;
import blackjack.domain.Outcome;
import blackjack.domain.Role;

public class PersonalResultDto {

	private final List<String> cards;
	private final int totalScore;
	private final Map<Outcome, Integer> competeResult;

	private PersonalResultDto(final Hand hand, final Map<Outcome, Integer> competeResult) {
		this.cards = hand.getCards().stream()
			.map(Card::getInformation)
			.collect(Collectors.toList());
		this.totalScore = hand.calculateOptimalScore();
		this.competeResult = competeResult;
	}

	public static PersonalResultDto from(final Role role) {
		return new PersonalResultDto(role.getHand(), role.getCompeteResult());
	}
}
