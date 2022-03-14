package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Result {
    private static final int BLACK_JACK = 21;

    private final Map<Participant, Integer> playersRevenue;
    private final List<Participant> players;
    private final Participant dealer;
    private int dealerRevenue;

    public Result(Participant dealer, List<Participant> players) {
        this.dealer = dealer;
        this.players = players;
        this.playersRevenue = new LinkedHashMap<>();
    }

    public void calculateRevenue() {
        calculatePlayersRevenue();
        calculateDealerRevenue();
    }

    private void calculatePlayersRevenue() {
        players.forEach(player -> playersRevenue.put(player, calculatePlayerRevenue(player)));
    }

    private void calculateDealerRevenue() {
        dealerRevenue = playersRevenue.values()
            .stream()
            .mapToInt(i -> i)
            .sum() * -1;
    }

    private int calculatePlayerRevenue(Participant player) {
        int playerScore = player.getScore();
        if (isWin(playerScore, dealer.getScore())) {
            if (playerScore == BLACK_JACK) {
                return player.getBetting() * 3 / 2;
            }
            return player.getBetting();
        }
        if (playerScore == BLACK_JACK) {
            return 0;
        }
        return -1 * player.getBetting();
    }

    private boolean isWin(int playerScore, int dealerScore) {
        if (playerScore > BLACK_JACK) {
            return false;
        }
        if (dealerScore > BLACK_JACK) {
            return true;
        }
        return dealerScore < playerScore;
    }


    public int getDealerRevenue() {
        return dealerRevenue;
    }

    public int getPlayerRevenue(Participant player) {
        return playersRevenue.get(player);
    }
}
