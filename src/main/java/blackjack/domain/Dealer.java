package blackjack.domain;

import java.util.List;

public class Dealer {

    private static final int DEALER_LIMIT_SCORE = 17;

    private final List<Card> cards;

    public Dealer(final List<Card> cards) {
        this.cards = cards;
    }

    public void draw(final Card card) {
        validateLimitScore();
        cards.add(card);
    }

    private void validateLimitScore() {
        if (score() >= DEALER_LIMIT_SCORE) {
            throw new IllegalStateException("[Error] 딜러 카드가 이미 17이상입니다.");
        }
    }

    private int score() {
        return cards.stream()
                .map(Card::getNumber)
                .mapToInt(CardNumber::getDefaultValue)
                .sum();
    }

    public boolean isEnd() {
        return score() >= DEALER_LIMIT_SCORE;
    }
}
