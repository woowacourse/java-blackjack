package blackjack.domain.gametable;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Participant;
import java.util.List;

public interface Playable {
    void giveCard(Participant participant);
}
