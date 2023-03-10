package blackjack.domain.result;

import blackjack.domain.card.CardGroup;

public class CardResult {

    private final CardGroup cards;
    private final Score score;

    public CardResult(final CardGroup cards, final Score score) {
        this.cards = cards;
        this.score = score;
    }

    public CardGroup getCards() {
        return cards;
    }

    public Score getScore() {
        return score;
    }
}
