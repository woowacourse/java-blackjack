package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.game.Money;
import blackjack.domain.role.Hand;
import blackjack.domain.role.Role;
import java.util.List;
import java.util.stream.Collectors;

public class DealerResultDto {

	private final String name;
	private final List<String> cards;
	private final int totalScore;
	private final boolean bust;
	private final int revenueResult;

	private DealerResultDto(final String name, final Hand hand, final Money money) {
		this.name = name;
		this.cards = hand.getCards().stream()
				.map(Card::getDenominationAndSuit)
				.collect(Collectors.toList());
		this.totalScore = hand.calculateOptimalScore();
		this.bust = hand.isBust(totalScore);
		this.revenueResult = money.getValue().intValue();
	}

	public static DealerResultDto from(final Role dealer, final Money money) {
		return new DealerResultDto(dealer.getName(), dealer.getHand(), money);
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

	public int getRevenueResult() {
		return revenueResult;
	}
}
