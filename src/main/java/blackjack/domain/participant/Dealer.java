package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.game.Score;

public class Dealer extends Participant {

    private static final int MAXIMUM_CARDS_QUANTITY = 3;
    private static final int MAXIMUM_SCORE = 16;
    private static final String DEALER_NAME = "딜러";
    private static final String INVALID_CARD_QUANTITY_EXCEPTION_MESSAGE = "딜러는 최대 3개의 카드만 지닐 수 있습니다.";

    private Dealer(final String name, final CardBundle cardBundle) {
        super(name, cardBundle);
    }

    public static Dealer of(final CardBundle cardBundle) {
        return new Dealer(DEALER_NAME, cardBundle);
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
        return score.toInt() <= MAXIMUM_SCORE;
    }

    // TODO: handle NPE
    public Card getOpenCard() {
        return cardBundle.getCards()
                .stream()
                .findFirst()
                .get();
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "name='" + name + '\'' +
                ", cardBundle=" + cardBundle +
                '}';
    }
}
