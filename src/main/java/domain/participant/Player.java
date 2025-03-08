package domain.participant;

import domain.MatchResult;
import domain.card.CardDeck;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

public class Player {
    private static final int BLACKJACK_NUMBER = 21;
    private final int INITIAL_HIT_COUNT = 2;

    private final String name;
    private final CardDeck hand;

    public Player(final String name) {
        this.hand = new CardDeck(new ArrayList<>());
        this.name = name;
    }

    public void hitCards(final CardDeck standard) {
        for (int i = 0; i < INITIAL_HIT_COUNT; i++) {
            hand.addCard(standard.hitCard());
        }
    }

    public void addCard(final CardDeck standard) {
        hand.addCard(standard.hitCard());
    }

    public int sum() {
        return hand.sum();
    }

    public void draw(final Function<Player, Boolean> answer, final Consumer<Player> playerDeck, final CardDeck standard) {
        while (!isBust() && answer.apply(this)) {
            addCard(standard);
            playerDeck.accept(this);
        }
    }

    public MatchResult calculateWinner(final int dealerSum){
        return MatchResult.calculateWinner(dealerSum, this.sum());
    }

    private boolean isBust() {
        return sum() > BLACKJACK_NUMBER;
    }

    public String getName() {
        return name;
    }

    public CardDeck getHand() {
        return hand;
    }

}
