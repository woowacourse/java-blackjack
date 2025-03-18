package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.participant.Participants;
import blackjack.domain.result.ProfitResult;

public final class BlackjackGame {

    private final Deck deck;
    private final Participants participants;

    public BlackjackGame(final Deck deck, final Participants participants) {
        this.deck = deck;
        this.participants = participants;
    }

    public void dealInitialCards() {
        final Hand hand = deck.drawInitialCards(participants.getInitialCardSize());
        participants.dealInitialCards(hand);
    }

    public ProfitResult makeProfitResult() {
        return participants.makeDealerWinningResult();
    }

    public Deck getDeck() {
        return deck;
    }

    public Participants getParticipants() {
        return participants;
    }
}
