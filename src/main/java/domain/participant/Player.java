package domain.participant;

import domain.card.CardDeck;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

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

    private boolean isBust() {
        return sum() > BLACKJACK_NUMBER;
    }

    public void draw(BooleanSupplier answer, Consumer<Player> playerDeck, Dealer dealer) {
        while (!isBust() && answer.getAsBoolean()) {
            addCard(dealer);
            playerDeck.accept(this);
        }
    }
}
