package domain.participant;

import domain.card.Hand;

public class ParticipantInfo {
	private final Name name;
	private final Hand hand;

	public ParticipantInfo(Participant participant) {
		this.name = participant.getName();
		this.hand = participant.getHand();
	}

	public Name getName() {
		return Name.copyOf(name);
	}

	public Hand getHand() {
		return Hand.copyOf(hand);
	}
}
