package blackjack.domain;

import java.util.List;
import java.util.Set;

public interface CardStorage {
    void add(Card card);

    Set<Integer> calculatePossibleSums();

    List<Card> getCards();
}
