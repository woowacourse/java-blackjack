package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Participant;
import java.util.List;

public interface Playable {
    void giveCard(Participant participant);

    Card pop();

    boolean isEmpty();

    List<Card> initCards();
}
