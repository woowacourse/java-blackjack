package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;

import java.util.List;

public class DealerCardsScoreResponse {

    private final List<Card> cards;
    private final int score;

    public DealerCardsScoreResponse(Dealer dealer) {
        this.cards = dealer.getCards().getCards();
        this.score = dealer.calculateScore();
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
