package domain.participant;

import domain.card.Card;
import java.util.List;

public interface Gambler {

    boolean canTakeMoreCard();

    void takeCards(Card... card);

    int calculateScore();

    String getName();

    List<Card> getCards();
}
