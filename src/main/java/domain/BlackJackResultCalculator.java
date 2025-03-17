package domain;

import domain.participant.Participant;
import domain.participant.Participants;
import java.util.ArrayList;
import java.util.List;

public class BlackJackResultCalculator {

    private BlackJackResultCalculator() {
    }

    public static ParticipantsResult calculate(Participants participants) {
        Participant dealer = participants.getDealer();
        DealerResult dealerResult = new DealerResult();
        List<PlayerResult> playerResults = new ArrayList<>();
        for (Participant player : participants.getPlayerParticipants()) {
            BlackJackWinningStatus currentDealerResult = BlackJackWinningStatus.calculateDealerResult(
                    dealer.isBlackJack(),
                    dealer.getTotalValue(),
                    player.isBlackJack(),
                    player.getTotalValue());
            dealerResult.add(currentDealerResult);
            playerResults.add(new PlayerResult(player, currentDealerResult.reverse()));
        }
        return new ParticipantsResult(dealerResult, playerResults);
    }
}
