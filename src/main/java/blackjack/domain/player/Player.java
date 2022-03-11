package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class Player {

    protected static final int MAX_SCORE = 21;

    private final Cards cards;
    private final String name;

    Player(final List<Card> cards, final String name) {
        this.cards = new Cards(cards);
        this.name = name;
    }

    public int calculateFinalScore() {
        final int score = getScoreByAceEleven();
        if (score <= MAX_SCORE) {
            return score;
        }
        return getScoreByAceOne();
    }

    protected int getScoreByAceEleven() {
        return cards.calculateScoreByAceEleven();
    }

    protected int getScoreByAceOne() {
        return cards.calculateScoreByAceOne();
    }

    public void addCard(final Card card) {
        cards.addCard(card);
    }

    public List<Card> getCards() {
        return this.cards.getCards();
    }

    public String getName() {
        return this.name;
    }
}
