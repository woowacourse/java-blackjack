package blakjack.domain.state;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.card.Card;

public class Init extends State {
    private static final int CARD_COUNT_THRESHOLD = 1;

    public Init(final PrivateArea privateArea, final Chip chip) {
        super(privateArea, chip);
    }

    @Override
        //순서 의존적임
    State draw(final Card card) {
        privateArea.addCard(card);
        if (privateArea.isBlackjack()) {
            return new Blackjack(privateArea, chip);
        }
        if (privateArea.getCardsSize() > CARD_COUNT_THRESHOLD) {
            return new Hit(privateArea, chip);
        }
        return new Init(privateArea, chip);
    }
}
