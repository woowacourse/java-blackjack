package participant;

import card.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import participant.value.Money;
import result.AllPlayerResult;
import result.GameStatus;
import result.PlayerResult;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players createByNames(List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(Player::new)
                .toList();
        return new Players(players);
    }

    public static Players create(List<Player> players) {
        return new Players(players);
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Players openHand() {
        List<Player> playersWithOpenHand = players.stream()
                .map(player -> new Player(player.getName(), player.openHand(), Money.ZERO))
                .toList();
        return Players.create(playersWithOpenHand);
    }

    public Players initializeHandWith(Deck deck) {
        List<Player> updatedPlayers = new ArrayList<>();
        for (Player player : players) {
            Player updatedPlayer = (Player) player.initializeHandWith(deck.drawDefaultHand());
            updatedPlayers.add(updatedPlayer);
        }
        return Players.create(updatedPlayers);
    }

    public Players updatePlayers(Function<Player, Player> updateAction) {
        List<Player> updatedPlayers = players.stream()
                .map(updateAction)
                .toList();
        return Players.create(updatedPlayers);
    }

    public Players bet(Function<Player, Money> bettingAction) {
        List<Player> playersAfterBetting = new ArrayList<>();

        for (Player player : players) {
            Money betAmount = bettingAction.apply(player);
            playersAfterBetting.add(Player.withBet(player.getName(), betAmount));
        }

        return new Players(playersAfterBetting);
    }

    public AllPlayerResult calculateAllPlayerResult(Dealer dealer) {
        List<PlayerResult> allPlayerResultInfo = new ArrayList<>();
        for (Player player : players) {
            GameStatus gameStatus = player.calculateResultAgainst(dealer);
            PlayerResult playerResult = new PlayerResult(player, gameStatus);
            allPlayerResultInfo.add(playerResult);
        }
        return AllPlayerResult.from(allPlayerResultInfo);
    }
}
