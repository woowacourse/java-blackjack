package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public class Dealer {

    public static final int BOUND_FOR_ADDITIONAL_CARD = 16;

    private final String name;
    private final Cards cards;

    private Dealer(String name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public Dealer(Cards cards) {
        this("딜러", cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean doesNeedToDraw() {
        return cards.calculateScore() <= BOUND_FOR_ADDITIONAL_CARD;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.toList();
    }

    public int getScore() {
        return cards.calculateScore();
    }
}
