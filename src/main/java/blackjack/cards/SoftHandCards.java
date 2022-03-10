package blackjack.cards;

import blackjack.Card;
import blackjack.Score;

final class SoftHandCards extends ChangeableCards {

    SoftHandCards(HardHandCards cards) {
        super(cards);
    }

    @Override
    public Score score() {
        int score = stream()
                .mapToInt(Card::softRank)
                .sum();
        return new Score(score);
    }
}
