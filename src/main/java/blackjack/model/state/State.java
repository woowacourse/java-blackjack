package blackjack.model.state;

import blackjack.model.cards.Card;
import blackjack.model.cards.Cards;
import blackjack.model.results.Result;
import java.util.List;

public interface State {
    State draw(Card card);

    State drawCards(List<Card> cardsToAdd);

    State stand();

    Result determineResult(State otherState);

    Cards cards();

    int getScore();

    default boolean isBlackJack() {
        return this instanceof BlackJack;
    }

    default boolean isBust() {
        return this instanceof Bust;
    }

    default boolean isHit() {
        return this instanceof Hit;
    }
}
