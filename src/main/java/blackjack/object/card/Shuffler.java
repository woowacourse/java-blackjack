package blackjack.object.card;

import java.util.List;

public interface Shuffler {
    List<Card> shuffle(List<Card> cards);
}
