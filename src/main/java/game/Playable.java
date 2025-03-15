package game;

import card.Card;
import java.util.List;

public interface Playable {
    void receiveCard(Card card);

    boolean canReceiveCard();

    int score();

    boolean isBlackjack();

    boolean isBust();

    String getNickname();

    List<Card> getCards();
}
