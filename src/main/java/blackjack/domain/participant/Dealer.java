package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.game.Score;

public class Dealer extends Participant {

    public static final String UNIQUE_NAME = "딜러";
    private static final String EMPTY_CARD_BUNDLE_EXCEPTION_MESSAGE = "딜러는 카드를 최소 2장의 카드를 지니고 있어야 합니다.";

    private Dealer(final CardBundle cardBundle) {
        super(UNIQUE_NAME, cardBundle);
    }

    public static Dealer of(final CardBundle cardBundle) {
        return new Dealer(cardBundle);
    }

    @Override
    public boolean canReceive() {
        Score score = cardBundle.getScore();
        return score.toInt() <= Score.DEALER_EXTRA_CARD_LIMIT;
    }

    public boolean isBlackjack() {
        Score score = cardBundle.getScore();
        return score.toInt() == Score.BLACKJACK;
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
