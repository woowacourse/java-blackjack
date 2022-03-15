package blackjack.domain.dto;

import blackjack.domain.Card;
import blackjack.domain.Participant;

import java.util.List;

public class ParticipantDto {
    private final String name;
    private final List<Card> cards;
    private final int score;

    private ParticipantDto(Participant participant, List<Card> cards) {
        this.name = participant.getName();
        this.cards = cards;
        this.score = participant.getHoldingCard().calculateTotal();
    }

    public static ParticipantDto of(Participant participant, List<Card> cards) {
        return new ParticipantDto(participant, cards);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }

}
