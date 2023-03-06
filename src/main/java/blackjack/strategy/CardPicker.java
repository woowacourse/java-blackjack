package blackjack.strategy;

import blackjack.domain.card.Card;
import java.util.List;

public interface CardPicker {

    Card pick(List<Card> cards);
}
