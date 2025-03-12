package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public interface Participant {

    boolean tracksWinLossCount();

    boolean shouldRevealSingleCard();

    boolean doesHaveName();

    List<Card> getCards();

    List<Integer> getPossibleSums();

    void takeCard(Card newCard);

    int getOptimisticValue();
}
