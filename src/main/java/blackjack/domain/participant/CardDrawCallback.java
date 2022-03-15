package blackjack.domain.participant;

import java.util.List;

import blackjack.domain.card.Card;

public interface CardDrawCallback {

    boolean isContinuable(final String participantName);

    void onUpdate(final String participantName, final List<Card> cards);

}
