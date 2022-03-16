package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.game.Money;
import blackjack.domain.role.Hand;
import blackjack.domain.role.Role;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerResultDto {

	private final String name;
	private final List<String> cards;
	private final int totalScore;
	private final boolean bust;
	private final String revenueResult;

	private PlayerResultDto(final String name, final Hand hand, final Money money) {
		this.name = name;
		this.cards = hand.getCards().stream()
				.map(Card::getInformation)
				.collect(Collectors.toList());
		this.totalScore = hand.calculateOptimalScore();
		this.bust = hand.isBust(totalScore);
		this.revenueResult = money.getValue().toString();
	}

	public static PlayerResultDto from(final Role player, final Money money) {
		return new PlayerResultDto(player.getName(), player.getHand(), money);
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

	public String getRevenueResult() {
		return revenueResult;
	}
}
