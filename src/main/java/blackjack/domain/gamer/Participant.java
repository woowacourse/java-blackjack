package blackjack.domain.gamer;

import blackjack.domain.card.Score;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Card;
import java.util.List;

public interface Participant {
    boolean isNotAbleToTake();

    String getName();

    Score sumCards();

    Score sumCardsForResult();

    Cards getCards();

    List<Card> getUnmodifiableCards();

    void takeCard(Card card);

}
