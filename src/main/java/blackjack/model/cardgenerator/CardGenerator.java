package blackjack.model.cardgenerator;

import blackjack.vo.Card;

@FunctionalInterface
public interface CardGenerator {
    Card pick();
}
