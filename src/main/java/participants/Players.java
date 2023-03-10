package participants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import card.Card;
import dto.PlayerOpenDto;
import dto.PlayerResultDto;
import dto.PlayerWinningDto;

public class Players {
    private final List<Player> players = new ArrayList<>();

    public void add(Player player) {
        players.add(player);
    }

    public void takeCard(int playerIndex, Card card) {
        Player player = players.get(playerIndex);
        player.hit(card);
    }

    public int count() {
        return players.size();
    }

    public boolean isBust(int playerIndex) {
        return players.get(playerIndex).isBust();
    }

    public List<PlayerResultDto> getPlayerResults() {
        return players.stream()
                .map(PlayerResultDto::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public void calculateWinning(Dealer dealer) {
        for (Player player : players) {
            player.compareScoreWith(dealer);
        }
    }

    public List<PlayerWinningDto> getWinningResults() {
        return players.stream()
                .map(PlayerWinningDto::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<PlayerOpenDto> getPlayerCards() {
        return players.stream()
                .map(PlayerOpenDto::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public Name findPlayer(int playerIndex) {
        return players.get(playerIndex).getName();
    }

    public Player findPlayer(Name name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 플레이어 입니다."));
    }
}
