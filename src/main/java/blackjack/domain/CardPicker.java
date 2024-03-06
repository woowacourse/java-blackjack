package blackjack.domain;

import java.util.List;

@FunctionalInterface
public interface CardPicker {

    Card pick(List<Card> cards);
}
