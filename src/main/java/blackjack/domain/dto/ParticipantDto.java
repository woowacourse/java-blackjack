package blackjack.domain.dto;

import blackjack.domain.Participant;
import blackjack.domain.card.Cards;

public class ParticipantDto {

	private final String name;
	private final Cards cards;

	public ParticipantDto(String name, Cards cards) {
		this.name = name;
		this.cards = cards;
	}

	public static ParticipantDto from(Participant participant) {
		return new ParticipantDto(participant.getName(), participant.getCards());
	}

	public String getName() {
		return name;
	}

	public Cards getCards() {
		return cards;
	}
}
