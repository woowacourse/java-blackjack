package domain.blackjack;

import domain.card.Card;
import domain.card.CardSelectStrategy;
import domain.card.Deck;
import domain.card.RandomCardSelectStrategy;
import java.util.List;

abstract class Gamer {
    protected final BlackJackGameMachine blackJackGameMachine;

    Gamer(BlackJackGameMachine blackJackGameMachine) {
        this.blackJackGameMachine = blackJackGameMachine;
    }

    abstract DrawResult draw(Deck deck, CardSelectStrategy cardSelectStrategy);

    final DrawResult drawRandom(Deck deck) {
        return draw(deck, RandomCardSelectStrategy.INSTANCE);
    }

    public final List<Card> getRawHoldingCards() {
        return blackJackGameMachine.getRawHoldingCards();
    }

    public final int calculateSummationCardPointAsInt() {
        return blackJackGameMachine.calculateSummationCardPointAsInt();
    }

    final GameResult calculateGameResult(Gamer other) {
        return GameResultCalculator.calculate(this, other);
    }

    final SummationCardPoint calculateSummationCardPoint() {
        return blackJackGameMachine.calculateSummationCardPoint();
    }

    final boolean isBust() {
        return blackJackGameMachine.isBust();
    }
}
