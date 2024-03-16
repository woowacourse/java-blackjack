package domain.blackjack;

import domain.card.Deck;
import domain.card.RandomCardSelectStrategy;

public class Dealer extends Gamer {

    static Dealer of(HoldingCards holdingCards) {
        return new Dealer(new BlackJackGameMachine(holdingCards));
    }

    Dealer(BlackJackGameMachine blackJackGameMachine) {
        super(blackJackGameMachine, 0);
    }

    @Override
    DrawResult draw(Deck deck) {
        return blackJackGameMachine.draw(deck, RandomCardSelectStrategy.INSTANCE,
                new DealerCardDrawCondition(blackJackGameMachine));
    }
}
