package domain.game;

import domain.card.Card;
import java.util.List;

public interface TakeMoreCardSelector {

    boolean isYes(String name);

    void takenResult(String name, List<Card> cards);

    void dealerTakenResult();
}
