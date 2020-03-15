package blackjack.domain.result;

import java.util.HashMap;
import java.util.Map;

import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;

public class ScoreBoard {
    private Map<Participant, Integer> scoreBoard;

    public ScoreBoard(Participants participants) {
        scoreBoard = new HashMap<>();
        for (Participant participant : participants) {
            scoreBoard.put(participant, participant.score());
        }
    }

    public int get(final Participant participant) {
        return scoreBoard.get(participant);
    }
}

