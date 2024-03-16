package domain.blackjack;

import domain.card.Deck;
import domain.card.RandomCardSelectStrategy;

public class Dealer extends Gamer {

    Dealer(BlackJackGameMachine blackJackGameMachine) {
        super(blackJackGameMachine);
    }

    static Dealer of(HoldingCards holdingCards) {
        return new Dealer(new BlackJackGameMachine(holdingCards));
    }

    @Override
    public DrawResult draw(Deck deck) {
        return blackJackGameMachine.draw(deck, RandomCardSelectStrategy.INSTANCE,
                new DealerCardDrawCondition(blackJackGameMachine));
    }
}
