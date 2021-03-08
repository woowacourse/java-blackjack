package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public interface Playable {
    Card pop();

    boolean isEmpty();

    List<Card> initCards();
}
