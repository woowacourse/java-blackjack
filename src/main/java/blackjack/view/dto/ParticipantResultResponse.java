package blackjack.view.dto;

import blackjack.domain.BlackJackGame;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class ParticipantResultResponse {

    private final String name;
    private final int profit;

    private ParticipantResultResponse(final String name, final int profit) {
        this.name = name;
        this.profit = profit;
    }

    public static ParticipantResultResponse forPlayer(final Player player, final BlackJackGame blackJackGame) {
        return new ParticipantResultResponse(player.getName(), 0);
    }

    public static ParticipantResultResponse forDealer(final BlackJackGame blackJackGame) {
        final Dealer dealer = blackJackGame.getDealer();
        return new ParticipantResultResponse(dealer.getName(), 0);
    }

    public String getName() {
        return name;
    }

    public int getProfit() {
        return profit;
    }
}
