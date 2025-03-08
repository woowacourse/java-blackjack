package domain;

import java.util.HashSet;
import java.util.Set;

public class BlackJackResultCalculator {

    private BlackJackResultCalculator() {
    }

    public static ParticipantsResult calculate(Participants participants) {
        int dealerValue = 0;
        for (Participant participant : participants.getParticipants()) {
            if (participant instanceof Dealer) {
                dealerValue = participant.getTotalValue();
                break;
            }
        }

        Set<ParticipantResult> tmpResult = new HashSet<>();
        ParticipantResult dealerResult = new DealerResult();

        for (Participant participant : participants.getParticipants()) {
            if (participant instanceof Player) {
                GameResult gameResult = GameResult.calculateDealerResult(dealerValue,
                    participant.getTotalValue());
                ParticipantResult playerResult = new PlayerResult(participant.getName(),
                    gameResult);
                dealerResult.add(gameResult.reverse());
                tmpResult.add(playerResult);
            }
        }
        tmpResult.add(dealerResult);
        return new ParticipantsResult(tmpResult);
    }
}
