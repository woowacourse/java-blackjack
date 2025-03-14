package blackjack.domain.game;

import blackjack.domain.card.Card;
import java.util.List;

public interface Participant {
    boolean shouldRevealSingleCard();

    boolean doesHaveName();

    List<Card> getCards();

    void takeCard(Card newCard);

    int getOptimisticValue();

    boolean ableToTakeMoreCards();

    boolean canDecideToTakeMoreCard();

    boolean isOverLimit(int limit);

    boolean isChallenger();
}
