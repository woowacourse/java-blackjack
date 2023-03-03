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

    public ResultGame(Participants participants) {
        this.dealer = participants.getDealer();
        this.players = participants.getPlayers();
        this.playersResult = new HashMap<>();
    }

    public void calculateResult() {
        players.forEach(this::compareScore);
    }

    private void compareScore(Player player) {
        if (dealer.isBust()) {
            compareScoreWithBustDealer(player);
            return;
        }
        compareScoreWithNotBustDealer(player);
    }

    private void compareScoreWithNotBustDealer(Player player) {
        int dealerScore = dealer.getTotalScore();
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

    private void compareScoreWithBustDealer(Player player) {
        if (player.isBust()) {
            playersResult.put(player, WinTieLose.TIE);
            return;
        }
        playersResult.put(player, WinTieLose.WIN);
    }

    public int getDealerCount(WinTieLose expected){
        return (int) playersResult.values()
                .stream()
                .filter(winTieLose -> winTieLose.equals(expected.reverseValue()))
                .count();
    }

    public WinTieLose getPlayerResult(Player player){
        return playersResult.get(player);
    }
}