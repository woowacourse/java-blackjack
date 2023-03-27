package blackjack.dto;

import blackjack.domain.BetAmount;
import blackjack.domain.participant.Participant;

public class ParticipantGameResultDto {

    private final String name;
    private final int amount;

    private ParticipantGameResultDto(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public static ParticipantGameResultDto of(Participant participant, BetAmount betAmount) {
        return new ParticipantGameResultDto(participant.getName(), betAmount.getValue());
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
