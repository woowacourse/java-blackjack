package blackjack.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;

public class ParticipantDto {

    private final String name;
    private final List<Card> cards;
    private final int score;

    private ParticipantDto(String name, List<Card> cards, int score) {
        this.name = name;
        this.cards = new ArrayList<>(cards);
        this.score = score;
    }

    public static ParticipantDto from(Participant participant) {
        return new ParticipantDto(participant.getName(), participant.getCards(), participant.getScore());
    }

    public String getName() {
        return name;
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int getScore() {
        return score;
    }
}
