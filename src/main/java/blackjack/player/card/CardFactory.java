package blackjack.player.card;

import java.util.Stack;

public final class CardFactory {

    private final Stack<Card> blackjackCards;

    private CardFactory() {
        this.blackjackCards = new Stack<>();
        this.blackjackCards.addAll(Card.getCardCache());
    }

    public static CardFactory getInstance() {
        return new CardFactory();
    }

}
