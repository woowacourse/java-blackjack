package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public interface Shuffler {
    List<Card> shuffle(List<Card> cards);
}
