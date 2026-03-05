package blackjack.model;

import java.util.List;

public interface PickStrategy {

    Card pick(List<Card> cards);
}
