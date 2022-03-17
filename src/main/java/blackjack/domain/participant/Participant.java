package blackjack.domain.participant;

import blackjack.domain.DrawCallback;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.result.Score;

import java.util.List;

public interface Participant {

    boolean isBust();

    boolean isBlackjack();

    boolean hasHigherScoreThan(Participant participant);

    boolean hasSameScoreWith(Participant participant);

    void drawCard(final Deck deck);

    void hitOrStand(final Deck deck, final DrawCallback callback);

    String getName();

    Score calculateScore();

    List<Card> getCards();
}

