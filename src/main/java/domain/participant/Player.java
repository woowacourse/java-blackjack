package domain.participant;

import static domain.blackJack.MatchResult.BLACKJACK;
import static domain.blackJack.MatchResult.DRAW;
import static domain.blackJack.MatchResult.LOSE;
import static domain.blackJack.MatchResult.WIN;

import domain.blackJack.MatchResult;
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
        while (!hand.isBust() && answer.apply(this)) {
            hand.addCard(standard.hitCard());
            playerDeck.accept(this);
        }
    }

    @Override
    public Hand getFirstOpenHand() {
        return hand;
    }

    public int calculateProfit(Dealer dealer) {
        return money.calculateProfit(calculateResult(dealer));
    }

    public MatchResult calculateResult(Dealer dealer) {
        int playerSum = sum();
        int dealerSum = dealer.sum();

        if (isBlackjack()) {
            if (dealer.isBlackjack()) {
                return DRAW;
            }
            return BLACKJACK;
        }

        if ((!dealer.isBust() && dealerSum > playerSum) || isBust()) {
            return LOSE;
        }

        if (dealerSum < playerSum || dealer.isBust()) {
            return WIN;
        }
        return DRAW;
    }

    public String getName() {
        return name;
    }
}
