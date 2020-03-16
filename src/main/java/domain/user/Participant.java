package domain.user;

import domain.card.Card;
import java.util.List;

public interface Participant {
    List<Card> getCards();
}
