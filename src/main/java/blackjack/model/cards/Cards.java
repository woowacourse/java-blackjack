package blackjack.model.cards;

import blackjack.model.Card;
import blackjack.model.Score;
import java.util.stream.Stream;

public interface Cards {

    Score bestScore();

    Score maxScore();

    void take(Card card);

    Stream<Card> stream();

}
