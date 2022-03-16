package blackjack.domain;

import blackjack.domain.card.Card;

import java.util.List;

public interface DrawCallback {

    boolean canContinue(final String name);

    void onUpdate(final String playerName, final List<Card> cards);
}
