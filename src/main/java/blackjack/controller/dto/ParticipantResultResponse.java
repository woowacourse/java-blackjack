package blackjack.controller.dto;

import blackjack.domain.BlackJackGame;
import blackjack.domain.participant.PlayerName;

public class ParticipantResultResponse {

    private final String name;
    private final int profit;

    private ParticipantResultResponse(final String name, final int profit) {
        this.name = name;
        this.profit = profit;
    }

    public static ParticipantResultResponse ofPlayer(final PlayerName playerName, final BlackJackGame blackJackGame) {
        return new ParticipantResultResponse(playerName.getValue(), blackJackGame.getPlayerProfit(playerName));
    }

    public static ParticipantResultResponse ofDealer(final BlackJackGame blackJackGame) {
        return new ParticipantResultResponse(blackJackGame.getDealerName(), blackJackGame.getDealerProfit());
    }

    public String getName() {
        return name;
    }

    public int getProfit() {
        return profit;
    }
}
