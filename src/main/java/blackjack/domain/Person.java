package blackjack.domain;

import java.util.List;

public interface Person {

    void addCard();

    List<Card> showCards();

    boolean isHit();

    int calculateScore();
}
