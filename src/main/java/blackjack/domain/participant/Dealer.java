package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.game.Score;

public class Dealer extends Participant {

    public static final String UNIQUE_NAME = "딜러";
    private static final int MAXIMUM_CARDS_QUANTITY = 3;
    private static final String INVALID_CARD_QUANTITY_EXCEPTION_MESSAGE = "딜러는 최대 3장의 카드만 지닐 수 있습니다.";
    private static final String EMPTY_CARD_BUNDLE_EXCEPTION_MESSAGE = "딜러는 카드를 최소 2장의 카드를 지니고 있어야 합니다.";

    private Dealer(final CardBundle cardBundle) {
        super(UNIQUE_NAME, cardBundle);
    }

    public static Dealer of(final CardBundle cardBundle) {
        return new Dealer(cardBundle);
    }

    public void receiveCard(Card card) {
        validateCardQuantity();
        cardBundle.add(card);
    }

    private void validateCardQuantity() {
        if (cardBundle.getCards().size() >= MAXIMUM_CARDS_QUANTITY) {
            throw new IllegalArgumentException(INVALID_CARD_QUANTITY_EXCEPTION_MESSAGE);
        }
    }

    public boolean canReceive() {
        Score score = cardBundle.getScore();
        return score.toInt() <= Score.DEALER_EXTRA_CARD_LIMIT;
    }

    public Card getOpenCard() {
        return cardBundle.getCards()
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(EMPTY_CARD_BUNDLE_EXCEPTION_MESSAGE));
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "name='" + name + '\'' +
                ", cardBundle=" + cardBundle +
                '}';
    }
}
