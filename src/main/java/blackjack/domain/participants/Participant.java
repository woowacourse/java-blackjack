package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public interface Participant {
    void draw(Card card);

    int score();

    void drawMoreCard(Deck deck);

    boolean isDealer();

    void set(Result result);

    String handStatus();

    String getName();

    int countHand();
}
