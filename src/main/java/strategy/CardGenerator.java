package strategy;

import domain.card.Card;
import java.util.Queue;

@FunctionalInterface
public interface CardGenerator {

    Queue<Card> generate();
}
