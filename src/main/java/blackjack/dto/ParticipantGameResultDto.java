package blackjack.dto;

import blackjack.domain.participant.Participant;

public class ParticipantGameResultDto {

    private final String name;
    private final int amount;

    private ParticipantGameResultDto(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public static ParticipantGameResultDto of(Participant participant) {
        return new ParticipantGameResultDto(participant.getName(), participant.getBetAmount());
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
