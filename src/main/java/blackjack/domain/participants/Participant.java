package blackjack.domain.participants;

import blackjack.domain.card.Deck;

public interface Participant {
    void draw(Deck deck);

    int score();

    void drawMoreCard(Deck deck);

    boolean isDealer();

    String cards();

    String getName();
}
