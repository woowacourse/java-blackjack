package blackjack.model.game;

import static blackjack.model.game.ParticipantResult.BLACKJACK;
import static blackjack.model.game.ParticipantResult.DRAW;
import static blackjack.model.game.ParticipantResult.LOSE;
import static blackjack.model.game.ParticipantResult.WIN;

import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Participants;
import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private final Map<Participant, ParticipantResult> winLoseResult;

    public GameResult(Dealer dealer, Participants participants) {
        Map<Participant, ParticipantResult> winLoseResult = new HashMap<>();
        participants.getParticipants().forEach(
                participant -> {
                    winLoseResult.put(participant, getParticipantResult(dealer, participant));
                }
        );
        this.winLoseResult = winLoseResult;
    }

    private ParticipantResult getParticipantResult(Dealer dealer, Participant participant) {
        if (participant.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        int dealerPoint = dealer.calculatePoint();
        int participantPoint = participant.calculatePoint();
        if (participantPoint == 21 && dealerPoint == 21) {
            return DRAW;
        }
        if (participantPoint == 21) {
            return BLACKJACK;
        }
        if (dealerPoint > participantPoint) {
            return LOSE;
        }
        if (dealerPoint < participantPoint) {
            return WIN;
        }
        return DRAW;
    }

    public Map<Participant, ParticipantResult> getWinLoseResult() {
        return winLoseResult;
    }

    public int getDealerWinCount() {
        return (int) winLoseResult.values().stream()
                .filter(result -> result == LOSE)
                .count();
    }

    public int getDealerLoseCount() {
        return (int) winLoseResult.values().stream()
                .filter(result -> result == WIN)
                .count();
    }
}
