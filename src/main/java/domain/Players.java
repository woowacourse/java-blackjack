package domain;

import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(final List<Player> players) {
        this.players = List.copyOf(players);
    }

    public int getSize() {
        return players.size();
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public void decideWinner(Dealer dealer) {
        int dealerResultScore = toResultScore(dealer.getResult(), dealer.checkBust());
        for (Player player : players) {
            applyOutcome(dealerResultScore, dealer, player);
        }
    }

    private void applyOutcome(int dealerResultScore, Dealer dealer, Player player) {
        int playerResultScore = toResultScore(player.getResult(), player.checkBust());
        if (dealerResultScore == playerResultScore) {
            recordOutcome(dealer, player, Outcome.DRAW, Outcome.DRAW);
            return;
        }
        if (dealerResultScore > playerResultScore) {
            recordOutcome(dealer, player, Outcome.WIN, Outcome.LOSE);
            return;
        }
        recordOutcome(dealer, player, Outcome.LOSE, Outcome.WIN);
    }

    private int toResultScore(int score, boolean bust) {
        if (bust) {
            return -1;
        }
        return score;
    }

    private void recordOutcome(Dealer dealer, Player player, Outcome dealerOutcome, Outcome playerOutcome) {
        dealer.addResult(dealerOutcome);
        player.setOutcome(playerOutcome);
    }
}
