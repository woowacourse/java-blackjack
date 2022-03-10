package blackjack.model.cards;

import blackjack.model.Card;
import blackjack.model.Score;

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
