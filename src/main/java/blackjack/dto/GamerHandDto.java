package blackjack.dto;

import java.util.List;

import blackjack.domain.gamer.BlackjackGamer;

public record GamerHandDto(String name, List<CardDto> gamerHand) {

	public static GamerHandDto fromGamer(BlackjackGamer gamer) {
		String gamerName = gamer.getName().value();
		List<CardDto> cards = gamer.getHand().cards().stream()
			.map(CardDto::fromCard)
			.toList();

		return new GamerHandDto(gamerName, cards);
	}
}
