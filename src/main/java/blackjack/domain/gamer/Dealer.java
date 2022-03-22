package blackjack.domain.gamer;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Gamer {
    private static final int ADDITIONAL_DISTRIBUTE_STANDARD = 16;
    private static final String NAME = "딜러";

    public Dealer(List<Card> cards) {
        super(NAME);
        validateInitCardSize(cards);
        for (Card card : cards) {
            addCard(card);
        }
    }

    private void validateInitCardSize(List<Card> cards) {
        if (cards.size() != INIT_DISTRIBUTION_COUNT) {
            throw new IllegalArgumentException("초기 카드는 2장만 Draw 해야 합니다.");
        }
    }

    @Override
    public boolean canDraw() {
        return getCardsNumberSum() <= ADDITIONAL_DISTRIBUTE_STANDARD;
    }
}
