package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.result.ProfitResult;
import java.util.ArrayDeque;
import java.util.Queue;

public final class BlackjackGame {

    private final Participants participants;

    private Queue<Player> players;

    public BlackjackGame(final Participants participants) {
        this.participants = participants;
        this.players = new ArrayDeque<>(participants.findHitEligiblePlayers().getPlayers());
    }

    public void dealInitialCards(final Deck deck) {
        final Hand hand = deck.drawInitialCards(participants.getInitialCardSize());
        participants.dealInitialCards(hand);
    }

    public boolean isPlaying() {
        return !players.isEmpty();
    }

    public Player findCurrentTurnPlayer() {
        return players.poll();
    }

    public ProfitResult makeProfitResult() {
        return participants.makeDealerWinningResult();
    }

    public Participants getParticipants() {
        return participants;
    }
}
