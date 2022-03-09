package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public class Dealer {

    private static final int MAX_SCORE = 21;
    private static final int ADD_CARD_CONDITION = 16;

    private final Cards cards;

    public Dealer(List<Card> cards) {
        this.cards = new Cards(cards);
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public boolean acceptableCard() {
        return cards.calculateScoreByAceEleven() <= ADD_CARD_CONDITION;
    }

    public void addCard(Card card) {
        this.cards.addCard(card);
    }

    public int calculateScore() {
        final int scoreByAceOne = cards.calculateScoreByAceOne();
        final int scoreByAceEleven = cards.calculateScoreByAceEleven();

        if (scoreByAceEleven <= MAX_SCORE) {
            return scoreByAceEleven;
        }
        return scoreByAceOne;
    }
}
