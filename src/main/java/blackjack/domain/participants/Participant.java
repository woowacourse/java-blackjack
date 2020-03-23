package blackjack.domain.participants;

import blackjack.domain.card.Deck;
import blackjack.domain.participants.money.Money;

public interface Participant {
    void draw(Deck deck);

    int score();

    void drawMoreCard(Deck deck);

    boolean isDealer();

    int cardCount();

    String cards();

    String getName();

    void bet(String amount);

    void earn(Money money);

    void lose(Money money);

    void loseAll();

    Money getMoney();
}
