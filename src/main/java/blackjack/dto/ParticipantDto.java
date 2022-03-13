package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;

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

    public static ParticipantDto toShowFirstCards(final Participant participant) {
        return new ParticipantDto(participant.getName(), participant.showFirstCards(), participant.getScore());
    }

    public static ParticipantDto toOpenAllCards(final Participant participant) {
        return new ParticipantDto(participant.getName(), participant.getCards(), participant.getScore());
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
