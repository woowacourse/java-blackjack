package blackjack.dto;

import java.util.Set;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Name;

public class ParticipantDto {

    private final Name name;
    private final Set<Card> cards;
    private final int score;

    public ParticipantDto(Name name, Set<Card> cards, int score) {
        this.name = name;
        this.cards = cards;
        this.score = score;
    }

    public Name getName() {
        return name;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
