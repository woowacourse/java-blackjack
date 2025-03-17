package domain;

import domain.participant.Participant;
import domain.participant.Participants;
import domain.result.DealerResults;
import domain.result.DealerWinningStatus;
import domain.result.PlayerResults;
import domain.result.PlayerWinningStatus;
import java.util.ArrayList;
import java.util.List;

public class BlackJackResultCalculator {

    private BlackJackResultCalculator() {
    }

    public static ParticipantsResult calculate(Participants participants) {
        Participant dealer = participants.getDealer();
        List<DealerWinningStatus> dealerResults = new ArrayList<>();
        List<PlayerWinningStatus> playerResults = new ArrayList<>();
        for (Participant player : participants.getPlayers()) {
            BlackJackWinningStatus dealerBlackJackWinningStatus = BlackJackWinningStatus.calculateDealerResult(
                    dealer.isBlackJack(),
                    dealer.getTotalValue(),
                    player.isBlackJack(),
                    player.getTotalValue());
            dealerResults.add(new DealerWinningStatus(player.getName(), dealerBlackJackWinningStatus));
            playerResults.add(new PlayerWinningStatus(player.getName(), dealerBlackJackWinningStatus.reverse()));
        }
        return new ParticipantsResult(new DealerResults(dealerResults), new PlayerResults(playerResults));
    }
}
