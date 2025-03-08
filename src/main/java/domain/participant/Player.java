package domain.participant;

import domain.MatchResult;
import domain.card.CardDeck;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

public class Player {
    private final String name;
    private final CardDeck hand;

    public Player(final String name) {
        this.hand = new CardDeck(new ArrayList<>());
        this.name = name;
    }

    public void hitCards(final CardDeck standard) {
        hand.hitCards(standard);
    }

    public void draw(final Function<Player, Boolean> answer, final Consumer<Player> playerDeck,
                     final CardDeck standard) {
        while (!isBust() && answer.apply(this)) {
            hand.addCard(standard.hitCard());
            playerDeck.accept(this);
        }
    }

    public int sum() {
        return hand.sumWithAce();
    }

    public MatchResult calculateWinner(final int dealerSum) {
        return MatchResult.calculateWinner(dealerSum, this.sum());
    }

    private boolean isBust() {
        return MatchResult.isBust(sum());
    }

    public String getName() {
        return name;
    }

    public CardDeck getHand() {
        return hand;
    }

}
