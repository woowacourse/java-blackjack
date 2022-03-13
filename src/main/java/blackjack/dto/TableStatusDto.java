package blackjack.dto;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.role.Role;

public class TableStatusDto {

	private final String roleName;
	private final List<String> cards;

	private TableStatusDto(final String roleName, final Hand hand) {
		this.roleName = roleName;
		cards = hand.getCards().stream()
			.map(Card::getInformation)
			.collect(Collectors.toList());
	}

	public static TableStatusDto from(final Role role) {
		return new TableStatusDto(role.getName(), role.getHand());
	}

	public String getRoleName() {
		return roleName;
	}

	public List<String> getCards() {
		return cards;
	}
}
