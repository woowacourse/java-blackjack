package blackjack.domain;

import blackjack.domain.gamer.Gamer;

public interface GameManager {

    int STARTING_CARDS_SIZE = 2;

    void drawStartingCards(Gamer gamer);

    void drawCard(Gamer gamer);
}
