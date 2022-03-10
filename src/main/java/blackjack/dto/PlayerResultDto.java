package blackjack.dto;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import blackjack.domain.Card;
import blackjack.domain.Hand;
import blackjack.domain.Outcome;
import blackjack.domain.Role;

public class PlayerResultDto {

	private final String name;
	private final List<String> cards;
	private final int totalScore;
	private final Outcome competeResult;

	private PlayerResultDto(final String name, final Hand hand, final Map<Outcome, Integer> competeResult) {
		this.name = name;
		this.cards = hand.getCards().stream()
			.map(Card::getInformation)
			.collect(Collectors.toList());
		this.totalScore = hand.calculateOptimalScore();
		this.competeResult = competeResult.keySet().stream()
			.findFirst()
			.orElseThrow(NoSuchElementException::new);
	}

	public static PlayerResultDto from(final Role player) {
		return new PlayerResultDto(player.getName(), player.getHand(), player.getCompeteResult());
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

	public Outcome getCompeteResult() {
		return competeResult;
	}
}
