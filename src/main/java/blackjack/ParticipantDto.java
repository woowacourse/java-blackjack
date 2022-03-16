package blackjack;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;

public class ParticipantDto {

	private final String name;
	private final List<Card> cards;

	public ParticipantDto(String name, List<Card> cards) {
		this.name = name;
		this.cards = cards;
	}

	public static ParticipantDto from(Participant participant) {
		return new ParticipantDto(participant.getName(), participant.getCards());
	}

	public String getName() {
		return name;
	}

	public List<Card> getCards() {
		return cards;
	}
}
