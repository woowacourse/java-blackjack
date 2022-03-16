package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.role.Role;
import java.util.List;
import java.util.stream.Collectors;

public class DealerTableDto {

	private final String roleName;
	private final List<String> cards;

	private DealerTableDto(final String roleName, final List<Card> cards) {
		this.roleName = roleName;
		this.cards = cards.stream()
				.map(Card::getDenominationAndSuit)
				.collect(Collectors.toList());
	}

	public static DealerTableDto from(final Role dealer) {
		return new DealerTableDto(dealer.getName(), dealer.openHand());
	}

	public String getRoleName() {
		return roleName;
	}

	public List<String> getCards() {
		return cards;
	}
}
