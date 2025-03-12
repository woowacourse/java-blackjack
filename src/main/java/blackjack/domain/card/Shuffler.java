package blackjack.domain.card;

import java.util.List;

public interface Shuffler {
    List<Card> shuffle(List<Card> cards);
}
