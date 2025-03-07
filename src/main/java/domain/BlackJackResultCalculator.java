package domain;

import java.util.ArrayList;
import java.util.List;

public class BlackJackResultCalculator {

    private BlackJackResultCalculator() {
    }

    public static ParticipantsResult calculate(Participants participants) {
        Participant dealer = participants.getDealer();
        List<Participant> players = participants.getPlayerParticipants();
        List<PlayerResult> playerResults = new ArrayList<>();
        DealerResult dealerResult = new DealerResult();
        for (Participant player : players) {
            GameResult currentDealerResult = GameResult.calculateDealerResult(dealer.getTotalValue(),
                    player.getTotalValue());
            dealerResult.add(currentDealerResult);
            playerResults.add(new PlayerResult(player, currentDealerResult.reverse()));
        }
        return new ParticipantsResult(dealerResult, playerResults);
    }
}
