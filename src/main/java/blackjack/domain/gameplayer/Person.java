package blackjack.domain.gameplayer;

import blackjack.domain.card.Card;

import java.util.List;

public interface Person {

    void addCard(Card card);

    List<Card> showCards();

    boolean isHit();

    int calculateScore();
}
