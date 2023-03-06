package blackjack.strategy;

import blackjack.domain.Card;

import java.util.List;

public interface CardPicker {

    Card pick(List<Card> cards);
}
