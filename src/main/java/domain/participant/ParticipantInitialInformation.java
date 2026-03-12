package domain.participant;

import domain.betiing.BetAmount;

public class ParticipantInitialInformation {

    private final ParticipantName participantName;
    private final BetAmount betAmount;

    private ParticipantInitialInformation(ParticipantName participantName, BetAmount betAmount) {
        this.participantName = participantName;
        this.betAmount = betAmount;
    }

    public static ParticipantInitialInformation of(ParticipantName participantName, BetAmount betAmount) {
        return new ParticipantInitialInformation(participantName, betAmount);
    }

    public ParticipantName name() {
        return participantName;
    }

    public BetAmount betAmount() {
        return betAmount;
    }


}
