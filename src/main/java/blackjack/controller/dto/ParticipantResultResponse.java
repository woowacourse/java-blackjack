package blackjack.controller.dto;

import blackjack.domain.BlackJackGame;

public class ParticipantResultResponse {

    private final String name;
    private final int profit;

    private ParticipantResultResponse(final String name, final int profit) {
        this.name = name;
        this.profit = profit;
    }

    public static ParticipantResultResponse forDealer(final BlackJackGame blackJackGame) {
        return new ParticipantResultResponse(blackJackGame.getDealerName(), 0);
    }

    public String getName() {
        return name;
    }

    public int getProfit() {
        return profit;
    }
}
