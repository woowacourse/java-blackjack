package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.game.Score;

public class Dealer {

    private static final int MAXIMUM_CARDS_QUANTITY = 3;
    private static final int MAXIMUM_SCORE = 16;
    private static final String INVALID_CARD_QUANTITY_EXCEPTION_MESSAGE = "딜러는 최대 3개의 카드만 지닐 수 있습니다.";

    private final String name = "딜러";
    private final CardBundle cardBundle;

    public Dealer(final CardBundle cardBundle) {
        this.cardBundle = cardBundle;
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

    public String getName() {
        return name;
    }

    public CardBundle getCardBundle() {
        return cardBundle;
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "name='" + name + '\'' +
                ", cardBundle=" + cardBundle +
                '}';
    }
}
