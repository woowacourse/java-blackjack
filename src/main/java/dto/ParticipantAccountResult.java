package dto;

import domain.Participant;

public class ParticipantAccountResult {

    private final String name;
    private final double account;

    private ParticipantAccountResult(final String name, final double account) {
        this.name = name;
        this.account = account;
    }

    public static ParticipantAccountResult toDto(final Participant participant) {
        return new ParticipantAccountResult(participant.getName(), participant.getAccount());
    }

    public String getName() {
        return name;
    }

    public double getAccount() {
        return account;
    }
}
