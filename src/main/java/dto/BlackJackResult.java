package dto;

import domain.Participant;

public class BlackJackResult {

    private final String name;
    private final double account;

    private BlackJackResult(final String name, final double account) {
        this.name = name;
        this.account = account;
    }

    public static BlackJackResult toDto(final Participant participant) {
        return new BlackJackResult(participant.getName(), participant.getAccount());
    }

    public String getName() {
        return name;
    }

    public double getAccount() {
        return account;
    }
}
