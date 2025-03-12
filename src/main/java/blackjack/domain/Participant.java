package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public interface Participant {

    boolean tracksWinLossCount();

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
