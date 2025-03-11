package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.ScoreCalculator;
import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private final Cards cards;

    public Dealer(Card... cards) {
        this(new Cards(new ArrayList<>(List.of(cards)), new ScoreCalculator()));
    }

    public Dealer(Cards cards) {
        this.cards = cards;
    }

    public int calculateMaxScore() {
        return cards.calculateMaxScore();
    }

    public void drawAdditionalCard(Deck deck) {
        while (calculateMaxScore() <= 16) {
            cards.take(deck.draw());
        }
    }

    public void prepareCards(Deck deck) {
        cards.take(deck.draw(), deck.draw());
    }

    public Cards getCards() {
        return cards;
    }

    public int getCardSize() {
        return cards.getSize();
    }
}
