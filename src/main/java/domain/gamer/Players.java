package domain.gamer;

import dto.GameResultDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public void initCard(Dealer dealer) {
        for (Player player : players) {
            player.initCard();
            player.initCard();
        }
    }

    public void play(PlayerTurn playerTurn, Dealer dealer) {
        for (Player player : players) {
            playerTurn.playTurn(player, dealer);
        }
    }

    public List<Name> getNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public GameResultDto makeResult(Dealer dealer, Referee referee) {
        Map<Player, Integer> result = new LinkedHashMap<>();
        for (Player player : players) {
            PlayerResult playerResult = referee.judgeResult(player.cards, dealer.cards);
            int profit = player.getProfit(playerResult);
            result.put(player, profit);
        }
        return new GameResultDto(result);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
