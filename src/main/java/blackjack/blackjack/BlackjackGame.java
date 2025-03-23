package blackjack.blackjack;

import blackjack.blackjack.card.Deck;
import blackjack.blackjack.participant.Participants;
import blackjack.blackjack.participant.Player;
import blackjack.blackjack.result.ProfitResult;
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
        participants.dealInitialCards(deck);
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

    public void stayToPlayerIfRunning(final Player currentTurnPlayer) {
        currentTurnPlayer.stayIfRunning();
    }

    public Participants getParticipants() {
        return participants;
    }
}
