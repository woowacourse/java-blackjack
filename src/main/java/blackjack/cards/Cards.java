package blackjack.cards;

import blackjack.Card;
import blackjack.Score;
import java.util.stream.Stream;

public interface Cards {

    Score score();

    Stream<Card> stream();

    void take(Card card);

    static ChangeableCards mixHandCards(Card card1, Card card2, Card... cards) {
        return new MixHandCards(new HardHandCards(card1, card2, cards));
    }

    static ChangeableCards softHandCards(Card card1, Card card2, Card... cards) {
        return new SoftHandCards(new HardHandCards(card1, card2, cards));
    }
}
