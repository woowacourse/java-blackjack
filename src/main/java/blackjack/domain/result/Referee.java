package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public class Referee {
    private final Players players;
    private final Dealer dealer;

    public Referee(Participants participants) {
        this.players = participants.getPlayers();
        this.dealer = participants.getDealer();
    }

    public BlackjackResult generateResult() {
        Map<Player, HandResult> playerResults = new LinkedHashMap<>();
        for (Player player : players.getValues()) {
            HandResult playerResult = getPlayerResult(player);
            playerResults.put(player, playerResult);
        }
        return new BlackjackResult(playerResults);
    }

    private HandResult getPlayerResult(Player player) {
        if (dealer.isBust()) {
            return HandResult.WIN;
        }
        if (player.isBust()) {
            return HandResult.LOSE;
        }
        return getPlayerResultWithoutBust(player);
    }

    private HandResult getPlayerResultWithoutBust(Player player) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();
        if (playerScore == dealerScore) {
            return HandResult.DRAW;
        }
        if (playerScore < dealerScore) {
            return HandResult.LOSE;
        }
        return HandResult.WIN;
    }
}
