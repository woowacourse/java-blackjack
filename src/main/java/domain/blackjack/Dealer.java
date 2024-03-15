package domain.blackjack;

import domain.card.CardSelectStrategy;
import domain.card.Deck;

public class Dealer extends Gamer {

    static Dealer of(HoldingCards holdingCards) {
        return new Dealer(new BlackJackGameMachine(holdingCards));
    }

    Dealer(BlackJackGameMachine blackJackGameMachine) {
        super(blackJackGameMachine, 0);
    }

    @Override
    DrawResult draw(Deck deck, CardSelectStrategy cardSelectStrategy) {
        return blackJackGameMachine.draw(deck, cardSelectStrategy, new DealerCardDrawCondition(blackJackGameMachine));
    }
}
