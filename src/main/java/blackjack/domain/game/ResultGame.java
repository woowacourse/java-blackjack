package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

import java.util.HashMap;
import java.util.List;

public class ResultGame {
    private final HashMap<Player, WinTieLose> playersResult;
    private final Dealer dealer;
    private final List<Player> players;

    public ResultGame(final Participants participants) {
        this.dealer = participants.getDealer();
        this.players = participants.getPlayers();
        this.playersResult = new HashMap<>();
    }

    public void calculateResult() {
        players.forEach(this::compareScore);
    }

    private void compareScore(final Player player) {
        if (dealer.isBust()) {
            compareScoreWithBustDealer(player);
            return;
        }
        if (player.isBust()) {
            compareScoreWithBustedPlayer(player);
            return;
        }
        compareScoreWithNotBustDealer(player);
    }

    private void compareScoreWithBustedPlayer(final Player player) {
        if (dealer.isBust()) {
            playersResult.put(player, WinTieLose.TIE);
            return;
        }
        playersResult.put(player, WinTieLose.LOSE);

    }

    private void compareScoreWithNotBustDealer(final Player player) {
        final int dealerScore = dealer.getTotalScore();
        if (dealerScore == player.getTotalScore()) {
            playersResult.put(player, WinTieLose.TIE);
        }
        if (dealerScore < player.getTotalScore()) {
            playersResult.put(player, WinTieLose.WIN);
        }
        if (dealerScore > player.getTotalScore()) {
            playersResult.put(player, WinTieLose.LOSE);
        }
    }

    private void compareScoreWithBustDealer(final Player player) {
        if (player.isBust()) {
            playersResult.put(player, WinTieLose.TIE);
            return;
        }
        playersResult.put(player, WinTieLose.WIN);
    }

    public int getDealerCount(final WinTieLose expected) {
        return (int) playersResult.values()
                .stream()
                .filter(winTieLose -> winTieLose.equals(expected.reverseValue()))
                .count();
    }

    public WinTieLose getPlayerResult(final Player player) {
        return playersResult.get(player);
    }
}