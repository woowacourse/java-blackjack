package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public interface Participant {
    void receiveCard(Card card);

    List<Card> showInitCards();

    List<Card> showCards();

    boolean isReceiveCard();

    int sumTotalScore();

    String getName();
}
