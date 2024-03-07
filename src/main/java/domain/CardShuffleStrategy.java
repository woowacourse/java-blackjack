package domain;

import java.util.List;

@FunctionalInterface
public interface CardShuffleStrategy {
    void shuffle(List<Card> cards);
}
