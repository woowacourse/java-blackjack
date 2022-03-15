package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.result.Score;

import java.util.List;

public interface Participant {

    boolean isBust();

    boolean isBlackjack();

    boolean hasHigherScoreThan(AbstractParticipant abstractParticipant);

    boolean hasSameScoreWith(AbstractParticipant abstractParticipant);

    void drawCard(final Deck deck);

    String getName();

    Score calculateScore();

    List<Card> getCards();
}

