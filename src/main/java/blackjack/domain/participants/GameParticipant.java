package blackjack.domain.participants;

import blackjack.domain.card.Card;

public interface GameParticipant {
    void receiveHands(Hands newHands);
    void hit(Card card);
    int calculateScore();
    boolean canHit();
    boolean isBurst();
    Name getName();
    Hands getHands();
    int getHandsSize();
}
