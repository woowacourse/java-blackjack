package blackjack.domain.cardholder;

import blackjack.domain.card.Card;
import java.util.List;

public interface CardHolder {

    List<Card> getAllCards();

    void takeCard(Card newCard);

    List<Integer> calculatePossibleSums();

    int getOptimisticValue();
}
