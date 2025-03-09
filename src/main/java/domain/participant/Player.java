package domain.participant;

import domain.MatchResult;
import domain.card.CardDeck;
import java.util.function.Consumer;
import java.util.function.Function;

public class Player extends Participant {
    private final String name;

    public Player(final String name) {
        super();
        this.name = name;
    }

    public void draw(final Function<Player, Boolean> answer, final Consumer<Player> playerDeck,
                     final CardDeck standard) {
        while (!isBust() && answer.apply(this)) {
            hand.addCard(standard.hitCard());
            playerDeck.accept(this);
        }
    }

    public MatchResult calculateWinner(final int dealerSum) {
        return MatchResult.calculateResultOfPlayer(dealerSum, this.sum());
    }

    private boolean isBust() {
        return MatchResult.isBust(sum());
    }

    public String getName() {
        return name;
    }

}
