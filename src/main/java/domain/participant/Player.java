package domain.participant;

import domain.MatchResult;
import domain.card.CardDeck;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class Player {
    private static final int BLACKJACK_NUMBER = 21;

    private final String name;
    private final CardDeck hand;

    public Player(String name) {
        this.hand = new CardDeck(new ArrayList<>());
        this.name = name;
    }

    public void hitCards(Dealer dealer) {
        for (int i = 0; i < 2; i++) {
            hand.addCard(dealer.hitCard());
        }
    }

    public void addCard(Dealer dealer) {
        hand.addCard(dealer.hitCard());
    }

    public int sum() {
        return hand.sum();
    }

    public CardDeck getHand() {
        return hand;
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

    public MatchResult calculateWinner(int dealerSum){
        return MatchResult.calculateWinner(dealerSum, this.sum());
    }
}
