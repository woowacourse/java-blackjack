package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.game.Score;

public class Dealer extends Participant {

    private static final int MAXIMUM_CARDS_QUANTITY = 3;
    private static final String DEALER_NAME = "딜러";
    private static final String INVALID_CARD_QUANTITY_EXCEPTION_MESSAGE = "딜러는 최대 3개의 카드만 지닐 수 있습니다.";

    private Dealer(final String name, final Hand hand) {
        super(name, hand);
    }

    public static Dealer of(final Hand hand) {
        return new Dealer(DEALER_NAME, hand);
    }

    public void receiveCard(Card card) {
        validateCardQuantity();
        hand.add(card);
    }

    private void validateCardQuantity() {
        if (hand.getCards().size() >= MAXIMUM_CARDS_QUANTITY) {
            throw new IllegalArgumentException(INVALID_CARD_QUANTITY_EXCEPTION_MESSAGE);
        }
    }

    public boolean canReceive() {
        Score score = hand.getScore();
        return score.toInt() <= Score.DEALER_EXTRA_CARD_LIMIT;
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "name='" + name + '\'' +
                ", hand=" + hand +
                '}';
    }
}
