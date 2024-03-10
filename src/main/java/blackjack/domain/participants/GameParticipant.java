package blackjack.domain.participants;

import blackjack.domain.deck.Card;
import blackjack.domain.deck.Hands;

public interface GameParticipant {
    void receiveHands(Hands hands);
    void receiveCard(Card card);
    int calculateScore();
    boolean canReceiveCard();
    Name getName();
    Hands getHands();
}
