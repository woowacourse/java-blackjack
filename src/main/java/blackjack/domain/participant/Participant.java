package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldingCards;

import java.util.List;

public abstract class Participant {

    private static final String ERROR_INVALID_NAME = "[ERROR] 유저의 이름은 한 글자 이상이어야 합니다.";

    protected final String name;
    protected final HoldingCards holdingCards;

    public Participant(String name) {
        validateName(name);
        this.name = name;
        this.holdingCards = new HoldingCards();
    }

    private void validateName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(ERROR_INVALID_NAME);
        }
    }

    public boolean isBust() {
        return holdingCards.isBust();
    }

    public void receiveCard(Deck deck) {
        holdingCards.addCard(deck.drawCard());
    }

    public int getScore() {
        return holdingCards.cardSum();
    }

    public List<Card> getHoldingCards() {
        return holdingCards.getAllCards();
    }

    public String getName() {
        return name;
    }
}
