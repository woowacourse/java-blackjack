package blackjack.model;

import java.util.List;

public interface CardShuffleStrategy {
    List<Card> shuffle(List<Card> cards);
}
