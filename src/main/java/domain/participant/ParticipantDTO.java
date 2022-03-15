package domain.participant;

import java.util.ArrayList;
import java.util.List;

import domain.card.CardDTO;

public class ParticipantDTO {
	private final NameDTO name;
	private final List<CardDTO> hand;

	public ParticipantDTO(NameDTO name, List<CardDTO> hand) {
		this.name = name;
		this.hand = hand;
	}

	public NameDTO getName() {
		return name;
	}

	public List<CardDTO> getHand() {
		return new ArrayList<>(hand);
	}
}
