package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;

public interface Participant {
    boolean isAbleToTake();

    String getName();

    Cards getCards();

    int sizeOfCards();

    void takeCard(Card card);

    boolean isBlackjack();

    boolean isBurst();

    Score finalScore();

}
