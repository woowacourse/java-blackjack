package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.role.Hand;
import blackjack.domain.game.Outcome;
import blackjack.domain.role.Role;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerResultDto {

	private final String name;
	private final List<String> cards;
	private final int totalScore;
	private final boolean bust;
	private final Outcome competeResult;

	private PlayerResultDto(final String name, final Hand hand, final Outcome competeResult) {
		this.name = name;
		this.cards = hand.getCards().stream()
				.map(Card::getInformation)
				.collect(Collectors.toList());
		this.totalScore = hand.calculateOptimalScore();
		this.bust = hand.isBust(totalScore);
		this.competeResult = competeResult;
	}

	public static PlayerResultDto from(final Role player, final Outcome competeResult) {
		return new PlayerResultDto(player.getName(), player.getHand(), competeResult);
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

	public Outcome getCompeteResult() {
		return competeResult;
	}
}
