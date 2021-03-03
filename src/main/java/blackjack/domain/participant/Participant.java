package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

interface Participant {
    void receiveCard(Card card);

    List<Card> showInitCards();

    List<Card> showCards();

    boolean isReceiveCard();
}
