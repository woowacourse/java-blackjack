package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.HoldingCards;

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
        if (name.length() == 0) {
            throw new IllegalArgumentException(ERROR_INVALID_NAME);
        }
    }

    public void receiveCard(Card card) {
        holdingCards.addCard(card);
    }

    public HoldingCards getHoldingCards() {
        return holdingCards;
    }

    public String getName() {
        return name;
    }
}
