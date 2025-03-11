package domain.participant;

import domain.blackJack.MatchResult;
import domain.blackJack.Result;
import domain.card.CardDeck;
import java.util.function.Consumer;
import java.util.function.Function;

public class Player extends Participant {
    private final String name;
    private final Result result;
    private final Money money;

    public Player(final String name, final Money money) {
        super();
        this.name = name;
        this.result = new Result();
        this.money = money;
    }

    public void draw(final Function<Player, Boolean> answer, final Consumer<Player> playerDeck,
                     final CardDeck standard) {
        while (!isBust() && answer.apply(this)) {
            hand.addCard(standard.hitCard());
            playerDeck.accept(this);
        }
    }

    public MatchResult calculateWinner(final int dealerSum) {
        return result.calculateResultOfPlayer(dealerSum, this.sum());
    }

    private boolean isBust() {
        return result.isBust(sum());
    }

    public String getName() {
        return name;
    }
}
