package blackjack.dto;

import java.util.Set;

import blackjack.domain.card.Card;

public class ParticipantDto {

    private final String name;
    private final Set<Card> cards;
    private final int score;

    public ParticipantDto(String name, Set<Card> cards, int score) {
        this.name = name;
        this.cards = cards;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
