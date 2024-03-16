package domain.blackjack;

import domain.card.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(List<String> playersName, List<Integer> battingMoneys) {
        List<Player> players = new ArrayList<>();
        for (int index = 0; index < playersName.size(); index++) {
            String playerName = playersName.get(index);
            int battingMoney = battingMoneys.get(index);
            players.add(Player.from(playerName, HoldingCards.of(), battingMoney));
        }
        this.players = Collections.unmodifiableList(players);
    }

    void drawOnce(Deck deck) {
        for (Player player : players) {
            player.draw(deck);
        }
    }

    List<EarningMoney> calculatePlayersEarningMoney(Dealer dealer) {
        return players.stream()
                .map(player -> {
                    GameResult gameResult = GameResultCalculator.calculate(player, dealer);
                    return player.calculateEarningMoney(gameResult);
                })
                .toList();
    }

    public List<String> getPlayerNames() {
        return players.stream().map(Player::getRawName).toList();
    }

    public List<Player> getPlayers() {
        return players;
    }
}
