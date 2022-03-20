package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.game.Money;
import blackjack.domain.role.Role;
import java.util.List;
import java.util.stream.Collectors;

public class ResultDto {

	private final String name;
	private final List<String> cards;
	private final int totalScore;
	private final boolean bust;
	private final double revenueResult;

	private ResultDto(final Role role, final Cards cards, final Money money) {
		this.name = role.getName();
		this.cards = cards.getCards().stream()
				.map(Card::getDenominationAndSuit)
				.collect(Collectors.toList());
		this.totalScore = role.getScore();
		this.bust = cards.isBust();
		this.revenueResult = money.getValue();
	}

	public static ResultDto from(final Role role, final Money money) {
		return new ResultDto(role, role.getCards(), money);
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

	public double getRevenueResult() {
		return revenueResult;
	}

	public boolean isBust() {
		return bust;
	}
}
