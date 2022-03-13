package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public class ParticipantDto {

    private final String name;
    private final List<Card> cards;
    private int score;

    public ParticipantDto(String name, List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    private ParticipantDto(String name, List<Card> cards, int score) {
        this.name = name;
        this.cards = cards;
        this.score = score;
    }

    public static ParticipantDto of(String name, List<Card> cards) {
        return new ParticipantDto(name, cards);
    }

    public static ParticipantDto of(String name, List<Card> cards, int sum) {
        return new ParticipantDto(name, cards, sum);
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
