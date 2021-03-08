package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Nickname;

import java.util.List;

public class ParticipantDto {
    private final Nickname name;
    private final Cards cards;
    private final int finalScore;

    public ParticipantDto(Nickname name, Cards cards, int finalScore) {
        this.name = name;
        this.cards = cards;
        this.finalScore = finalScore;
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public int getFinalScore() {
        return finalScore;
    }
}
