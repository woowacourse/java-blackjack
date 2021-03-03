package blackjack.domain;

import java.util.List;

interface Participant {
    void receiveCard(Card card);

    List<Card> showCards();
}
