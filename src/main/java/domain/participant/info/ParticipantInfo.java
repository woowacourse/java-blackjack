package domain.participant.info;

import domain.participant.Participant;

public class ParticipantInfo {
	private final Name name;
	private final Hand hand;
	private final Betting betting;

	public ParticipantInfo(Participant participant) {
		this.name = participant.getName();
		this.hand = participant.getHand();
		this.betting = participant.getBetting();
	}

	public Name getName() {
		return name;
	}

	public Hand getHand() {
		return Hand.copyOf(hand);
	}

	public Betting getBetting() {
		return betting;
	}

}
