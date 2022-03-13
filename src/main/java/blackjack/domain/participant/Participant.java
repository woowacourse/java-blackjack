package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.HoldingCard;

public abstract class Participant {

    private static final String ERROR_INVALID_NAME = "[ERROR] 유저의 이름은 한 글자 이상이어야 합니다.";

    protected final String name;
    protected final HoldingCard holdingCard;

    public Participant(String name) {
        validateName(name);
        this.name = name;
        this.holdingCard = new HoldingCard();
    }

    private void validateName(String name) {
        if (name.length() == 0) {
            throw new IllegalArgumentException(ERROR_INVALID_NAME);
        }
    }

    public void receiveCard(Card card) {
        holdingCard.addCard(card);
    }

    public int getCardSum() {
        return holdingCard.cardSum();
    }

    public HoldingCard getHoldingCard() {
        return holdingCard;
    }

    public String getName() {
        return name;
    }
}
