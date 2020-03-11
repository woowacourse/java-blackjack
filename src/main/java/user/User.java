package user;

import card.Deck;

public interface User {
    void hit(Deck deck);
    boolean checkBurst();
    int handSize();
}
