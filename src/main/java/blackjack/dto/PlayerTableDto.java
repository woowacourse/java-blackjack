package blackjack.dto;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.Card;
import blackjack.domain.Role;

public class PlayerTableDto {

	private final String roleName;
	private final List<String> cards;

	private PlayerTableDto(final String roleName, final List<Card> cards) {
		this.roleName = roleName;
		this.cards = cards.stream()
				.map(Card::getInformation)
				.collect(Collectors.toList());
	}

	public static PlayerTableDto from(final Role role) {
		return new PlayerTableDto(role.getName(), role.openHand());
	}

	public String getRoleName() {
		return roleName;
	}

	public List<String> getCards() {
		return cards;
	}
}
