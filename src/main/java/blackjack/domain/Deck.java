package blackjack.domain;

import static blackjack.domain.ExceptionMessage.INVALID_TAKEOUT_CARDS_EMPTY;

import blackjack.domain.card.Card;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Player;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private final Queue<Card> cards;

    public Deck(final List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public List<Card> draw(final int count) {
        final List<Card> drawnCards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            drawnCards.add(takeOutOneCard());
        }
        return drawnCards;
    }

    public void handCardsTo(final Participant participant, final int count) {
        for (final Card card : draw(count)) {
            participant.take(card);
        }
    }

    public void handCardsTo(final List<Player> players, final int eachCount) {
        players.forEach(player -> handCardsTo(player, eachCount));
    }

    private Card takeOutOneCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(INVALID_TAKEOUT_CARDS_EMPTY);
        }
        return cards.poll();
    }
}
