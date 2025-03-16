package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.result.BetAmount;
import java.util.List;

public class Player implements Participant {

    private static final int STARTING_CARD_SIZE = 2;
    private static final int CARD_DRAW_THRESHOLD = 21;

    private final String name;
    private final Hand hand;
    private final BetAmount betAmount;

    public Player(String name, Hand hand, BetAmount betAmount) {
        this.name = name;
        this.hand = hand;
        this.betAmount = betAmount;
    }

    @Override
    public List<Card> getStartingCards() {
        List<Card> cards = hand.getAllCards();
        return cards.subList(0, STARTING_CARD_SIZE);
    }

    @Override
    public List<Card> getCards() {
        return hand.getAllCards();
    }

    @Override
    public void takeCard(Card newCard) {
        hand.takeCard(newCard);
    }

    @Override
    public int getOptimisticValue() {
        return hand.getOptimisticValue();
    }

    @Override
    public boolean ableToTakeMoreCards() {
        return hand.calculatePossibleSums().stream()
                .allMatch(sum -> sum <= CARD_DRAW_THRESHOLD);
    }

    @Override
    public boolean canDecideToTakeMoreCard() {
        return true;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getBetAmount() {
        return betAmount.getAmount();
    }
}
