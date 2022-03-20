package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.role.Role;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerTableDto {

	private final String roleName;
	private final List<String> cards;

	private PlayerTableDto(final String roleName, final List<Card> cards) {
		this.roleName = roleName;
		this.cards = cards.stream()
				.map(Card::getDenominationAndSuit)
				.collect(Collectors.toList());
	}

	public static PlayerTableDto from(final Role role) {
		return new PlayerTableDto(role.getName(), role.openCards());
	}

	public String getRoleName() {
		return roleName;
	}

	public List<String> getCards() {
		return cards;
	}
}
