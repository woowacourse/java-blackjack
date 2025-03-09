package domain.participant;

import domain.card.Card;
import java.util.List;

public interface Gambler {

    void takeCards(Card... card);

    int calculateScore();

    List<Card> getCards();
}
