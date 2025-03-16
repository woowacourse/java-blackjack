package domain.participant;

import static domain.blackJack.MatchResult.BLACKJACK;
import static domain.blackJack.MatchResult.DRAW;
import static domain.blackJack.MatchResult.LOSE;
import static domain.blackJack.MatchResult.WIN;

import domain.blackJack.MatchResult;
import domain.blackJack.PlayerAnswer;
import domain.blackJack.ShowDeck;
import domain.card.CardDeck;
import domain.card.Hand;

public class Player extends Participant {
    private final String name;
    private final Money money;

    public Player(final String name, final Money money) {
        super();
        this.name = name;
        this.money = money;
    }

    public void draw(final PlayerAnswer answer, final ShowDeck playerDeck,
                     final CardDeck standard) {
        while (!hand.isBust() && answer.apply(this)) {
            hand.addCard(standard.hitCard());
            playerDeck.show(this);
        }
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

    @Override
    public Hand getFirstOpenHand() {
        return hand;
    }

    public String getName() {
        return name;
    }
}
