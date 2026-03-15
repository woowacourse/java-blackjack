package model;

import java.util.List;

public interface Participant {

    int calculateTotalScore();

    void addCard(Card card);

    void checkBlackJack();

    boolean canHit();

    String name();

    List<Card> cards();
}
