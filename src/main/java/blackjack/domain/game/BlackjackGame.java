package blackjack.domain.game;

import java.util.List;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

public class BlackjackGame {

    private final Deck deck = new Deck();
    private final Participants participants;

    public BlackjackGame(List<Player> players) {
        this.participants = new Participants(players);
        participants.dealInitialCards(deck);
    }

    public boolean isPlayersTurnEnd() {
        return participants.isDealerBlackjack() || participants.isAllPlayerTurnEnd();
    }

    public void playPlayerTurn(boolean isHit) {
        if (isHit) {
            participants.dealToPlayer(deck);
            return;
        }
        participants.stayCurrentPlayer();
    }

    public boolean isDealerTurnEnd() {
        return !participants.isDealerMustHit();
    }

    public void playDealerTurn() {
        participants.dealToDealer(deck);
    }

    public Player getCurrentPlayer() {
        return participants.getCurrentPlayer();
    }

    public Participants getParticipants() {
        return participants;
    }
}
