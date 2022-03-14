package blackjack.dto;

import blackjack.domain.participant.Participant;
import blackjack.domain.card.HoldingCard;

public class ParticipantDto {
    private final String name;
    private final HoldingCard cards;

    private ParticipantDto(Participant participant) {
        this.name = participant.getName();
        this.cards = participant.getHoldingCard();
    }

    public static ParticipantDto of(Participant participant) {
        return new ParticipantDto(participant);
    }

    public String getName() {
        return name;
    }

    public HoldingCard getCards() {
        return cards;
    }

    public int getSum() {
        return cards.computeTotalScore();
    }
}
