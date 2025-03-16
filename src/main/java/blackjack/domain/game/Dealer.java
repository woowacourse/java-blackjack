package blackjack.domain.game;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer implements Participant {

    private final Hand hand;

    public Dealer(Hand hand) {
        this.hand = hand;
    }

    @Override
    public List<Card> getStartingCards() {
        return List.of(hand.getAllCards().getFirst());
    }

    @Override
    public String getName() {
        throw new IllegalArgumentException("딜러의 이름은 존재하지 않습니다.");
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
                .allMatch(sum -> sum <= 16);
    }

    @Override
    public boolean canDecideToTakeMoreCard() {
        return false;
    }
}
