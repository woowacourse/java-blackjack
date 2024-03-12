package domain.blackjack;

import domain.card.Card;
import domain.card.CardSelectStrategy;
import domain.card.Deck;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Gamer {

    public static Dealer of(HoldingCards holdingCards) {
        return new Dealer(new BlackJackGameMachine(holdingCards));
    }

    Dealer(BlackJackGameMachine blackJackGameMachine) {
        super(blackJackGameMachine);
    }

    @Override
    public DrawResult draw(Deck deck, CardSelectStrategy cardSelectStrategy) {
        return blackJackGameMachine.draw(deck, cardSelectStrategy, new DealerCardDrawCondition(blackJackGameMachine));
    }

    public List<Card> getRawHoldingCardsWithoutFirstCard() {
        List<Card> rawHoldingCards = new ArrayList<>(blackJackGameMachine.getRawHoldingCards());
        rawHoldingCards.remove(0);
        return List.copyOf(rawHoldingCards);
    }
}
