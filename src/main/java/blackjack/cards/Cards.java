package blackjack.cards;

import blackjack.Card;
import blackjack.Score;
import java.util.stream.Stream;

public interface Cards {

    Score score();

    Stream<Card> stream();

    void take(Card card);

    static ChangeableCards mixHandCards(Card... cards) {
        return new MixHandCards(new HardHandCards(cards));
    }

    static ChangeableCards softHandCards(Card... cards) {
        return new SoftHandCards(new HardHandCards(cards));
    }
}
