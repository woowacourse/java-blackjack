package domain;

import java.util.List;

@FunctionalInterface
public interface CardShuffleStrategy {

    List<Card> shuffle(List<Card> cards);
}
