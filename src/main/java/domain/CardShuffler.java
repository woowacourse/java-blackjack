package domain;

import java.util.List;

@FunctionalInterface
public interface CardShuffler {
    void shuffle(List<Card> cards);
}
