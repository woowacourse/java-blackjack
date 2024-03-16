package domain.blackjack;

import domain.card.Card;
import domain.card.Deck;
import java.util.List;

abstract class Gamer {
    protected final BlackJackGameMachine blackJackGameMachine;

    Gamer(BlackJackGameMachine blackJackGameMachine) {
        this.blackJackGameMachine = blackJackGameMachine;
    }

    abstract DrawResult draw(Deck deck);

    public final int calculateSummationCardPointAsInt() {
        return blackJackGameMachine.calculateSummationCardPointAsInt();
    }

    final SummationCardPoint calculateSummationCardPoint() {
        return blackJackGameMachine.calculateSummationCardPoint();
    }

    final boolean isBust() {
        return blackJackGameMachine.isBust();
    }

    final boolean isBlackJack() {
        return blackJackGameMachine.isBlackJack();
    }

    public final List<Card> getRawHoldingCards() {
        return getRawHoldingCards(AllCardShowStrategy.INSTANCE);
    }

    public final List<Card> getRawHoldingCards(CardShowStrategy cardShowStrategy) {
        return blackJackGameMachine.getRawHoldingCards(cardShowStrategy);
    }
}
