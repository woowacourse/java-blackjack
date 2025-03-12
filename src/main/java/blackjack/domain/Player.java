package blackjack.domain;

import blackjack.common.Constants;
import blackjack.common.ErrorMessage;
import java.util.List;

public class Player implements Participant {

    private final String name;
    private final Hand hand;

    public Player(String name, Hand hand) {
        validName(name);
        this.name = name;
        this.hand = hand;
    }

    public void validName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.USE_VALID_NAME.getMessage());
        }
    }

    @Override
    public List<Card> getAllCards() {
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
    public boolean canTakeCard() {
        return hand.canTakeCardWithin(Constants.BUSTED_STANDARD_VALUE);
    }

    public String getName() {
        return name;
    }

    public boolean isBusted() {
        return hand.isBusted();
    }
}
