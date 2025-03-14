package domain.participant;

import domain.blackJack.MatchResult;
import domain.blackJack.Result;
import domain.card.CardDeck;
import domain.card.Hand;
import java.util.function.Consumer;
import java.util.function.Function;

public class Player extends Participant {
    private final String name;
    private final Money money;

    public Player(final String name, final Money money) {
        super();
        this.name = name;
        this.money = money;
    }

    public void draw(final Function<Player, Boolean> answer, final Consumer<Player> playerDeck,
                     final CardDeck standard) {
        while (!isBust() && answer.apply(this)) {
            hand.addCard(standard.hitCard());
            playerDeck.accept(this);
        }
    }

    @Override
    public Hand getFirstOpenHand() {
        return hand;
    }

    public int calculateProfit(MatchResult matchResult) {
        return money.calculateProfit(matchResult);
    }

    private boolean isBust() {
        return Result.isBust(sum());
    }

    public String getName() {
        return name;
    }
}
