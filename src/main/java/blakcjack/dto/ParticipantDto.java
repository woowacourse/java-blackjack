package blakcjack.dto;

import blakcjack.domain.card.Card;
import blakcjack.domain.participant.Participant;

import java.util.List;

public class ParticipantDto {
    private final String name;
    private final String type;
    private final int score;
    private final List<Card> cards;


    public ParticipantDto(final String name, final String type, final int score, final List<Card> cards) {
        this.name = name;
        this.type = type;
        this.score = score;
        this.cards = cards;
    }

    public static ParticipantDto from(final Participant participant) {
        return new ParticipantDto(participant.getNameValue(),
                participant.getType().getDescription(),
                participant.showScore(),
                participant.showCardList());
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
