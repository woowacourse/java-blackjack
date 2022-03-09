package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public class Dealer implements Player {

    private static final int ADD_CARD_CONDITION = 16;

    private final Cards cards;

    public Dealer(final List<Card> cards) {
        this.cards = new Cards(cards);
    }

    public boolean acceptableCard() {
        return cards.calculateScoreByAceEleven() <= ADD_CARD_CONDITION;
    }

    @Override
    public void addCard(final Card card) {
        this.cards.addCard(card);
    }

    @Override
    public int calculateScore() {
        final int scoreByAceOne = cards.calculateScoreByAceOne();
        final int scoreByAceEleven = cards.calculateScoreByAceEleven();

        if (scoreByAceEleven <= MAX_SCORE) {
            return scoreByAceEleven;
        }
        return scoreByAceOne;
    }

    @Override
    public List<Card> getCards() {
        return cards.getCards();
    }
}
