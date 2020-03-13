package domain.user;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Deck;
import domain.result.WinningResult;

public class Players {

    private List<Player> players;

    private Players(String input) {
        List<String> names = Arrays.asList(input.split(",", -1));
        this.players = names.stream()
                .map(String::trim)
                .map(Player::new)
                .collect(Collectors.toList());

    }

    public static Players of(String names) {
        return new Players(names);
    }

    public void draw(Deck deck) {
        players.forEach(
                player -> player.draw(deck.dealOut())
        );
    }

    public String getAllNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    public String getAllFirstDrawResults() {
        return players.stream()
                .map(Player::getDrawResult)
                .collect(Collectors.joining("\n"));
    }

    public String getAllTotalDrawResults() {
        return players.stream()
                .map(Player::getTotalDrawResult)
                .collect(Collectors.joining("\n"));
    }

    public void decideWinner(Dealer dealer) {
        players.forEach(player -> player.win(dealer));
    }

    public String getAllTotalWinningResults() {
        return players.stream()
                .map(Player::getTotalWinningResult)
                .collect(Collectors.joining("\n"));
    }

    public List<WinningResult> getWinningResults() {
        return Collections.unmodifiableList(
                players.stream()
                        .map(Player::getWinningResult)
                        .collect(Collectors.toList())
        );
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
