package blackjack.dto;

import java.util.Set;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;

public class ParticipantDto {

    private final Participant participant;

    public ParticipantDto(Participant participant) {
        this.participant = participant;
    }

    public String getName() {
        return participant.getName();
    }

    public Set<Card> getCards() {
        return participant.getCards();
    }

    public int getScore() {
        return participant.getScore();
    }
}
