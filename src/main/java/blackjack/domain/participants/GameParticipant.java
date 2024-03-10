package blackjack.domain.participants;

import blackjack.domain.Card;
import blackjack.domain.Hands;

public interface GameParticipant {
    void receiveHands(Hands hands);
    void hit(Card card);
    int calculateScore();
    boolean canHit();
    Name getName();
    Hands getHands();
}
