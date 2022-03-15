package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.AbstractParticipant;

import java.util.ArrayList;
import java.util.List;

public class ParticipantDto {

    private final String name;
    private final List<Card> cards;
    private final int score;

    private ParticipantDto(final String name, final List<Card> cards, final int score) {
        this.name = name;
        this.cards = new ArrayList<>(cards);
        this.score = score;
    }

    public static ParticipantDto toShowFirstCards(final AbstractParticipant abstractParticipant) {
        return new ParticipantDto(
                abstractParticipant.getName(),
                abstractParticipant.showFirstCards(),
                abstractParticipant.calculateScore().getValue());
    }

    public static ParticipantDto toOpenAllCards(final AbstractParticipant abstractParticipant) {
        return new ParticipantDto(
                abstractParticipant.getName(),
                abstractParticipant.getCards(),
                abstractParticipant.calculateScore().getValue());
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
