package domain.participant;

import domain.card.CardDeck;
import java.util.ArrayList;

public class Player {
    private static final int BLACKJACK_NUMBER = 21;

    private final String name;
    private final CardDeck cardDeck;

    public Player(String name) {
        this.cardDeck = new CardDeck(new ArrayList<>());
        this.name = name;
    }

    public void hitCards(Dealer dealer) {
        for (int i = 0; i < 2; i++) {
            cardDeck.addCard(dealer.hitCard());
        }
    }

    public void addCard(Dealer dealer) {
        cardDeck.addCard(dealer.hitCard());
    }

    public int sum() {
        return cardDeck.sum();
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }

    public boolean isBust() {
        return sum() > BLACKJACK_NUMBER;
    }
}
