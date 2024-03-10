package blackjack.domain.participants;

import blackjack.domain.Card;
import blackjack.domain.Hands;

public interface GameParticipant {
    void receiveHands(Hands hands);
    void receiveCard(Card card);
    int calculateScore();
    boolean canReceiveCard();
    Name getName();
    Hands getHands();
}
